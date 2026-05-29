package com.sable.spaceenginemod.client;

import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.config.ClientConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = SpaceengineS.MODID)
public class SpaceShaderEventHandler {

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        // Update shader state at the start of each frame
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;

        if (level != null) {
            boolean shouldBeActive = SpaceengineS.isSpaceDimension(level) && ClientConfig.enableSpaceLighting();
            SpaceInvertPostProcessor.INSTANCE.setActive(shouldBeActive);
        }
    }
}
