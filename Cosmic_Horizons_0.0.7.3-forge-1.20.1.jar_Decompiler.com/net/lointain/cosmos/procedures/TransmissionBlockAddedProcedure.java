package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TransmissionBlockAddedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("flow", (double)0.0F);
         }

         if (world instanceof Level) {
            Level _level = (Level)world;
            _level.m_7260_(_bp, _bs, _bs, 3);
         }
      }

      if (!world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("energy", (double)0.0F);
         }

         if (world instanceof Level) {
            Level _level = (Level)world;
            _level.m_7260_(_bp, _bs, _bs, 3);
         }
      }

   }
}
