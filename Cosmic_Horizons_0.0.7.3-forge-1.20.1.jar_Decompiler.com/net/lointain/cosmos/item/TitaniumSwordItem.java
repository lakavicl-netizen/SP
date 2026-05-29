package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class TitaniumSwordItem extends SwordItem {
   public TitaniumSwordItem() {
      super(new Tier() {
         public int m_6609_() {
            return 1800;
         }

         public float m_6624_() {
            return 4.0F;
         }

         public float m_6631_() {
            return 2.0F;
         }

         public int m_6604_() {
            return 2;
         }

         public int m_6601_() {
            return 8;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.TITANIUM_INGOT.get())});
         }
      }, 3, -3.0F, new Item.Properties());
   }
}
