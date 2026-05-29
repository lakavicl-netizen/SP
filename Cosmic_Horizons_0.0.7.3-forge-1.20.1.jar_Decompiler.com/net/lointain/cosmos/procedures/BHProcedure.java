package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;

public class BHProcedure {
   private static BlackHoleRenderData scheduledRenderData = null;

   public static void handleRender(PoseStack poseStack, float partialTicks, Camera camera) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      if (level != null) {
         if (scheduledRenderData != null) {
            renderBlackhole(poseStack, scheduledRenderData.position, scheduledRenderData.rotation, scheduledRenderData.color, (double)scheduledRenderData.size, (double)scheduledRenderData.intensity, (double)scheduledRenderData.step, (double)scheduledRenderData.speed, partialTicks);
            scheduledRenderData = null;
         }

      }
   }

   public static void scheduleRenderBlackhole(Vec3 position, Vec3 rotation, Vec3 color, double size, double intensity, double step, double speed) {
      scheduledRenderData = new BlackHoleRenderData(position, rotation, color, (float)size, (float)intensity, (float)step, (float)speed);
   }

   public static void renderBlackholeDirectly(PoseStack poseStack, Vec3 position, Vec3 rotation, Vec3 color, double size, double intensity, double step, double speed, float partialTicks) {
      renderBlackhole(poseStack, position, rotation, color, (double)((float)size), (double)((float)intensity), (double)((float)step), (double)((float)speed), partialTicks);
   }

   private static void renderBlackhole(PoseStack poseStack, Vec3 position, Vec3 rotation, Vec3 color, double size, double intensity, double step, double speed, float partialTicks) {
      Minecraft minecraft = Minecraft.m_91087_();
      float eventTick = (partialTicks + (float)minecraft.f_91065_.m_93079_()) / 92.0F;
      BlackHoleRenderer.renderBlackhole(position, rotation, color, (float)size, (float)intensity, (float)step, (float)speed, eventTick);
   }

   private static class BlackHoleRenderData {
      Vec3 position;
      Vec3 rotation;
      Vec3 color;
      float size;
      float intensity;
      float step;
      float speed;

      BlackHoleRenderData(Vec3 position, Vec3 rotation, Vec3 color, float size, float intensity, float step, float speed) {
         this.position = position;
         this.rotation = rotation;
         this.color = color;
         this.size = size;
         this.intensity = intensity;
         this.step = step;
         this.speed = speed;
      }
   }
}
