package net.lointain.cosmos.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class SteelRedstonerecieverOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (((<undefinedtype>)(new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "powerLevel") > (double)0.0F && !world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("powerLevel", ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "powerLevel") - (double)1.0F);
         }

         if (world instanceof Level) {
            Level _level = (Level)world;
            _level.m_7260_(_bp, _bs, _bs, 3);
         }
      }

      if (((<undefinedtype>)(new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "powerLevel") > (double)2.0F) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockState _bs = ((Block)CosmosModBlocks.STEEL_RESTONE_RECIEVER_ON.get()).m_49966_();
         BlockState _bso = world.m_8055_(_bp);
         UnmodifiableIterator _be = _bso.m_61148_().entrySet().iterator();

         while(_be.hasNext()) {
            Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)_be.next();
            Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
            if (_property != null && _bs.m_61143_(_property) != null) {
               try {
                  _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
               } catch (Exception var17) {
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
               } catch (Exception var16) {
               }
            }
         }
      } else {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockState _bs = ((Block)CosmosModBlocks.STEEL_RESTONE_RECIEVER_OFF.get()).m_49966_();
         BlockState _bso = world.m_8055_(_bp);
         UnmodifiableIterator var27 = _bso.m_61148_().entrySet().iterator();

         while(var27.hasNext()) {
            Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)var27.next();
            Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
            if (_property != null && _bs.m_61143_(_property) != null) {
               try {
                  _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
               } catch (Exception var15) {
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
               } catch (Exception var14) {
               }
            }
         }
      }

   }
}
