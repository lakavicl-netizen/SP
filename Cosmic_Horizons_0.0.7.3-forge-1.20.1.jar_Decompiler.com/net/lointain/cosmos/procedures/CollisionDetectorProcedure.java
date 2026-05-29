package net.lointain.cosmos.procedures;

import io.netty.buffer.Unpooled;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.lointain.cosmos.world.inventory.LandingSelectorMenu;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class CollisionDetectorProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         new ArrayList();
         boolean isCollided = false;
         boolean inside_a_valk_ship = false;
         double roll = (double)0.0F;
         double pitch = (double)0.0F;
         double yaw = (double)0.0F;
         double half_scale = (double)0.0F;
         double Scale = (double)0.0F;
         double posX = (double)0.0F;
         double posY = (double)0.0F;
         double posZ = (double)0.0F;
         double range = (double)0.0F;
         double distanceSqrZ = (double)0.0F;
         double distanceSqrY = (double)0.0F;
         double distanceSqrX = (double)0.0F;
         double angle = (double)0.0F;
         double Target_Distance = (double)0.0F;
         Vec3 cubepos = Vec3.f_82478_;
         Vec3 rotated = Vec3.f_82478_;
         Vec3 vector = Vec3.f_82478_;
         Vec3 rotatedZAxis = Vec3.f_82478_;
         Vec3 toPlayer = Vec3.f_82478_;
         Vec3 rotatedXAxis = Vec3.f_82478_;
         Vec3 rotatedYAxis = Vec3.f_82478_;
         Vec3 position = Vec3.f_82478_;
         String dimension = "";
         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).check_collision && CosmosModVariables.WorldVariables.get(world).collision_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var50 = CosmosModVariables.WorldVariables.get(world).collision_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            ListTag var10000;
            if (var50 instanceof ListTag) {
               ListTag _listTag = (ListTag)var50;
               var10000 = _listTag.m_6426_();
            } else {
               var10000 = new ListTag();
            }

            if (!var10000.isEmpty()) {
               List<Object> Target_List = DistanceOrderProviderProcedure.execute(CosmosModVariables.WorldVariables.get(world).global_collision_position_map, (double)1.0F, entity.m_9236_().m_46472_().m_135782_().toString(), entity.m_20182_());
               Tag var54 = CosmosModVariables.WorldVariables.get(world).collision_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
               if (var54 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var54;
                  var10000 = _listTag.m_6426_();
               } else {
                  var10000 = new ListTag();
               }

               Object var118 = Target_List.get(0);
               double var10001;
               if (var118 instanceof Number) {
                  Number _doubleValue = (Number)var118;
                  var10001 = _doubleValue.doubleValue();
               } else {
                  var10001 = (double)0.0F;
               }

               Tag _listTag = var10000.get((int)var10001);
               CompoundTag var121;
               if (_listTag instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)_listTag;
                  var121 = _compoundTag.m_6426_();
               } else {
                  var121 = new CompoundTag();
               }

               CompoundTag Target_object = var121;
               Tag _setval = Target_object.m_128423_("scale");
               double var122;
               if (_setval instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)_setval;
                  var122 = _doubleTag.m_7061_();
               } else {
                  var122 = (double)0.0F;
               }

               Scale = var122;
               Vec3 var123 = new Vec3;
               _setval = Target_object.m_128423_("x");
               double var10002;
               if (_setval instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)_setval;
                  var10002 = _doubleTag.m_7061_();
               } else {
                  var10002 = (double)0.0F;
               }

               _setval = Target_object.m_128423_("y");
               double var10003;
               if (_setval instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)_setval;
                  var10003 = _doubleTag.m_7061_();
               } else {
                  var10003 = (double)0.0F;
               }

               _setval = Target_object.m_128423_("z");
               double var10004;
               if (_setval instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)_setval;
                  var10004 = _doubleTag.m_7061_();
               } else {
                  var10004 = (double)0.0F;
               }

               var123.<init>(var10002, var10003, var10004);
               cubepos = var123;
               Target_Distance = cubepos.m_82554_(entity.m_20182_());
               if (Target_Distance <= Scale / (double)2.0F * Math.cbrt((double)3.0F)) {
                  _setval = Target_object.m_128423_("yaw");
                  double var124;
                  if (_setval instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_setval;
                     var124 = _doubleTag.m_7061_();
                  } else {
                     var124 = (double)0.0F;
                  }

                  yaw = var124;
                  _setval = Target_object.m_128423_("pitch");
                  if (_setval instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_setval;
                     var124 = _doubleTag.m_7061_();
                  } else {
                     var124 = (double)0.0F;
                  }

                  pitch = var124;
                  _setval = Target_object.m_128423_("roll");
                  if (_setval instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_setval;
                     var124 = _doubleTag.m_7061_();
                  } else {
                     var124 = (double)0.0F;
                  }

                  roll = var124;
                  toPlayer = entity.m_20182_().m_82546_(cubepos);
                  rotatedXAxis = (new Vec3((double)1.0F, (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-roll)).m_82524_(((float)Math.PI / 180F) * (float)(-yaw));
                  rotatedYAxis = (new Vec3((double)0.0F, (double)1.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-roll)).m_82496_(-0.017453292F * (float)pitch);
                  rotatedZAxis = (new Vec3((double)0.0F, (double)0.0F, (double)1.0F)).m_82496_(-0.017453292F * (float)pitch).m_82524_(((float)Math.PI / 180F) * (float)(-yaw));
                  distanceSqrX = rotatedXAxis.m_82490_(toPlayer.m_82526_(rotatedXAxis)).m_82556_();
                  distanceSqrY = rotatedYAxis.m_82490_(toPlayer.m_82526_(rotatedYAxis)).m_82556_();
                  distanceSqrZ = rotatedZAxis.m_82490_(toPlayer.m_82526_(rotatedZAxis)).m_82556_();
                  range = Scale * Scale / (double)4.0F;
                  isCollided = distanceSqrX <= range && distanceSqrY <= range && distanceSqrZ <= range;
               }

               if (!isCollided) {
                  String _setval = "^";
                  entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                     capability.landing_coords = _setval;
                     capability.syncPlayerVariables(entity);
                  });
               }

               if (isCollided && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.equals("^")) {
                  label185: {
                     if (entity instanceof Player) {
                        Player _plr56 = (Player)entity;
                        if (_plr56.f_36096_ instanceof LandingSelectorMenu) {
                           break label185;
                        }
                     }

                     boolean _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.pitch_i = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.pitch_d = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.roll_i = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.roll_d = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.thrust = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.thrust_drop = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     _setval = false;
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.thrust_catch = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     String _setval = "=";
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.landing_coords = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                     if (entity.m_20159_() && entity instanceof Player) {
                        Player _player = (Player)entity;
                        Vec3 _setval = new Vec3(entity.m_20202_().m_20184_().m_7096_(), entity.m_20202_().m_20184_().m_7098_(), entity.m_20202_().m_20184_().m_7094_());
                        _player.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                           capability.entry_velocity = _setval;
                           capability.syncPlayerVariables(_player);
                        });
                     }

                     if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        CompoundTag var127 = CosmosModVariables.MapVariables.get(world).antena_locations;
                        Tag var56 = Target_object.m_128423_("travel_to");
                        String var130;
                        if (var56 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var56;
                           var130 = _stringTag.m_7916_();
                        } else {
                           var130 = "";
                        }

                        Tag var55 = var127.m_128423_(var130);
                        ListTag var128;
                        if (var55 instanceof ListTag) {
                           ListTag _listTag = (ListTag)var55;
                           var128 = _listTag.m_6426_();
                        } else {
                           var128 = new ListTag();
                        }

                        ListTag _setval = var128;
                        _player.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                           capability.entry_world = _setval;
                           capability.syncPlayerVariables(_player);
                        });
                     }

                     if (entity instanceof ServerPlayer) {
                        ServerPlayer _ent = (ServerPlayer)entity;
                        final BlockPos _bpos = BlockPos.m_274561_(x, y, z);
                        NetworkHooks.openScreen(_ent, new MenuProvider() {
                           public Component m_5446_() {
                              return Component.m_237113_("LandingSelector");
                           }

                           public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
                              return new LandingSelectorMenu(id, inventory, (new FriendlyByteBuf(Unpooled.buffer())).m_130064_(_bpos));
                           }
                        }, _bpos);
                     }
                  }
               }

               if (entity instanceof Player) {
                  Player _plr70 = (Player)entity;
                  if (_plr70.f_36096_ instanceof LandingSelectorMenu) {
                     if (!((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.equals("^") && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.equals("=")) {
                        posX = ((<undefinedtype>)(new Object() {
                           double convert(String s) {
                              try {
                                 return Double.parseDouble(s.trim());
                              } catch (Exception var3) {
                                 return (double)0.0F;
                              }
                           }
                        })).convert(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.substring(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.indexOf("*") + "*".length(), ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.indexOf("|"))) + (double)0.0F;
                        posY = (double)(550 + Mth.m_216271_(RandomSource.m_216327_(), -8, 8));
                        posZ = ((<undefinedtype>)(new Object() {
                           double convert(String s) {
                              try {
                                 return Double.parseDouble(s.trim());
                              } catch (Exception var3) {
                                 return (double)0.0F;
                              }
                           }
                        })).convert(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.substring(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.indexOf("|") + "|".length(), ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.indexOf("~"))) + (double)0.0F;
                        _listTag = Target_object.m_128423_("travel_to");
                        String var129;
                        if (_listTag instanceof StringTag) {
                           StringTag _stringTag = (StringTag)_listTag;
                           var129 = _stringTag.m_7916_();
                        } else {
                           var129 = "";
                        }

                        dimension = var129;
                        if (entity.m_20159_()) {
                           String _setval = entity.m_20202_().m_20149_();
                           entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                              capability.vehicle = _setval;
                              capability.syncPlayerVariables(entity);
                           });
                           if (world instanceof ServerLevel) {
                              ServerLevel _level = (ServerLevel)world;
                              _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20202_().m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
                           }

                           if (world instanceof ServerLevel) {
                              ServerLevel _level = (ServerLevel)world;
                              _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
                           }

                           CosmosMod.queueServerWork(25, () -> {
                              for(int index0 = 0; index0 < 60; ++index0) {
                                 if (!(entity.m_20202_() instanceof RocketSeatEntity) && !entity.m_9236_().m_5776_() && entity.m_20194_() != null) {
                                    Commands var10000 = entity.m_20194_().m_129892_();
                                    CommandSourceStack var10001 = new CommandSourceStack(CommandSource.f_80164_, entity.m_20182_(), entity.m_20155_(), entity.m_9236_() instanceof ServerLevel ? (ServerLevel)entity.m_9236_() : null, 4, entity.m_7755_().getString(), entity.m_5446_(), entity.m_9236_().m_7654_(), entity);
                                    String var10002 = entity.m_20149_();
                                    var10000.m_230957_(var10001, "ride " + var10002 + " mount " + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).vehicle);
                                 }
                              }

                           });
                           _setval = "^";
                           entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                              capability.landing_coords = _setval;
                              capability.syncPlayerVariables(entity);
                           });
                        } else {
                           if (world instanceof ServerLevel) {
                              ServerLevel _level = (ServerLevel)world;
                              _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
                           }

                           String _setval = "^";
                           entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                              capability.landing_coords = _setval;
                              capability.syncPlayerVariables(entity);
                           });
                        }

                        return;
                     }

                     return;
                  }
               }

               if (entity instanceof Player) {
                  Player _plr100 = (Player)entity;
                  if (_plr100.f_36096_ instanceof LandingSelectorMenu) {
                     return;
                  }
               }

               if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).landing_coords.equals("=")) {
                  if (isCollided) {
                     if (entity.m_20159_()) {
                        entity.m_20202_().m_20256_(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_velocity.m_82559_(new Vec3((double)-4.0F, (double)-4.0F, (double)-4.0F)));
                     } else {
                        entity.m_20256_(new Vec3(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_velocity.m_82559_(new Vec3((double)-4.0F, (double)-4.0F, (double)-4.0F)).m_7096_(), ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_velocity.m_82559_(new Vec3((double)-4.0F, (double)-4.0F, (double)-4.0F)).m_7098_(), ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_velocity.m_82559_(new Vec3((double)-4.0F, (double)-4.0F, (double)-4.0F)).m_7094_()));
                     }
                  } else if (!isCollided) {
                     String _setval = "^";
                     entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                        capability.landing_coords = _setval;
                        capability.syncPlayerVariables(entity);
                     });
                  }
               }
            }
         }

      }
   }
}
