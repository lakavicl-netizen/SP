package com.sable.spaceenginemod.teleportation.sable;

import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.config.CommonConfig;
import com.sable.spaceenginemod.space.Celestial;
import com.sable.spaceenginemod.space.VantagePoint;
import com.sable.spaceenginemod.teleportation.DimensionTravelTeleporter;
import com.sable.spaceenginemod.teleportation.TravelDirection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Quaterniond;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * Sends a player from a planet dimension into the space dimension when they
 * climb past {@link CommonConfig#getAtmosphereExitHeight()}.
 *
 * <p>Ported from {@code shipwrights.genesis.teleportation.integration
 * .PlanetToSpaceTeleporter}. The Genesis original listened for
 * {@code TickEvent.LevelTickEvent} and iterated every loaded VS ship in the
 * level, teleporting the ship + its passengers when the ship's world position
 * crossed the atmosphere boundary. This port:
 *
 * <ul>
 *   <li>uses {@link PlayerTickEvent.Post}, fired per-player per-tick, so the
 *       trigger is "player altitude" rather than "ship altitude";</li>
 *   <li>drops the VS ship-collector and ship-teleport entirely (Sable owns
 *       sublevel transfer);</li>
 *   <li>only moves the player — any sublevel the player is standing in must
 *       be warped separately via {@code dev.egg.SubLevelWarper}.</li>
 * </ul>
 */
public final class PlanetToSpaceTeleporter {

    private final boolean gameTest;

    public PlanetToSpaceTeleporter(boolean gameTest) {
        this.gameTest = gameTest;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPlayerTickPost(PlayerTickEvent.Post event) {
        // gameTest path is a no-op for now — preserved to keep the
        // constructor signature compatible with the call site in
        // SpaceengineS without exercising the teleport in tests.
        if (gameTest) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ServerLevel level = player.serverLevel();
        if (SpaceengineS.isSpaceDimension(level)) return;          // already in space
        if (SpaceengineS.isSubSpaceDimension(level)) return;       // wormhole has its own flow

        Celestial body = SpaceengineS.getCelestialForLevel(level);
        if (body == null) return;                                  // not a registered planet dim

        if (player.getY() <= CommonConfig.getAtmosphereExitHeight()) return;

        ResourceKey<Level> spaceKey = ResourceKey.create(Registries.DIMENSION, SpaceengineS.SPACE_DIM);
        ServerLevel spaceLevel = level.getServer().getLevel(spaceKey);
        if (spaceLevel == null) return;

        if (DimensionTravelTeleporter.isOnCooldown(player.getId(), level.getGameTime())) return;

        long ticks = SpaceengineS.getTicks(level);
        Vector3d playerPos = new Vector3d(player.getX(), player.getY(), player.getZ());
        VantagePoint vp = VantagePoint.get(level, playerPos, ticks, 0f);
        if (!(vp instanceof VantagePoint.OnCelestial vantage)) return;

        Vector3d spaceTarget = computeSpaceTarget(vantage);
        BlockPos targetBlockPos = BlockPos.containing(spaceTarget.x, spaceTarget.y, spaceTarget.z);

        if (NeoForge.EVENT_BUS.post(new TeleportDisallowedEvent(player, body)).isCanceled()) return;

        DimensionTravelTeleporter.teleportEntity(
                player,
                spaceKey,
                targetBlockPos,
                TravelDirection.PLANET_TO_SPACE
        );
    }

    /**
     * Mirrors the Genesis {@code computeSpaceTarget} math: place the player
     * just above the celestial's "north pole" in space-frame coordinates,
     * then rotate into world space. Visible for testing.
     */
    static Vector3d computeSpaceTarget(VantagePoint.OnCelestial vantagePoint) {
        Vector3d targetPos = new Vector3d(0, vantagePoint.celestial().getActualSize() + 20, 0);
        vantagePoint.cameraRotationFromNorthPole().conjugate(new Quaterniond()).transform(targetPos);
        vantagePoint.getCelestialRotation().transform(targetPos);
        Vector3dc planetPos = vantagePoint.getPosition();
        targetPos.add(planetPos.x(), planetPos.y(), planetPos.z());
        return targetPos;
    }
}
