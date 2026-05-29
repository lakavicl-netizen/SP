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
import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.joml.Matrix4f;

@EventBusSubscriber({Dist.CLIENT})
public class SpaceSuitThrustProcedure {
   private static boolean texture = false;
   private static BufferBuilder bufferBuilder = null;
   private static VertexBuffer vertexBuffer = null;
   private static PoseStack poseStack = null;
   private static Matrix4f projectionMatrix = null;
   private static boolean worldCoordinate = true;
   private static Vec3 offset;
   private static float partialTick;
   private static int ticks;
   private static int currentStage;
   private static int targetStage;
   private static double fov;

   private static Matrix4f projectionMatrix() {
      PoseStack stack = new PoseStack();
      GameRenderer gr = Minecraft.m_91087_().f_91063_;
      stack.m_252931_(gr.m_253088_(fov));
      return stack.m_85850_().m_252922_();
   }

   private static void add(double x, double y, double z, float u, float v, int color) {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         bufferBuilder.m_5483_(x, y, z).m_193479_(color);
         if (texture) {
            bufferBuilder.m_7421_(u, v);
         }

         bufferBuilder.m_85969_(15728880).m_5752_();
      }
   }

   private static boolean begin(VertexFormat.Mode mode, boolean texture, boolean update) {
      if (bufferBuilder == null || !bufferBuilder.m_85732_()) {
         if (update) {
            clear();
         }

         if (vertexBuffer == null) {
            SpaceSuitThrustProcedure.texture = texture;
            bufferBuilder = Tesselator.m_85913_().m_85915_();
            if (texture) {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85820_);
            } else {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85816_);
            }

            return true;
         }
      }

      return false;
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

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      SpaceSuitThrustProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         SpaceSuitThrustProcedure.targetStage = targetStage;
         return true;
      } else {
         return false;
      }
   }

   @SubscribeEvent
   public static void getFov(ViewportEvent.ComputeFov event) {
      fov = event.getFOV();
   }

   private static void renderShape(VertexBuffer vertexBuffer, double x, double y, double z, float yaw, float pitch, float roll, float xScale, float yScale, float zScale, int color) {
      if (currentStage != 0 && currentStage == targetStage) {
         if (poseStack != null && projectionMatrix != null) {
            if (vertexBuffer != null) {
               if (fov != (double)-1.0F) {
                  boolean overlay = currentStage == 1;
                  boolean texture = vertexBuffer.m_166892_().hasUV(0);
                  float i;
                  float j;
                  float k;
                  if (overlay) {
                     i = (float)x;
                     j = (float)y;
                     k = (float)z;
                     roll += 180.0F;
                  } else if (worldCoordinate) {
                     Vec3 pos = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_();
                     i = (float)(x - pos.m_7096_());
                     j = (float)(y - pos.m_7098_());
                     k = (float)(z - pos.m_7094_());
                  } else {
                     i = (float)x;
                     j = (float)y;
                     k = (float)z;
                  }

                  poseStack.m_85836_();
                  poseStack.m_252880_(i, j, k);
                  poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
                  poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
                  poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
                  poseStack.m_85841_(xScale, yScale, zScale);
                  poseStack.m_85837_(offset.m_7096_(), offset.m_7098_(), offset.m_7094_());
                  RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
                  vertexBuffer.m_85921_();
                  ShaderInstance shaderInstance;
                  Matrix4f matrix4f;
                  if (overlay) {
                     PoseStack modelViewStack = RenderSystem.getModelViewStack();
                     modelViewStack.m_85836_();
                     modelViewStack.m_252931_(poseStack.m_85850_().m_252922_());
                     matrix4f = modelViewStack.m_85850_().m_252922_();
                     shaderInstance = texture ? GameRenderer.m_172814_() : GameRenderer.m_172811_();
                     modelViewStack.m_85849_();
                  } else {
                     matrix4f = poseStack.m_85850_().m_252922_();
                     shaderInstance = texture ? GameRenderer.m_172835_() : GameRenderer.m_172832_();
                  }

                  vertexBuffer.m_253207_(matrix4f, overlay ? projectionMatrix : projectionMatrix(), shaderInstance);
                  VertexBuffer.m_85931_();
                  RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                  poseStack.m_85849_();
               }
            }
         }
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void renderGUI(RenderGuiEvent.Pre event) {
      if (Minecraft.m_91087_().f_91080_ == null) {
         poseStack = event.getGuiGraphics().m_280168_();
         projectionMatrix = RenderSystem.getProjectionMatrix();
         partialTick = event.getPartialTick();
         renderOverlay(event);
      }

   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void renderScreen(ScreenEvent.Render.Post event) {
      poseStack = event.getGuiGraphics().m_280168_();
      projectionMatrix = RenderSystem.getProjectionMatrix();
      partialTick = event.getPartialTick();
      renderOverlay(event);
   }

   private static void renderOverlay(Event event) {
      if (Minecraft.m_91087_().m_91268_().m_85449_() > (double)0.0F) {
         currentStage = 1;
         RenderSystem.depthMask(true);
         RenderSystem.enableDepthTest();
         renderShapes(event);
         RenderSystem.disableCull();
         RenderSystem.depthMask(true);
         currentStage = 0;
      }

   }

   @SubscribeEvent
   public static void renderLevel(RenderLevelStageEvent event) {
      poseStack = event.getPoseStack();
      projectionMatrix = event.getProjectionMatrix();
      partialTick = event.getPartialTick();
      ticks = event.getRenderTick();
      if (event.getStage() == Stage.AFTER_SKY) {
         currentStage = 2;
         RenderSystem.depthMask(false);
         renderShapes(event);
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
      } else if (event.getStage() == Stage.AFTER_PARTICLES) {
         currentStage = 3;
         RenderSystem.depthMask(true);
         renderShapes(event);
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
      }

      currentStage = 0;
   }

   private static void renderShapes(Event event) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      if (level != null && entity != null) {
         Vec3 pos = entity.m_20318_(partialTick);
         LightTexture lightTexture = minecraft.f_91063_.m_109154_();
         lightTexture.m_109896_();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         execute(event, level, entity, (double)partialTick);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
         RenderSystem.colorMask(true, true, true, true);
         lightTexture.m_109891_();
      }

   }

   public static void execute(LevelAccessor world, Entity entity, double partialTick) {
      execute((Event)null, world, entity, partialTick);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, double partialTick) {
      if (entity != null) {
         Entity player = null;
         double bodyYaw = (double)0.0F;
         double xoffset = (double)0.0F;
         double yoffset = (double)0.0F;
         double zoffset = (double)0.0F;
         if (SpacesuitwornLogicProcedure.execute(world, entity)) {
            double var10000;
            if (entity instanceof Player) {
               Player _bodyYawContext = (Player)entity;
               var10000 = (double)_bodyYawContext.f_20883_;
            } else {
               var10000 = (double)0.0F;
            }

            bodyYaw = var10000;
            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && !Screen.m_96638_()) {
               xoffset = 0.17;
               yoffset = 0.57;
               zoffset = -0.34;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               xoffset = -0.17;
               yoffset = 0.57;
               zoffset = -0.34;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), Mth.m_14139_(0.02, entity.m_20184_().m_7098_(), 0.2), entity.m_20184_().m_7094_()));
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && Screen.m_96638_()) {
               xoffset = 0.17;
               yoffset = 0.65;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               xoffset = -0.17;
               yoffset = 0.65;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), Mth.m_14139_(0.02, entity.m_20184_().m_7098_(), 0.2), entity.m_20184_().m_7094_()));
            }

            if (Screen.m_96638_()) {
               xoffset = 0.4;
               yoffset = 1.1;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, 0.1, (double)0.0F);
               xoffset = -0.4;
               yoffset = 1.1;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, 0.1, (double)0.0F);
               entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), Mth.m_14139_(0.02, entity.m_20184_().m_7098_(), -0.2), entity.m_20184_().m_7094_()));
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && !Screen.m_96638_()) {
               xoffset = 0.285;
               yoffset = 0.95;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 0.95;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = 0.285;
               yoffset = 1.3;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 1.3;
               zoffset = -0.65;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && Screen.m_96638_()) {
               xoffset = 0.17;
               yoffset = 0.65;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               xoffset = -0.17;
               yoffset = 0.65;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), (double)0.0F, -0.1, (double)0.0F);
               entity.m_20256_(new Vec3(entity.m_20184_().m_7096_(), Mth.m_14139_(0.02, entity.m_20184_().m_7098_(), 0.2), entity.m_20184_().m_7094_()));
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && !entity.m_20096_() && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && Screen.m_96638_()) {
               xoffset = 0.285;
               yoffset = 0.65;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 0.65;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = 0.285;
               yoffset = 0.9;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 0.9;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            } else if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && entity.m_20096_() && !((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && Screen.m_96638_()) {
               xoffset = 0.285;
               yoffset = 0.9;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 0.9;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = 0.285;
               yoffset = (double)1.25F;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = (double)1.25F;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_d && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust && Screen.m_96638_()) {
               xoffset = 0.285;
               yoffset = (double)1.0F;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = (double)1.0F;
               zoffset = (double)-0.5F;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = 0.285;
               yoffset = 1.35;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.285;
               yoffset = 1.35;
               zoffset = -0.4;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, -Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && Screen.m_96638_()) {
               xoffset = 0.4;
               yoffset = 0.95;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.4;
               yoffset = 0.95;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            } else if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).pitch_i && !Screen.m_96638_()) {
               xoffset = 0.4;
               yoffset = 1.2;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
               xoffset = -0.4;
               yoffset = 1.2;
               zoffset = 0.05;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && Screen.m_96638_()) {
               xoffset = 0.4;
               yoffset = 0.9;
               zoffset = 0.15;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_(((float)Math.PI / 2F)).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_(((float)Math.PI / 2F)).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            } else if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_i && !Screen.m_96638_()) {
               xoffset = 0.4;
               yoffset = 1.2;
               zoffset = 0.15;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_(((float)Math.PI / 2F)).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_(((float)Math.PI / 2F)).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }

            if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d && Screen.m_96638_()) {
               xoffset = -0.4;
               yoffset = 0.9;
               zoffset = 0.15;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_((-(float)Math.PI / 2F)).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_((-(float)Math.PI / 2F)).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            } else if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll_d && !Screen.m_96638_()) {
               xoffset = -0.4;
               yoffset = 1.2;
               zoffset = 0.15;
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.AIR_THRUST.get(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7096_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7098_(), entity.m_20318_((float)partialTick).m_82549_(new Vec3(xoffset * Math.cos(Math.toRadians(bodyYaw)) - zoffset * Math.sin(Math.toRadians(bodyYaw)), yoffset, xoffset * Math.sin(Math.toRadians(bodyYaw)) + zoffset * Math.cos(Math.toRadians(bodyYaw)))).m_7094_(), Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_((-(float)Math.PI / 2F)).m_82542_(0.06, (double)1.0F, (double)1.0F).m_7096_(), (double)0.0F, Vec3.m_82498_(0.0F, (float)bodyYaw).m_82524_((-(float)Math.PI / 2F)).m_82542_((double)1.0F, (double)1.0F, 0.06).m_7094_());
            }
         }

      }
   }

   static {
      offset = Vec3.f_82478_;
      partialTick = 0.0F;
      ticks = 0;
      currentStage = 0;
      targetStage = 0;
      fov = (double)-1.0F;
   }
}
