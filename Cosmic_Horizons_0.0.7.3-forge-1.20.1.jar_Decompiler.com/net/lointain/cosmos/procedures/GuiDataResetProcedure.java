package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.lointain.cosmos.world.inventory.LandingSelectorMenu;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.lointain.cosmos.world.inventory.ProjectionSelectorMenu;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class GuiDataResetProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof Player) {
            Player _plr0 = (Player)entity;
            if (_plr0.f_36096_ instanceof PlanetSelectorMenu) {
               return;
            }
         }

         if (entity instanceof Player) {
            Player _plr1 = (Player)entity;
            if (_plr1.f_36096_ instanceof LandingSelectorMenu) {
               return;
            }
         }

         if (entity instanceof Player) {
            Player _plr2 = (Player)entity;
            if (_plr2.f_36096_ instanceof ProjectionSelectorMenu) {
               return;
            }
         }

         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter != (double)0.0F || !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).search_bar.equals("") || ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).planet_page_iter != (double)0.0F || ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index != (double)0.0F || ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).planet_page_sel_index != (double)0.0F) {
            double _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.planet_page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_sel_index = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.planet_page_sel_index = _setval;
               capability.syncPlayerVariables(entity);
            });
            String _setval = "";
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.search_bar = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
