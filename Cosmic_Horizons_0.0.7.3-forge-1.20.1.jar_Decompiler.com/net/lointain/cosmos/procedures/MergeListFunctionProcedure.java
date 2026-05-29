package net.lointain.cosmos.procedures;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class MergeListFunctionProcedure {
   public static ListTag execute(ListTag List1, ListTag List2) {
      if (List1 != null && List2 != null) {
         ListTag list1 = List1;

         for(Tag dataelementiterator : List2) {
            list1.m_7614_(list1.size(), dataelementiterator);
         }

         return list1;
      } else {
         return new ListTag();
      }
   }
}
