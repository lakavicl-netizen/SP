package net.lointain.cosmos.procedures;

import net.minecraft.world.level.LevelAccessor;

public class CableupdatetickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.m_5776_()) {
         CablemodleupdatermaainProcedure.execute(world, x, y, z);
         TransmissionBlockTickProcedure.execute(world, x, y, z);
      }

   }
}
