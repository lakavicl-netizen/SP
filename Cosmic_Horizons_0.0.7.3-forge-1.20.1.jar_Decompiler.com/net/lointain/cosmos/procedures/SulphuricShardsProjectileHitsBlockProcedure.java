package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class SulphuricShardsProjectileHitsBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      int horizontalRadiusSphere = 1;
      int verticalRadiusSphere = 1;
      int yIterationsSphere = verticalRadiusSphere;

      for(int i = -verticalRadiusSphere; i <= yIterationsSphere; ++i) {
         for(int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; ++xi) {
            for(int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; ++zi) {
               double distanceSq = (double)(xi * xi) / (double)(horizontalRadiusSphere * horizontalRadiusSphere) + (double)(i * i) / (double)(verticalRadiusSphere * verticalRadiusSphere) + (double)(zi * zi) / (double)(horizontalRadiusSphere * horizontalRadiusSphere);
               if (distanceSq <= (double)1.0F && !world.m_8055_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
                  world.m_7731_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi), Blocks.f_50016_.m_49966_(), 3);
               }
            }
         }
      }

   }
}
