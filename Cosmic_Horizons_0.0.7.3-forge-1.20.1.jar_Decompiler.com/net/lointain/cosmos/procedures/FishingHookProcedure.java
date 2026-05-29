package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.FishingHookTickEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class FishingHookProcedure {
   @SubscribeEvent
   public static void onEventTriggered(FishingHookTickEvent event) {
      execute(event, event.getEntity().m_9236_(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         String dim = "";
         double gravity = (double)0.0F;
         dim = entity.m_9236_().m_46472_().m_135782_().toString();
         if (ApplyGravityLogicProcedure.execute(world, dim)) {
            gravity = GravityDataProviderProcedure.execute(world, dim);
            if (gravity == (double)0.0F) {
               if (!entity.m_20068_()) {
                  entity.m_20242_(true);
               }
            } else {
               entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), (entity.m_20184_().m_7098_() / 0.98 + 0.03 - gravity * 0.03) * FrictionDataProviderProcedure.execute(world, dim), entity.m_20184_().m_7094_()));
            }
         }

      }
   }
}
