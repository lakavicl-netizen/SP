package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModEntities;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class AshedsulpherTNTProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.m_9236_().m_46472_() == Level.f_46428_ && entity.m_9236_().m_46472_() == Level.f_46430_ && entity.m_9236_().m_46472_() == Level.f_46429_) {
            if (entity.m_9236_().m_46472_() == Level.f_46428_ || entity.m_9236_().m_46472_() == Level.f_46430_ || entity.m_9236_().m_46472_() == Level.f_46429_) {
               ItemStack var19;
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var19 = _livEnt.m_21206_();
               } else {
                  var19 = ItemStack.f_41583_;
               }

               if (var19.m_41720_() != Items.f_42409_) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var19 = _livEnt.m_21205_();
                  } else {
                     var19 = ItemStack.f_41583_;
                  }

                  if (var19.m_41720_() != Items.f_42409_) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var19 = _livEnt.m_21206_();
                     } else {
                        var19 = ItemStack.f_41583_;
                     }

                     if (var19.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
                        if (entity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)entity;
                           var19 = _livEnt.m_21205_();
                        } else {
                           var19 = ItemStack.f_41583_;
                        }

                        if (var19.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
                           return;
                        }
                     }
                  }
               }

               world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
               if (world instanceof ServerLevel) {
                  ServerLevel _level = (ServerLevel)world;
                  Entity entityToSpawn = ((EntityType)CosmosModEntities.ENTITY_ASHED_TNT.get()).m_262496_(_level, BlockPos.m_274561_(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)0.0F);
                  }
               }
            }
         } else {
            ItemStack var10000;
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
                  return;
               }
            }

            world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
            if (world instanceof ServerLevel) {
               ServerLevel _level = (ServerLevel)world;
               Entity entityToSpawn = ((EntityType)CosmosModEntities.ENTITY_ASHED_TNT.get()).m_262496_(_level, BlockPos.m_274561_(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)0.0F);
               }
            }
         }

      }
   }
}
