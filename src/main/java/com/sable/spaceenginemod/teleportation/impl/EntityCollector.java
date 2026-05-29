package com.sable.spaceenginemod.teleportation.impl;

import com.sable.spaceenginemod.SpaceengineS;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaterniond;
import org.joml.Quaterniondc;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.HashMap;
import java.util.Map;

/**
 * Collects entities to be teleported across dimensions and computes their
 * destination positions. Ported from {@code shipwrights.genesis.teleportation
 * .impl.EntityCollector} with all Valkyrien Skies ship machinery removed —
 * the Sable port does not move ships, only entities (players, mobs, items).
 *
 * <p>The remaining behavior is plain vanilla: query a world-space {@link AABB}
 * around an origin, rotate each entity's position around that origin, then
 * offset by the destination origin. Passengers are collapsed to their root
 * vehicle so passenger trees teleport together.
 */
public class EntityCollector {

    private final ServerLevel oldLevel;
    private final Map<Entity, Vec3> entityStorage = new HashMap<>();

    public EntityCollector(ServerLevel oldLevel) {
        this.oldLevel = oldLevel;
    }

    /**
     * Collect all entities inside {@code worldAABB} (in the source level's
     * world space) and compute their re-mapped positions in the destination
     * frame using {@code origin}, {@code newPos}, and {@code rotation}.
     *
     * @return map of root-vehicle entity → destination position
     */
    public Map<Entity, Vec3> collect(AABB worldAABB, Vector3dc origin, Vector3dc newPos, Quaterniondc rotation) {
        double inflate = 8 * SpaceengineS.getDimensionScale(oldLevel);
        oldLevel.getEntities(
                (Entity) null,
                worldAABB.inflate(inflate),
                entity -> !entityStorage.containsKey(entity)
        ).forEach(entity -> addEntity(entity, origin, newPos, rotation));

        return entityStorage;
    }

    /**
     * Convenience overload that uses a zero-rotation frame. Useful for the
     * Sable code paths where we only translate entities between dimensions.
     */
    public Map<Entity, Vec3> collect(AABB worldAABB, Vec3 origin, Vec3 newPos) {
        return collect(
                worldAABB,
                new Vector3d(origin.x, origin.y, origin.z),
                new Vector3d(newPos.x, newPos.y, newPos.z),
                new Quaterniond()
        );
    }

    private void addEntity(Entity entity, Vector3dc origin, Vector3dc newPos, Quaterniondc rotation) {
        Entity root = entity.getRootVehicle();
        if (entityStorage.containsKey(root)) {
            return;
        }
        Vec3 pos = root.position();
        Vector3d relPos = new Vector3d(pos.x, pos.y, pos.z).sub(origin);
        rotation.transform(relPos);
        relPos.add(newPos);
        entityStorage.put(root, new Vec3(relPos.x, relPos.y, relPos.z));
    }
}
