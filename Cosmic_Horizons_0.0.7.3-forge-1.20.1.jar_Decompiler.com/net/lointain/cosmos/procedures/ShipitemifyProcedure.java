package net.lointain.cosmos.procedures;

import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class ShipitemifyProcedure {
   public static void execute(Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         ItemStack var10000;
         if (sourceentity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)sourceentity;
            var10000 = _livEnt.m_21205_();
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.NICKLE_PLATE.get()) {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21205_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.STEEL_PLATE.get()) {
                  if (sourceentity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)sourceentity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() != CosmosModItems.TITANIUM_PLATE.get()) {
                     if (sourceentity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)sourceentity;
                        var10000 = _livEnt.m_21205_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() != CosmosModItems.STEELENGINE.get()) {
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)sourceentity;
                           var10000 = _livEnt.m_21205_();
                        } else {
                           var10000 = ItemStack.f_41583_;
                        }

                        if (var10000.m_41720_() != CosmosModItems.T_2_THRUSTER.get()) {
                           if (sourceentity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)sourceentity;
                              var10000 = _livEnt.m_21205_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() != CosmosModItems.T_3_THRUSTER.get()) {
                              if (sourceentity instanceof LivingEntity) {
                                 LivingEntity _livEnt = (LivingEntity)sourceentity;
                                 var10000 = _livEnt.m_21205_();
                              } else {
                                 var10000 = ItemStack.f_41583_;
                              }

                              if (var10000.m_41720_() != CosmosModItems.T_4_THRUSTER.get() && ((CosmosModVariables.PlayerVariables)sourceentity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).shift) {
                                 int var35;
                                 if (entity instanceof RocketSeatEntity) {
                                    RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                    var35 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                                 } else {
                                    var35 = 0;
                                 }

                                 if (var35 == 0) {
                                    if (sourceentity instanceof LivingEntity) {
                                       LivingEntity _entity = (LivingEntity)sourceentity;
                                       ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.STEEL_SPACE_NODULE.get())).m_41777_();
                                       _setstack.m_41764_(1);
                                       _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                       if (_entity instanceof Player) {
                                          Player _player = (Player)_entity;
                                          _player.m_150109_().m_6596_();
                                       }
                                    }

                                    ItemStack var36;
                                    if (sourceentity instanceof LivingEntity) {
                                       LivingEntity _livEnt = (LivingEntity)sourceentity;
                                       var36 = _livEnt.m_21205_();
                                    } else {
                                       var36 = ItemStack.f_41583_;
                                    }

                                    CompoundTag var37 = var36.m_41784_();
                                    double var10002;
                                    if (entity instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                       var10002 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                                    } else {
                                       var10002 = (double)0.0F;
                                    }

                                    var37.m_128347_("engine", var10002);
                                 } else {
                                    if (entity instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                       var35 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                                    } else {
                                       var35 = 0;
                                    }

                                    if (var35 == 1) {
                                       if (sourceentity instanceof LivingEntity) {
                                          LivingEntity _entity = (LivingEntity)sourceentity;
                                          ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.TITANIUM_SPACE_NODULE.get())).m_41777_();
                                          _setstack.m_41764_(1);
                                          _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                          if (_entity instanceof Player) {
                                             Player _player = (Player)_entity;
                                             _player.m_150109_().m_6596_();
                                          }
                                       }

                                       ItemStack var39;
                                       if (sourceentity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)sourceentity;
                                          var39 = _livEnt.m_21205_();
                                       } else {
                                          var39 = ItemStack.f_41583_;
                                       }

                                       CompoundTag var40 = var39.m_41784_();
                                       double var44;
                                       if (entity instanceof RocketSeatEntity) {
                                          RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                          var44 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                                       } else {
                                          var44 = (double)0.0F;
                                       }

                                       var40.m_128347_("engine", var44);
                                    } else {
                                       if (entity instanceof RocketSeatEntity) {
                                          RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                          var35 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                                       } else {
                                          var35 = 0;
                                       }

                                       if (var35 == 2) {
                                          if (sourceentity instanceof LivingEntity) {
                                             LivingEntity _entity = (LivingEntity)sourceentity;
                                             ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.NICKEL_SPACE_NODULE.get())).m_41777_();
                                             _setstack.m_41764_(1);
                                             _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                             if (_entity instanceof Player) {
                                                Player _player = (Player)_entity;
                                                _player.m_150109_().m_6596_();
                                             }
                                          }

                                          ItemStack var42;
                                          if (sourceentity instanceof LivingEntity) {
                                             LivingEntity _livEnt = (LivingEntity)sourceentity;
                                             var42 = _livEnt.m_21205_();
                                          } else {
                                             var42 = ItemStack.f_41583_;
                                          }

                                          CompoundTag var43 = var42.m_41784_();
                                          double var45;
                                          if (entity instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                                             var45 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                                          } else {
                                             var45 = (double)0.0F;
                                          }

                                          var43.m_128347_("engine", var45);
                                       }
                                    }
                                 }

                                 if (!entity.m_9236_().m_5776_()) {
                                    entity.m_146870_();
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }
}
