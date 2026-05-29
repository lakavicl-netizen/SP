package net.lointain.cosmos;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;

public class BoatTickEvent extends Event {
   private Entity entity;

   public BoatTickEvent(Entity entity) {
      this.entity = entity;
   }

   public Entity getEntity() {
      return this.entity;
   }
}
