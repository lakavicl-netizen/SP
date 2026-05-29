package net.lointain.cosmos.procedures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexBuffer.Usage;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.math.Axis;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.DimensionSpecialEffectsManager;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.joml.Matrix4f;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class RenderSunlightsProcedure {
   private static int ticks = 0;
   private static float partialTick = 0.0F;
   private static PoseStack poseStack = null;
   private static Matrix4f projectionMatrix = null;
   private static Runnable setupFog = null;
   private static VertexBuffer abyssBuffer = null;
   private static VertexBuffer deepSkyBuffer = null;
   private static VertexBuffer skyboxBuffer = null;
   private static VertexBuffer starBuffer = null;
   private static int amount = 0;
   private static int seed = 0;
   private static final Predicate<Object[]> PREDICATE = (params) -> {
      ticks = (Integer)params[1];
      partialTick = (Float)params[2];
      poseStack = (PoseStack)params[3];
      projectionMatrix = (Matrix4f)params[5];
      setupFog = (Runnable)params[7];
      FogRenderer.m_109036_();
      setupFog.run();
      Minecraft minecraft = Minecraft.m_91087_();
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      if (entity != null) {
         ClientLevel level = minecraft.f_91073_;
         Vec3 pos = entity.m_20318_(partialTick);
         execute((Event)null, level, entity);
      }

      return false;
   };

   public static void renderAbyss(int color, boolean constant) {
      Minecraft minecraft = Minecraft.m_91087_();
      boolean visible = minecraft.f_91074_.m_20299_(partialTick).m_7098_() - minecraft.f_91073_.m_6106_().m_171687_(minecraft.f_91073_) < (double)0.0F;
      if (visible || constant) {
         if (abyssBuffer != null) {
            abyssBuffer.m_85921_();
         } else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::m_172808_);
            BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
            bufferBuilder.m_166779_(Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85814_);
            bufferBuilder.m_5483_((double)0.0F, (double)-16.0F, (double)0.0F).m_5752_();

            for(int i = 0; i <= 8; ++i) {
               bufferBuilder.m_5483_((double)(-512.0F * Mth.m_14089_((float)i * 45.0F * ((float)Math.PI / 180F))), (double)-16.0F, (double)(512.0F * Mth.m_14031_((float)i * 45.0F * ((float)Math.PI / 180F)))).m_5752_();
            }

            abyssBuffer = new VertexBuffer(Usage.STATIC);
            abyssBuffer.m_85921_();
            abyssBuffer.m_231221_(bufferBuilder.m_231175_());
         }

         poseStack.m_85836_();
         poseStack.m_252880_(0.0F, 12.0F, 0.0F);
         RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
         abyssBuffer.m_253207_(poseStack.m_85850_().m_252922_(), projectionMatrix, GameRenderer.m_172808_());
         VertexBuffer.m_85931_();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         poseStack.m_85849_();
      }

   }

   public static void renderDeepSky(int color) {
      if (deepSkyBuffer == null) {
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::m_172808_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85814_);
         bufferBuilder.m_5483_((double)0.0F, (double)16.0F, (double)0.0F).m_193479_(color).m_5752_();

         for(int i = 0; i <= 8; ++i) {
            bufferBuilder.m_5483_((double)(512.0F * Mth.m_14089_(45.0F * (float)i * ((float)Math.PI / 180F))), (double)16.0F, (double)(512.0F * Mth.m_14031_(45.0F * (float)i * ((float)Math.PI / 180F)))).m_5752_();
         }

         deepSkyBuffer = new VertexBuffer(Usage.STATIC);
         deepSkyBuffer.m_85921_();
         deepSkyBuffer.m_231221_(bufferBuilder.m_231175_());
      } else {
         deepSkyBuffer.m_85921_();
      }

      RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
      deepSkyBuffer.m_253207_(poseStack.m_85850_().m_252922_(), projectionMatrix, GameRenderer.m_172808_());
      VertexBuffer.m_85931_();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void renderEndSky(float yaw, float pitch, float roll, int color, boolean constant) {
      Minecraft minecraft = Minecraft.m_91087_();
      Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
      boolean invisible = minecraft.f_91073_.m_104583_().m_5781_(Mth.m_14107_(pos.m_7096_()), Mth.m_14107_(pos.m_7098_())) || minecraft.f_91065_.m_93090_().m_93715_();
      if (!invisible || constant) {
         poseStack.m_85836_();
         poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
         poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
         poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
         Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
         RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
         RenderSystem.setShader(GameRenderer::m_172817_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85817_);

         for(int i = 0; i < 6; ++i) {
            switch (i) {
               case 0:
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, -100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, 100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, 100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, -100.0F).m_7421_(16.0F, 0.0F).m_5752_();
                  break;
               case 1:
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, 100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, 100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, 100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, 100.0F).m_7421_(16.0F, 0.0F).m_5752_();
                  break;
               case 2:
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, -100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, -100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, -100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, -100.0F).m_7421_(16.0F, 0.0F).m_5752_();
                  break;
               case 3:
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, 100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, -100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, -100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, 100.0F).m_7421_(16.0F, 0.0F).m_5752_();
                  break;
               case 4:
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, -100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, 100.0F, 100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, 100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, -100.0F, -100.0F, -100.0F).m_7421_(16.0F, 0.0F).m_5752_();
                  break;
               case 5:
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, -100.0F).m_7421_(0.0F, 0.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, -100.0F, 100.0F).m_7421_(0.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, 100.0F).m_7421_(16.0F, 16.0F).m_5752_();
                  bufferBuilder.m_252986_(matrix4f, 100.0F, 100.0F, -100.0F).m_7421_(16.0F, 0.0F).m_5752_();
            }
         }

         BufferUploader.m_231202_(bufferBuilder.m_231175_());
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         poseStack.m_85849_();
      }

   }

   public static void renderMoon(float size, int color, boolean phase, boolean constant) {
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      float r = size / 2.0F;
      float u0 = 0.0F;
      float v0 = 0.0F;
      float u1 = 1.0F;
      float v1 = 1.0F;
      if (phase) {
         int i0 = level.m_46941_();
         int i1 = i0 & 3;
         int i2 = i0 >> 2 & 1;
         u0 = (float)i1 / 4.0F;
         v0 = (float)i2 / 2.0F;
         u1 = (float)(i1 + 1) / 4.0F;
         v1 = (float)(i2 + 1) / 2.0F;
      }

      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= 1.0F - level.m_46722_(partialTick);
      }

      poseStack.m_85836_();
      poseStack.m_252781_(Axis.f_252403_.m_252977_(level.m_46942_(partialTick) * 360.0F));
      Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
      RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
      RenderSystem.setShader(GameRenderer::m_172817_);
      BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
      bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85817_);
      bufferBuilder.m_252986_(matrix4f, -r, -100.0F, -r).m_7421_(u1, v1).m_5752_();
      bufferBuilder.m_252986_(matrix4f, -r, -100.0F, r).m_7421_(u0, v1).m_5752_();
      bufferBuilder.m_252986_(matrix4f, r, -100.0F, r).m_7421_(u0, v0).m_5752_();
      bufferBuilder.m_252986_(matrix4f, r, -100.0F, -r).m_7421_(u1, v0).m_5752_();
      BufferUploader.m_231202_(bufferBuilder.m_231175_());
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   public static void renderSky(boolean deepSky, boolean sunlights, boolean sun, boolean moon, boolean stars, boolean abyss) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      if (deepSky) {
         Vec3 color = level.m_171660_(minecraft.f_91063_.m_109153_().m_90583_(), partialTick);
         RenderSystem.defaultBlendFunc();
         renderDeepSky(-16777216 | (int)(color.m_7096_() * (double)255.0F) << 16 | (int)(color.m_7098_() * (double)255.0F) << 8 | (int)(color.m_7094_() * (double)255.0F));
      }

      if (sunlights) {
         float[] color = level.m_104583_().m_7518_(level.m_46942_(partialTick), partialTick);
         if (color != null) {
            RenderSystem.defaultBlendFunc();
            renderSunlights((int)(color[3] * 255.0F) << 24 | (int)(color[0] * 255.0F) << 16 | (int)(color[1] * 255.0F) << 8 | (int)(color[2] * 255.0F));
         }
      }

      if (sun) {
         RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft:textures/environment/sun.png"));
         RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
         renderSun(60.0F, -1, false);
      }

      if (moon) {
         RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft:textures/environment/moon_phases.png"));
         RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
         renderMoon(40.0F, -1, true, false);
      }

      if (stars) {
         int color = (int)(level.m_104811_(partialTick) * 255.0F);
         RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
         renderStars(1500, 10842, 90.0F, level.m_46942_(partialTick) * 360.0F, 0.0F, color << 24 | color << 16 | color << 8 | color, false);
      }

      if (abyss) {
         RenderSystem.defaultBlendFunc();
         renderAbyss(-16777216, false);
      }

   }

   public static void renderSkybox(float yaw, float pitch, float roll, int color, boolean constant) {
      Minecraft minecraft = Minecraft.m_91087_();
      Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
      boolean invisible = minecraft.f_91073_.m_104583_().m_5781_(Mth.m_14107_(pos.m_7096_()), Mth.m_14107_(pos.m_7098_())) || minecraft.f_91065_.m_93090_().m_93715_();
      if (!invisible || constant) {
         if (skyboxBuffer != null) {
            skyboxBuffer.m_85921_();
         } else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::m_172817_);
            BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
            bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85817_);

            for(int i = 0; i < 6; ++i) {
               switch (i) {
                  case 0:
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)0.5F).m_7421_(0.0F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)0.5F).m_7421_(0.33333334F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.33333334F, 0.0F).m_5752_();
                     break;
                  case 1:
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)0.5F).m_7421_(0.33333334F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.33333334F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.6666667F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)0.5F).m_7421_(0.6666667F, 0.0F).m_5752_();
                     break;
                  case 2:
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)0.5F).m_7421_(0.6666667F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)0.5F).m_7421_(0.6666667F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)0.5F).m_7421_(1.0F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)0.5F).m_7421_(1.0F, 0.0F).m_5752_();
                     break;
                  case 3:
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)0.5F).m_7421_(0.0F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)0.5F).m_7421_(0.0F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.33333334F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.33333334F, 0.5F).m_5752_();
                     break;
                  case 4:
                     bufferBuilder.m_5483_((double)-0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.33333334F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)-0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.33333334F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.6666667F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.6666667F, 0.5F).m_5752_();
                     break;
                  case 5:
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)-0.5F).m_7421_(0.6666667F, 0.5F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)-0.5F).m_7421_(0.6666667F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)-0.5F, (double)0.5F).m_7421_(1.0F, 1.0F).m_5752_();
                     bufferBuilder.m_5483_((double)0.5F, (double)0.5F, (double)0.5F).m_7421_(1.0F, 0.5F).m_5752_();
               }
            }

            skyboxBuffer = new VertexBuffer(Usage.STATIC);
            skyboxBuffer.m_85921_();
            skyboxBuffer.m_231221_(bufferBuilder.m_231175_());
         }

         float size = (float)(minecraft.f_91066_.m_193772_() << 6);
         poseStack.m_85836_();
         poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
         poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
         poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
         poseStack.m_85841_(size, size, size);
         RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
         skyboxBuffer.m_253207_(poseStack.m_85850_().m_252922_(), projectionMatrix, GameRenderer.m_172817_());
         VertexBuffer.m_85931_();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         poseStack.m_85849_();
      }

   }

   public static void renderStars(int amount, int seed, float yaw, float pitch, float roll, int color, boolean constant) {
      if (starBuffer != null && amount == RenderSunlightsProcedure.amount && seed == RenderSunlightsProcedure.seed) {
         starBuffer.m_85921_();
      } else {
         RenderSunlightsProcedure.amount = amount;
         RenderSunlightsProcedure.seed = seed;
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::m_172808_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85814_);
         RandomSource randomsource = RandomSource.m_216335_((long)seed);

         for(int i = 0; i < amount; ++i) {
            float f0 = randomsource.m_188501_() * 2.0F - 1.0F;
            float f1 = randomsource.m_188501_() * 2.0F - 1.0F;
            float f2 = randomsource.m_188501_() * 2.0F - 1.0F;
            float f3 = 0.15F + 0.1F * randomsource.m_188501_();
            float f4 = f0 * f0 + f1 * f1 + f2 * f2;
            if (f4 < 1.0F && f4 > 0.01F) {
               f4 = 1.0F / Mth.m_14116_(f4);
               f0 *= f4;
               f1 *= f4;
               f2 *= f4;
               float f5 = f0 * 100.0F;
               float f6 = f1 * 100.0F;
               float f7 = f2 * 100.0F;
               float f8 = (float)Math.atan2((double)f0, (double)f2);
               float f9 = Mth.m_14031_(f8);
               float f10 = Mth.m_14089_(f8);
               float f11 = (float)Math.atan2((double)Mth.m_14116_(f0 * f0 + f2 * f2), (double)f1);
               float f12 = Mth.m_14031_(f11);
               float f13 = Mth.m_14089_(f11);
               float f14 = (float)randomsource.m_188500_() * (float)Math.PI * 2.0F;
               float f15 = Mth.m_14031_(f14);
               float f16 = Mth.m_14089_(f14);

               for(int j = 0; j < 4; ++j) {
                  float f17 = (float)((j & 2) - 1) * f3;
                  float f18 = (float)((j + 1 & 2) - 1) * f3;
                  float f20 = f17 * f16 - f18 * f15;
                  float f21 = f18 * f16 + f17 * f15;
                  float f22 = -f20 * f13;
                  float f23 = f22 * f9 - f21 * f10;
                  float f24 = f20 * f12;
                  float f25 = f21 * f9 + f22 * f10;
                  bufferBuilder.m_5483_((double)(f5 + f23), (double)(f6 + f24), (double)(f7 + f25)).m_5752_();
               }
            }
         }

         if (starBuffer != null) {
            starBuffer.close();
         }

         starBuffer = new VertexBuffer(Usage.STATIC);
         starBuffer.m_85921_();
         starBuffer.m_231221_(bufferBuilder.m_231175_());
      }

      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= 1.0F - Minecraft.m_91087_().f_91073_.m_46722_(partialTick);
      }

      poseStack.m_85836_();
      poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
      poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
      poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
      FogRenderer.m_109017_();
      RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
      starBuffer.m_253207_(poseStack.m_85850_().m_252922_(), projectionMatrix, GameRenderer.m_172808_());
      VertexBuffer.m_85931_();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      setupFog.run();
      poseStack.m_85849_();
   }

   public static void renderSun(float size, int color, boolean constant) {
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      float r = size / 2.0F;
      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= 1.0F - level.m_46722_(partialTick);
      }

      poseStack.m_85836_();
      poseStack.m_252781_(Axis.f_252403_.m_252977_(level.m_46942_(partialTick) * 360.0F));
      Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
      RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
      RenderSystem.setShader(GameRenderer::m_172817_);
      BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
      bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85817_);
      bufferBuilder.m_252986_(matrix4f, r, 100.0F, -r).m_7421_(0.0F, 0.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, r, 100.0F, r).m_7421_(1.0F, 0.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, -r, 100.0F, r).m_7421_(1.0F, 1.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, -r, 100.0F, -r).m_7421_(0.0F, 1.0F).m_5752_();
      BufferUploader.m_231202_(bufferBuilder.m_231175_());
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   public static void renderSunlights(int color) {
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      float[] rawColor = level.m_104583_().m_7518_(level.m_46942_(partialTick), partialTick);
      if (rawColor != null) {
         int red = color >> 16 & 255;
         int green = color >> 8 & 255;
         int blue = color & 255;
         int alpha = (int)((float)(color >>> 24) * rawColor[3]);
         Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::m_172811_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
         boolean flag = Mth.m_14031_(level.m_46490_(partialTick)) < 0.0F;
         if (flag) {
            bufferBuilder.m_252986_(matrix4f, 100.0F, 0.0F, 0.0F).m_6122_(red, green, blue, alpha).m_5752_();
         } else {
            bufferBuilder.m_252986_(matrix4f, -100.0F, 0.0F, 0.0F).m_6122_(red, green, blue, alpha).m_5752_();
         }

         for(int i = 0; i <= 16; ++i) {
            float deg = (float)i * ((float)Math.PI * 2F) / 16.0F;
            float sin = Mth.m_14031_(deg);
            float cos = Mth.m_14089_(deg);
            if (flag) {
               bufferBuilder.m_252986_(matrix4f, cos * 120.0F, cos * 40.0F * rawColor[3], -sin * 120.0F).m_6122_(red, green, blue, 0).m_5752_();
            } else {
               bufferBuilder.m_252986_(matrix4f, -cos * 120.0F, cos * 40.0F * rawColor[3], sin * 120.0F).m_6122_(red, green, blue, 0).m_5752_();
            }
         }

         BufferUploader.m_231202_(bufferBuilder.m_231175_());
      }

   }

   public static void renderTexture(float size, float yaw, float pitch, float roll, int color, boolean constant) {
      float r = size / 2.0F;
      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= 1.0F - Minecraft.m_91087_().f_91073_.m_46722_(partialTick);
      }

      poseStack.m_85836_();
      poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
      poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
      poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
      Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
      RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, alpha);
      RenderSystem.setShader(GameRenderer::m_172817_);
      BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
      bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85817_);
      bufferBuilder.m_252986_(matrix4f, r, r, 100.0F).m_7421_(0.0F, 0.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, r, -r, 100.0F).m_7421_(0.0F, 1.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, -r, -r, 100.0F).m_7421_(1.0F, 1.0F).m_5752_();
      bufferBuilder.m_252986_(matrix4f, -r, r, 100.0F).m_7421_(1.0F, 0.0F).m_5752_();
      BufferUploader.m_231202_(bufferBuilder.m_231175_());
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   @SubscribeEvent
   public static void skySetup(FMLClientSetupEvent event) {
      try {
         Field field = DimensionSpecialEffectsManager.class.getDeclaredField("EFFECTS");
         field.setAccessible(true);
         UnmodifiableIterator var2 = ((ImmutableMap)field.get((Object)null)).values().iterator();

         while(var2.hasNext()) {
            DimensionSpecialEffects dimensionSpecialEffects = (DimensionSpecialEffects)var2.next();
            if (dimensionSpecialEffects instanceof SetupProcedure.CosmosModDimensionSpecialEffects cosmosSpecialEffects) {
               Class<?> effects = dimensionSpecialEffects.getClass();
               ((Set)effects.getField("CUSTOM_SKY").get((Object)null)).add(PREDICATE);
            }
         }
      } catch (Exception var6) {
      }

   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (CosmosModVariables.WorldVariables.get(world).skybox_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var6 = CosmosModVariables.WorldVariables.get(world).skybox_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (var6 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var6;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag skybox_data = var10000;
            if (skybox_data.m_128441_("sunlight_color")) {
               var6 = skybox_data.m_128423_("sunlight_color");
               if (var6 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var6;
                  var10000 = _compoundTag.m_6426_();
               } else {
                  var10000 = new CompoundTag();
               }

               CompoundTag sunlight_data = var10000;
               Tag var9 = sunlight_data.m_128423_("alpha");
               double var18;
               if (var9 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var9;
                  var18 = _doubleTag.m_7061_();
               } else {
                  var18 = (double)0.0F;
               }

               int var19 = (int)var18 << 24;
               var9 = sunlight_data.m_128423_("r");
               double var10001;
               if (var9 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var9;
                  var10001 = _doubleTag.m_7061_();
               } else {
                  var10001 = (double)0.0F;
               }

               var19 |= (int)var10001 << 16;
               var9 = sunlight_data.m_128423_("g");
               if (var9 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var9;
                  var10001 = _doubleTag.m_7061_();
               } else {
                  var10001 = (double)0.0F;
               }

               var19 |= (int)var10001 << 8;
               var9 = sunlight_data.m_128423_("b");
               if (var9 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var9;
                  var10001 = _doubleTag.m_7061_();
               } else {
                  var10001 = (double)0.0F;
               }

               renderSunlights(var19 | (int)var10001);
            }
         }

      }
   }
}
