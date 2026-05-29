package net.lointain.cosmos.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SimulationsEventProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         CollisionDetectorProcedure.execute(world, x, y, z, entity);
         AtmosphericCollisionDetectorProcedure.execute(world, entity);
      }
   }
}
