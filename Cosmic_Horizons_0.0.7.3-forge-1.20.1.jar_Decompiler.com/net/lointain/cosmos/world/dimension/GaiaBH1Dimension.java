package net.lointain.cosmos.world.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.DimensionSpecialEffects.SkyType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber
public class GaiaBH1Dimension {
   @EventBusSubscriber(
      bus = Bus.MOD
   )
   public static class SolarSystemSpecialEffectsHandler {
      @SubscribeEvent
      @OnlyIn(Dist.CLIENT)
      public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
         DimensionSpecialEffects customEffect = new DimensionSpecialEffects(Float.NaN, true, SkyType.NONE, false, false) {
            {
               super(p_108866_, p_108867_, p_108868_, p_108869_, p_108870_);
            }

            public Vec3 m_5927_(Vec3 color, float sunHeight) {
               return color;
            }

            public boolean m_5781_(int x, int y) {
               return false;
            }
         };
         event.register(new ResourceLocation("cosmos:gaia_bh_1"), customEffect);
      }
   }
}
