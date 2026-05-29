package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RollspeedupProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("roll_speed", Mth.m_14008_(((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "roll_speed") + 0.1, (double)0.0F, (double)3.0F));
         }

         if (world instanceof Level) {
            Level _level = (Level)world;
            _level.m_7260_(_bp, _bs, _bs, 3);
         }
      }

   }
}
