package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

public class TorchextinguishedRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Direction direction) {
      if (direction != null) {
         if (direction == Direction.UP) {
            if (world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50627_) {
               world.m_7731_(BlockPos.m_274561_(x, y + (double)1.0F, z), ((Block)CosmosModBlocks.EXTINGUISHEDTORCH.get()).m_49966_(), 3);
               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         } else if (direction == Direction.NORTH) {
            if (world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_60734_() == Blocks.f_50627_) {
               label92: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z - (double)1.0F), ((Block)CosmosModBlocks.EXTINGUISHEDTORCHWALL.get()).m_49966_(), 3);
                  Direction _dir = Direction.WEST;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z - (double)1.0F);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label92;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         } else if (direction == Direction.SOUTH) {
            if (world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_60734_() == Blocks.f_50627_) {
               label97: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z + (double)1.0F), ((Block)CosmosModBlocks.EXTINGUISHEDTORCHWALL.get()).m_49966_(), 3);
                  Direction _dir = Direction.EAST;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z + (double)1.0F);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label97;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         } else if (direction == Direction.WEST) {
            if (world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_60734_() == Blocks.f_50627_) {
               label102: {
                  world.m_7731_(BlockPos.m_274561_(x - (double)1.0F, y, z), ((Block)CosmosModBlocks.EXTINGUISHEDTORCHWALL.get()).m_49966_(), 3);
                  Direction _dir = Direction.SOUTH;
                  BlockPos _pos = BlockPos.m_274561_(x - (double)1.0F, y, z);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label102;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         } else if (direction == Direction.EAST && (world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_60734_() == Blocks.f_50627_)) {
            label107: {
               world.m_7731_(BlockPos.m_274561_(x + (double)1.0F, y, z), ((Block)CosmosModBlocks.EXTINGUISHEDTORCHWALL.get()).m_49966_(), 3);
               Direction _dir = Direction.NORTH;
               BlockPos _pos = BlockPos.m_274561_(x + (double)1.0F, y, z);
               BlockState _bs = world.m_8055_(_pos);
               Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
               if (_property instanceof DirectionProperty) {
                  DirectionProperty _dp = (DirectionProperty)_property;
                  if (_dp.m_6908_().contains(_dir)) {
                     world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                     break label107;
                  }
               }

               _property = _bs.m_60734_().m_49965_().m_61081_("axis");
               if (_property instanceof EnumProperty) {
                  EnumProperty _ap = (EnumProperty)_property;
                  if (_ap.m_6908_().contains(_dir.m_122434_())) {
                     world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                  }
               }
            }

            if (world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
               } else {
                  _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
               }
            }
         }

      }
   }
}
