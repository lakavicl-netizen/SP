package net.lointain.cosmos.procedures;

import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class DataclientchangeProcedure {
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
               var10000 = _livEnt.m_21206_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
               return;
            }
         }

         if (((CosmosModVariables.PlayerVariables)sourceentity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).shift) {
            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == CosmosModItems.STEEL_PLATE.get()) {
               int var71;
               if (entity instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                  var71 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
               } else {
                  var71 = 0;
               }

               if (var71 != 0) {
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                     _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                  }

                  if ((((<undefinedtype>)(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                        } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                           return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                        } else {
                           return false;
                        }
                     }
                  })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                        } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                           return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                        } else {
                           return false;
                        }
                     }
                  })).checkGamemode(sourceentity)) && sourceentity instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)sourceentity;
                     ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.STEEL_PLATE.get())).m_41777_();
                     ItemStack var85;
                     if (sourceentity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)sourceentity;
                        var85 = _livEnt.m_21205_();
                     } else {
                        var85 = ItemStack.f_41583_;
                     }

                     _setstack.m_41764_(var85.m_41613_() - 1);
                     _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                     if (_entity instanceof Player) {
                        Player _player = (Player)_entity;
                        _player.m_150109_().m_6596_();
                     }
                  }
               }
            } else {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21205_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.TITANIUM_PLATE.get()) {
                  int var70;
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                     var70 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                  } else {
                     var70 = 0;
                  }

                  if (var70 != 1) {
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                     }

                     if ((((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity)) && sourceentity instanceof LivingEntity) {
                        LivingEntity _entity = (LivingEntity)sourceentity;
                        ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.TITANIUM_PLATE.get())).m_41777_();
                        ItemStack var84;
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)sourceentity;
                           var84 = _livEnt.m_21205_();
                        } else {
                           var84 = ItemStack.f_41583_;
                        }

                        _setstack.m_41764_(var84.m_41613_() - 1);
                        _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                        if (_entity instanceof Player) {
                           Player _player = (Player)_entity;
                           _player.m_150109_().m_6596_();
                        }
                     }
                  }
               } else {
                  if (sourceentity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)sourceentity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() == CosmosModItems.NICKLE_PLATE.get()) {
                     int var69;
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                        var69 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                     } else {
                        var69 = 0;
                     }

                     if (var69 != 2) {
                        if (entity instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                           _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                        }

                        if ((((<undefinedtype>)(new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                              } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                                 return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                              } else {
                                 return false;
                              }
                           }
                        })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                              } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                                 return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                              } else {
                                 return false;
                              }
                           }
                        })).checkGamemode(sourceentity)) && sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.NICKLE_PLATE.get())).m_41777_();
                           ItemStack var10001;
                           if (sourceentity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)sourceentity;
                              var10001 = _livEnt.m_21205_();
                           } else {
                              var10001 = ItemStack.f_41583_;
                           }

                           _setstack.m_41764_(var10001.m_41613_() - 1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }
                        }
                     }
                  }
               }
            }

            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == CosmosModItems.STEELENGINE.get()) {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.STEELENGINE.get()) {
                  int var83;
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                     var83 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var83 = 0;
                  }

                  if (var83 != 0) {
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_engine, 0);
                     }

                     if (((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity)) {
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }
                        }

                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                              return;
                           }
                        }

                        return;
                     }
                  }

                  return;
               }
            }

            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == CosmosModItems.T_2_THRUSTER.get()) {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.T_2_THRUSTER.get()) {
                  int var82;
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                     var82 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var82 = 0;
                  }

                  if (var82 != 1) {
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_engine, 1);
                     }

                     if (((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity)) {
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }
                        }

                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                              return;
                           }
                        }

                        return;
                     }
                  }

                  return;
               }
            }

            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == CosmosModItems.T_3_THRUSTER.get()) {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.T_3_THRUSTER.get()) {
                  int var81;
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                     var81 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var81 = 0;
                  }

                  if (var81 != 2) {
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_engine, 2);
                     }

                     if (((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity)) {
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }
                        }

                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                              return;
                           }
                        }

                        return;
                     }
                  }

                  return;
               }
            }

            if (sourceentity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)sourceentity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() == CosmosModItems.T_4_THRUSTER.get()) {
               if (sourceentity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)sourceentity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.T_4_THRUSTER.get()) {
                  int var80;
                  if (entity instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entity;
                     var80 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var80 = 0;
                  }

                  if (var80 != 3) {
                     if (entity instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntSetI = (RocketSeatEntity)entity;
                        _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_engine, 3);
                     }

                     if (((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity) || ((<undefinedtype>)(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                           } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                              return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                           } else {
                              return false;
                           }
                        }
                     })).checkGamemode(sourceentity)) {
                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }
                        }

                        if (sourceentity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)sourceentity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
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
