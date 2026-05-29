package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class InitialiseregistryProcedure {
   public static void execute(LevelAccessor world) {
      String attachedDimensionID = "";
      String texts = "";
      List<Object> jsons = new ArrayList();
      List<Object> order = new ArrayList();
      new JsonObject();
      JsonObject data = new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      ListTag newlist = new ListTag();
      new ListTag();
      new ListTag();
      new ListTag();
      ListTag sent_list = new ListTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         String var10000;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var10000 = _stringTag.m_7916_();
         } else {
            var10000 = "";
         }

         texts = var10000;

         try {
            data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
         } catch (Exception var27) {
         }

         if (data.has("gui_data")) {
            JsonObject gui_data = data.get("gui_data").getAsJsonObject();

            for(int j = 0; j <= gui_data.size() - 1; ++j) {
               JsonObject iter = gui_data.get((String)gui_data.keySet().stream().toList().get(j)).getAsJsonObject();
               order.add(iter.get("order").getAsDouble());
               jsons.add(iter);
            }
         }
      }

      for(int m = 0; m < order.size() - 1; ++m) {
         for(int l = 0; l < order.size() - m - 1; ++l) {
            Object var25 = order.get(l);
            double var43;
            if (var25 instanceof Number _doubleValue) {
               var43 = _doubleValue.doubleValue();
            } else {
               var43 = (double)0.0F;
            }

            var25 = order.get(l + 1);
            double var10001;
            if (var25 instanceof Number _doubleValue) {
               var10001 = _doubleValue.doubleValue();
            } else {
               var10001 = (double)0.0F;
            }

            if (var43 > var10001) {
               Collections.swap(order, l, l + 1);
               Collections.swap(jsons, l, l + 1);
            }
         }
      }

      for(Object _listValueIterator : jsons) {
         newlist.m_7614_(newlist.size(), StringTag.m_129297_((new Gson()).toJson(_listValueIterator)));
      }

      CosmosModVariables.WorldVariables.get(world).planet_sel_data = newlist;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CompoundTag gravity_map = new CompoundTag();
      CompoundTag friction_map = new CompoundTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         JsonObject var44;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var44 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var44 = new JsonObject();
         }

         data = var44;
         if (data.has("dimensional_data")) {
            JsonObject gravity_data = data.get("dimensional_data").getAsJsonObject();
            if (gravity_data.has("gravity")) {
               gravity_map.m_128365_(data.get("attached_dimention_id").getAsString(), FloatTag.m_128566_((float)(gravity_data.get("gravity").getAsDouble() / (double)100.0F)));
               friction_map.m_128365_(data.get("attached_dimention_id").getAsString(), FloatTag.m_128566_((float)gravity_data.get("air_resistance").getAsDouble()));
            }
         }
      }

      CosmosModVariables.WorldVariables.get(world).gravity_data = gravity_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).friction_data = friction_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         String var45;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var45 = _stringTag.m_7916_();
         } else {
            var45 = "";
         }

         texts = var45;

         try {
            data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
         } catch (Exception var26) {
         }

         if (data.has("local_id") && data.has("planet_data")) {
            sent_list.m_7614_(sent_list.size(), StringTag.m_129297_((new Gson()).toJson(data)));
         }
      }

      CosmosModVariables.WorldVariables.get(world).projection_list = sent_list;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      InitialiseCollisionDataProcedure.execute(world);
      InitialiseLightSourceDataProcedure.execute(world);
      InitialiseRenderSequenceProcedure.execute(world);
      InitialiseDimensionalDataProcedure.execute(world);
   }
}
