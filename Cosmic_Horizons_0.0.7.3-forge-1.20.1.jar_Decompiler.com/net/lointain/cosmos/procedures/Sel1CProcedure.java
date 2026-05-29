package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;

public class Sel1CProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if ((double)0.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter != ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index) {
            double _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.planet_page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.planet_page_sel_index = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_sel_index = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.current_tick = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
