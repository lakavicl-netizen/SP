package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class FaceshipProcedure {
   public static ViewportEvent.ComputeFov provider = null;

   public static void setFOV(double fov) {
      provider.setFOV(fov);
   }

   @SubscribeEvent
   public static void computeFOV(ViewportEvent.ComputeFov event) {
      provider = event;
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      Entity entity = provider.getCamera().m_90592_();
      if (level != null && entity != null) {
         Vec3 entPos = entity.m_20318_((float)provider.getPartialTick());
         execute(provider, entity);
      }

   }

   public static void execute(Entity entity) {
      execute((Event)null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         if (entity.m_20202_() instanceof RocketSeatEntity && (Minecraft.m_91087_().f_91066_.m_92176_() == CameraType.THIRD_PERSON_BACK || Minecraft.m_91087_().f_91066_.m_92176_() == CameraType.THIRD_PERSON_FRONT)) {
            setFOV((double)85.0F);
         }

      }
   }
}
