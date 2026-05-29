package net.lointain.cosmos.procedures;

import net.minecraft.world.level.LevelAccessor;

public class SteelBatteryOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      String nbtSouthTagName = "";
      String nbtWestTagName = "";
      String nbtNorthTagName = "";
      String nbtEastTagName = "";
      String nbtUpTagName = "";
      double send = (double)0.0F;
      double capacity = (double)0.0F;
      double set_cap = (double)0.0F;
      double maxSend = (double)0.0F;
      StorageBlockTickProcedure.execute(world, x, y, z);
   }
}
