package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class GuiopenlogicProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof Player) {
            AbstractContainerMenu containerMenu = ((Player)entity).f_36096_;
            Minecraft minecraft = Minecraft.m_91087_();
            if (minecraft.f_91080_ == null) {
               boolean _setval = false;
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.gui_open = _setval;
                  capability.syncPlayerVariables(entity);
               });
            } else {
               boolean _setval = true;
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.gui_open = _setval;
                  capability.syncPlayerVariables(entity);
               });
            }
         }

      }
   }
}
