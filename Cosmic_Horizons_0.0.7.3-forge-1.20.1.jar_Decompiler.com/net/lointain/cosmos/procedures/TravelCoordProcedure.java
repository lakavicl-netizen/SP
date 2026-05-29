package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class TravelCoordProcedure {
   public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         double clarific = (double)0.0F;
         double posX = (double)0.0F;
         double posY = (double)0.0F;
         double posZ = (double)0.0F;
         String texts = "";
         List<Object> required_dimensions = new ArrayList();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         boolean dimention_check = false;
         boolean check = false;
         clarific = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).planet_page_sel_index;
         Tag var25 = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (var25 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var25;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
         dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            var25 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var52;
            if (var25 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var25;
               var52 = _stringTag.m_7916_();
            } else {
               var52 = "";
            }

            if (var52.equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         check = false;
         if (read_json.get("unlocking_dimension").getAsString().equals("none")) {
            check = true;
         } else {
            for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
               String var10001;
               if (dataelementiterator instanceof StringTag) {
                  StringTag _stringTag = (StringTag)dataelementiterator;
                  var10001 = _stringTag.m_7916_();
               } else {
                  var10001 = "";
               }

               required_dimensions.add(var10001);
            }

            label170:
            for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
               if (dataelementiterator instanceof StringTag) {
                  StringTag _stringTag = (StringTag)dataelementiterator;
                  var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
               } else {
                  var10000 = new JsonObject();
               }

               JsonObject gui_iter = var10000;
               if (gui_iter.has("gui_data")) {
                  JsonObject gui_data = gui_iter.get("gui_data").getAsJsonObject();

                  for(int l = 0; l < gui_data.size(); ++l) {
                     JsonObject gui_iter_data = gui_data.get((String)gui_data.keySet().stream().toList().get(l)).getAsJsonObject();
                     if (read_json.get("unlocking_dimension").getAsString().equals(gui_iter_data.get("travel_dimension").getAsString())) {
                        JsonObject object_data = gui_iter_data.get("object_data").getAsJsonObject();

                        for(int m = 0; m < object_data.size(); ++m) {
                           JsonObject iter_data = object_data.get((String)object_data.keySet().stream().toList().get(m)).getAsJsonObject();
                           if (!required_dimensions.contains(iter_data.get("unlocking_dimension").getAsString())) {
                              check = false;
                              continue label170;
                           }

                           check = true;
                        }
                        break;
                     }
                  }
               }
            }
         }

         if (dimention_check && check) {
            if (entity.m_9236_().m_46472_().m_135782_().toString().equals(read_json.get("travel_dimension").getAsString())) {
               if (entity.m_20202_() instanceof RocketSeatEntity) {
                  if (((<undefinedtype>)(new Object() {
                     double convert(String s) {
                        try {
                           return Double.parseDouble(s.trim());
                        } catch (Exception var3) {
                           return (double)0.0F;
                        }
                     }
                  })).convert(guistate.containsKey("text:YPOS") ? ((EditBox)guistate.get("text:YPOS")).m_94155_() : "") > (double)-159.0F) {
                     if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        _player.m_6915_();
                     }

                     Entity _ent = entity.m_20202_();
                     _ent.m_6021_(((<undefinedtype>)(new Object() {
                        double convert(String s) {
                           try {
                              return Double.parseDouble(s.trim());
                           } catch (Exception var3) {
                              return (double)0.0F;
                           }
                        }
                     })).convert(guistate.containsKey("text:XPOS") ? ((EditBox)guistate.get("text:XPOS")).m_94155_() : ""), ((<undefinedtype>)(new Object() {
                        double convert(String s) {
                           try {
                              return Double.parseDouble(s.trim());
                           } catch (Exception var3) {
                              return (double)0.0F;
                           }
                        }
                     })).convert(guistate.containsKey("text:YPOS") ? ((EditBox)guistate.get("text:YPOS")).m_94155_() : ""), ((<undefinedtype>)(new Object() {
                        double convert(String s) {
                           try {
                              return Double.parseDouble(s.trim());
                           } catch (Exception var3) {
                              return (double)0.0F;
                           }
                        }
                     })).convert(guistate.containsKey("text:ZPOS") ? ((EditBox)guistate.get("text:ZPOS")).m_94155_() : ""));
                     if (_ent instanceof ServerPlayer) {
                        ServerPlayer _serverPlayer = (ServerPlayer)_ent;
                        _serverPlayer.f_8906_.m_9774_(((<undefinedtype>)(new Object() {
                           double convert(String s) {
                              try {
                                 return Double.parseDouble(s.trim());
                              } catch (Exception var3) {
                                 return (double)0.0F;
                              }
                           }
                        })).convert(guistate.containsKey("text:XPOS") ? ((EditBox)guistate.get("text:XPOS")).m_94155_() : ""), ((<undefinedtype>)(new Object() {
                           double convert(String s) {
                              try {
                                 return Double.parseDouble(s.trim());
                              } catch (Exception var3) {
                                 return (double)0.0F;
                              }
                           }
                        })).convert(guistate.containsKey("text:YPOS") ? ((EditBox)guistate.get("text:YPOS")).m_94155_() : ""), ((<undefinedtype>)(new Object() {
                           double convert(String s) {
                              try {
                                 return Double.parseDouble(s.trim());
                              } catch (Exception var3) {
                                 return (double)0.0F;
                              }
                           }
                        })).convert(guistate.containsKey("text:ZPOS") ? ((EditBox)guistate.get("text:ZPOS")).m_94155_() : ""), _ent.m_146908_(), _ent.m_146909_());
                     }

                     if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        if (!_player.m_9236_().m_5776_()) {
                           String var54 = (new DecimalFormat("##")).format(((<undefinedtype>)(new Object() {
                              double convert(String s) {
                                 try {
                                    return Double.parseDouble(s.trim());
                                 } catch (Exception var3) {
                                    return (double)0.0F;
                                 }
                              }
                           })).convert(guistate.containsKey("text:XPOS") ? ((EditBox)guistate.get("text:XPOS")).m_94155_() : ""));
                           _player.m_5661_(Component.m_237113_("Traveled To " + var54 + " " + (new DecimalFormat("##")).format(((<undefinedtype>)(new Object() {
                              double convert(String s) {
                                 try {
                                    return Double.parseDouble(s.trim());
                                 } catch (Exception var3) {
                                    return (double)0.0F;
                                 }
                              }
                           })).convert(guistate.containsKey("text:YPOS") ? ((EditBox)guistate.get("text:YPOS")).m_94155_() : "")) + " " + (new DecimalFormat("##")).format(((<undefinedtype>)(new Object() {
                              double convert(String s) {
                                 try {
                                    return Double.parseDouble(s.trim());
                                 } catch (Exception var3) {
                                    return (double)0.0F;
                                 }
                              }
                           })).convert(guistate.containsKey("text:ZPOS") ? ((EditBox)guistate.get("text:ZPOS")).m_94155_() : ""))), true);
                        }
                     }
                  } else {
                     Object var46 = guistate.get("text:YPOS");
                     if (var46 instanceof EditBox) {
                        EditBox _tf = (EditBox)var46;
                        _tf.m_94144_("Beyond limit");
                     }
                  }
               }
            } else {
               Object var47 = guistate.get("text:XPOS");
               if (var47 instanceof EditBox) {
                  EditBox _tf = (EditBox)var47;
                  _tf.m_94144_("Not a location");
               }

               var47 = guistate.get("text:YPOS");
               if (var47 instanceof EditBox) {
                  EditBox _tf = (EditBox)var47;
                  _tf.m_94144_("Not a location");
               }

               var47 = guistate.get("text:ZPOS");
               if (var47 instanceof EditBox) {
                  EditBox _tf = (EditBox)var47;
                  _tf.m_94144_("Not a location");
               }
            }
         }

      }
   }
}
