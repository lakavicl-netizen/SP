package com.sable.spaceenginemod.client;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

@EventBusSubscriber(modid = SpaceengineS.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DimensionEffectsRegistration {

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(SpaceengineS.SPACE_DIM, new SpaceDimensionEffects());
        event.register(SpaceengineS.WORMHOLE_DIM, new WormholeDimensionEffects());
        event.register(SpaceengineS.GENERIC_PLANET_ID, new PlanetDimensionEffects());
    }
}
