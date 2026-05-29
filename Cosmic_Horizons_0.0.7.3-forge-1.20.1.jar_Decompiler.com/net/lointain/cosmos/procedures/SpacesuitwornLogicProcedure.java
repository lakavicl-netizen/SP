package net.lointain.cosmos.procedures;

import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class SpacesuitwornLogicProcedure {
   public static boolean execute(LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         boolean dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var4 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var10000;
            if (var4 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var4;
               var10000 = _stringTag.m_7916_();
            } else {
               var10000 = "";
            }

            if (var10000.equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         if (dimention_check && !(entity.m_20202_() instanceof RocketSeatEntity)) {
            ItemStack var18;
            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() != CosmosModItems.STEEL_SUIT_BOOTS.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var18 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
               } else {
                  var18 = ItemStack.f_41583_;
               }

               if (var18.m_41720_() != CosmosModItems.TITANIUM_SUIT_BOOTS.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _entGetArmor = (LivingEntity)entity;
                     var18 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
                  } else {
                     var18 = ItemStack.f_41583_;
                  }

                  if (var18.m_41720_() != CosmosModItems.NICKEL_SUIT_BOOTS.get()) {
                     return false;
                  }
               }
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() != CosmosModItems.STEEL_SUIT_LEGGINGS.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var18 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
               } else {
                  var18 = ItemStack.f_41583_;
               }

               if (var18.m_41720_() != CosmosModItems.TITANIUM_SUIT_LEGGINGS.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _entGetArmor = (LivingEntity)entity;
                     var18 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
                  } else {
                     var18 = ItemStack.f_41583_;
                  }

                  if (var18.m_41720_() != CosmosModItems.NICKEL_SUIT_LEGGINGS.get()) {
                     return false;
                  }
               }
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() != CosmosModItems.STEEL_SUIT_CHESTPLATE.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var18 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
               } else {
                  var18 = ItemStack.f_41583_;
               }

               if (var18.m_41720_() != CosmosModItems.TITANIUM_SUIT_CHESTPLATE.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _entGetArmor = (LivingEntity)entity;
                     var18 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
                  } else {
                     var18 = ItemStack.f_41583_;
                  }

                  if (var18.m_41720_() != CosmosModItems.NICKEL_SUIT_CHESTPLATE.get()) {
                     return false;
                  }
               }
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() == CosmosModItems.STEEL_SUIT_HELMET.get()) {
               return true;
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() == CosmosModItems.TITANIUM_SUIT_HELMET.get()) {
               return true;
            }

            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var18 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
            } else {
               var18 = ItemStack.f_41583_;
            }

            if (var18.m_41720_() == CosmosModItems.NICKEL_SUIT_HELMET.get()) {
               return true;
            }
         }

         return false;
      }
   }
}
