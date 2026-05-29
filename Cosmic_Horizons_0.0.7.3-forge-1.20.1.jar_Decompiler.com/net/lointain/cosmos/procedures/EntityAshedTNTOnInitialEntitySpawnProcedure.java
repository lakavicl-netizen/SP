package net.lointain.cosmos.procedures;

import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.init.CosmosModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class EntityAshedTNTOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double G = (double)0.0F;
         double S = (double)0.0F;
         double M = (double)0.0F;
         CosmosMod.queueServerWork(60, () -> {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_254849_((Entity)null, x, y, z, 2.0F, ExplosionInteraction.TNT);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)1.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)1.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)1.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)0.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)0.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)1.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)-1.0F, (double)1.0F, (double)0.0F);
               }
            }

            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get()).m_262496_(_level, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.m_146922_(45.0F);
                  entityToSpawn.m_5618_(45.0F);
                  entityToSpawn.m_5616_(45.0F);
                  entityToSpawn.m_20334_((double)0.0F, (double)1.0F, (double)-1.0F);
               }
            }

            if (!entity.m_9236_().m_5776_()) {
               entity.m_146870_();
            }

         });
      }
   }
}
