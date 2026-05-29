package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class TicktimerProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof Player) {
            Player _plr0 = (Player)entity;
            if (_plr0.f_36096_ instanceof PlanetSelectorMenu) {
               if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).current_tick <= (double)1.0F) {
                  double _setval = Math.min((double)1.0F, Math.max(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).current_tick + 0.02, (double)0.0F));
                  entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                     capability.current_tick = _setval;
                     capability.syncPlayerVariables(entity);
                  });
               }

               return;
            }
         }

         double _setval = (double)0.0F;
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.current_tick = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
