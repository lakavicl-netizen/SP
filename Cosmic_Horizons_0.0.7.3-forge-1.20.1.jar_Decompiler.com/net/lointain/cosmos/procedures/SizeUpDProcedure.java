package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SizeUpDProcedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z) {
      return ((<undefinedtype>)(new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "size") < (double)45.0F;
   }
}
