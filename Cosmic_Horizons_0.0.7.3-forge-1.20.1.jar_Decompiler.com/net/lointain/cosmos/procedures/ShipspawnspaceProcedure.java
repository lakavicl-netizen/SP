package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModEntities;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ShipspawnspaceProcedure {
   @SubscribeEvent
   public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
      String usehand = event.getHand().toString().toLowerCase();
      usehand = usehand.replace("_", "");
      execute(event, event.getLevel(), (double)event.getPos().m_123341_(), (double)event.getPos().m_123342_(), (double)event.getPos().m_123343_(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute((Event)null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         boolean dimention_check = false;
         new JsonObject();
         new JsonObject();

         for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
            JsonObject var10000;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
            } else {
               var10000 = new JsonObject();
            }

            JsonObject data = var10000;
            JsonObject dimension_data = data.get("dimensional_data").getAsJsonObject();
            if (dimension_data.get("dimension_type").getAsString().equals("space")) {
               if (entity.m_9236_().m_46472_().m_135782_().toString().equals(data.get("attached_dimention_id").getAsString())) {
                  dimention_check = true;
                  break;
               }

               dimention_check = false;
            }
         }

         if (!world.m_8055_(BlockPos.m_274561_(((<undefinedtype>)(new Object() {
            public Vec3 get(Entity entity, double length) {
               return Vec3.m_82528_(entity.m_9236_().m_45547_(new ClipContext(entity.m_146892_(), entity.m_146892_().m_82549_(entity.m_20154_().m_82490_(length)), Block.OUTLINE, Fluid.NONE, (Entity)null)).m_82425_());
            }
         })).get(entity, (double)5.0F).m_7096_(), ((<undefinedtype>)(new Object() {
            public Vec3 get(Entity entity, double length) {
               return Vec3.m_82528_(entity.m_9236_().m_45547_(new ClipContext(entity.m_146892_(), entity.m_146892_().m_82549_(entity.m_20154_().m_82490_(length)), Block.OUTLINE, Fluid.NONE, (Entity)null)).m_82425_());
            }
         })).get(entity, (double)5.0F).m_7098_(), ((<undefinedtype>)(new Object() {
            public Vec3 get(Entity entity, double length) {
               return Vec3.m_82528_(entity.m_9236_().m_45547_(new ClipContext(entity.m_146892_(), entity.m_146892_().m_82549_(entity.m_20154_().m_82490_(length)), Block.OUTLINE, Fluid.NONE, (Entity)null)).m_82425_());
            }
         })).get(entity, (double)5.0F).m_7094_())).m_60815_() && dimention_check) {
            ItemStack var136;
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var136 = _livEnt.m_21205_();
            } else {
               var136 = ItemStack.f_41583_;
            }

            if (var136.m_41720_() != CosmosModItems.STEEL_SPACE_NODULE.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var136 = _livEnt.m_21206_();
               } else {
                  var136 = ItemStack.f_41583_;
               }

               if (var136.m_41720_() != CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var136 = _livEnt.m_21205_();
                  } else {
                     var136 = ItemStack.f_41583_;
                  }

                  if (var136.m_41720_() != CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var136 = _livEnt.m_21206_();
                     } else {
                        var136 = ItemStack.f_41583_;
                     }

                     if (var136.m_41720_() != CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (entity instanceof LivingEntity) {
                           LivingEntity _livEnt = (LivingEntity)entity;
                           var136 = _livEnt.m_21205_();
                        } else {
                           var136 = ItemStack.f_41583_;
                        }

                        if (var136.m_41720_() != CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var136 = _livEnt.m_21206_();
                           } else {
                              var136 = ItemStack.f_41583_;
                           }

                           if (var136.m_41720_() != CosmosModItems.NICKEL_SPACE_NODULE.get()) {
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
                              var136 = _livEnt.m_21205_();
                           } else {
                              var136 = ItemStack.f_41583_;
                           }

                           if (var136.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var168 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var181 = RocketSeatEntity.DATA_engine;
                                       ItemStack var192;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var192 = _livEnt.m_21205_();
                                       } else {
                                          var192 = ItemStack.f_41583_;
                                       }

                                       var168.m_135381_(var181, (int)var192.m_41784_().m_128459_("engine"));
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
                                 var136 = _livEnt.m_21205_();
                              } else {
                                 var136 = ItemStack.f_41583_;
                              }

                              if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var136 = _livEnt.m_21206_();
                                 } else {
                                    var136 = ItemStack.f_41583_;
                                 }

                                 if (var136.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                                    if (world instanceof ServerLevel) {
                                       ServerLevel _serverLevel = (ServerLevel)world;
                                       Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                       if (entityinstance != null) {
                                          entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                          }

                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             SynchedEntityData var171 = _datEntSetI.m_20088_();
                                             EntityDataAccessor var182 = RocketSeatEntity.DATA_engine;
                                             ItemStack var193;
                                             if (entity instanceof LivingEntity) {
                                                LivingEntity _livEnt = (LivingEntity)entity;
                                                var193 = _livEnt.m_21206_();
                                             } else {
                                                var193 = ItemStack.f_41583_;
                                             }

                                             var171.m_135381_(var182, (int)var193.m_41784_().m_128459_("engine"));
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
                              var136 = _livEnt.m_21205_();
                           } else {
                              var136 = ItemStack.f_41583_;
                           }

                           if (var136.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var163 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var179 = RocketSeatEntity.DATA_engine;
                                       ItemStack var190;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var190 = _livEnt.m_21205_();
                                       } else {
                                          var190 = ItemStack.f_41583_;
                                       }

                                       var163.m_135381_(var179, (int)var190.m_41784_().m_128459_("engine"));
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
                                 var136 = _livEnt.m_21205_();
                              } else {
                                 var136 = ItemStack.f_41583_;
                              }

                              if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var136 = _livEnt.m_21206_();
                                 } else {
                                    var136 = ItemStack.f_41583_;
                                 }

                                 if (var136.m_41720_() == CosmosModItems.NICKEL_SPACE_NODULE.get()) {
                                    if (world instanceof ServerLevel) {
                                       ServerLevel _serverLevel = (ServerLevel)world;
                                       Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                       if (entityinstance != null) {
                                          entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 2);
                                          }

                                          if (entityinstance instanceof RocketSeatEntity) {
                                             RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                             SynchedEntityData var166 = _datEntSetI.m_20088_();
                                             EntityDataAccessor var180 = RocketSeatEntity.DATA_engine;
                                             ItemStack var191;
                                             if (entity instanceof LivingEntity) {
                                                LivingEntity _livEnt = (LivingEntity)entity;
                                                var191 = _livEnt.m_21206_();
                                             } else {
                                                var191 = ItemStack.f_41583_;
                                             }

                                             var166.m_135381_(var180, (int)var191.m_41784_().m_128459_("engine"));
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
                        var136 = _livEnt.m_21205_();
                     } else {
                        var136 = ItemStack.f_41583_;
                     }

                     if (var136.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var156 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var177 = RocketSeatEntity.DATA_engine;
                                 ItemStack var188;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var188 = _livEnt.m_21205_();
                                 } else {
                                    var188 = ItemStack.f_41583_;
                                 }

                                 var156.m_135381_(var177, (int)var188.m_41784_().m_128459_("engine"));
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
                           var136 = _livEnt.m_21205_();
                        } else {
                           var136 = ItemStack.f_41583_;
                        }

                        if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var136 = _livEnt.m_21206_();
                           } else {
                              var136 = ItemStack.f_41583_;
                           }

                           if (var136.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var159 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var178 = RocketSeatEntity.DATA_engine;
                                       ItemStack var189;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var189 = _livEnt.m_21206_();
                                       } else {
                                          var189 = ItemStack.f_41583_;
                                       }

                                       var159.m_135381_(var178, (int)var189.m_41784_().m_128459_("engine"));
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
                        var136 = _livEnt.m_21205_();
                     } else {
                        var136 = ItemStack.f_41583_;
                     }

                     if (var136.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var151 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var175 = RocketSeatEntity.DATA_engine;
                                 ItemStack var186;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var186 = _livEnt.m_21205_();
                                 } else {
                                    var186 = ItemStack.f_41583_;
                                 }

                                 var151.m_135381_(var175, (int)var186.m_41784_().m_128459_("engine"));
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
                           var136 = _livEnt.m_21205_();
                        } else {
                           var136 = ItemStack.f_41583_;
                        }

                        if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var136 = _livEnt.m_21206_();
                           } else {
                              var136 = ItemStack.f_41583_;
                           }

                           if (var136.m_41720_() == CosmosModItems.TITANIUM_SPACE_NODULE.get()) {
                              if (world instanceof ServerLevel) {
                                 ServerLevel _serverLevel = (ServerLevel)world;
                                 Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                                 if (entityinstance != null) {
                                    entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 1);
                                    }

                                    if (entityinstance instanceof RocketSeatEntity) {
                                       RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                       SynchedEntityData var154 = _datEntSetI.m_20088_();
                                       EntityDataAccessor var176 = RocketSeatEntity.DATA_engine;
                                       ItemStack var187;
                                       if (entity instanceof LivingEntity) {
                                          LivingEntity _livEnt = (LivingEntity)entity;
                                          var187 = _livEnt.m_21206_();
                                       } else {
                                          var187 = ItemStack.f_41583_;
                                       }

                                       var154.m_135381_(var176, (int)var187.m_41784_().m_128459_("engine"));
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
                  var136 = _livEnt.m_21205_();
               } else {
                  var136 = ItemStack.f_41583_;
               }

               if (var136.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (world instanceof ServerLevel) {
                     ServerLevel _serverLevel = (ServerLevel)world;
                     Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                        }

                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           SynchedEntityData var144 = _datEntSetI.m_20088_();
                           EntityDataAccessor var173 = RocketSeatEntity.DATA_engine;
                           ItemStack var184;
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var184 = _livEnt.m_21205_();
                           } else {
                              var184 = ItemStack.f_41583_;
                           }

                           var144.m_135381_(var173, (int)var184.m_41784_().m_128459_("engine"));
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
                     var136 = _livEnt.m_21205_();
                  } else {
                     var136 = ItemStack.f_41583_;
                  }

                  if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var136 = _livEnt.m_21206_();
                     } else {
                        var136 = ItemStack.f_41583_;
                     }

                     if (var136.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var147 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var174 = RocketSeatEntity.DATA_engine;
                                 ItemStack var185;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var185 = _livEnt.m_21206_();
                                 } else {
                                    var185 = ItemStack.f_41583_;
                                 }

                                 var147.m_135381_(var174, (int)var185.m_41784_().m_128459_("engine"));
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
                  var136 = _livEnt.m_21205_();
               } else {
                  var136 = ItemStack.f_41583_;
               }

               if (var136.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                  if (world instanceof ServerLevel) {
                     ServerLevel _serverLevel = (ServerLevel)world;
                     Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                        }

                        if (entityinstance instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                           SynchedEntityData var139 = _datEntSetI.m_20088_();
                           EntityDataAccessor var10001 = RocketSeatEntity.DATA_engine;
                           ItemStack var10002;
                           if (entity instanceof LivingEntity) {
                              LivingEntity _livEnt = (LivingEntity)entity;
                              var10002 = _livEnt.m_21205_();
                           } else {
                              var10002 = ItemStack.f_41583_;
                           }

                           var139.m_135381_(var10001, (int)var10002.m_41784_().m_128459_("engine"));
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
                     var136 = _livEnt.m_21205_();
                  } else {
                     var136 = ItemStack.f_41583_;
                  }

                  if (var136.m_41720_() == Blocks.f_50016_.m_5456_()) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var136 = _livEnt.m_21206_();
                     } else {
                        var136 = ItemStack.f_41583_;
                     }

                     if (var136.m_41720_() == CosmosModItems.STEEL_SPACE_NODULE.get()) {
                        if (world instanceof ServerLevel) {
                           ServerLevel _serverLevel = (ServerLevel)world;
                           Entity entityinstance = ((EntityType)CosmosModEntities.ROCKET_SEAT.get()).m_262451_(_serverLevel, (CompoundTag)null, (Consumer)null, BlockPos.m_274561_(x, y - (double)1.0F, z), MobSpawnType.MOB_SUMMONED, false, false);
                           if (entityinstance != null) {
                              entityinstance.m_146922_(world.m_213780_().m_188501_() * 360.0F);
                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 _datEntSetI.m_20088_().m_135381_(RocketSeatEntity.DATA_type, 0);
                              }

                              if (entityinstance instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntSetI = (RocketSeatEntity)entityinstance;
                                 SynchedEntityData var142 = _datEntSetI.m_20088_();
                                 EntityDataAccessor var172 = RocketSeatEntity.DATA_engine;
                                 ItemStack var183;
                                 if (entity instanceof LivingEntity) {
                                    LivingEntity _livEnt = (LivingEntity)entity;
                                    var183 = _livEnt.m_21206_();
                                 } else {
                                    var183 = ItemStack.f_41583_;
                                 }

                                 var142.m_135381_(var172, (int)var183.m_41784_().m_128459_("engine"));
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
