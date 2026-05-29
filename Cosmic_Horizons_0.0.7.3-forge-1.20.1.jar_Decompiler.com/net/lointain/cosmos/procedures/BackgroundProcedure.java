package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class BackgroundProcedure {
   public static String execute(LevelAccessor world, Entity entity) {
      if (entity == null) {
         return "";
      } else {
         new JsonObject();
         Tag var4 = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (var4 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var4;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
         return "cosmos:textures/screens/" + read_json.get("background").getAsString() + ".png";
      }
   }
}
