package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class InitialiseLightSourceDataProcedure {
   public static void execute(LevelAccessor world) {
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      CompoundTag light_map = new CompoundTag();
      CompoundTag opaque_object_map = new CompoundTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         JsonObject var10000;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject data = var10000;
         if (data.has("planet_data")) {
            JsonObject planet_data = data.get("planet_data").getAsJsonObject();
            ListTag light_list = new ListTag();
            ListTag opaque_list = new ListTag();

            for(int iter = 0; iter < planet_data.size(); ++iter) {
               JsonObject mint = planet_data.get((String)planet_data.keySet().stream().toList().get(iter)).getAsJsonObject();
               if (mint.has("glowing") && mint.get("glowing").getAsBoolean()) {
                  JsonObject core_color = mint.get("core_color").getAsJsonObject();
                  JsonObject bloom_color = mint.get("bloom_color").getAsJsonObject();
                  if (core_color.get("r").getAsDouble() != (double)0.0F || core_color.get("g").getAsDouble() != (double)0.0F || core_color.get("b").getAsDouble() != (double)0.0F) {
                     light_list.m_7614_(light_list.size(), JsontomapconverterProcedure.execute(mint));
                  }
               }

               if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                  opaque_list.m_7614_(opaque_list.size(), JsontomapconverterProcedure.execute(mint));
               }
            }

            if (light_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var16 = light_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var21;
               if (var16 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var16;
                  var21 = _listTag.m_6426_();
               } else {
                  var21 = new ListTag();
               }

               ListTag old_light_list = var21;
               light_map.m_128473_(data.get("attached_dimention_id").getAsString());
               light_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_light_list, light_list));
            } else {
               light_map.m_128365_(data.get("attached_dimention_id").getAsString(), light_list);
            }

            if (opaque_object_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var20 = opaque_object_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var22;
               if (var20 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var20;
                  var22 = _listTag.m_6426_();
               } else {
                  var22 = new ListTag();
               }

               ListTag old_opaque_list = var22;
               opaque_object_map.m_128473_(data.get("attached_dimention_id").getAsString());
               opaque_object_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_opaque_list, opaque_list));
            } else {
               opaque_object_map.m_128365_(data.get("attached_dimention_id").getAsString(), opaque_list);
            }
         }
      }

      CosmosModVariables.WorldVariables.get(world).light_source_map = light_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).opaque_object_map = opaque_object_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
   }
}
