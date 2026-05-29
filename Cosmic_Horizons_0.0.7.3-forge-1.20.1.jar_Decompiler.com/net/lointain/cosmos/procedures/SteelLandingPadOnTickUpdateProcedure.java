package net.lointain.cosmos.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SteelLandingPadOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      boolean logic = false;

      for(int m = (int)(y - (double)5.0F); m <= 800; ++m) {
         if (!world.m_6443_(RocketSeatEntity.class, AABB.m_165882_(new Vec3(x, (double)m, z), (double)2.5F, (double)2.5F, (double)2.5F), (e) -> true).isEmpty()) {
            logic = true;
            break;
         }

         logic = false;
      }

      if (logic) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockState _bs = ((Block)CosmosModBlocks.STEEL_LANDING_PAD_ON.get()).m_49966_();
         BlockState _bso = world.m_8055_(_bp);
         UnmodifiableIterator var11 = _bso.m_61148_().entrySet().iterator();

         while(var11.hasNext()) {
            Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)var11.next();
            Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
            if (_property != null && _bs.m_61143_(_property) != null) {
               try {
                  _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
               } catch (Exception var21) {
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
               } catch (Exception var20) {
               }
            }
         }

         int horizontalRadiusSquare = 6;
         int verticalRadiusSquare = 6;
         int yIterationsSquare = verticalRadiusSquare;

         for(int i = -verticalRadiusSquare; i <= yIterationsSquare; ++i) {
            for(int xi = -horizontalRadiusSquare; xi <= horizontalRadiusSquare; ++xi) {
               for(int zi = -horizontalRadiusSquare; zi <= horizontalRadiusSquare; ++zi) {
                  if ((world.m_8055_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi)).m_60734_() == CosmosModBlocks.STEEL_RESTONE_RECIEVER_ON.get() || world.m_8055_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi)).m_60734_() == CosmosModBlocks.STEEL_RESTONE_RECIEVER_OFF.get()) && ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)xi, y + (double)i, y + (double)i), "powerLevel") < (double)3.0F && !world.m_5776_()) {
                     BlockPos _bp = BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi);
                     BlockEntity _blockEntity = world.m_7702_(_bp);
                     BlockState _bs = world.m_8055_(_bp);
                     if (_blockEntity != null) {
                        _blockEntity.getPersistentData().m_128347_("powerLevel", (double)5.0F);
                     }

                     if (world instanceof Level) {
                        Level _level = (Level)world;
                        _level.m_7260_(_bp, _bs, _bs, 3);
                     }
                  }
               }
            }
         }
      } else if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.STEEL_LANDING_PAD_OFF.get()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockState _bs = ((Block)CosmosModBlocks.STEEL_LANDING_PAD_OFF.get()).m_49966_();
         BlockState _bso = world.m_8055_(_bp);
         UnmodifiableIterator var32 = _bso.m_61148_().entrySet().iterator();

         while(var32.hasNext()) {
            Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)var32.next();
            Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
            if (_property != null && _bs.m_61143_(_property) != null) {
               try {
                  _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
               } catch (Exception var19) {
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
               } catch (Exception var18) {
               }
            }
         }
      }

   }
}
