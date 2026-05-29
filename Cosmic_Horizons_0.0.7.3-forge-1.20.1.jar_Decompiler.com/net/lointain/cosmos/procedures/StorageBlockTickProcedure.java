package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StorageBlockTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      Direction direction = Direction.NORTH;

      for(Direction directioniterator : Direction.values()) {
         if ((world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission"))) || world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage")))) && StorageTransmitLogicProcedure.execute(world, x, y, z, directioniterator) && ((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "flow") > (double)4.0F && ((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "flow") <= (double)4.0F && !world.m_5776_()) {
            BlockPos _bp = BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_());
            BlockEntity _blockEntity = world.m_7702_(_bp);
            BlockState _bs = world.m_8055_(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().m_128347_("flow", (double)5.0F);
            }

            if (world instanceof Level) {
               Level _level = (Level)world;
               _level.m_7260_(_bp, _bs, _bs, 3);
            }
         }
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractDown")) {
         direction = Direction.DOWN;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractUp")) {
         direction = Direction.UP;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractEast")) {
         direction = Direction.EAST;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractWest")) {
         direction = Direction.WEST;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractNorth")) {
         direction = Direction.NORTH;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "extractSouth")) {
         direction = Direction.SOUTH;
         ExtractProcedure.execute(world, x, y, z, direction);
      }

   }
}
