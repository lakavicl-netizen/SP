package net.lointain.cosmos.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class GuiEventProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         TickproviderProcedure.execute(entity);
         GuiDataResetProcedure.execute(entity);
         TicktimerProcedure.execute(entity);
         TravelCheckProcedure.execute(entity);
         TempplanetselopenProcedure.execute(world, entity);
      }
   }
}
