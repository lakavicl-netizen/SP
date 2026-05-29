package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;

public class JsontomapconverterProcedure {
   public static CompoundTag execute(JsonObject json) {
      if (json == null) {
         return new CompoundTag();
      } else {
         new JsonObject();
         String property = "";
         CompoundTag returning_map = new CompoundTag();
         JsonObject object = json;

         for(int iter = 0; iter < object.size(); ++iter) {
            property = (String)object.keySet().stream().toList().get(iter);
            if (object.get(property).isJsonPrimitive() && object.get(property).getAsJsonPrimitive().isNumber()) {
               returning_map.m_128365_(property, DoubleTag.m_128500_(object.get(property).getAsDouble()));
            } else if (object.get(property).isJsonPrimitive() && object.get(property).getAsJsonPrimitive().isString()) {
               returning_map.m_128365_(property, StringTag.m_129297_(object.get(property).getAsString()));
            } else if (object.get(property).isJsonPrimitive() && object.get(property).getAsJsonPrimitive().isBoolean()) {
               returning_map.m_128365_(property, ByteTag.m_128273_(object.get(property).getAsBoolean()));
            } else if (object.get(property).isJsonObject()) {
               returning_map.m_128365_(property, execute(object.get(property).getAsJsonObject()));
            }
         }

         return returning_map;
      }
   }
}
