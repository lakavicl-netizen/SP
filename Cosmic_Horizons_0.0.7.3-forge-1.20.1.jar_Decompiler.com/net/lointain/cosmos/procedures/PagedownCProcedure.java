package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class PagedownCProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (CosmosModVariables.WorldVariables.get(world).projection_list.size() > 2 && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter < (double)(CosmosModVariables.WorldVariables.get(world).projection_list.size() - 2)) {
            double _setval = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter + (double)1.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
