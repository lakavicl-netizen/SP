package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;

public class CustomCtrlOnKeyPressedProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).gui_open) {
            boolean _setval = true;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.ctrl = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
