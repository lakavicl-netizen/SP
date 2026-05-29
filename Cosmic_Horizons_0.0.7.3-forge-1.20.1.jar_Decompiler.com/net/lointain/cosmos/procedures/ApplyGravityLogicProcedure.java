package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.world.level.LevelAccessor;

public class ApplyGravityLogicProcedure {
   public static boolean execute(LevelAccessor world, String dimension) {
      if (dimension == null) {
         return false;
      } else {
         return CosmosModVariables.WorldVariables.get(world).gravity_data.m_128441_(dimension) && CosmosModVariables.WorldVariables.get(world).friction_data.m_128441_(dimension);
      }
   }
}
