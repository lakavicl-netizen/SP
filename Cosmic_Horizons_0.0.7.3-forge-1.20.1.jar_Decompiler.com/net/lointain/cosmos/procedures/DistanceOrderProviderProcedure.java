package net.lointain.cosmos.procedures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.phys.Vec3;

public class DistanceOrderProviderProcedure {
   public static List<Object> execute(CompoundTag map, double order, String dimension, Vec3 position) {
      if (map != null && dimension != null && position != null) {
         Vec3 main_vector = Vec3.f_82478_;
         Map<Object, Object> starting_map = new HashMap();
         new ArrayList();
         List<Object> sorted_order = new ArrayList();
         if (map.m_128441_(dimension)) {
            main_vector = position;
            Tag var11 = map.m_128423_(dimension);
            ListTag var10000;
            if (var11 instanceof ListTag) {
               ListTag _listTag = (ListTag)var11;
               var10000 = _listTag.m_6426_();
            } else {
               var10000 = new ListTag();
            }

            ListTag Pos_list = var10000;

            for(int iter = 0; iter < Pos_list.size(); ++iter) {
               Vec3 var10002 = new Vec3;
               Object var10004 = new Object() {
                  double convert(String s) {
                     try {
                        return Double.parseDouble(s.trim());
                     } catch (Exception var3) {
                        return (double)0.0F;
                     }
                  }
               };
               Tag var12 = Pos_list.get(iter);
               String var10005;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10005 = _stringTag.m_7916_();
               } else {
                  var10005 = "";
               }

               var12 = Pos_list.get(iter);
               String var10006;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10006 = _stringTag.m_7916_();
               } else {
                  var10006 = "";
               }

               int var38 = var10006.indexOf("`") + "`".length();
               var12 = Pos_list.get(iter);
               String var10007;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10007 = _stringTag.m_7916_();
               } else {
                  var10007 = "";
               }

               double var35 = ((<undefinedtype>)var10004).convert(var10005.substring(var38, var10007.indexOf("~")));
               Object var36 = new Object() {
                  double convert(String s) {
                     try {
                        return Double.parseDouble(s.trim());
                     } catch (Exception var3) {
                        return (double)0.0F;
                     }
                  }
               };
               var12 = Pos_list.get(iter);
               String var39;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var39 = _stringTag.m_7916_();
               } else {
                  var39 = "";
               }

               var12 = Pos_list.get(iter);
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10007 = _stringTag.m_7916_();
               } else {
                  var10007 = "";
               }

               int var42 = var10007.indexOf("~") + "~".length();
               var12 = Pos_list.get(iter);
               String var10008;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10008 = _stringTag.m_7916_();
               } else {
                  var10008 = "";
               }

               double var37 = ((<undefinedtype>)var36).convert(var39.substring(var42, var10008.indexOf("|")));
               Object var40 = new Object() {
                  double convert(String s) {
                     try {
                        return Double.parseDouble(s.trim());
                     } catch (Exception var3) {
                        return (double)0.0F;
                     }
                  }
               };
               var12 = Pos_list.get(iter);
               String var43;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var43 = _stringTag.m_7916_();
               } else {
                  var43 = "";
               }

               var12 = Pos_list.get(iter);
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10008 = _stringTag.m_7916_();
               } else {
                  var10008 = "";
               }

               int var45 = var10008.indexOf("|") + "|".length();
               var12 = Pos_list.get(iter);
               String var10009;
               if (var12 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var12;
                  var10009 = _stringTag.m_7916_();
               } else {
                  var10009 = "";
               }

               var10002.<init>(var35, var37, ((<undefinedtype>)var40).convert(var43.substring(var45, var10009.indexOf("\\"))));
               starting_map.put(main_vector.m_82554_(var10002), iter);
            }

            List<Object> sorting_list = starting_map.keySet().stream().toList();

            try {
               sorting_list = sorting_list.stream().sorted().toList();
            } catch (Exception e) {
               e.printStackTrace();
            }

            for(Object _listValueIterator : sorting_list) {
               sorted_order.add(starting_map.get(_listValueIterator));
            }

            if (order == (double)-1.0F) {
               Collections.reverse(sorted_order);
            }
         }

         return sorted_order;
      } else {
         return new ArrayList();
      }
   }
}
