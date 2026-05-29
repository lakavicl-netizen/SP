package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.shaders.FogShape;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class WeatherProcedure {
   public static ViewportEvent.RenderFog provider = null;

   public static void setDistance(float start, float end) {
      provider.setNearPlaneDistance(start);
      provider.setFarPlaneDistance(end);
      if (!provider.isCanceled()) {
         provider.setCanceled(true);
      }

   }

   public static void setShape(FogShape shape) {
      provider.setFogShape(shape);
      if (!provider.isCanceled()) {
         provider.setCanceled(true);
      }

   }

   @SubscribeEvent
   public static void renderFog(ViewportEvent.RenderFog event) {
      provider = event;
      if (provider.getMode() == FogMode.FOG_TERRAIN) {
         ClientLevel level = Minecraft.m_91087_().f_91073_;
         Entity entity = provider.getCamera().m_90592_();
         if (level != null && entity != null) {
            Vec3 pos = entity.m_20318_((float)provider.getPartialTick());
            execute(provider, entity);
         }
      }

   }

   public static void execute(Entity entity) {
      execute((Event)null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (entity.m_9236_().m_46472_() == ResourceKey.m_135785_(Registries.f_256858_, new ResourceLocation("cosmos:europa_lands")) && Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7098_() < (double)38.0F) {
            setShape(FogShape.CYLINDER);
            setDistance(140.0F, 200.0F);
         }

      }
   }
}
