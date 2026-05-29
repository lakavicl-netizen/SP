package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.world.level.LevelAccessor;

public class Syssel1DProcedure {
   public static boolean execute(LevelAccessor world) {
      return CosmosModVariables.WorldVariables.get(world).projection_list.size() > 0;
   }
}
