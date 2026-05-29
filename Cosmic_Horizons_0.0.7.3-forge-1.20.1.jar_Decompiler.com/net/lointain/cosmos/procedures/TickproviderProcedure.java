package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class TickproviderProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof Player) {
            Player _plr0 = (Player)entity;
            if (_plr0.f_36096_ instanceof PlanetSelectorMenu) {
               double _setval = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).ticks + (double)1.0F;
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.ticks = _setval;
                  capability.syncPlayerVariables(entity);
               });
               return;
            }
         }

         double _setval = (double)0.0F;
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.ticks = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
