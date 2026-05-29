package net.lointain.cosmos.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class CloseGUIProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof Player) {
            Player _player = (Player)entity;
            _player.m_6915_();
         }

      }
   }
}
