package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class SteelAxeItem extends AxeItem {
   public SteelAxeItem() {
      super(new Tier() {
         public int m_6609_() {
            return 1000;
         }

         public float m_6624_() {
            return 3.0F;
         }

         public float m_6631_() {
            return 3.0F;
         }

         public int m_6604_() {
            return 2;
         }

         public int m_6601_() {
            return 8;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.STEEL_INGOT.get())});
         }
      }, 1.0F, -3.1F, new Item.Properties());
   }
}
