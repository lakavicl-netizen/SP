package com.sable.spaceenginemod.client;

import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.content.particle.SpaceParticles;
import com.sable.spaceenginemod.content.particle.VerditeParticle;
import com.sable.spaceenginemod.content.particle.ZapBubbleParticle;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import team.lodestar.lodestone.systems.postprocess.PostProcessHandler;

import org.jetbrains.annotations.NotNull;

/**
 * Ported from genesis-1.20.1's {@code GenesisClientSetup}. The original
 * registered block-entity renderers, the Tulcite catalyzer menu screen, and
 * {@link net.minecraft.client.renderer.ItemBlockRenderTypes} for many plant
 * blocks. Those content systems were dropped during the 1.21.1 port, so only
 * the still-relevant client-setup work remains:
 *
 * <ul>
 *   <li>register the space invert post-processor with Lodestone</li>
 *   <li>clear cached planet texture render types on resource reload</li>
 *   <li>register particle providers</li>
 * </ul>
 */
@EventBusSubscriber(modid = SpaceengineS.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GenesisClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register post-processing shader for space dimension
            PostProcessHandler.addInstance(SpaceInvertPostProcessor.INSTANCE);
        });
    }

    @SubscribeEvent
    public static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        // Register a reload listener to clear planet texture caches when resources are reloaded
        event.registerReloadListener(new SimplePreparableReloadListener<Void>() {
            @Override
            protected @NotNull Void prepare(@NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profiler) {
                return null;
            }

            @Override
            protected void apply(@NotNull Void object, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profiler) {
                ShaderRegistry.clearTexturedPlanetRenderTypes();
                SpaceengineS.LOGGER.debug("Cleared planet texture caches");
            }
        });
    }

    @SubscribeEvent
    public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SpaceParticles.ZAP_BUBBLE_PARTICLES.get(), ZapBubbleParticle.Provider::new);
        event.registerSpriteSet(SpaceParticles.VERDITE_PARTICLES.get(), VerditeParticle.Provider::new);
    }
}
