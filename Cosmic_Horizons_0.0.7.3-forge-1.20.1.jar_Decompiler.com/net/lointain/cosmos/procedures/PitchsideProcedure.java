package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public class PitchsideProcedure {
   public static void execute(Entity entity, boolean dimension_check) {
      if (entity != null) {
         String texts = "";
         new JsonObject();
         new JsonObject();
         if (dimension_check && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust) {
            Entity var7 = entity.m_20202_();
            int var10000;
            if (var7 instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)var7;
               var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
            } else {
               var10000 = 0;
            }

            if (var10000 != 0) {
               Entity _datEntSetI = entity.m_20202_();
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var10000 = 0;
               }

               if (var10000 < 0) {
                  Entity var10 = entity.m_20202_();
                  if (var10 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var10;
                     SynchedEntityData var21 = _datEntSetI.m_20088_();
                     EntityDataAccessor var10001 = RocketSeatEntity.DATA_yaw;
                     var10 = entity.m_20202_();
                     int var10002;
                     if (var10 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var10;
                        var10002 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
                     } else {
                        var10002 = 0;
                     }

                     var21.m_135381_(var10001, var10002 - 1);
                  }
               }

               _datEntSetI = entity.m_20202_();
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var10000 = 0;
               }

               if (var10000 > 0) {
                  Entity var18 = entity.m_20202_();
                  if (var18 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var18;
                     SynchedEntityData var23 = _datEntSetI.m_20088_();
                     EntityDataAccessor var24 = RocketSeatEntity.DATA_yaw;
                     var18 = entity.m_20202_();
                     int var25;
                     if (var18 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var18;
                        var25 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
                     } else {
                        var25 = 0;
                     }

                     var23.m_135381_(var24, var25 + 1);
                  }
               }
            }
         }

      }
   }
}
