package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class TravellockProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         ListTag temp = new ListTag();
         if (entity instanceof Player) {
            Player _player = (Player)entity;
            _player.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.traveled_dimentions = temp;
               capability.syncPlayerVariables(_player);
            });
         }

         if (entity instanceof Player) {
            Player _player = (Player)entity;
            if (!_player.m_9236_().m_5776_()) {
               _player.m_5661_(Component.m_237113_("All Planets Locked"), false);
            }
         }

      }
   }
}
