package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.world.level.LevelAccessor;

public class Sel3DProcedure {
   public static boolean execute(LevelAccessor world) {
      return CosmosModVariables.WorldVariables.get(world).planet_sel_data.size() > 2;
   }
}
