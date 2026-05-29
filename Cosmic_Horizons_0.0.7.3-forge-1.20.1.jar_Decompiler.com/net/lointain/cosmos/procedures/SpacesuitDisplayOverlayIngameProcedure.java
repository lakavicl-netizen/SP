package net.lointain.cosmos.procedures;

import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SpacesuitDisplayOverlayIngameProcedure {
   public static boolean execute(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         ItemStack var10000;
         if (entity instanceof LivingEntity) {
            LivingEntity _entGetArmor = (LivingEntity)entity;
            var10000 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() != CosmosModItems.STEEL_SUIT_BOOTS.get()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var10000 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUM_SUIT_BOOTS.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var10000 = _entGetArmor.m_6844_(EquipmentSlot.FEET);
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.NICKEL_SUIT_BOOTS.get()) {
                  return false;
               }
            }
         }

         if (entity instanceof LivingEntity) {
            LivingEntity _entGetArmor = (LivingEntity)entity;
            var10000 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() != CosmosModItems.STEEL_SUIT_LEGGINGS.get()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var10000 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUM_SUIT_LEGGINGS.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var10000 = _entGetArmor.m_6844_(EquipmentSlot.LEGS);
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.NICKEL_SUIT_LEGGINGS.get()) {
                  return false;
               }
            }
         }

         if (entity instanceof LivingEntity) {
            LivingEntity _entGetArmor = (LivingEntity)entity;
            var10000 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() != CosmosModItems.STEEL_SUIT_CHESTPLATE.get()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var10000 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUM_SUIT_CHESTPLATE.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var10000 = _entGetArmor.m_6844_(EquipmentSlot.CHEST);
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.NICKEL_SUIT_CHESTPLATE.get()) {
                  return false;
               }
            }
         }

         if (entity instanceof LivingEntity) {
            LivingEntity _entGetArmor = (LivingEntity)entity;
            var10000 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() != CosmosModItems.STEEL_SUIT_HELMET.get()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _entGetArmor = (LivingEntity)entity;
               var10000 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUM_SUIT_HELMET.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entGetArmor = (LivingEntity)entity;
                  var10000 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.NICKEL_SUIT_HELMET.get()) {
                  return false;
               }
            }
         }

         if (!(entity.m_20202_() instanceof RocketSeatEntity)) {
            return true;
         } else {
            return false;
         }
      }
   }
}
