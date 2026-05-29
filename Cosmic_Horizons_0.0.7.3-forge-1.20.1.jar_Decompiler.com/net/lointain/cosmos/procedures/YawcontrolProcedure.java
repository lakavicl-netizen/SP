package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public class YawcontrolProcedure {
   public static void execute(Entity entity, boolean dimension_check) {
      if (entity != null) {
         String texts = "";
         boolean dimention_check = false;
         new JsonObject();
         new JsonObject();
         if (!dimension_check) {
            if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i) {
               Entity var19 = entity.m_20202_();
               if (var19 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntSetI = (RocketSeatEntity)var19;
                  _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_yaw, 0);
               }
            } else {
               Entity var8 = entity.m_20202_();
               int var10000;
               if (var8 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var8;
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

                  if (var10000 > 0) {
                     Entity var10 = entity.m_20202_();
                     if (var10 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)var10;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_yaw, -90);
                     }
                  }

                  _datEntSetI = entity.m_20202_();
                  if (_datEntSetI instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                     var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                  } else {
                     var10000 = 0;
                  }

                  if (var10000 < 0) {
                     Entity var30 = entity.m_20202_();
                     if (var30 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)var30;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_yaw, -90);
                     }
                  }
               }
            }

            Entity _datEntI = entity.m_20202_();
            int var65;
            if (_datEntI instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntI;
               var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
            } else {
               var65 = 0;
            }

            if (var65 != 0) {
               _datEntI = entity.m_20202_();
               if (_datEntI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntI;
                  var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var65 = 0;
               }

               if (var65 != 0) {
                  Entity _datEntSetI = entity.m_20202_();
                  if (_datEntSetI instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                     var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                  } else {
                     var65 = 0;
                  }

                  if (var65 > 0) {
                     _datEntSetI = entity.m_20202_();
                     if (_datEntSetI instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                        var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var65 = 0;
                     }

                     if (var65 < 0) {
                        Entity var14 = entity.m_20202_();
                        if (var14 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)var14;
                           SynchedEntityData var69 = _datEntSetI.m_20088_();
                           EntityDataAccessor var10001 = RocketSeatEntity.DATA_yaw;
                           var14 = entity.m_20202_();
                           int var10002;
                           if (var14 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var14;
                              var10002 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                           } else {
                              var10002 = 0;
                           }

                           var10002 = var10002 * 3 * 1;
                           var14 = entity.m_20202_();
                           int var10003;
                           if (var14 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var14;
                              var10003 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                           } else {
                              var10003 = 0;
                           }

                           var69.m_135381_(var10001, (var10002 + var10003 * -3) / 2);
                        }
                     }
                  }

                  _datEntSetI = entity.m_20202_();
                  if (_datEntSetI instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                     var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                  } else {
                     var65 = 0;
                  }

                  if (var65 < 0) {
                     _datEntSetI = entity.m_20202_();
                     if (_datEntSetI instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                        var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var65 = 0;
                     }

                     if (var65 > 0) {
                        Entity var54 = entity.m_20202_();
                        if (var54 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)var54;
                           SynchedEntityData var72 = _datEntSetI.m_20088_();
                           EntityDataAccessor var79 = RocketSeatEntity.DATA_yaw;
                           var54 = entity.m_20202_();
                           int var83;
                           if (var54 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var54;
                              var83 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                           } else {
                              var83 = 0;
                           }

                           var83 = var83 * 3 * -1;
                           var54 = entity.m_20202_();
                           int var89;
                           if (var54 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var54;
                              var89 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                           } else {
                              var89 = 0;
                           }

                           var72.m_135381_(var79, (var83 + var89 * 3) / 2);
                        }
                     }
                  }

                  _datEntSetI = entity.m_20202_();
                  if (_datEntSetI instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                     var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                  } else {
                     var65 = 0;
                  }

                  if (var65 > 0) {
                     _datEntSetI = entity.m_20202_();
                     if (_datEntSetI instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                        var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var65 = 0;
                     }

                     if (var65 > 0) {
                        Entity var57 = entity.m_20202_();
                        if (var57 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)var57;
                           SynchedEntityData var75 = _datEntSetI.m_20088_();
                           EntityDataAccessor var80 = RocketSeatEntity.DATA_yaw;
                           var57 = entity.m_20202_();
                           int var85;
                           if (var57 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var57;
                              var85 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                           } else {
                              var85 = 0;
                           }

                           var85 *= -3;
                           var57 = entity.m_20202_();
                           int var90;
                           if (var57 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var57;
                              var90 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                           } else {
                              var90 = 0;
                           }

                           var75.m_135381_(var80, (var85 + var90 * -3) / 2);
                        }
                     }
                  }

                  _datEntSetI = entity.m_20202_();
                  if (_datEntSetI instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                     var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                  } else {
                     var65 = 0;
                  }

                  if (var65 < 0) {
                     _datEntSetI = entity.m_20202_();
                     if (_datEntSetI instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                        var65 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var65 = 0;
                     }

                     if (var65 < 0) {
                        Entity var60 = entity.m_20202_();
                        if (var60 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)var60;
                           SynchedEntityData var78 = _datEntSetI.m_20088_();
                           EntityDataAccessor var81 = RocketSeatEntity.DATA_yaw;
                           var60 = entity.m_20202_();
                           int var87;
                           if (var60 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var60;
                              var87 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                           } else {
                              var87 = 0;
                           }

                           var87 = var87 * 3 * 1;
                           var60 = entity.m_20202_();
                           int var91;
                           if (var60 instanceof RocketSeatEntity) {
                              RocketSeatEntity _datEntI = (RocketSeatEntity)var60;
                              var91 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                           } else {
                              var91 = 0;
                           }

                           var78.m_135381_(var81, (var87 + var91 * 3) / 2);
                        }
                     }
                  }
               }
            }
         }

      }
   }
}
