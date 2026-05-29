package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.LivingEntityTickEvent;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class LivingEntityProcedure {
   @SubscribeEvent
   public static void onEventTriggered(LivingEntityTickEvent event) {
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
         if (ApplyGravityLogicProcedure.execute(world, dim) && !entity.m_20077_() && !entity.m_20069_()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt3 = (LivingEntity)entity;
               if (_livEnt3.m_21255_()) {
                  return;
               }
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt4 = (LivingEntity)entity;
               if (_livEnt4.m_21023_(MobEffects.f_19591_)) {
                  return;
               }
            }

            if (!(entity instanceof RocketSeatEntity)) {
               gravity = GravityDataProviderProcedure.execute(world, dim);
               if (gravity == (double)0.0F) {
                  if (!entity.m_20068_()) {
                     entity.m_20242_(true);
                  }
               } else if (entity.m_20068_()) {
                  entity.m_20242_(false);
               }

               if (!(entity instanceof Player) && !(entity instanceof ServerPlayer)) {
                  if (gravity == (double)0.0F) {
                     if (!entity.m_20068_()) {
                        entity.m_20242_(true);
                     }
                  } else {
                     if (entity.m_20068_()) {
                        entity.m_20242_(false);
                     }

                     entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), (entity.m_20184_().m_7098_() / 0.98 + 0.08 - gravity * 0.08) * FrictionDataProviderProcedure.execute(world, dim), entity.m_20184_().m_7094_()));
                     entity.f_19789_ = (float)((double)entity.f_19789_ * gravity);
                  }
               } else {
                  if (entity instanceof Player) {
                     Player player = (Player)entity;
                     if (player.m_150110_().f_35935_) {
                        return;
                     }
                  }

                  if (!((<undefinedtype>)(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.f_8941_.m_9290_() == GameType.SPECTATOR;
                        } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                           return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SPECTATOR;
                        } else {
                           return false;
                        }
                     }
                  })).checkGamemode(entity)) {
                     if (gravity == (double)0.0F) {
                        if (!entity.m_20068_()) {
                           entity.m_20242_(true);
                        }
                     } else {
                        if (entity.m_20068_()) {
                           entity.m_20242_(false);
                        }

                        entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), (entity.m_20184_().m_7098_() / 0.98 + 0.08 - gravity * 0.08) * FrictionDataProviderProcedure.execute(world, dim), entity.m_20184_().m_7094_()));
                        entity.f_19789_ = (float)((double)entity.f_19789_ * gravity);
                     }
                  }
               }
            }
         }

      }
   }
}
