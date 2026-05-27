package com.sable.spaceenginemod;

import com.sable.spaceengine_tp.SpaceModContent;
import com.sable.spaceengine_tp.SpaceTeleportHandler;
import com.sable.spaceengine_tp.entity.PlanetEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(SpaceengineS.MODID)
public class SpaceengineS {
    public static final String MODID = "space_engine_s";
    public static final Logger LOGGER = LogUtils.getLogger();

    private boolean planetSpawned = false;

    public SpaceengineS(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        SpaceModContent.ENTITIES.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new SpaceTeleportHandler());

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Space Engine-S mod loaded!");
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        planetSpawned = false;
    }

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Pre event) {
        if (planetSpawned) return;

        var server = event.getServer();
        ServerLevel spaceLevel = server.getLevel(SpaceModContent.SPACE_LEVEL_KEY);

        if (spaceLevel != null) {
            boolean hasPlanet = false;
            for (Entity entity : spaceLevel.getAllEntities()) {
                if (entity instanceof PlanetEntity) {
                    hasPlanet = true;
                    break;
                }
            }

            if (!hasPlanet) {
                PlanetEntity planet = new PlanetEntity(SpaceModContent.PLANET_ENTITY.get(), spaceLevel);
                planet.setPos(0, 128, 0);
                spaceLevel.addFreshEntity(planet);
                LOGGER.info("Spawned planet in space dimension at (0, 128, 0)");
            }
            planetSpawned = true;
        }
    }
}
