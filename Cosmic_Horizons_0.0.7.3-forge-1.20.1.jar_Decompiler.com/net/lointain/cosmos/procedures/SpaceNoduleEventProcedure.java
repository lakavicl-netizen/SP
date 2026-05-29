package net.lointain.cosmos.procedures;

import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SpaceNoduleEventProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         boolean dimention_check = false;
         if (entity.m_20202_() instanceof RocketSeatEntity) {
            if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
               Tag var4 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
               String var10000;
               if (var4 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var4;
                  var10000 = _stringTag.m_7916_();
               } else {
                  var10000 = "";
               }

               if (var10000.equals("space")) {
                  dimention_check = true;
               } else {
                  dimention_check = false;
               }
            } else {
               dimention_check = false;
            }

            YawcontrolProcedure.execute(entity, dimention_check);
            PitchsideProcedure.execute(entity, dimention_check);
            ShipMovementProcedure.execute(entity, dimention_check);
            ProvideThrustProcedure.execute(world, entity, dimention_check);
            ProvidedropcatchthrustProcedure.execute(world, entity, dimention_check);
         }

      }
   }
}
