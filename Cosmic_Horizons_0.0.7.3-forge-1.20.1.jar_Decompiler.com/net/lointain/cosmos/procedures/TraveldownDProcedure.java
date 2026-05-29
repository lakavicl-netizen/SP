package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class TraveldownDProcedure {
   public static boolean execute(LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         new JsonObject();
         new JsonObject();
         double object_size = (double)0.0F;
         Tag var7 = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (var7 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var7;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
         JsonObject object_read_json = read_json.get("object_data").getAsJsonObject();
         object_size = (double)object_read_json.size();
         return object_size > (double)5.0F && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).planet_page_iter < object_size - (double)5.0F;
      }
   }
}
