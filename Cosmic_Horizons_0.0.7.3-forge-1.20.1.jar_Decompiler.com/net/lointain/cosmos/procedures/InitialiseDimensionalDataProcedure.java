package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class InitialiseDimensionalDataProcedure {
   public static void execute(LevelAccessor world) {
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      CompoundTag dimensional_map = new CompoundTag();
      CompoundTag dimension_type_map = new CompoundTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         JsonObject var10000;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject data = var10000;
         if (data.has("dimensional_data")) {
            JsonObject dimensional_data = data.get("dimensional_data").getAsJsonObject();
            dimensional_map.m_128365_(data.get("attached_dimention_id").getAsString(), JsontomapconverterProcedure.execute(dimensional_data));
            dimension_type_map.m_128365_(data.get("attached_dimention_id").getAsString(), StringTag.m_129297_(dimensional_data.get("dimension_type").getAsString()));
         }
      }

      CosmosModVariables.WorldVariables.get(world).dimensional_data = dimensional_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).dimension_type = dimension_type_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
   }
}
