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
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class SendBlockTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      Direction direction = Direction.NORTH;
      double send_rate = (double)0.0F;
      boolean Eflow = false;
      send_rate = ((<undefinedtype>)(new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "SendRate");
      Eflow = ((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "Eflow");
      if (Eflow) {
         if (((<undefinedtype>)(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperDown")) {
            direction = Direction.DOWN;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
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
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperUp")) {
            direction = Direction.UP;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
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
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperEast")) {
            direction = Direction.EAST;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
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
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperWest")) {
            direction = Direction.WEST;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
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
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperNorth")) {
            direction = Direction.NORTH;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
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
         })).getValue(world, BlockPos.m_274561_(x, y, z), "cablesFCopperSouth")) {
            direction = Direction.SOUTH;
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
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
            } else if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage"))) && !world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") + send_rate, (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "EnergyStorage")));
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }
         }

         if (!world.m_5776_()) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockEntity _blockEntity = world.m_7702_(_bp);
            BlockState _bs = world.m_8055_(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().m_128347_("energy", send_rate);
            }

            if (world instanceof Level) {
               Level _level = (Level)world;
               _level.m_7260_(_bp, _bs, _bs, 3);
            }
         }

         BlockEntity _ent = world.m_7702_(BlockPos.m_274561_(x, y, z));
         int _amount = (int)send_rate;
         if (_ent != null) {
            _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> capability.receiveEnergy(_amount, false));
         }
      }

   }
}
