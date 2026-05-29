package net.lointain.cosmos;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;

public class ThrownExperienceBottleTickEvent extends Event {
   private Entity entity;

   public ThrownExperienceBottleTickEvent(Entity entity) {
      this.entity = entity;
   }

   public Entity getEntity() {
      return this.entity;
   }
}
