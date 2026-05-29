package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;

public class LandoriginProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         String _setval = "*0|0~";
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.landing_coords = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
