package com.sable.spaceengine_tp;

import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.api.SubLevelHelper;
import dev.ryanhcode.sable.api.entity.EntitySubLevelUtil;
import dev.ryanhcode.sable.api.sublevel.KinematicContraption;
import dev.ryanhcode.sable.api.sublevel.ServerSubLevelContainer;
import dev.ryanhcode.sable.companion.math.Pose3d;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import dev.ryanhcode.sable.sublevel.SubLevel;
import dev.ryanhcode.sable.sublevel.plot.ServerLevelPlot;
import dev.ryanhcode.sable.sublevel.storage.SubLevelRemovalReason;
import dev.ryanhcode.sable.sublevel.system.SubLevelPhysicsSystem;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import java.util.*;

public class SableSubLevelWarper {

    public static int warpSubLevelToDimension(ServerSubLevel subLevel, Level destination, Vector3d position) {
        ServerSubLevelContainer sourceContainer = ServerSubLevelContainer.getContainer(subLevel.getLevel());
        ServerSubLevelContainer destinationContainer = ServerSubLevelContainer.getContainer((ServerLevel) destination);
        if (sourceContainer == null || destinationContainer == null) return 0;

        Collection<SubLevel> subLevels = SubLevelHelper.getConnectedChain(subLevel);
        Vector3d center = subLevel.logicalPose().position();

        warperSubLevels(subLevels, sourceContainer, destinationContainer, center, position);
        return subLevels.size();
    }

    private static void warperSubLevels(Collection<SubLevel> compoundSubLevel, ServerSubLevelContainer sourceContainer, ServerSubLevelContainer destinationContainer, Vector3d center, Vector3d position) {
        HashMap<UUID, AbstractMap.SimpleEntry<UUID, Vec3i>> oldToNew = new HashMap<>();
        HashMap<UUID, CompoundTag> subLevelTags = new HashMap<>();
        HashMap<UUID, ServerLevelPlot> subLevelPlots = new HashMap<>();
        HashMap<UUID, Set<Entity>> visitedEntities = new HashMap<>();

        for (SubLevel subLevel : compoundSubLevel) {
            ServerSubLevel serverSubLevel = (ServerSubLevel) subLevel;

            AABB box = new AABB(
                -subLevel.boundingBox().width() / 2 + center.x, -subLevel.boundingBox().height() / 2 + center.y, -subLevel.boundingBox().length() / 2 + center.z,
                subLevel.boundingBox().width() / 2 + center.x, subLevel.boundingBox().height() / 2 + center.y, subLevel.boundingBox().length() / 2 + center.z
            ).inflate(1.0);

            List<Entity> candidates = sourceContainer.getLevel().getEntities(null, box);
            visitedEntities.put(subLevel.getUniqueId(), new HashSet<>(candidates));

            for (KinematicContraption contraption : serverSubLevel.getPlot().getContraptions()) {
                if (contraption instanceof Entity e)
                    e.remove(Entity.RemovalReason.DISCARDED);
            }

            CompoundTag tag = TeleportSubLevelTemplate.save(serverSubLevel.getPlot());

            Pose3d pose = new Pose3d();
            pose.position().set(new Vector3d(subLevel.logicalPose().position()).sub(new Vector3d(center)).add(position));
            pose.orientation().set(subLevel.logicalPose().orientation());

            ServerSubLevel copy = (ServerSubLevel) destinationContainer.allocateNewSubLevel(pose);
            subLevelTags.put(subLevel.getUniqueId(), tag);

            Vec3i start = serverSubLevel.getPlot().getCenterBlock().offset(0, sourceContainer.getLevel().dimensionType().minY(), 0);
            Vec3i end = copy.getPlot().getCenterBlock().offset(0, destinationContainer.getLevel().dimensionType().minY(), 0);
            Vec3i offset = end.subtract(start);

            oldToNew.put(subLevel.getUniqueId(), new AbstractMap.SimpleEntry<>(copy.getUniqueId(), offset));
            subLevelPlots.put(subLevel.getUniqueId(), copy.getPlot());
        }

        Set<UUID> visited = new HashSet<>();
        var physics = SubLevelPhysicsSystem.get(destinationContainer.getLevel());

        for (SubLevel subLevel : compoundSubLevel) {
            ServerLevelPlot plot = subLevelPlots.get(subLevel.getUniqueId());
            ServerSubLevel copy = plot.getSubLevel();

            Pose3d pose = new Pose3d(copy.logicalPose());

            TeleportSubLevelTemplate.load(plot, subLevelTags.get(subLevel.getUniqueId()));

            physics.getPipeline().teleport(copy, pose.position(), pose.orientation());

            if (subLevel.getName() != null)
                copy.setName(subLevel.getName());

            for (Entity entity : visitedEntities.get(subLevel.getUniqueId())) {
                teleportEntity(entity, sourceContainer, destinationContainer, center, position, subLevel, oldToNew, visited);
            }
        }

        for (SubLevel subLevel : compoundSubLevel) {
            sourceContainer.removeSubLevel(subLevel, SubLevelRemovalReason.REMOVED);
        }
    }

    private static void teleportEntity(Entity entity, ServerSubLevelContainer sourceContainer, ServerSubLevelContainer destinationContainer, Vector3d center, Vector3d position, SubLevel subLevel, HashMap<UUID, AbstractMap.SimpleEntry<UUID, Vec3i>> oldToNew, Set<UUID> visited) {
        if (visited.contains(entity.getUUID())) return;
        visited.add(entity.getUUID());

        Vector3d newPos;
        if (!EntitySubLevelUtil.shouldKick(entity) && !entity.isPassenger()) {
            var pos = entity.trackingPosition();
            var offset = oldToNew.get(subLevel.getUniqueId()).getValue();
            newPos = new Vector3d(pos.x + offset.getX(), pos.y + offset.getY(), pos.z + offset.getZ());
        } else {
            var offset = new Vector3d(position).sub(center);
            var pos = Sable.HELPER.projectOutOfSubLevel(sourceContainer.getLevel(), entity.position());
            newPos = new Vector3d(pos.x + offset.x, pos.y + offset.y, pos.z + offset.z);
        }

        entity.unRide();
        entity.teleportTo(destinationContainer.getLevel(), newPos.x, newPos.y, newPos.z, Set.of(), entity.getYRot(), entity.getXRot());
    }

    public static boolean isPlayerInSableSubLevel(Entity player) {
        return Sable.HELPER.getContaining(player) != null;
    }

    public static ServerSubLevel getPlayerSubLevel(Entity player) {
        SubLevel subLevel = Sable.HELPER.getContaining(player);
        return subLevel instanceof ServerSubLevel ? (ServerSubLevel) subLevel : null;
    }
}
