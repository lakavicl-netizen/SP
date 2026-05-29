package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexBuffer.Usage;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
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
public class RenderCANDYProcedure {
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
            RenderCANDYProcedure.texture = texture;
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
      RenderCANDYProcedure.scale = scale;
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      RenderCANDYProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         RenderCANDYProcedure.targetStage = targetStage;
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
         execute(event);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
      }

   }

   public static void execute() {
      execute((Event)null);
   }

   private static void execute(@Nullable Event event) {
      String type = "";
      Direction render_direction = Direction.NORTH;
      boolean atmosphere_capability = false;
      Vec3 objRot = Vec3.f_82478_;
      Vec3 vertex4 = Vec3.f_82478_;
      Vec3 objScale = Vec3.f_82478_;
      Vec3 vertex1 = Vec3.f_82478_;
      Vec3 vertex3 = Vec3.f_82478_;
      Vec3 vertex2 = Vec3.f_82478_;
      Vec3 objPos = Vec3.f_82478_;
      Vec3 uv = Vec3.f_82478_;
      Vec3 vector3 = Vec3.f_82478_;
      Vec3 vector4 = Vec3.f_82478_;
      Vec3 vector1 = Vec3.f_82478_;
      Vec3 vector2 = Vec3.f_82478_;
      VertexBuffer texture_cube = null;
      VertexBuffer rings = null;
      VertexBuffer empty_shape = null;
      VertexBuffer blackhole = null;
      VertexBuffer cube = null;
      double color = (double)0.0F;
      double i = (double)0.0F;
      double j = (double)0.0F;
      double layering = (double)0.0F;
      double k = (double)0.0F;
      double l = (double)0.0F;
      double layer = (double)0.0F;
      double counter = (double)0.0F;
      double D_iter = (double)0.0F;
      double p = (double)0.0F;
      double q = (double)0.0F;
      double current_tick = (double)0.0F;
      double iter = (double)0.0F;
      double frames = (double)0.0F;
      double ring_rot = (double)0.0F;
      double multiple = (double)0.0F;
      double main_color = (double)0.0F;
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
