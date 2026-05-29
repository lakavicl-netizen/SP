package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexBuffer.Usage;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.joml.Matrix4f;

@EventBusSubscriber({Dist.CLIENT})
public class CompassrendermentProcedure {
   private static boolean texture = false;
   private static BufferBuilder bufferBuilder = null;
   private static VertexBuffer vertexBuffer = null;
   private static double fov = (double)0.0F;
   private static PoseStack poseStack = null;
   private static Matrix4f projectionMatrix = null;
   private static Matrix4f bobbingProjectionMatrix = null;
   private static Matrix4f noBobbingProjectionMatrix = null;
   private static boolean worldCoordinate = true;
   private static Vec3 offset;
   private static float scale;
   private static float partialTick;
   private static int ticks;
   private static int currentStage;
   private static int targetStage;

   private static void add(double x, double y, double z, float u, float v, int color) {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         if (texture) {
            bufferBuilder.m_5483_(x, y, z).m_7421_(u, v).m_193479_(color).m_5752_();
         } else {
            bufferBuilder.m_5483_(x, y, z).m_193479_(color).m_5752_();
         }

      }
   }

   private static void add(double size, int color) {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         size /= (double)2.0F;
         if (texture) {
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
         } else {
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
         }

      }
   }

   private static boolean begin(VertexFormat.Mode mode, boolean texture, boolean update) {
      if (bufferBuilder == null || !bufferBuilder.m_85732_()) {
         if (update) {
            clear();
         }

         if (vertexBuffer == null) {
            CompassrendermentProcedure.texture = texture;
            bufferBuilder = Tesselator.m_85913_().m_85915_();
            if (texture) {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85819_);
            } else {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85815_);
            }

            return true;
         }
      }

      return false;
   }

   private static void bob(boolean bobbing) {
      if (bobbing) {
         projectionMatrix = bobbingProjectionMatrix;
      } else {
         if (noBobbingProjectionMatrix == null) {
            PoseStack poseStack = new PoseStack();
            poseStack.m_252931_(Minecraft.m_91087_().f_91063_.m_253088_(fov));
            noBobbingProjectionMatrix = poseStack.m_85850_().m_252922_();
         }

         projectionMatrix = noBobbingProjectionMatrix;
      }

   }

   private static void clear() {
      if (vertexBuffer != null) {
         vertexBuffer.close();
         vertexBuffer = null;
      }

   }

   private static void end() {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         if (vertexBuffer != null) {
            vertexBuffer.close();
         }

         vertexBuffer = new VertexBuffer(Usage.STATIC);
         vertexBuffer.m_85921_();
         vertexBuffer.m_231221_(bufferBuilder.m_231175_());
         VertexBuffer.m_85931_();
      }
   }

   private static void offset(double x, double y, double z) {
      offset = new Vec3(x, y, z);
   }

   private static void release() {
      targetStage = 0;
   }

   private static void scale(float scale) {
      CompassrendermentProcedure.scale = scale;
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      CompassrendermentProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         CompassrendermentProcedure.targetStage = targetStage;
         return true;
      } else {
         return false;
      }
   }

   private static void renderShape(VertexBuffer vertexBuffer, double x, double y, double z, float yaw, float pitch, float roll, float xScale, float yScale, float zScale, int color) {
      if (currentStage != 0 && currentStage == targetStage) {
         if (poseStack != null && projectionMatrix != null) {
            if (vertexBuffer != null) {
               float i;
               float j;
               float k;
               if (!worldCoordinate) {
                  i = (float)x;
                  j = (float)y;
                  k = (float)z;
               } else {
                  Vec3 pos = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_();
                  i = (float)(x - pos.m_7096_());
                  j = (float)(y - pos.m_7098_());
                  k = (float)(z - pos.m_7094_());
               }

               poseStack.m_85836_();
               poseStack.m_85841_(scale, scale, scale);
               poseStack.m_252880_(i, j, k);
               poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
               poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
               poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
               poseStack.m_85841_(xScale, yScale, zScale);
               poseStack.m_85837_(offset.m_7096_(), offset.m_7098_(), offset.m_7094_());
               Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
               RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
               vertexBuffer.m_85921_();
               vertexBuffer.m_253207_(matrix4f, projectionMatrix, vertexBuffer.m_166892_().hasUV(0) ? GameRenderer.m_172820_() : GameRenderer.m_172811_());
               VertexBuffer.m_85931_();
               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
               poseStack.m_85849_();
            }
         }
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void computeFOV(ViewportEvent.ComputeFov event) {
      fov = event.getFOV();
   }

   @SubscribeEvent
   public static void renderLevel(RenderLevelStageEvent event) {
      poseStack = event.getPoseStack();
      projectionMatrix = event.getProjectionMatrix();
      bobbingProjectionMatrix = projectionMatrix;
      noBobbingProjectionMatrix = null;
      partialTick = event.getPartialTick();
      ticks = event.getRenderTick();
      if (event.getStage() == Stage.AFTER_SKY) {
         currentStage = 1;
         RenderSystem.depthMask(false);
         renderShapes(event);
         RenderSystem.enableCull();
         RenderSystem.depthMask(true);
      } else if (event.getStage() == Stage.AFTER_PARTICLES) {
         currentStage = 2;
         RenderSystem.depthMask(true);
         renderShapes(event);
         RenderSystem.enableCull();
         RenderSystem.depthMask(true);
      }

      currentStage = 0;
   }

   private static void renderShapes(Event event) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      if (level != null && entity != null) {
         Vec3 pos = entity.m_20318_(partialTick);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         execute(event, entity, (double)partialTick);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
      }

   }

   public static void execute(Entity entity, double partialTick) {
      execute((Event)null, entity, partialTick);
   }

   private static void execute(@Nullable Event event, Entity entity, double partialTick) {
      if (entity != null) {
         double vic = (double)0.0F;
         double sic = (double)0.0F;
         double particleRadius = (double)0.0F;
         double particleAmount = (double)0.0F;
         ItemStack var10000;
         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10000 = _livEnt.m_21205_();
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (var10000.m_41720_() != CosmosModItems.SPACECOMPASS.get()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21206_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.SPACECOMPASS.get()) {
               return;
            }
         }

         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10000 = _livEnt.m_21206_();
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (!var10000.m_41793_()) {
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (!var10000.m_41793_()) {
               return;
            }
         }

         RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
         if (begin(Mode.TRIANGLES, false, true)) {
            add((double)0.0F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
            add((double)0.5F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)-0.5F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)-0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
            add((double)0.25F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)-0.5F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.5F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, -1);
            add((double)0.0F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
            end();
         }

         if (target(2)) {
            renderShape(shape(), entity.m_20299_((float)partialTick).m_82549_(entity.m_20252_((float)partialTick)).m_7096_(), entity.m_20299_((float)partialTick).m_82549_(entity.m_20252_((float)partialTick)).m_7098_() + (double)0.5F, entity.m_20299_((float)partialTick).m_82549_(entity.m_20252_((float)partialTick)).m_7094_(), (float)((<undefinedtype>)(new Object() {
               public double get(Vec3 vec3) {
                  return Math.toDegrees(Math.acos(vec3.m_82542_((double)1.0F, (double)0.0F, (double)1.0F).m_82541_().m_7094_())) * (vec3.m_7096_() >= (double)0.0F ? (double)-1.0F : (double)1.0F);
               }
            })).get((new Vec3(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_X + (double)0.5F, ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_Y + (double)0.5F, ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_Z + (double)0.5F)).m_82546_(entity.m_20299_((float)partialTick))), (float)(-Math.toDegrees(Math.asin((new Vec3(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_X + (double)0.5F, ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_Y + (double)0.5F, ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).compass_Z + (double)0.5F)).m_82546_(entity.m_20299_((float)partialTick)).m_82541_().m_7098_()))), 0.0F, 0.5F, 0.5F, 0.5F, MapColor.f_283869_.f_283871_ | -16777216);
            release();
         }

      }
   }

   static {
      offset = Vec3.f_82478_;
      scale = 1.0F;
      partialTick = 0.0F;
      ticks = 0;
      currentStage = 0;
      targetStage = 0;
   }
}
