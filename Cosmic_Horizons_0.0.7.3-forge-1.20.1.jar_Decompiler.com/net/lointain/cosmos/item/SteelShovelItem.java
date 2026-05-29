package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class SteelShovelItem extends ShovelItem {
   public SteelShovelItem() {
      super(new Tier() {
         public int m_6609_() {
            return 1480;
         }

         public float m_6624_() {
            return 8.5F;
         }

         public float m_6631_() {
            return 0.0F;
         }

         public int m_6604_() {
            return 3;
         }

         public int m_6601_() {
            return 7;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.STEEL_INGOT.get())});
         }
      }, 1.0F, -3.0F, new Item.Properties());
   }
}
