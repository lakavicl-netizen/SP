package com.sable.spaceengine_tp;

import com.sable.spaceengine_tp.entity.PlanetEntity;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Vector3d;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SpaceTeleportHandler {
    public static final ResourceKey<Level> SPACE_LEVEL_KEY = ResourceKey.create(
        net.minecraft.core.registries.Registries.DIMENSION,
        ResourceLocation.fromNamespaceAndPath("space_engine_s", "space")
    );

    private static final int OVERWORLD_TELEPORT_Y = 100;
    private static final int OVERWORLD_RETURN_Y = 50;
    private static final double PLANET_APPROACH_DISTANCE = 50.0;
    private static final double SPAWN_DISTANCE_FROM_PLANET = 150.0;
    private static final long COOLDOWN_MS = 2000;

    private final Map<UUID, Long> lastTeleportTime = new ConcurrentHashMap<>();

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        long now = System.currentTimeMillis();
        Long last = lastTeleportTime.get(player.getUUID());
        if (last != null && now - last < COOLDOWN_MS) return;

        Level currentLevel = player.level();
        ServerLevel serverLevel = (ServerLevel) currentLevel;
        ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);
        ServerLevel spaceLevel = serverLevel.getServer().getLevel(SPACE_LEVEL_KEY);

        if (overworld == null || spaceLevel == null) return;

        if (currentLevel.dimension() == Level.OVERWORLD) {
            if (player.getY() >= OVERWORLD_TELEPORT_Y) {
                teleportToSpace(player, overworld, spaceLevel);
                lastTeleportTime.put(player.getUUID(), now);
            }
        } else if (currentLevel.dimension() == SPACE_LEVEL_KEY) {
            Vec3 planetCenter = getPlanetCenter(spaceLevel);
            if (planetCenter != null && player.position().distanceTo(planetCenter) <= PLANET_APPROACH_DISTANCE) {
                teleportToOverworld(player, spaceLevel, overworld);
                lastTeleportTime.put(player.getUUID(), now);
            }
        }
    }

    private Vec3 getPlanetCenter(ServerLevel spaceLevel) {
        for (Entity entity : spaceLevel.getAllEntities()) {
            if (entity instanceof PlanetEntity) {
                return entity.position();
            }
        }
        return null;
    }

    private void teleportToSpace(ServerPlayer player, ServerLevel overworld, ServerLevel spaceLevel) {
        ServerSubLevel sableSubLevel = SableSubLevelWarper.getPlayerSubLevel(player);

        if (sableSubLevel != null) {
            Vec3 spawnPos = new Vec3(SPAWN_DISTANCE_FROM_PLANET, 128, 0);
            SableSubLevelWarper.warpSubLevelToDimension(sableSubLevel, spaceLevel, new Vector3d(spawnPos.x, spawnPos.y, spawnPos.z));
        } else {
            player.teleportTo(spaceLevel, SPAWN_DISTANCE_FROM_PLANET, 128, 0, Set.of(), player.getYRot(), player.getXRot());
        }
    }

    private void teleportToOverworld(ServerPlayer player, ServerLevel spaceLevel, ServerLevel overworld) {
        ServerSubLevel sableSubLevel = SableSubLevelWarper.getPlayerSubLevel(player);

        if (sableSubLevel != null) {
            SableSubLevelWarper.warpSubLevelToDimension(sableSubLevel, overworld, new Vector3d(0, OVERWORLD_RETURN_Y, 0));
        } else {
            player.teleportTo(overworld, 0, OVERWORLD_RETURN_Y, 0, Set.of(), player.getYRot(), player.getXRot());
        }
    }


}
