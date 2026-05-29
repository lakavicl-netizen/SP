package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightingProcedure {
   public static void handleRender(PoseStack poseStack, float partialTicks, Camera camera) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      Level world = Minecraft.m_91087_().f_91073_;
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      Vec3 position = Vec3.f_82478_;
      Vec3 direction = Vec3.f_82478_;
      Vec3 color = Vec3.f_82478_;
      position = entity.m_20299_(partialTicks);
      direction = entity.m_20252_(partialTicks);
      color = new Vec3((double)1.0F, 0.88, 0.52);
      poseStack.m_85836_();
      ItemStack var10000;
      if (entity instanceof LivingEntity _entGetArmor) {
         var10000 = _entGetArmor.m_6844_(EquipmentSlot.HEAD);
      } else {
         var10000 = ItemStack.f_41583_;
      }

      if (var10000.m_41720_() == CosmosModItems.STEEL_FLASH_HELMET_ON_HELMET.get()) {
         AerialLightRenderer.renderLight(position, direction, 100.0F, 45.0F, 4.5F, 55.0F, color, poseStack);
      }

      poseStack.m_85849_();
   }
}
