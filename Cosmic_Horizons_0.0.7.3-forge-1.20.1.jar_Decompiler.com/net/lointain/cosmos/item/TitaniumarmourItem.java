package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class TitaniumarmourItem extends ArmorItem {
   public TitaniumarmourItem(ArmorItem.Type type, Item.Properties properties) {
      super(new ArmorMaterial() {
         public int m_266425_(ArmorItem.Type type) {
            return (new int[]{13, 15, 16, 11})[type.m_266308_().m_20749_()] * 15;
         }

         public int m_7366_(ArmorItem.Type type) {
            return (new int[]{2, 9, 10, 8})[type.m_266308_().m_20749_()];
         }

         public int m_6646_() {
            return 9;
         }

         public SoundEvent m_7344_() {
            return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_chain"));
         }

         public Ingredient m_6230_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.TITANIUM_INGOT.get())});
         }

         public String m_6082_() {
            return "titaniumarmour";
         }

         public float m_6651_() {
            return 0.0F;
         }

         public float m_6649_() {
            return 0.3F;
         }
      }, type, properties);
   }

   public static class Helmet extends TitaniumarmourItem {
      public Helmet() {
         super(Type.HELMET, new Item.Properties());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/models/armor/titanium_armor_layer_1.png";
      }
   }

   public static class Chestplate extends TitaniumarmourItem {
      public Chestplate() {
         super(Type.CHESTPLATE, new Item.Properties());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/models/armor/titanium_armor_layer_1.png";
      }
   }

   public static class Leggings extends TitaniumarmourItem {
      public Leggings() {
         super(Type.LEGGINGS, new Item.Properties());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/models/armor/titanium_armor_layer_2.png";
      }
   }

   public static class Boots extends TitaniumarmourItem {
      public Boots() {
         super(Type.BOOTS, new Item.Properties());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/models/armor/titanium_armor_layer_1.png";
      }
   }
}
