package net.lointain.cosmos.init;

import net.lointain.cosmos.client.gui.AntenaNamerScreen;
import net.lointain.cosmos.client.gui.CHamberedsupersmelterScreen;
import net.lointain.cosmos.client.gui.LandingSelectorScreen;
import net.lointain.cosmos.client.gui.PlanetSelectorScreen;
import net.lointain.cosmos.client.gui.ProjectionSelectorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class CosmosModScreens {
   @SubscribeEvent
   public static void clientLoad(FMLClientSetupEvent event) {
      event.enqueueWork(() -> {
         MenuScreens.m_96206_((MenuType)CosmosModMenus.C_HAMBEREDSUPERSMELTER.get(), CHamberedsupersmelterScreen::new);
         MenuScreens.m_96206_((MenuType)CosmosModMenus.PLANET_SELECTOR.get(), PlanetSelectorScreen::new);
         MenuScreens.m_96206_((MenuType)CosmosModMenus.ANTENA_NAMER.get(), AntenaNamerScreen::new);
         MenuScreens.m_96206_((MenuType)CosmosModMenus.LANDING_SELECTOR.get(), LandingSelectorScreen::new);
         MenuScreens.m_96206_((MenuType)CosmosModMenus.PROJECTION_SELECTOR.get(), ProjectionSelectorScreen::new);
      });
   }
}
