package net.lointain.cosmos.procedures;

import net.minecraft.world.entity.Entity;

public class NoduleRightClickEventsProcedure {
   public static void execute(Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         ShipitemifyProcedure.execute(entity, sourceentity);
         DataclientchangeProcedure.execute(entity, sourceentity);
      }
   }
}
