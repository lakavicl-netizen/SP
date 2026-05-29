package net.lointain.cosmos.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;
import java.util.Map;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class CablemodleupdaterLtcProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      BlockState blockSelector = Blocks.f_50016_.m_49966_();
      String tagPath = "";
      String nbtSouthTagName = "";
      String nbtWestTagName = "";
      String nbtNorthTagName = "";
      String nbtEastTagName = "";
      String nbtUpTagName = "";
      String nbtDownTagName = "";
      if (!world.m_5776_()) {
         blockSelector = ((Block)CosmosModBlocks.CABLELTC.get()).m_49966_();
         tagPath = "cosmos:cables/f/copper";
         nbtNorthTagName = "cablesFCopperNorth";
         nbtEastTagName = "cablesFCopperEast";
         nbtSouthTagName = "cablesFCopperSouth";
         nbtWestTagName = "cablesFCopperWest";
         nbtUpTagName = "cablesFCopperUp";
         nbtDownTagName = "cablesFCopperDown";
         if (!world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y + (double)1.0F, z), nbtDownTagName) && (world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y - (double)1.0F, z), nbtUpTagName)) && !world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z + (double)1.0F), nbtNorthTagName) && (world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z - (double)1.0F), nbtSouthTagName)) && (world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x + (double)1.0F, y, z), nbtWestTagName)) && !world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x - (double)1.0F, y, z), nbtEastTagName)) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockState _bs = blockSelector;
            BlockState _bso = world.m_8055_(_bp);
            UnmodifiableIterator var163 = _bso.m_61148_().entrySet().iterator();

            while(var163.hasNext()) {
               Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)var163.next();
               Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
               if (_property != null && _bs.m_61143_(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                  } catch (Exception var29) {
                  }
               }
            }

            BlockEntity _be = world.m_7702_(_bp);
            CompoundTag _bnbt = null;
            if (_be != null) {
               _bnbt = _be.m_187480_();
               _be.m_7651_();
            }

            world.m_7731_(_bp, _bs, 3);
            if (_bnbt != null) {
               _be = world.m_7702_(_bp);
               if (_be != null) {
                  try {
                     _be.m_142466_(_bnbt);
                  } catch (Exception var28) {
                  }
               }
            }

            label421: {
               Direction _dir = Direction.NORTH;
               BlockPos _pos = BlockPos.m_274561_(x, y, z);
               _bso = world.m_8055_(_pos);
               Property<?> _property = _bso.m_60734_().m_49965_().m_61081_("facing");
               if (_property instanceof DirectionProperty) {
                  DirectionProperty _dp = (DirectionProperty)_property;
                  if (_dp.m_6908_().contains(_dir)) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_dp, _dir), 3);
                     break label421;
                  }
               }

               _property = _bso.m_60734_().m_49965_().m_61081_("axis");
               if (_property instanceof EnumProperty) {
                  EnumProperty _ap = (EnumProperty)_property;
                  if (_ap.m_6908_().contains(_dir.m_122434_())) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_ap, _dir.m_122434_()), 3);
                  }
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtUpTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtDownTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtNorthTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtEastTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtSouthTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtWestTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }
         } else if (!world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y + (double)1.0F, z), nbtDownTagName) && (world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y - (double)1.0F, z), nbtUpTagName)) && (world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z + (double)1.0F), nbtNorthTagName)) && !world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z - (double)1.0F), nbtSouthTagName) && (world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x + (double)1.0F, y, z), nbtWestTagName)) && !world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x - (double)1.0F, y, z), nbtEastTagName)) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockState _bs = blockSelector;
            BlockState _bso = world.m_8055_(_bp);
            UnmodifiableIterator _level = _bso.m_61148_().entrySet().iterator();

            while(_level.hasNext()) {
               Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)_level.next();
               Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
               if (_property != null && _bs.m_61143_(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                  } catch (Exception var27) {
                  }
               }
            }

            BlockEntity _be = world.m_7702_(_bp);
            CompoundTag _bnbt = null;
            if (_be != null) {
               _bnbt = _be.m_187480_();
               _be.m_7651_();
            }

            world.m_7731_(_bp, _bs, 3);
            if (_bnbt != null) {
               _be = world.m_7702_(_bp);
               if (_be != null) {
                  try {
                     _be.m_142466_(_bnbt);
                  } catch (Exception var26) {
                  }
               }
            }

            label435: {
               Direction _dir = Direction.EAST;
               BlockPos _pos = BlockPos.m_274561_(x, y, z);
               _bso = world.m_8055_(_pos);
               Property<?> _property = _bso.m_60734_().m_49965_().m_61081_("facing");
               if (_property instanceof DirectionProperty) {
                  DirectionProperty _dp = (DirectionProperty)_property;
                  if (_dp.m_6908_().contains(_dir)) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_dp, _dir), 3);
                     break label435;
                  }
               }

               _property = _bso.m_60734_().m_49965_().m_61081_("axis");
               if (_property instanceof EnumProperty) {
                  EnumProperty _ap = (EnumProperty)_property;
                  if (_ap.m_6908_().contains(_dir.m_122434_())) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_ap, _dir.m_122434_()), 3);
                  }
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtUpTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtDownTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtNorthTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtEastTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtSouthTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtWestTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }
         } else if (world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y + (double)1.0F, z), nbtDownTagName) || !world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y - (double)1.0F, z), nbtUpTagName) || !world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z + (double)1.0F), nbtNorthTagName) || world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z - (double)1.0F), nbtSouthTagName) || world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x + (double)1.0F, y, z), nbtWestTagName) || !world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x - (double)1.0F, y, z), nbtEastTagName)) {
            if (!world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x, y + (double)1.0F, z), nbtDownTagName) && (world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x, y - (double)1.0F, z), nbtUpTagName)) && !world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z + (double)1.0F), nbtNorthTagName) && (world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z - (double)1.0F), nbtSouthTagName)) && !world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) && !((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x + (double)1.0F, y, z), nbtWestTagName) && (world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation(tagPath.toLowerCase(Locale.ENGLISH)))) || ((<undefinedtype>)(new Object() {
               public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
               }
            })).getValue(world, BlockPos.m_274561_(x - (double)1.0F, y, z), nbtEastTagName))) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockState _bs = blockSelector;
               BlockState _bso = world.m_8055_(_bp);
               UnmodifiableIterator _level = _bso.m_61148_().entrySet().iterator();

               while(_level.hasNext()) {
                  Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)_level.next();
                  Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
                  if (_property != null && _bs.m_61143_(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                     } catch (Exception var23) {
                     }
                  }
               }

               BlockEntity _be = world.m_7702_(_bp);
               CompoundTag _bnbt = null;
               if (_be != null) {
                  _bnbt = _be.m_187480_();
                  _be.m_7651_();
               }

               world.m_7731_(_bp, _bs, 3);
               if (_bnbt != null) {
                  _be = world.m_7702_(_bp);
                  if (_be != null) {
                     try {
                        _be.m_142466_(_bnbt);
                     } catch (Exception var22) {
                     }
                  }
               }

               label463: {
                  Direction _dir = Direction.WEST;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z);
                  _bso = world.m_8055_(_pos);
                  Property<?> _property = _bso.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bso.m_61124_(_dp, _dir), 3);
                        break label463;
                     }
                  }

                  _property = _bso.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bso.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtUpTagName, false);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtDownTagName, true);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtNorthTagName, true);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtEastTagName, false);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtSouthTagName, false);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }

               if (!world.m_5776_()) {
                  _bp = BlockPos.m_274561_(x, y, z);
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  _bso = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128379_(nbtWestTagName, true);
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bso, _bso, 3);
                  }
               }
            }
         } else {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockState _bs = blockSelector;
            BlockState _bso = world.m_8055_(_bp);
            UnmodifiableIterator _be = _bso.m_61148_().entrySet().iterator();

            while(_be.hasNext()) {
               Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)_be.next();
               Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
               if (_property != null && _bs.m_61143_(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                  } catch (Exception var25) {
                  }
               }
            }

            BlockEntity _be = world.m_7702_(_bp);
            CompoundTag _bnbt = null;
            if (_be != null) {
               _bnbt = _be.m_187480_();
               _be.m_7651_();
            }

            world.m_7731_(_bp, _bs, 3);
            if (_bnbt != null) {
               _be = world.m_7702_(_bp);
               if (_be != null) {
                  try {
                     _be.m_142466_(_bnbt);
                  } catch (Exception var24) {
                  }
               }
            }

            label449: {
               Direction _dir = Direction.SOUTH;
               BlockPos _pos = BlockPos.m_274561_(x, y, z);
               _bso = world.m_8055_(_pos);
               Property<?> _property = _bso.m_60734_().m_49965_().m_61081_("facing");
               if (_property instanceof DirectionProperty) {
                  DirectionProperty _dp = (DirectionProperty)_property;
                  if (_dp.m_6908_().contains(_dir)) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_dp, _dir), 3);
                     break label449;
                  }
               }

               _property = _bso.m_60734_().m_49965_().m_61081_("axis");
               if (_property instanceof EnumProperty) {
                  EnumProperty _ap = (EnumProperty)_property;
                  if (_ap.m_6908_().contains(_dir.m_122434_())) {
                     world.m_7731_(_pos, (BlockState)_bso.m_61124_(_ap, _dir.m_122434_()), 3);
                  }
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtUpTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtDownTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtNorthTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtEastTagName, false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtSouthTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }

            if (!world.m_5776_()) {
               _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               _bso = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_(nbtWestTagName, true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bso, _bso, 3);
               }
            }
         }
      }

   }
}
