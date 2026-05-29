package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class StorageRecieveLogicProcedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z, Direction direction) {
      if (direction == null) {
         return false;
      } else {
         Direction dir = Direction.NORTH;
         String dir_tag = "";
         if (direction == Direction.UP) {
            dir_tag = "sendDown";
         } else if (direction == Direction.DOWN) {
            dir_tag = "sendUp";
         } else if (direction == Direction.WEST) {
            dir_tag = "sendEast";
         } else if (direction == Direction.EAST) {
            dir_tag = "sendWest";
         } else if (direction == Direction.SOUTH) {
            dir_tag = "sendNorth";
         } else if (direction == Direction.NORTH) {
            dir_tag = "sendSouth";
         }

         return ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), dir_tag);
      }
   }
}
