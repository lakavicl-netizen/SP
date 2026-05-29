package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class OriginDProcedure {
   public static boolean execute(LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         boolean check = false;
         List<Object> required_dimensions = new ArrayList();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         Tag var11 = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (var11 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var11;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
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

            label66:
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
                              continue label66;
                           }

                           check = true;
                        }
                        break;
                     }
                  }
               }
            }
         }

         return check;
      }
   }
}
