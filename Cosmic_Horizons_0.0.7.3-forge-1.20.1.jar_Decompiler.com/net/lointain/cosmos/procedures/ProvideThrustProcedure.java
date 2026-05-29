package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class ProvideThrustProcedure {
   public static void execute(LevelAccessor world, Entity entity, boolean dimension_check) {
      if (entity != null) {
         String texts = "";
         boolean dimention_check = false;
         new JsonObject();
         new JsonObject();
         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust) {
            if (dimension_check) {
               Entity _ent = entity.m_20202_();
               Entity var10 = entity.m_20202_();
               int var10001;
               if (var10 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var10;
                  var10001 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
               } else {
                  var10001 = 0;
               }

               _ent.m_146922_((float)(var10001 / 1));
               var10 = entity.m_20202_();
               if (var10 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var10;
                  var10001 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var10001 = 0;
               }

               _ent.m_146926_((float)(var10001 / 1));
               _ent.m_5618_(_ent.m_146908_());
               _ent.m_5616_(_ent.m_146908_());
               _ent.f_19859_ = _ent.m_146908_();
               _ent.f_19860_ = _ent.m_146909_();
               if (_ent instanceof LivingEntity) {
                  LivingEntity _entity = (LivingEntity)_ent;
                  _entity.f_20884_ = _entity.m_146908_();
                  _entity.f_20886_ = _entity.m_146908_();
               }

               Entity var12 = entity.m_20202_();
               int var10000;
               if (var12 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var12;
                  var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
               } else {
                  var10000 = 0;
               }

               if (var10000 == 0) {
                  T1thrustspaceProcedure.execute(world, entity);
               } else {
                  var12 = entity.m_20202_();
                  if (var12 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var12;
                     var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var10000 = 0;
                  }

                  if (var10000 == 1) {
                     T2thrustspaceProcedure.execute(world, entity);
                  } else {
                     var12 = entity.m_20202_();
                     if (var12 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var12;
                        var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                     } else {
                        var10000 = 0;
                     }

                     if (var10000 == 2) {
                        T3thrustspaceProcedure.execute(world, entity);
                     } else {
                        var12 = entity.m_20202_();
                        if (var12 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntI = (RocketSeatEntity)var12;
                           var10000 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                        } else {
                           var10000 = 0;
                        }

                        if (var10000 == 3) {
                           T4thrustspaceProcedure.execute(world, entity);
                        }
                     }
                  }
               }
            } else {
               Entity _ent = entity.m_20202_();
               int var51;
               if (_ent instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_ent;
                  var51 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
               } else {
                  var51 = 0;
               }

               var51 = Math.abs(var51 * -1);
               _ent = entity.m_20202_();
               int var58;
               if (_ent instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)_ent;
                  var58 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
               } else {
                  var58 = 0;
               }

               if (var51 < Math.abs(var58 * -1)) {
                  _ent = entity.m_20202_();
                  Entity var40 = entity.m_20202_();
                  if (var40 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var40;
                     var58 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
                  } else {
                     var58 = 0;
                  }

                  _ent.m_146922_((float)var58);
                  var40 = entity.m_20202_();
                  int var10002;
                  if (var40 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var40;
                     var10002 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                  } else {
                     var10002 = 0;
                  }

                  _ent.m_146926_((float)(270 + var10002 * -1));
                  _ent.m_5618_(_ent.m_146908_());
                  _ent.m_5616_(_ent.m_146908_());
                  _ent.f_19859_ = _ent.m_146908_();
                  _ent.f_19860_ = _ent.m_146909_();
                  if (_ent instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)_ent;
                     _entity.f_20884_ = _entity.m_146908_();
                     _entity.f_20886_ = _entity.m_146908_();
                  }
               } else {
                  _ent = entity.m_20202_();
                  Entity var42 = entity.m_20202_();
                  if (var42 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var42;
                     var58 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
                  } else {
                     var58 = 0;
                  }

                  _ent.m_146922_((float)var58);
                  var42 = entity.m_20202_();
                  int var61;
                  if (var42 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var42;
                     var61 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                  } else {
                     var61 = 0;
                  }

                  _ent.m_146926_((float)(270 + var61 * -1));
                  _ent.m_5618_(_ent.m_146908_());
                  _ent.m_5616_(_ent.m_146908_());
                  _ent.f_19859_ = _ent.m_146908_();
                  _ent.f_19860_ = _ent.m_146909_();
                  if (_ent instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)_ent;
                     _entity.f_20884_ = _entity.m_146908_();
                     _entity.f_20886_ = _entity.m_146908_();
                  }
               }

               entity.m_20202_().m_20256_(new Vec3(Mth.m_14139_((double)1.0F, entity.m_20202_().m_20184_().m_7096_(), (entity.m_20202_().m_20184_().m_7096_() + (double)1.0F * entity.m_20202_().m_20154_().f_82479_ * (Math.abs(entity.m_20202_().m_20184_().m_7096_() / (double)1000.0F) + 0.7)) * (double)-1.0F), Mth.m_14139_((double)1.0F, entity.m_20202_().m_20184_().m_7098_(), entity.m_20202_().m_20184_().m_7098_() + 0.6 * entity.m_20202_().m_20154_().f_82480_ * (Math.abs(entity.m_20202_().m_20184_().m_7098_() / (double)1000.0F) + 0.2)), Mth.m_14139_((double)1.0F, entity.m_20202_().m_20184_().m_7094_(), (entity.m_20202_().m_20184_().m_7094_() + (double)1.0F * entity.m_20202_().m_20154_().f_82481_ * (Math.abs(entity.m_20202_().m_20184_().m_7094_() / (double)1000.0F) + 0.7)) * (double)-1.0F)));
               Entity var44 = entity.m_20202_();
               if (var44 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var44;
                  var51 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
               } else {
                  var51 = 0;
               }

               if (var51 == 0) {
                  T1thrustsurfaceProcedure.execute(world, entity);
               } else {
                  var44 = entity.m_20202_();
                  if (var44 instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)var44;
                     var51 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var51 = 0;
                  }

                  if (var51 == 1) {
                     T2thrustsurfaceProcedure.execute(world, entity);
                  } else {
                     var44 = entity.m_20202_();
                     if (var44 instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)var44;
                        var51 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                     } else {
                        var51 = 0;
                     }

                     if (var51 == 2) {
                        T3thrustsurfaceProcedure.execute(world, entity);
                     } else {
                        var44 = entity.m_20202_();
                        if (var44 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntI = (RocketSeatEntity)var44;
                           var51 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                        } else {
                           var51 = 0;
                        }

                        if (var51 == 3) {
                           T4thrustsurfaceProcedure.execute(world, entity);
                        }
                     }
                  }
               }

               entity.m_20202_().f_19789_ = 0.2F;
            }
         } else {
            Entity _ent = entity.m_20202_();
            _ent.m_146922_(0.0F);
            _ent.m_146926_(270.0F);
            _ent.m_5618_(_ent.m_146908_());
            _ent.m_5616_(_ent.m_146908_());
            _ent.f_19859_ = _ent.m_146908_();
            _ent.f_19860_ = _ent.m_146909_();
            if (_ent instanceof LivingEntity) {
               LivingEntity _entity = (LivingEntity)_ent;
               _entity.f_20884_ = _entity.m_146908_();
               _entity.f_20886_ = _entity.m_146908_();
            }
         }

      }
   }
}
