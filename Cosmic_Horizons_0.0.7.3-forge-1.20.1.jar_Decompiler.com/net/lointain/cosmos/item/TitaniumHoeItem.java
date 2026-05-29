package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class TitaniumHoeItem extends HoeItem {
   public TitaniumHoeItem() {
      super(new Tier() {
         public int m_6609_() {
            return 1900;
         }

         public float m_6624_() {
            return 8.0F;
         }

         public float m_6631_() {
            return 1.0F;
         }

         public int m_6604_() {
            return 4;
         }

         public int m_6601_() {
            return 9;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.TITANIUM_INGOT.get())});
         }
      }, 0, -3.0F, new Item.Properties());
   }
}
