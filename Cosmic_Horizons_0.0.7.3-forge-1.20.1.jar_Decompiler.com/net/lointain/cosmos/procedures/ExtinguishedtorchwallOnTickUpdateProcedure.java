package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

public class ExtinguishedtorchwallOnTickUpdateProcedure {
   public static void execute(final LevelAccessor world, double x, double y, double z) {
      if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.EXTINGUISHEDTORCHWALL.get()) {
         if (world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_60734_() == Blocks.f_50016_ && ((<undefinedtype>)(new Object() {
            public Direction getDirection(BlockPos pos) {
               BlockState _bs = world.m_8055_(pos);
               Property<?> property = _bs.m_60734_().m_49965_().m_61081_("facing");
               if (property != null) {
                  Comparable var5 = _bs.m_61143_(property);
                  if (var5 instanceof Direction) {
                     Direction _dir = (Direction)var5;
                     return _dir;
                  }
               }

               if (_bs.m_61138_(BlockStateProperties.f_61365_)) {
                  return Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61365_), AxisDirection.POSITIVE);
               } else {
                  return _bs.m_61138_(BlockStateProperties.f_61364_) ? Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61364_), AxisDirection.POSITIVE) : Direction.NORTH;
               }
            }
         })).getDirection(BlockPos.m_274561_(x, y, z)) == Direction.NORTH) {
            BlockPos _pos = BlockPos.m_274561_(x, y, z);
            Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
            world.m_46961_(_pos, false);
         } else if (world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_60734_() == Blocks.f_50016_ && ((<undefinedtype>)(new Object() {
            public Direction getDirection(BlockPos pos) {
               BlockState _bs = world.m_8055_(pos);
               Property<?> property = _bs.m_60734_().m_49965_().m_61081_("facing");
               if (property != null) {
                  Comparable var5 = _bs.m_61143_(property);
                  if (var5 instanceof Direction) {
                     Direction _dir = (Direction)var5;
                     return _dir;
                  }
               }

               if (_bs.m_61138_(BlockStateProperties.f_61365_)) {
                  return Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61365_), AxisDirection.POSITIVE);
               } else {
                  return _bs.m_61138_(BlockStateProperties.f_61364_) ? Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61364_), AxisDirection.POSITIVE) : Direction.NORTH;
               }
            }
         })).getDirection(BlockPos.m_274561_(x, y, z)) == Direction.SOUTH) {
            BlockPos _pos = BlockPos.m_274561_(x, y, z);
            Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
            world.m_46961_(_pos, false);
         } else if (world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_60734_() == Blocks.f_50016_ && ((<undefinedtype>)(new Object() {
            public Direction getDirection(BlockPos pos) {
               BlockState _bs = world.m_8055_(pos);
               Property<?> property = _bs.m_60734_().m_49965_().m_61081_("facing");
               if (property != null) {
                  Comparable var5 = _bs.m_61143_(property);
                  if (var5 instanceof Direction) {
                     Direction _dir = (Direction)var5;
                     return _dir;
                  }
               }

               if (_bs.m_61138_(BlockStateProperties.f_61365_)) {
                  return Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61365_), AxisDirection.POSITIVE);
               } else {
                  return _bs.m_61138_(BlockStateProperties.f_61364_) ? Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61364_), AxisDirection.POSITIVE) : Direction.NORTH;
               }
            }
         })).getDirection(BlockPos.m_274561_(x, y, z)) == Direction.WEST) {
            BlockPos _pos = BlockPos.m_274561_(x, y, z);
            Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
            world.m_46961_(_pos, false);
         } else if (world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_60734_() == Blocks.f_50016_ && ((<undefinedtype>)(new Object() {
            public Direction getDirection(BlockPos pos) {
               BlockState _bs = world.m_8055_(pos);
               Property<?> property = _bs.m_60734_().m_49965_().m_61081_("facing");
               if (property != null) {
                  Comparable var5 = _bs.m_61143_(property);
                  if (var5 instanceof Direction) {
                     Direction _dir = (Direction)var5;
                     return _dir;
                  }
               }

               if (_bs.m_61138_(BlockStateProperties.f_61365_)) {
                  return Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61365_), AxisDirection.POSITIVE);
               } else {
                  return _bs.m_61138_(BlockStateProperties.f_61364_) ? Direction.m_122387_((Direction.Axis)_bs.m_61143_(BlockStateProperties.f_61364_), AxisDirection.POSITIVE) : Direction.NORTH;
               }
            }
         })).getDirection(BlockPos.m_274561_(x, y, z)) == Direction.EAST) {
            BlockPos _pos = BlockPos.m_274561_(x, y, z);
            Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
            world.m_46961_(_pos, false);
         }
      } else if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.EXTINGUISHEDTORCH.get() && world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() == Blocks.f_50016_) {
         BlockPos _pos = BlockPos.m_274561_(x, y, z);
         Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
         world.m_46961_(_pos, false);
      }

   }
}
