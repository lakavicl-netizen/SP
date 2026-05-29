package net.lointain.cosmos.procedures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexBuffer.Usage;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
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
import org.lwjgl.opengl.GL11;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class DisableCloudsProcedure {
   private static int ticks = 0;
   private static float partialTick = 0.0F;
   private static PoseStack poseStack = null;
   private static Matrix4f projectionMatrix = null;
   private static VertexBuffer cloudBuffer = null;
   private static CloudStatus cloudStatus = null;
   private static double x = (double)0.0F;
   private static double y = (double)0.0F;
   private static double z = (double)0.0F;
   private static float width = 12.0F;
   private static float height = 4.0F;
   private static final Predicate<Object[]> PREDICATE = (params) -> {
      ticks = (Integer)params[1];
      partialTick = (Float)params[2];
      poseStack = (PoseStack)params[3];
      projectionMatrix = (Matrix4f)params[7];
      Minecraft minecraft = Minecraft.m_91087_();
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      if (entity != null) {
         ClientLevel level = minecraft.f_91073_;
         Vec3 pos = entity.m_20318_(partialTick);
         return execute((Event)null, level);
      } else {
         return false;
      }
   };

   private static void buildClouds(CloudStatus cloudStatus, double x, double y, double z) {
      if (cloudBuffer != null && DisableCloudsProcedure.cloudStatus == cloudStatus && DisableCloudsProcedure.x == x && DisableCloudsProcedure.y == y && DisableCloudsProcedure.z == z) {
         cloudBuffer.m_85921_();
      } else {
         DisableCloudsProcedure.cloudStatus = cloudStatus;
         DisableCloudsProcedure.x = x;
         DisableCloudsProcedure.y = y;
         DisableCloudsProcedure.z = z;
         Minecraft minecraft = Minecraft.m_91087_();
         RenderSystem.bindTexture(RenderSystem.getShaderTexture(0));
         float du = 1.0F / (float)GL11.glGetTexLevelParameteri(3553, 0, 4096);
         float dv = 1.0F / (float)GL11.glGetTexLevelParameteri(3553, 0, 4097);
         float dx = (float)Mth.m_14107_(x) * du;
         float dz = (float)Mth.m_14107_(z) * dv;
         float cloudY = (float)Math.floor(y / (double)height) * height;
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85822_);
         if (cloudStatus == CloudStatus.FANCY) {
            for(int i = -3; i <= 4; ++i) {
               for(int j = -3; j <= 4; ++j) {
                  float cloudX = (float)i * 8.0F;
                  float cloudZ = (float)j * 8.0F;
                  if (cloudY > -height - 1.0F) {
                     bufferBuilder.m_5483_((double)cloudX, (double)cloudY, (double)(cloudZ + 8.0F)).m_7421_(cloudX * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.7F, 0.7F, 0.7F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)cloudY, (double)(cloudZ + 8.0F)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.7F, 0.7F, 0.7F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)cloudY, (double)cloudZ).m_7421_((cloudX + 8.0F) * du + dx, cloudZ * dv + dz).m_85950_(0.7F, 0.7F, 0.7F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)cloudX, (double)cloudY, (double)cloudZ).m_7421_(cloudX * du + dx, cloudZ * dv + dz).m_85950_(0.7F, 0.7F, 0.7F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                  }

                  if (cloudY <= height + 1.0F) {
                     bufferBuilder.m_5483_((double)cloudX, (double)(cloudY + height - 9.765625E-4F), (double)(cloudZ + 8.0F)).m_7421_(cloudX * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)(cloudY + height - 9.765625E-4F), (double)(cloudZ + 8.0F)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)(cloudY + height - 9.765625E-4F), (double)cloudZ).m_7421_((cloudX + 8.0F) * du + dx, cloudZ * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
                     bufferBuilder.m_5483_((double)cloudX, (double)(cloudY + height - 9.765625E-4F), (double)cloudZ).m_7421_(cloudX * du + dx, cloudZ * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, 1.0F, 0.0F).m_5752_();
                  }

                  if (i > -1) {
                     for(int k = 0; k < 8; ++k) {
                        bufferBuilder.m_5483_((double)(cloudX + (float)k), (double)cloudY, (double)(cloudZ + 8.0F)).m_7421_((cloudX + (float)k + 0.5F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(-1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k), (double)(cloudY + height), (double)(cloudZ + 8.0F)).m_7421_((cloudX + (float)k + 0.5F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(-1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k), (double)(cloudY + height), (double)cloudZ).m_7421_((cloudX + (float)k + 0.5F) * du + dx, cloudZ * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(-1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k), (double)cloudY, (double)cloudZ).m_7421_((cloudX + (float)k + 0.5F) * du + dx, cloudZ * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(-1.0F, 0.0F, 0.0F).m_5752_();
                     }
                  }

                  if (i <= 1) {
                     for(int k = 0; k < 8; ++k) {
                        bufferBuilder.m_5483_((double)(cloudX + (float)k + 1.0F - 9.765625E-4F), (double)cloudY, (double)(cloudZ + 8.0F)).m_7421_((cloudX + (float)k + 0.5F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k + 1.0F - 9.765625E-4F), (double)(cloudY + height), (double)(cloudZ + 8.0F)).m_7421_((cloudX + (float)k + 0.5F) * du + dx, (cloudZ + 8.0F) * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k + 1.0F - 9.765625E-4F), (double)(cloudY + height), (double)cloudZ).m_7421_((cloudX + (float)k + 0.5F) * du + dx, cloudZ * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(1.0F, 0.0F, 0.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + (float)k + 1.0F - 9.765625E-4F), (double)cloudY, (double)cloudZ).m_7421_((cloudX + (float)k + 0.5F) * du + dx, cloudZ * dv + dz).m_85950_(0.9F, 0.9F, 0.9F, 1.0F).m_5601_(1.0F, 0.0F, 0.0F).m_5752_();
                     }
                  }

                  if (j > -1) {
                     for(int k = 0; k < 8; ++k) {
                        bufferBuilder.m_5483_((double)cloudX, (double)(cloudY + height), (double)(cloudZ + (float)k)).m_7421_(cloudX * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, -1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)(cloudY + height), (double)(cloudZ + (float)k)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, -1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)cloudY, (double)(cloudZ + (float)k)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, -1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)cloudX, (double)cloudY, (double)(cloudZ + (float)k)).m_7421_(cloudX * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, -1.0F).m_5752_();
                     }
                  }

                  if (j <= 1) {
                     for(int k = 0; k < 8; ++k) {
                        bufferBuilder.m_5483_((double)cloudX, (double)(cloudY + height), (double)(cloudZ + (float)k + 1.0F - 9.765625E-4F)).m_7421_(cloudX * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, 1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)(cloudY + height), (double)(cloudZ + (float)k + 1.0F - 9.765625E-4F)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, 1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)(cloudX + 8.0F), (double)cloudY, (double)(cloudZ + (float)k + 1.0F - 9.765625E-4F)).m_7421_((cloudX + 8.0F) * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, 1.0F).m_5752_();
                        bufferBuilder.m_5483_((double)cloudX, (double)cloudY, (double)(cloudZ + (float)k + 1.0F - 9.765625E-4F)).m_7421_(cloudX * du + dx, (cloudZ + (float)k + 0.5F) * dv + dz).m_85950_(0.8F, 0.8F, 0.8F, 1.0F).m_5601_(0.0F, 0.0F, 1.0F).m_5752_();
                     }
                  }
               }
            }
         } else if (cloudStatus == CloudStatus.FAST) {
            for(int i = -32; i < 32; i += 32) {
               for(int j = -32; j < 32; j += 32) {
                  bufferBuilder.m_5483_((double)i, (double)cloudY, (double)(j + 32)).m_7421_((float)i * du + dx, (float)(j + 32) * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                  bufferBuilder.m_5483_((double)(i + 32), (double)cloudY, (double)(j + 32)).m_7421_((float)(i + 32) * du + dx, (float)(j + 32) * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                  bufferBuilder.m_5483_((double)(i + 32), (double)cloudY, (double)j).m_7421_((float)(i + 32) * du + dx, (float)j * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
                  bufferBuilder.m_5483_((double)i, (double)cloudY, (double)j).m_7421_((float)i * du + dx, (float)j * dv + dz).m_85950_(1.0F, 1.0F, 1.0F, 1.0F).m_5601_(0.0F, -1.0F, 0.0F).m_5752_();
               }
            }
         }

         if (cloudBuffer != null) {
            cloudBuffer.close();
         }

         cloudBuffer = new VertexBuffer(Usage.STATIC);
         cloudBuffer.m_85921_();
         cloudBuffer.m_231221_(bufferBuilder.m_231175_());
      }

   }

   public static void renderClouds(CloudStatus cloudStatus, double altitude, double vx, double vz, int color) {
      if (cloudStatus != CloudStatus.OFF) {
         RenderSystem.bindTexture(RenderSystem.getShaderTexture(0));
         int tw = GL11.glGetTexLevelParameteri(3553, 0, 4096) << 3;
         int th = GL11.glGetTexLevelParameteri(3553, 0, 4097) << 3;
         if (tw > 0 && th > 0) {
            Minecraft minecraft = Minecraft.m_91087_();
            Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
            double factor = (double)((float)ticks + partialTick) * 0.03;
            double x = (pos.m_7096_() + factor * -vx) / (double)width;
            double y = altitude + 0.33 - pos.m_7098_();
            double z = (pos.m_7094_() + factor * -vz) / (double)width + 0.33;
            x -= (double)(Mth.m_14107_(x / (double)tw) * tw);
            z -= (double)(Mth.m_14107_(z / (double)th) * th);
            float dx = (float)(x - (double)Mth.m_14107_(x));
            float dy = (float)(y / (double)height - (double)Mth.m_14107_(y / (double)height)) * height;
            float dz = (float)(z - (double)Mth.m_14107_(z));
            buildClouds(cloudStatus, x, y, z);
            poseStack.m_85836_();
            poseStack.m_85841_(width, 1.0F, width);
            poseStack.m_252880_(-dx, dy, -dz);
            Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
            ShaderInstance shaderInstance = GameRenderer.m_172838_();
            RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
            if (cloudStatus == CloudStatus.FANCY) {
               RenderSystem.colorMask(false, false, false, false);
               cloudBuffer.m_253207_(matrix4f, projectionMatrix, shaderInstance);
               RenderSystem.colorMask(true, true, true, true);
               cloudBuffer.m_253207_(matrix4f, projectionMatrix, shaderInstance);
            } else if (cloudStatus == CloudStatus.FAST) {
               RenderSystem.colorMask(true, true, true, true);
               cloudBuffer.m_253207_(matrix4f, projectionMatrix, shaderInstance);
            }

            VertexBuffer.m_85931_();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.m_85849_();
         }

      }
   }

   @SubscribeEvent
   public static void cloudsSetup(FMLClientSetupEvent event) {
      try {
         Field field = DimensionSpecialEffectsManager.class.getDeclaredField("EFFECTS");
         field.setAccessible(true);
         UnmodifiableIterator var2 = ((ImmutableMap)field.get((Object)null)).values().iterator();

         while(var2.hasNext()) {
            DimensionSpecialEffects dimensionSpecialEffects = (DimensionSpecialEffects)var2.next();
            if (dimensionSpecialEffects instanceof SetupProcedure.CosmosModDimensionSpecialEffects cosmosSpecialEffects) {
               Class<?> effects = dimensionSpecialEffects.getClass();
               ((Set)effects.getField("CUSTOM_CLOUDS").get((Object)null)).add(PREDICATE);
            }
         }
      } catch (Exception var6) {
      }

   }

   public static boolean execute(LevelAccessor world) {
      return execute((Event)null, world);
   }

   private static boolean execute(@Nullable Event event, LevelAccessor world) {
      boolean logic = false;
      if (CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128441_(((Level)world).m_46472_().m_135782_().toString())) {
         Tag var5 = CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128423_(((Level)world).m_46472_().m_135782_().toString());
         CompoundTag var10000;
         if (var5 instanceof CompoundTag) {
            CompoundTag _compoundTag = (CompoundTag)var5;
            var10000 = _compoundTag.m_6426_();
         } else {
            var10000 = new CompoundTag();
         }

         CompoundTag dimensional_data = var10000;
         if (dimensional_data.m_128441_("clouds")) {
            var5 = dimensional_data.m_128423_("clouds");
            boolean var8;
            if (var5 instanceof ByteTag) {
               ByteTag _byteTag = (ByteTag)var5;
               var8 = _byteTag.m_7063_() == 1;
            } else {
               var8 = false;
            }

            if (!var8) {
               logic = true;
            } else {
               logic = false;
            }
         }
      }

      return logic;
   }
}
