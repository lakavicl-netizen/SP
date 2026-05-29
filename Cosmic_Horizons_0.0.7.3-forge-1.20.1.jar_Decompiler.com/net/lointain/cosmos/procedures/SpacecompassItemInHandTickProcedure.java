package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class SpacecompassItemInHandTickProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         double _setval = itemstack.m_41784_().m_128459_("X");
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.compass_X = _setval;
            capability.syncPlayerVariables(entity);
         });
         _setval = itemstack.m_41784_().m_128459_("Y");
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.compass_Y = _setval;
            capability.syncPlayerVariables(entity);
         });
         _setval = itemstack.m_41784_().m_128459_("Z");
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.compass_Z = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
