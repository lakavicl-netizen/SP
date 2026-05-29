package net.lointain.cosmos.procedures;

import java.util.concurrent.atomic.AtomicInteger;
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

public class ExtractProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Direction direction) {
      if (direction != null) {
         Direction dir = Direction.NORTH;
         double recieved = (double)0.0F;
         if ((world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:transmission"))) || world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage")))) && ((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "energy") != ((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "EnergyStorage")) {
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
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "energy") + Mth.m_14008_(((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy"), (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "ReceiveRate")), (double)0.0F, ((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "EnergyStorage")));
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            BlockEntity _ent = world.m_7702_(BlockPos.m_274561_(x, y, z));
            int _amount = (int)((double)((<undefinedtype>)(new Object() {
               public int getEnergyStored(LevelAccessor level, BlockPos pos) {
                  AtomicInteger _retval = new AtomicInteger(0);
                  BlockEntity _ent = level.m_7702_(pos);
                  if (_ent != null) {
                     _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> _retval.set(capability.getEnergyStored()));
                  }

                  return _retval.get();
               }
            })).getEnergyStored(world, BlockPos.m_274561_(x, y, z)) + Mth.m_14008_(((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy"), (double)0.0F, ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "ReceiveRate")));
            if (_ent != null) {
               _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> capability.receiveEnergy(_amount, false));
            }

            recieved = Mth.m_14008_(((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy"), (double)0.0F, ((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "ReceiveRate"));
            if (world.m_8055_(BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())).m_204336_(BlockTags.create(new ResourceLocation("cosmos:storage")))) {
               if (!world.m_5776_()) {
                  BlockPos _bp = BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_());
                  BlockEntity _blockEntity = world.m_7702_(_bp);
                  BlockState _bs = world.m_8055_(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().m_128347_("energy", Mth.m_14008_(((<undefinedtype>)(new Object() {
                        public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.m_7702_(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                        }
                     })).getValue(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_()), "energy") - recieved, (double)0.0F, ((<undefinedtype>)(new Object() {
                        public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.m_7702_(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                        }
                     })).getValue(world, BlockPos.m_274561_(x, y, z), "EnergyStorage")));
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     _level.m_7260_(_bp, _bs, _bs, 3);
                  }
               }

               _ent = world.m_7702_(BlockPos.m_274561_(x, y, z));
               _amount = (int)((double)((<undefinedtype>)(new Object() {
                  public int getEnergyStored(LevelAccessor level, BlockPos pos) {
                     AtomicInteger _retval = new AtomicInteger(0);
                     BlockEntity _ent = level.m_7702_(pos);
                     if (_ent != null) {
                        _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> _retval.set(capability.getEnergyStored()));
                     }

                     return _retval.get();
                  }
               })).getEnergyStored(world, BlockPos.m_274561_(x + (double)direction.m_122429_(), y + (double)direction.m_122430_(), z + (double)direction.m_122431_())) - recieved);
               if (_ent != null) {
                  _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> capability.receiveEnergy(_amount, false));
               }
            }
         }

      }
   }
}
