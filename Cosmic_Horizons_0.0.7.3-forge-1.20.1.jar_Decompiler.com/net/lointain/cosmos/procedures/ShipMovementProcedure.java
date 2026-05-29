package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public class ShipMovementProcedure {
   public static void execute(Entity entity, boolean dimension_check) {
      if (entity != null) {
         String texts = "";
         new JsonObject();
         new JsonObject();
         if (dimension_check) {
            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i) {
               Entity var7 = entity.m_20202_();
               int var10000;
               if (var7 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var7;
                  var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var10000 = 0;
               }

               if (var10000 < 30) {
                  Entity var9 = entity.m_20202_();
                  if (var9 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var9;
                     SynchedEntityData var102 = _datEntSetI.m_20088_();
                     EntityDataAccessor var10001 = RocketSeatEntity.DATA_pitch;
                     var9 = entity.m_20202_();
                     int var10002;
                     if (var9 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var9;
                        var10002 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var10002 = 0;
                     }

                     var102.m_135381_(var10001, var10002 + 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var103;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var103 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var103 = 0;
               }

               if (var103 < 0) {
                  Entity var72 = entity.m_20202_();
                  if (var72 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var72;
                     SynchedEntityData var104 = _datEntSetI.m_20088_();
                     EntityDataAccessor var133 = RocketSeatEntity.DATA_pitch;
                     var72 = entity.m_20202_();
                     int var148;
                     if (var72 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var72;
                        var148 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var148 = 0;
                     }

                     var104.m_135381_(var133, var148 + 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var105;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var105 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var105 = 0;
               }

               if (var105 > -30) {
                  Entity var74 = entity.m_20202_();
                  if (var74 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var74;
                     SynchedEntityData var106 = _datEntSetI.m_20088_();
                     EntityDataAccessor var134 = RocketSeatEntity.DATA_pitch;
                     var74 = entity.m_20202_();
                     int var149;
                     if (var74 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var74;
                        var149 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var149 = 0;
                     }

                     var106.m_135381_(var134, var149 - 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var107;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var107 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var107 = 0;
               }

               if (var107 > 0) {
                  Entity var76 = entity.m_20202_();
                  if (var76 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var76;
                     SynchedEntityData var108 = _datEntSetI.m_20088_();
                     EntityDataAccessor var135 = RocketSeatEntity.DATA_pitch;
                     var76 = entity.m_20202_();
                     int var150;
                     if (var76 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var76;
                        var150 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var150 = 0;
                     }

                     var108.m_135381_(var135, var150 - 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i) {
               Entity _datEntSetI = entity.m_20202_();
               int var109;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var109 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var109 = 0;
               }

               if (var109 < 20) {
                  Entity var78 = entity.m_20202_();
                  if (var78 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var78;
                     SynchedEntityData var110 = _datEntSetI.m_20088_();
                     EntityDataAccessor var136 = RocketSeatEntity.DATA_roll;
                     var78 = entity.m_20202_();
                     int var151;
                     if (var78 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var78;
                        var151 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var151 = 0;
                     }

                     var110.m_135381_(var136, var151 + 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var111;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var111 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var111 = 0;
               }

               if (var111 < 0) {
                  Entity var80 = entity.m_20202_();
                  if (var80 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var80;
                     SynchedEntityData var112 = _datEntSetI.m_20088_();
                     EntityDataAccessor var137 = RocketSeatEntity.DATA_roll;
                     var80 = entity.m_20202_();
                     int var152;
                     if (var80 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var80;
                        var152 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var152 = 0;
                     }

                     var112.m_135381_(var137, var152 + 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var113;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var113 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var113 = 0;
               }

               if (var113 > -20) {
                  Entity var82 = entity.m_20202_();
                  if (var82 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var82;
                     SynchedEntityData var114 = _datEntSetI.m_20088_();
                     EntityDataAccessor var138 = RocketSeatEntity.DATA_roll;
                     var82 = entity.m_20202_();
                     int var153;
                     if (var82 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var82;
                        var153 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var153 = 0;
                     }

                     var114.m_135381_(var138, var153 - 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity _datEntSetI = entity.m_20202_();
               int var115;
               if (_datEntSetI instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_datEntSetI;
                  var115 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var115 = 0;
               }

               if (var115 > 0) {
                  Entity var84 = entity.m_20202_();
                  if (var84 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var84;
                     SynchedEntityData var116 = _datEntSetI.m_20088_();
                     EntityDataAccessor var139 = RocketSeatEntity.DATA_roll;
                     var84 = entity.m_20202_();
                     int var154;
                     if (var84 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var84;
                        var154 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var154 = 0;
                     }

                     var116.m_135381_(var139, var154 - 1);
                  }
               }
            }
         } else {
            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i) {
               Entity var40 = entity.m_20202_();
               int var117;
               if (var40 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var40;
                  var117 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var117 = 0;
               }

               if (var117 < 15) {
                  Entity var86 = entity.m_20202_();
                  if (var86 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var86;
                     SynchedEntityData var118 = _datEntSetI.m_20088_();
                     EntityDataAccessor var140 = RocketSeatEntity.DATA_pitch;
                     var86 = entity.m_20202_();
                     int var155;
                     if (var86 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var86;
                        var155 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var155 = 0;
                     }

                     var118.m_135381_(var140, var155 + 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity var42 = entity.m_20202_();
               int var119;
               if (var42 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var42;
                  var119 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var119 = 0;
               }

               if (var119 < 0) {
                  Entity var88 = entity.m_20202_();
                  if (var88 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var88;
                     SynchedEntityData var120 = _datEntSetI.m_20088_();
                     EntityDataAccessor var141 = RocketSeatEntity.DATA_pitch;
                     var88 = entity.m_20202_();
                     int var156;
                     if (var88 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var88;
                        var156 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var156 = 0;
                     }

                     var120.m_135381_(var141, var156 + 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity var44 = entity.m_20202_();
               int var121;
               if (var44 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var44;
                  var121 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var121 = 0;
               }

               if (var121 > -15) {
                  Entity var90 = entity.m_20202_();
                  if (var90 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var90;
                     SynchedEntityData var122 = _datEntSetI.m_20088_();
                     EntityDataAccessor var142 = RocketSeatEntity.DATA_pitch;
                     var90 = entity.m_20202_();
                     int var157;
                     if (var90 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var90;
                        var157 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var157 = 0;
                     }

                     var122.m_135381_(var142, var157 - 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d) {
               Entity var46 = entity.m_20202_();
               int var123;
               if (var46 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var46;
                  var123 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var123 = 0;
               }

               if (var123 > 0) {
                  Entity var92 = entity.m_20202_();
                  if (var92 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var92;
                     SynchedEntityData var124 = _datEntSetI.m_20088_();
                     EntityDataAccessor var143 = RocketSeatEntity.DATA_pitch;
                     var92 = entity.m_20202_();
                     int var158;
                     if (var92 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var92;
                        var158 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                     } else {
                        var158 = 0;
                     }

                     var124.m_135381_(var143, var158 - 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i) {
               Entity var48 = entity.m_20202_();
               int var125;
               if (var48 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var48;
                  var125 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var125 = 0;
               }

               if (var125 < 15) {
                  Entity var94 = entity.m_20202_();
                  if (var94 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var94;
                     SynchedEntityData var126 = _datEntSetI.m_20088_();
                     EntityDataAccessor var144 = RocketSeatEntity.DATA_roll;
                     var94 = entity.m_20202_();
                     int var159;
                     if (var94 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var94;
                        var159 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var159 = 0;
                     }

                     var126.m_135381_(var144, var159 + 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity var50 = entity.m_20202_();
               int var127;
               if (var50 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var50;
                  var127 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var127 = 0;
               }

               if (var127 < 0) {
                  Entity var96 = entity.m_20202_();
                  if (var96 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var96;
                     SynchedEntityData var128 = _datEntSetI.m_20088_();
                     EntityDataAccessor var145 = RocketSeatEntity.DATA_roll;
                     var96 = entity.m_20202_();
                     int var160;
                     if (var96 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var96;
                        var160 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var160 = 0;
                     }

                     var128.m_135381_(var145, var160 + 1);
                  }
               }
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity var52 = entity.m_20202_();
               int var129;
               if (var52 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var52;
                  var129 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var129 = 0;
               }

               if (var129 > -15) {
                  Entity var98 = entity.m_20202_();
                  if (var98 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var98;
                     SynchedEntityData var130 = _datEntSetI.m_20088_();
                     EntityDataAccessor var146 = RocketSeatEntity.DATA_roll;
                     var98 = entity.m_20202_();
                     int var161;
                     if (var98 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var98;
                        var161 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var161 = 0;
                     }

                     var130.m_135381_(var146, var161 - 1);
                  }
               }
            } else if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d) {
               Entity var54 = entity.m_20202_();
               int var131;
               if (var54 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var54;
                  var131 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var131 = 0;
               }

               if (var131 > 0) {
                  Entity var100 = entity.m_20202_();
                  if (var100 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)var100;
                     SynchedEntityData var132 = _datEntSetI.m_20088_();
                     EntityDataAccessor var147 = RocketSeatEntity.DATA_roll;
                     var100 = entity.m_20202_();
                     int var162;
                     if (var100 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var100;
                        var162 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                     } else {
                        var162 = 0;
                     }

                     var132.m_135381_(var147, var162 - 1);
                  }
               }
            }
         }

      }
   }
}
