package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class TravelDProcedure {
   public static boolean execute(LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         boolean check = false;
         new JsonObject();
         new JsonObject();
         new JsonObject();
         double clarific = (double)0.0F;
         clarific = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).planet_page_sel_index;
         Tag var9 = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (var9 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var9;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
         JsonObject obj_json = read_json.get("object_data").getAsJsonObject();
         JsonObject planet_json = obj_json.get((String)obj_json.keySet().stream().toList().get((int)clarific)).getAsJsonObject();

         for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
            String var14 = planet_json.get("unlocking_dimension").getAsString();
            String var10001;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10001 = _stringTag.m_7916_();
            } else {
               var10001 = "";
            }

            if (var14.equals(var10001)) {
               check = true;
               break;
            }

            check = false;
         }

         return check;
      }
   }
}
