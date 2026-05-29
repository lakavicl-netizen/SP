package com.sable.spaceenginemod.teleportation;

import com.sable.spaceenginemod.teleportation.impl.EntityTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Entry point for cross-dimension entity teleports. Ported from
 * {@code shipwrights.genesis.teleportation.DimensionTravelTeleporter}.
 *
 * <p>The original took a Valkyrien Skies {@code ServerShip} and translated the
 * whole ship plus nearby entities into the destination dimension. The Sable
 * port intentionally drops ship transport entirely (Sable handles its own
 * sublevel moves via {@code dev.egg.SubLevelWarper#WarpSubLevel}) and only
 * exposes the entity-level path. Call sites that previously called
 * {@code teleportShip(...)} should either:
 *
 * <ul>
 *   <li>use {@link #teleportEntity(ServerPlayer, ResourceKey, BlockPos, TravelDirection)}
 *       to move a single player, or</li>
 *   <li>use {@code SubLevelWarper.WarpSubLevel(...)} directly for sublevels.</li>
 * </ul>
 *
 * <p>A short per-entity cooldown is preserved from the original to avoid
 * teleport ping-pong when the altitude trigger straddles the threshold.
 */
public final class DimensionTravelTeleporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DimensionTravelTeleporter.class);
    private static final long COOLDOWN_TICKS = 20L;

    private static final ConcurrentHashMap<Integer, Long> entityCooldownExpiry = new ConcurrentHashMap<>();

    private DimensionTravelTeleporter() {}

    /**
     * Teleport a single player across dimensions, preserving their yaw/pitch
     * and applying the given destination position. Returns silently and does
     * nothing if the target level is not loaded, or if the entity is on a
     * cooldown from a prior teleport.
     *
     * @param player    the player to move
     * @param targetDim the destination dimension key
     * @param targetPos the destination block position; entity is placed at its bottom-center
     * @param direction informational; logged but not otherwise used here
     * @return true if the teleport was issued
     */
    public static boolean teleportEntity(
            ServerPlayer player,
            ResourceKey<Level> targetDim,
            BlockPos targetPos,
            TravelDirection direction
    ) {
        ServerLevel currentLevel = player.serverLevel();
        long now = currentLevel.getGameTime();
        if (isOnCooldown(player.getId(), now)) return false;

        ServerLevel targetLevel = currentLevel.getServer().getLevel(targetDim);
        if (targetLevel == null) {
            LOGGER.warn("[teleportEntity] target dimension {} is not loaded", targetDim.location());
            return false;
        }

        markCooldown(player.getId(), now);

        Vec3 destination = Vec3.atBottomCenterOf(targetPos);
        EntityTeleporter.teleportEntityAndPassengers(player, targetLevel, destination);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[teleportEntity] moved {} via {} to {} @ {}",
                    player.getGameProfile().getName(), direction, targetDim.location(), targetPos);
        }
        return true;
    }

    /**
     * Same as {@link #teleportEntity(ServerPlayer, ResourceKey, BlockPos, TravelDirection)}
     * but works on any {@link Entity} (not just players). Built on top of the
     * 1.21.1 {@link DimensionTransition} entry point so vanilla owns the
     * bookkeeping.
     */
    public static @Nullable Entity teleportEntity(
            Entity entity,
            ResourceKey<Level> targetDim,
            Vec3 targetPos,
            TravelDirection direction
    ) {
        if (!(entity.level() instanceof ServerLevel currentLevel)) return null;
        long now = currentLevel.getGameTime();
        if (isOnCooldown(entity.getId(), now)) return null;

        ServerLevel targetLevel = currentLevel.getServer().getLevel(targetDim);
        if (targetLevel == null) {
            LOGGER.warn("[teleportEntity] target dimension {} is not loaded", targetDim.location());
            return null;
        }

        markCooldown(entity.getId(), now);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[teleportEntity] moved entity#{} via {} to {} @ {}",
                    entity.getId(), direction, targetDim.location(), targetPos);
        }
        return EntityTeleporter.teleportEntityAndPassengers(entity, targetLevel, targetPos);
    }

    /** Returns true if {@code entityId} is still on a teleport cooldown at {@code currentTick}. */
    public static boolean isOnCooldown(int entityId, long currentTick) {
        Long expiry = entityCooldownExpiry.get(entityId);
        return expiry != null && currentTick < expiry;
    }

    private static void markCooldown(int entityId, long currentTick) {
        entityCooldownExpiry.put(entityId, currentTick + COOLDOWN_TICKS);
    }
}
