package net.lointain.cosmos.procedures;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModEntities;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ShipspawnProcedure {
   @SubscribeEvent
   public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
      execute(event, event.getLevel(), (double)event.getPos().m_123341_(), (double)event.getPos().m_123342_(), (double)event.getPos().m_123343_(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute((Event)null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (world.m_45573_(BlockPos.m_274561_(x, y, z)) > (double)0.0F) {
            ItemStack var10000;
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.STEEL_SPACE_NODULE.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() != CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21206_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() != CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (entity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)entity;
                           var10000 = _livEnt.m_21205_();
                        } else {
                           var10000 = ItemStack.f_41583_;
                        }

                        if (var10000.m_41720_() != CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10000 = _livEnt.m_21206_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() != CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                              return;
                           }
                        }

                        if (!((<undefinedtype>)(new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                              } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                                 return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                              } else {
                                 return false;
                              }
                           }
                        })).checkGamemode(entity) && !((<undefinedtype>)(new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                              } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                                 return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                              } else {
                                 return false;
                              }
                           }
                        })).checkGamemode(entity)) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10000 = _livEnt.m_21205_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var160 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var173 = RocketSeatEntity.DATA_engine;
                                       ItemStack var184;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var184 = _livEnt.m_21205_();
                                       } else {
                                          var184 = ItemStack.f_41583_;
                                       }

                                       var160.m_135381_(var173, (int)var184.m_41784_().m_128459_("engine"));
                                    }

                                    _serverLevel.m_7967_(entityinstance);
                                 }
                              }

                              if (entity instanceof LivingEntity) {
                                 LivingEntity _entity = (LivingEntity)entity;
                                 ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                 _setstack.m_41764_(1);
                                 _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                 if (_entity instanceof Player) {
                                    Player _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                 }

                                 return;
                              }
                           } else {
                              if (entity instanceof LivingEntity) {
                                 LivingEntity _livEnt = (LivingEntity)entity;
                                 var10000 = _livEnt.m_21205_();
                              } else {
                                 var10000 = ItemStack.f_41583_;
                              }

                              if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var10000 = _livEnt.m_21206_();
                                 } else {
                                    var10000 = ItemStack.f_41583_;
                                 }

                                 if (var10000.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                                    if (world instanceof ServerLevel) {
                                       ServerLevel _serverLevel = (ServerLevel)world;
                                       Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                       if (entityinstance != null) {
                                          entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                          }

                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             SynchedEntityData var163 = _datEntSetI.m_20088_();
                                             EntityDataAccessor var174 = RocketSeatEntity.DATA_engine;
                                             ItemStack var185;
                                             if (entity instanceof LivingEntity) {
                                                LivingEntity _livEnt = (LivingEntity)entity;
                                                var185 = _livEnt.m_21206_();
                                             } else {
                                                var185 = ItemStack.f_41583_;
                                             }

                                             var163.m_135381_(var174, (int)var185.m_41784_().m_128459_("engine"));
                                          }

                                          _serverLevel.m_7967_(entityinstance);
                                       }
                                    }

                                    if (entity instanceof LivingEntity) {
                                       LivingEntity _entity = (LivingEntity)entity;
                                       ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                       _setstack.m_41764_(1);
                                       _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                       if (_entity instanceof Player) {
                                          Player _player = (Player)_entity;
                                          _player.m_150109_().m_6596_();
                                       }

                                       return;
                                    }
                                 }

                                 return;
                              }
                           }

                           return;
                        } else {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10000 = _livEnt.m_21205_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var155 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var171 = RocketSeatEntity.DATA_engine;
                                       ItemStack var182;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var182 = _livEnt.m_21205_();
                                       } else {
                                          var182 = ItemStack.f_41583_;
                                       }

                                       var155.m_135381_(var171, (int)var182.m_41784_().m_128459_("engine"));
                                    }

                                    _serverLevel.m_7967_(entityinstance);
                                 }
                              }

                              if (entity instanceof LivingEntity) {
                                 LivingEntity _entity = (LivingEntity)entity;
                                 ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                 _setstack.m_41764_(1);
                                 _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                                 if (_entity instanceof Player) {
                                    Player _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                 }

                                 return;
                              }
                           } else {
                              if (entity instanceof LivingEntity) {
                                 LivingEntity _livEnt = (LivingEntity)entity;
                                 var10000 = _livEnt.m_21205_();
                              } else {
                                 var10000 = ItemStack.f_41583_;
                              }

                              if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var10000 = _livEnt.m_21206_();
                                 } else {
                                    var10000 = ItemStack.f_41583_;
                                 }

                                 if (var10000.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                                    if (world instanceof ServerLevel) {
                                       ServerLevel _serverLevel = (ServerLevel)world;
                                       Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                       if (entityinstance != null) {
                                          entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                          }

                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             SynchedEntityData var158 = _datEntSetI.m_20088_();
                                             EntityDataAccessor var172 = RocketSeatEntity.DATA_engine;
                                             ItemStack var183;
                                             if (entity instanceof LivingEntity) {
                                                LivingEntity _livEnt = (LivingEntity)entity;
                                                var183 = _livEnt.m_21206_();
                                             } else {
                                                var183 = ItemStack.f_41583_;
                                             }

                                             var158.m_135381_(var172, (int)var183.m_41784_().m_128459_("engine"));
                                          }

                                          _serverLevel.m_7967_(entityinstance);
                                       }
                                    }

                                    if (entity instanceof LivingEntity) {
                                       LivingEntity _entity = (LivingEntity)entity;
                                       ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                       _setstack.m_41764_(1);
                                       _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                       if (_entity instanceof Player) {
                                          Player _player = (Player)_entity;
                                          _player.m_150109_().m_6596_();
                                       }

                                       return;
                                    }
                                 }

                                 return;
                              }
                           }

                           return;
                        }
                     }
                  }

                  if (!((<undefinedtype>)(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                        } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                           return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                        } else {
                           return false;
                        }
                     }
                  })).checkGamemode(entity) && !((<undefinedtype>)(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                        } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                           return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                        } else {
                           return false;
                        }
                     }
                  })).checkGamemode(entity)) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21205_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var148 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var169 = RocketSeatEntity.DATA_engine;
                                 ItemStack var180;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var180 = _livEnt.m_21205_();
                                 } else {
                                    var180 = ItemStack.f_41583_;
                                 }

                                 var148.m_135381_(var169, (int)var180.m_41784_().m_128459_("engine"));
                              }

                              _serverLevel.m_7967_(entityinstance);
                           }
                        }

                        if (entity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)entity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }

                           return;
                        }
                     } else {
                        if (entity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)entity;
                           var10000 = _livEnt.m_21205_();
                        } else {
                           var10000 = ItemStack.f_41583_;
                        }

                        if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10000 = _livEnt.m_21206_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var151 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var170 = RocketSeatEntity.DATA_engine;
                                       ItemStack var181;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var181 = _livEnt.m_21206_();
                                       } else {
                                          var181 = ItemStack.f_41583_;
                                       }

                                       var151.m_135381_(var170, (int)var181.m_41784_().m_128459_("engine"));
                                    }

                                    _serverLevel.m_7967_(entityinstance);
                                 }
                              }

                              if (entity instanceof LivingEntity) {
                                 LivingEntity _entity = (LivingEntity)entity;
                                 ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                 _setstack.m_41764_(1);
                                 _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                 if (_entity instanceof Player) {
                                    Player _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                 }

                                 return;
                              }
                           }

                           return;
                        }
                     }

                     return;
                  } else {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21205_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var143 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var167 = RocketSeatEntity.DATA_engine;
                                 ItemStack var178;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var178 = _livEnt.m_21205_();
                                 } else {
                                    var178 = ItemStack.f_41583_;
                                 }

                                 var143.m_135381_(var167, (int)var178.m_41784_().m_128459_("engine"));
                              }

                              _serverLevel.m_7967_(entityinstance);
                           }
                        }

                        if (entity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)entity;
                           ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                           _setstack.m_41764_(1);
                           _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                           if (_entity instanceof Player) {
                              Player _player = (Player)_entity;
                              _player.m_150109_().m_6596_();
                           }

                           return;
                        }
                     } else {
                        if (entity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)entity;
                           var10000 = _livEnt.m_21205_();
                        } else {
                           var10000 = ItemStack.f_41583_;
                        }

                        if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10000 = _livEnt.m_21206_();
                           } else {
                              var10000 = ItemStack.f_41583_;
                           }

                           if (var10000.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var146 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var168 = RocketSeatEntity.DATA_engine;
                                       ItemStack var179;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var179 = _livEnt.m_21206_();
                                       } else {
                                          var179 = ItemStack.f_41583_;
                                       }

                                       var146.m_135381_(var168, (int)var179.m_41784_().m_128459_("engine"));
                                    }

                                    _serverLevel.m_7967_(entityinstance);
                                 }
                              }

                              if (entity instanceof LivingEntity) {
                                 LivingEntity _entity = (LivingEntity)entity;
                                 ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                                 _setstack.m_41764_(1);
                                 _entity.m_21008_(InteractionHand.OFF_HAND, _setstack);
                                 if (_entity instanceof Player) {
                                    Player _player = (Player)_entity;
                                    _player.m_150109_().m_6596_();
                                 }

                                 return;
                              }
                           }

                           return;
                        }
                     }

                     return;
                  }
               }
            }

            if (!((<undefinedtype>)(new Object() {
               public boolean checkGamemode(Entity _ent) {
                  if (_ent instanceof ServerPlayer _serverPlayer) {
                     return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                  } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                     return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                  } else {
                     return false;
                  }
               }
            })).checkGamemode(entity) && !((<undefinedtype>)(new Object() {
               public boolean checkGamemode(Entity _ent) {
                  if (_ent instanceof ServerPlayer _serverPlayer) {
                     return _serverPlayer.f_8941_.m_9290_() == GameType.ADVENTURE;
                  } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                     return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.ADVENTURE;
                  } else {
                     return false;
                  }
               }
            })).checkGamemode(entity)) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var10000 = _livEnt.m_21205_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (world instanceof ServerLevel) {
                     ServerLevel _serverLevel = (ServerLevel)world;
                     Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                        }

                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           SynchedEntityData var136 = _datEntSetI.m_20088_();
                           EntityDataAccessor var165 = RocketSeatEntity.DATA_engine;
                           ItemStack var176;
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var176 = _livEnt.m_21205_();
                           } else {
                              var176 = ItemStack.f_41583_;
                           }

                           var136.m_135381_(var165, (int)var176.m_41784_().m_128459_("engine"));
                        }

                        _serverLevel.m_7967_(entityinstance);
                     }
                  }

                  if (entity instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entity;
                     ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                     _setstack.m_41764_(1);
                     _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                     if (_entity instanceof Player) {
                        Player _player = (Player)_entity;
                        _player.m_150109_().m_6596_();
                     }
                  }
               } else {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21206_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var139 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var166 = RocketSeatEntity.DATA_engine;
                                 ItemStack var177;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var177 = _livEnt.m_21206_();
                                 } else {
                                    var177 = ItemStack.f_41583_;
                                 }

                                 var139.m_135381_(var166, (int)var177.m_41784_().m_128459_("engine"));
                              }

                              _serverLevel.m_7967_(entityinstance);
                           }
                        }

                        if (entity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)entity;
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
            } else {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var10000 = _livEnt.m_21205_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (world instanceof ServerLevel) {
                     ServerLevel _serverLevel = (ServerLevel)world;
                     Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                        }

                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           SynchedEntityData var131 = _datEntSetI.m_20088_();
                           EntityDataAccessor var10001 = RocketSeatEntity.DATA_engine;
                           ItemStack var10002;
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10002 = _livEnt.m_21205_();
                           } else {
                              var10002 = ItemStack.f_41583_;
                           }

                           var131.m_135381_(var10001, (int)var10002.m_41784_().m_128459_("engine"));
                        }

                        _serverLevel.m_7967_(entityinstance);
                     }
                  }

                  if (entity instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entity;
                     ItemStack _setstack = (new ItemStack(Blocks.f_50016_)).m_41777_();
                     _setstack.m_41764_(1);
                     _entity.m_21008_(InteractionHand.MAIN_HAND, _setstack);
                     if (_entity instanceof Player) {
                        Player _player = (Player)_entity;
                        _player.m_150109_().m_6596_();
                     }
                  }
               } else {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() == Blocks.f_50016_.m_5456_()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21206_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var134 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var164 = RocketSeatEntity.DATA_engine;
                                 ItemStack var175;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var175 = _livEnt.m_21206_();
                                 } else {
                                    var175 = ItemStack.f_41583_;
                                 }

                                 var134.m_135381_(var164, (int)var175.m_41784_().m_128459_("engine"));
                              }

                              _serverLevel.m_7967_(entityinstance);
                           }
                        }

                        if (entity instanceof LivingEntity) {
                           LivingEntity _entity = (LivingEntity)entity;
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
