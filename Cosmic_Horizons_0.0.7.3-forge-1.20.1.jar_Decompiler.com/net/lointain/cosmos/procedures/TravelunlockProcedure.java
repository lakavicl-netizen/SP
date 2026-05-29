package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class TravelunlockProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         String texts = "";
         JsonObject data = new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         List<Object> dimensions_required = new ArrayList();

         for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
            String var10000;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10000 = _stringTag.m_7916_();
            } else {
               var10000 = "";
            }

            texts = var10000;

            try {
               data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
            } catch (Exception var14) {
            }

            if (data.has("gui_data")) {
               JsonObject gui_data = data.get("gui_data").getAsJsonObject();

               for(int j = 0; j <= gui_data.size() - 1; ++j) {
                  JsonObject iter = gui_data.get((String)gui_data.keySet().stream().toList().get(j)).getAsJsonObject();
                  if (!dimensions_required.contains(iter.get("unlocking_dimension").getAsString()) || dimensions_required.isEmpty()) {
                     dimensions_required.add(iter.get("unlocking_dimension").getAsString());
                  }

                  JsonObject iter_obj = iter.get("object_data").getAsJsonObject();

                  for(int i = 0; i <= iter_obj.size() - 1; ++i) {
                     JsonObject iter_planet = iter_obj.get((String)iter_obj.keySet().stream().toList().get(i)).getAsJsonObject();
                     if (!dimensions_required.contains(iter_planet.get("unlocking_dimension").getAsString())) {
                        dimensions_required.add(iter_planet.get("unlocking_dimension").getAsString());
                     }
                  }
               }
            }
         }

         for(Object _listValueIterator : dimensions_required) {
            ListTag var21 = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions;
            int var10001 = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.size();
            String var10002;
            if (_listValueIterator instanceof String) {
               String _stringValue = (String)_listValueIterator;
               var10002 = _stringValue;
            } else {
               var10002 = "";
            }

            var21.m_7614_(var10001, StringTag.m_129297_(var10002));
         }

         ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.m_7614_(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.size(), StringTag.m_129297_("cosmos:gaia_bh_1"));
         if (entity instanceof Player) {
            Player _player = (Player)entity;
            if (!_player.m_9236_().m_5776_()) {
               _player.m_5661_(Component.m_237113_("All Planets Unlocked"), false);
            }
         }

      }
   }
}
