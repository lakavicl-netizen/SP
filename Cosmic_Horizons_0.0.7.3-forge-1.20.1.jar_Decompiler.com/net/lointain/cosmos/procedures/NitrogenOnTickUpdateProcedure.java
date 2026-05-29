package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;

public class NitrogenOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      ResourceKey var10000;
      if (world instanceof Level _lvl) {
         var10000 = _lvl.m_46472_();
      } else if (world instanceof WorldGenLevel _wgl) {
         var10000 = _wgl.m_6018_().m_46472_();
      } else {
         var10000 = Level.f_46428_;
      }

      if (var10000 != Level.f_46428_) {
         if (world instanceof Level) {
            Level _lvl = (Level)world;
            var10000 = _lvl.m_46472_();
         } else if (world instanceof WorldGenLevel) {
            WorldGenLevel _wgl = (WorldGenLevel)world;
            var10000 = _wgl.m_6018_().m_46472_();
         } else {
            var10000 = Level.f_46428_;
         }

         if (var10000 != Level.f_46429_) {
            if (world instanceof Level) {
               Level _lvl = (Level)world;
               var10000 = _lvl.m_46472_();
            } else if (world instanceof WorldGenLevel) {
               WorldGenLevel _wgl = (WorldGenLevel)world;
               var10000 = _wgl.m_6018_().m_46472_();
            } else {
               var10000 = Level.f_46428_;
            }

            if (var10000 != Level.f_46430_) {
               return;
            }
         }
      }

      world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
   }
}
