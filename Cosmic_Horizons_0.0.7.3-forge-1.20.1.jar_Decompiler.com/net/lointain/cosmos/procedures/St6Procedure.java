package net.lointain.cosmos.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class St6Procedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z) {
      return ((<undefinedtype>)(new Object() {
         public int getEnergyStored(LevelAccessor level, BlockPos pos) {
            AtomicInteger _retval = new AtomicInteger(0);
            BlockEntity _ent = level.m_7702_(pos);
            if (_ent != null) {
               _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> _retval.set(capability.getEnergyStored()));
            }

            return _retval.get();
         }
      })).getEnergyStored(world, BlockPos.m_274561_(x, y, z)) > 1000;
   }
}
