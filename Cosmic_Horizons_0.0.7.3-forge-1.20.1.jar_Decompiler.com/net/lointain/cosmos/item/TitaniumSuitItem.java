package net.lointain.cosmos.item;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import net.lointain.cosmos.client.model.Modelspace_suit;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public abstract class TitaniumSuitItem extends ArmorItem {
   public TitaniumSuitItem(ArmorItem.Type type, Item.Properties properties) {
      super(new ArmorMaterial() {
         public int m_266425_(ArmorItem.Type type) {
            return (new int[]{13, 15, 16, 11})[type.m_266308_().m_20749_()] * 19;
         }

         public int m_7366_(ArmorItem.Type type) {
            return (new int[]{2, 5, 6, 2})[type.m_266308_().m_20749_()];
         }

         public int m_6646_() {
            return 9;
         }

         public SoundEvent m_7344_() {
            return SoundEvents.f_271165_;
         }

         public Ingredient m_6230_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)CosmosModItems.TITANIUM_PLATE.get())});
         }

         public String m_6082_() {
            return "titanium_suit";
         }

         public float m_6651_() {
            return 0.0F;
         }

         public float m_6649_() {
            return 0.0F;
         }
      }, type, properties);
   }

   public static class Helmet extends TitaniumSuitItem {
      public Helmet() {
         super(Type.HELMET, new Item.Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(new IClientItemExtensions() {
            // $FF: synthetic field
            final TitaniumSuitItem.Helmet this$0;

            {
               this.this$0 = this$0;
            }

            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
               HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
               armorModel.f_102817_ = living.m_6144_();
               armorModel.f_102609_ = defaultModel.f_102609_;
               armorModel.f_102610_ = living.m_6162_();
               return armorModel;
            }
         });
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/entities/space_suit_titanium_vibrant.png";
      }
   }

   public static class Chestplate extends TitaniumSuitItem {
      public Chestplate() {
         super(Type.CHESTPLATE, new Item.Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(new IClientItemExtensions() {
            // $FF: synthetic field
            final TitaniumSuitItem.Chestplate this$0;

            {
               this.this$0 = this$0;
            }

            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
               HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).Body, "left_arm", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).LeftArm, "right_arm", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
               armorModel.f_102817_ = living.m_6144_();
               armorModel.f_102609_ = defaultModel.f_102609_;
               armorModel.f_102610_ = living.m_6162_();
               return armorModel;
            }
         });
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/entities/space_suit_titanium_vibrant.png";
      }
   }

   public static class Leggings extends TitaniumSuitItem {
      public Leggings() {
         super(Type.LEGGINGS, new Item.Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(new IClientItemExtensions() {
            // $FF: synthetic field
            final TitaniumSuitItem.Leggings this$0;

            {
               this.this$0 = this$0;
            }

            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
               HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).LeftLeg, "right_leg", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).RightLeg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
               armorModel.f_102817_ = living.m_6144_();
               armorModel.f_102609_ = defaultModel.f_102609_;
               armorModel.f_102610_ = living.m_6162_();
               return armorModel;
            }
         });
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/entities/space_suit_titanium_vibrant.png";
      }
   }

   public static class Boots extends TitaniumSuitItem {
      public Boots() {
         super(Type.BOOTS, new Item.Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(new IClientItemExtensions() {
            // $FF: synthetic field
            final TitaniumSuitItem.Boots this$0;

            {
               this.this$0 = this$0;
            }

            @OnlyIn(Dist.CLIENT)
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
               HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).LeftFoot, "right_leg", (new Modelspace_suit(Minecraft.m_91087_().m_167973_().m_171103_(Modelspace_suit.LAYER_LOCATION))).RightFoot, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
               armorModel.f_102817_ = living.m_6144_();
               armorModel.f_102609_ = defaultModel.f_102609_;
               armorModel.f_102610_ = living.m_6162_();
               return armorModel;
            }
         });
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "cosmos:textures/entities/space_suit_titanium_vibrant.png";
      }
   }
}
