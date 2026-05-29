package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class InitialiseCollisionDataProcedure {
   public static void execute(LevelAccessor world) {
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      CompoundTag collision_map = new CompoundTag();
      CompoundTag position_map = new CompoundTag();
      CompoundTag atmosphere_map = new CompoundTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         ListTag collision_data_list = new ListTag();
         ListTag position_list = new ListTag();
         JsonObject var10000;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject data = var10000;
         if (data.has("planet_data")) {
            JsonObject planet_data = data.get("planet_data").getAsJsonObject();

            for(int iter = 0; iter < planet_data.size(); ++iter) {
               JsonObject mint = planet_data.get((String)planet_data.keySet().stream().toList().get(iter)).getAsJsonObject();
               if (mint.has("collision") && mint.get("collision").getAsBoolean()) {
                  collision_data_list.m_7614_(collision_data_list.size(), JsontomapconverterProcedure.execute(mint));
                  int var10001 = position_list.size();
                  double var10002 = mint.get("x").getAsDouble();
                  position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + mint.get("y").getAsDouble() + "|" + mint.get("z").getAsDouble() + "\\"));
               }
            }

            if (collision_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var17 = collision_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var22;
               if (var17 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var17;
                  var22 = _listTag.m_6426_();
               } else {
                  var22 = new ListTag();
               }

               ListTag old_collision_data_list = var22;
               collision_map.m_128473_(data.get("attached_dimention_id").getAsString());
               collision_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_collision_data_list, collision_data_list));
            } else {
               collision_map.m_128365_(data.get("attached_dimention_id").getAsString(), collision_data_list);
            }

            if (position_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var21 = position_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var23;
               if (var21 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var21;
                  var23 = _listTag.m_6426_();
               } else {
                  var23 = new ListTag();
               }

               ListTag old_position_list = var23;
               position_map.m_128473_(data.get("attached_dimention_id").getAsString());
               position_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_position_list, position_list));
            } else {
               position_map.m_128365_(data.get("attached_dimention_id").getAsString(), position_list);
            }
         }

         if (data.has("dimensional_data")) {
            JsonObject dimensional_data = data.get("dimensional_data").getAsJsonObject();
            if (dimensional_data.has("atmospheric_data")) {
               JsonObject atmospheric_data = dimensional_data.get("atmospheric_data").getAsJsonObject();
               atmosphere_map.m_128365_(data.get("attached_dimention_id").getAsString(), JsontomapconverterProcedure.execute(atmospheric_data));
            }
         }
      }

      CosmosModVariables.WorldVariables.get(world).collision_data_map = collision_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).global_collision_position_map = position_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map = atmosphere_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
   }
}
