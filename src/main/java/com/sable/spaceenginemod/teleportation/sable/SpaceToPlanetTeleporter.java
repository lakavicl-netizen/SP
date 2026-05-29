package com.sable.spaceenginemod.teleportation.sable;

import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.config.CommonConfig;
import com.sable.spaceenginemod.space.Celestial;
import com.sable.spaceenginemod.space.SpaceLevel;
import com.sable.spaceenginemod.teleportation.DimensionTravelTeleporter;
import com.sable.spaceenginemod.teleportation.TravelDirection;
import com.sable.spaceenginemod.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

/**
 * Drops a player out of the space dimension and onto the nearest visitable
 * planet when they descend below {@link CommonConfig#getAtmosphereEntryHeight()}.
 *
 * <p>Ported from {@code shipwrights.genesis.teleportation.integration
 * .SpaceToPlanetTeleporter}. The Genesis original was driven by
 * {@code TickEvent.LevelTickEvent} and operated on Valkyrien Skies ships:
 * for every loaded ship whose AABB overlapped a celestial's OBB it would
 * teleport the ship + entities to that celestial's surface dimension. This
 * port:
 *
 * <ul>
 *   <li>uses {@link PlayerTickEvent.Post} so the trigger is the player's own
 *       altitude / proximity to a planet, not a ship's;</li>
 *   <li>drops the OBB overlap check and ship sort — we pick the nearest
 *       visitable celestial via {@link SpaceLevel#nearestCelestialWhere};</li>
 *   <li>keeps the scoreboard-team gate so packs can restrict who lands where,
 *       firing a {@link TeleportDisallowedEvent} when access is denied.</li>
 * </ul>
 */
public final class SpaceToPlanetTeleporter {

    /** Randomization range, in chunks, around (0,0) of the destination dim. */
    private static final int LANDING_ACCURACY = 8;

    private final boolean gameTest;

    public SpaceToPlanetTeleporter(boolean gameTest) {
        this.gameTest = gameTest;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPlayerTickPost(PlayerTickEvent.Post event) {
        if (gameTest) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ServerLevel spaceLevel = player.serverLevel();
        if (!SpaceengineS.isSpaceDimension(spaceLevel)) return;

        if (player.getY() >= CommonConfig.getAtmosphereEntryHeight()) return;

        if (DimensionTravelTeleporter.isOnCooldown(player.getId(), spaceLevel.getGameTime())) return;

        long ticks = SpaceengineS.getTicks(spaceLevel);
        Registry<Celestial> registry = SpaceengineS.getCelestialRegistry(spaceLevel);
        Vector3d playerPos = new Vector3d(player.getX(), player.getY(), player.getZ());

        Celestial nearest = getNearestPlanet(playerPos, ticks, registry);
        if (nearest == null) return;

        ResourceKey<Celestial> nearestKey = registry.getResourceKey(nearest).orElse(null);
        if (nearestKey == null) return;

        // Datapack/team gate, mirrored from genesis. Team name is the planet's
        // resource-location path; players outside the team are denied entry.
        PlayerTeam team = spaceLevel.getScoreboard().getPlayerTeam(nearestKey.location().getPath());
        if (team != null && !team.getPlayers().contains(player.getGameProfile().getName())) {
            NeoForge.EVENT_BUS.post(new TeleportDisallowedEvent(player, nearest));
            return;
        }

        ResourceKey<Level> targetDim = ResourceKey.create(Registries.DIMENSION, nearestKey.location());
        ServerLevel targetLevel = spaceLevel.getServer().getLevel(targetDim);
        if (targetLevel == null) return;

        if (NeoForge.EVENT_BUS.post(new TeleportDisallowedEvent(player, nearest)).isCanceled()) return;

        BlockPos landingPos = computePlanetTarget(targetLevel);
        DimensionTravelTeleporter.teleportEntity(
                player,
                targetDim,
                landingPos,
                TravelDirection.SPACE_TO_PLANET
        );
    }

    /** Random landing chunk near (0,0), at the configured atmosphere-entry height. */
    static BlockPos computePlanetTarget(ServerLevel targetLevel) {
        ChunkPos landingChunkPos = new ChunkPos(
                targetLevel.random.nextInt(LANDING_ACCURACY * 2 + 1) - LANDING_ACCURACY,
                targetLevel.random.nextInt(LANDING_ACCURACY * 2 + 1) - LANDING_ACCURACY
        );
        return new BlockPos(
                SectionPos.sectionToBlockCoord(landingChunkPos.x),
                CommonConfig.getAtmosphereEntryHeight(),
                SectionPos.sectionToBlockCoord(landingChunkPos.z)
        );
    }

    /** Returns the visitable celestial closest to {@code position}, or null. */
    static @Nullable Celestial getNearestPlanet(Vector3d position, long ticks, Registry<Celestial> registry) {
        Pair<Celestial, Double> hit = SpaceLevel.nearestCelestialWhere(
                registry, position, ticks, 0f, type -> type.isVisitable()
        );
        return hit != null ? hit.getFirst() : null;
    }
}
