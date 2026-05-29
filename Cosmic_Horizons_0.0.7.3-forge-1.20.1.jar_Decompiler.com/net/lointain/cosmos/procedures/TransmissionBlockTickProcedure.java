package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TransmissionBlockTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      Direction dir = Direction.NORTH;
      if (!world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("flow", Mth.m_14008_(((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "flow") - (double)0.5F, (double)0.0F, (double)5.0F));
         }

         if (world instanceof Level) {
            Level _level = (Level)world;
            _level.m_7260_(_bp, _bs, _bs, 3);
         }
      }

      for(Direction directioniterator : Direction.values()) {
         if ((world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission"))) || world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage")))) && ((<undefinedtype>)(new Object() {
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

         if (!world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage")))) {
            if (((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "energy") > ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "energy") && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "energy") + (double)0.5F, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "TransmissionRate")));
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }
         } else if (world.m_8055_(BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && StorageRecieveLogicProcedure.execute(world, x, y, z, directioniterator)) {
            if (((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "energy") > ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "energy") && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "energy") + (double)0.5F, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "TransmissionRate")));
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "energy") - ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "ReceiveRate"), (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)directioniterator.m_122429_(), y + (double)directioniterator.m_122430_(), z + (double)directioniterator.m_122431_()), "EnergyStorage")));
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }
         }
      }

      if (!world.m_5776_()) {
         BlockPos _bp = BlockPos.m_274561_(x, y, z);
         BlockEntity _blockEntity = world.m_7702_(_bp);
         BlockState _bs = world.m_8055_(_bp);
         if (_blockEntity != null) {
            _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "energy") - (double)0.5F, (double)0.0F, ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "TransmissionRate")));
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
      })).getValue(world, BlockPos.m_274561_(x, y, z), "flow") == (double)0.0F && !world.m_5776_()) {
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
