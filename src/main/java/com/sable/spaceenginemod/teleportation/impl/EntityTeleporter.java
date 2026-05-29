package com.sable.spaceenginemod.teleportation.impl;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaterniond;
import org.joml.Quaterniondc;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for teleporting entities across server levels (dimensions),
 * preserving passengers and their relative positions.
 *
 * <p>Ported from {@code shipwrights.genesis.teleportation.impl.EntityTeleporter}.
 * The only material change is the cross-dimension code path: instead of the
 * 1.20.1 clone-and-restore dance, we use the 1.21.1 vanilla helper
 * {@link Entity#changeDimension(DimensionTransition)}, which performs the
 * same swap atomically.
 */
public class EntityTeleporter {

    /**
     * Teleports an entity and all of its passengers to the given level and
     * position with no frame rotation.
     */
    public static <T extends Entity> T teleportEntityAndPassengers(T entity, ServerLevel newLevel, Vec3 newPos) {
        return teleportEntityAndPassengers(entity, newLevel, newPos, new Quaterniond());
    }

    /**
     * Teleports an entity and all of its passengers to the given level and
     * position. Passengers are detached before teleporting and reattached
     * afterward with positions offset relative to the vehicle's movement.
     * The frame rotation is applied to each entity's look direction so it
     * stays consistent relative to its surroundings.
     *
     * @return the entity reference in the destination level (may be a freshly
     *         created instance for non-player entities, or the same entity
     *         for players and same-level moves).
     */
    public static <T extends Entity> T teleportEntityAndPassengers(T entity, ServerLevel newLevel, Vec3 newPos, Quaterniondc rotation) {
        Vec3 oldPos = entity.position();
        List<Entity> passengers = detachPassengers(entity);

        T newEntity = teleportEntity(entity, newLevel, newPos, rotation);

        if (newEntity != null) {
            reattachPassengers(passengers, oldPos, newPos, newEntity, newLevel, rotation);
        }
        return newEntity;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> T teleportEntity(T entity, ServerLevel newLevel, Vec3 newPos, Quaterniondc rotation) {
        float[] newAngles = rotateLookAngles(entity.getYRot(), entity.getXRot(), rotation);
        float newYRot = newAngles[0];
        float newXRot = newAngles[1];

        // Same-level: cheap moveTo avoids the dimension-change overhead.
        if (entity.level() == newLevel) {
            entity.moveTo(newPos.x, newPos.y, newPos.z, newYRot, newXRot);
            entity.setYHeadRot(newYRot);
            entity.setYBodyRot(newYRot);
            return entity;
        }

        // Cross-dimension: hand off to vanilla. Works uniformly for players
        // (where the connection bookkeeping is critical) and for arbitrary
        // entities (which vanilla will clone-and-restore internally).
        DimensionTransition transition = new DimensionTransition(
                newLevel,
                newPos,
                entity.getDeltaMovement(),
                newYRot,
                newXRot,
                DimensionTransition.DO_NOTHING
        );
        return (T) entity.changeDimension(transition);
    }

    private static List<Entity> detachPassengers(Entity entity) {
        List<Entity> passengers = new ArrayList<>(entity.getPassengers());
        entity.ejectPassengers();
        return passengers;
    }

    private static void reattachPassengers(
            List<Entity> passengers,
            Vec3 oldPos,
            Vec3 newPos,
            Entity newEntity,
            ServerLevel newLevel,
            Quaterniondc rotation
    ) {
        for (Entity passenger : passengers) {
            Vec3 passengerPos = passenger.position().subtract(oldPos).add(newPos);

            Entity newPassenger = teleportEntityAndPassengers(passenger, newLevel, passengerPos, rotation);

            if (newPassenger != null) {
                newPassenger.startRiding(newEntity, true);
            }
        }
    }

    /**
     * Rotates a Minecraft yaw/pitch look direction by the given quaternion.
     * Minecraft uses: dx = -sin(yaw)*cos(pitch), dy = -sin(pitch),
     * dz = cos(yaw)*cos(pitch).
     *
     * @return float[]{newYRot, newXRot} in degrees
     */
    private static float[] rotateLookAngles(float yRot, float xRot, Quaterniondc rotation) {
        double yawRad = Math.toRadians(yRot);
        double pitchRad = Math.toRadians(xRot);

        double dx = -Math.sin(yawRad) * Math.cos(pitchRad);
        double dy = -Math.sin(pitchRad);
        double dz = Math.cos(yawRad) * Math.cos(pitchRad);

        Vector3d dir = rotation.transform(dx, dy, dz, new Vector3d());

        float newXRot = (float) Math.toDegrees(Math.asin(-dir.y));
        float newYRot = (float) Math.toDegrees(Math.atan2(-dir.x, dir.z));
        return new float[]{newYRot, newXRot};
    }
}
