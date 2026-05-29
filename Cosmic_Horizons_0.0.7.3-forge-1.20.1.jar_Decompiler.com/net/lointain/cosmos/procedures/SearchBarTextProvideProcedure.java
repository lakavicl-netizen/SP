package net.lointain.cosmos.procedures;

import java.util.HashMap;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;

public class SearchBarTextProvideProcedure {
   public static void execute(Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         if (!(guistate.containsKey("text:reg") ? ((EditBox)guistate.get("text:reg")).m_94155_() : "").equals("")) {
            String _setval = guistate.containsKey("text:reg") ? ((EditBox)guistate.get("text:reg")).m_94155_() : "";
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.search_bar = _setval;
               capability.syncPlayerVariables(entity);
            });
            double _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
         } else {
            String _setval = "";
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.search_bar = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
