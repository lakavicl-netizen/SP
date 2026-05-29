package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class NickelPickaxeItem extends PickaxeItem {
   public NickelPickaxeItem() {
      super(new Tier() {
         public int m_6609_() {
            return 800;
         }

         public float m_6624_() {
            return 8.5F;
         }

         public float m_6631_() {
            return 0.0F;
         }

         public int m_6604_() {
            return 1;
         }

         public int m_6601_() {
            return 3;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.NICKLE_INGOT.get())});
         }
      }, 1, -3.0F, new Item.Properties());
   }
}
