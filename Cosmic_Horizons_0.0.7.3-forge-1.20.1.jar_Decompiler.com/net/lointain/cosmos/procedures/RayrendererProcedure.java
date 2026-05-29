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
import java.util.Map;
import javax.annotation.Nullable;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
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
public class RayrendererProcedure {
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
            RayrendererProcedure.texture = texture;
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
      RayrendererProcedure.scale = scale;
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      RayrendererProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         RayrendererProcedure.targetStage = targetStage;
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
         execute(event, level, (double)partialTick, (double)ticks);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
      }

   }

   public static void execute(LevelAccessor world, double partialTick, double ticks) {
      execute((Event)null, world, partialTick, ticks);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double partialTick, double ticks) {
      Vec3 pos = Vec3.f_82478_;
      Vec3 rot = Vec3.f_82478_;
      Vec3 color = Vec3.f_82478_;
      Vec3 rotp = Vec3.f_82478_;
      double width = (double)0.0F;
      double length = (double)0.0F;
      double i = (double)0.0F;
      double j = (double)0.0F;
      double k = (double)0.0F;
      double l = (double)0.0F;
      double m = (double)0.0F;
      double speed = (double)0.0F;
      double pel = (double)0.0F;
      if (world instanceof ClientLevel _blockEntityContext) {
         int _scanRange = Minecraft.m_91087_().f_91066_.m_193772_();
         BlockPos _scanCenter = Minecraft.m_91087_().f_91074_.m_20183_();

         for(int _chunkZ = -_scanRange; _chunkZ <= _scanRange; ++_chunkZ) {
            for(int _chunkX = -_scanRange; _chunkX <= _scanRange; ++_chunkX) {
               LevelChunk _levelChunk = _blockEntityContext.m_6325_(SectionPos.m_123171_(_scanCenter.m_123341_() + (_chunkX << 4)), SectionPos.m_123171_(_scanCenter.m_123343_() + (_chunkZ << 4)));
               if (_levelChunk != null) {
                  for(Map.Entry<BlockPos, BlockEntity> _blockEntityEntry : _levelChunk.m_62954_().entrySet()) {
                     BlockState blockstateiterator = ((BlockEntity)_blockEntityEntry.getValue()).m_58900_();
                     int positionx = ((BlockPos)_blockEntityEntry.getKey()).m_123341_();
                     int positiony = ((BlockPos)_blockEntityEntry.getKey()).m_123342_();
                     int positionz = ((BlockPos)_blockEntityEntry.getKey()).m_123343_();
                     if (world.m_8055_(new BlockPos(positionx, positiony, positionz)).m_60734_() == CosmosModBlocks.DETONATOR_TARGET_ON.get()) {
                        color = new Vec3((double)255.0F, (double)80.0F, (double)5.0F);
                        pos = new Vec3((double)positionx + (double)0.5F, (double)positiony + (double)0.5F, (double)positionz + (double)0.5F);
                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "detonate")) {
                           rot = new Vec3((double)-90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                           if (target(2)) {
                              speed = 0.3;
                              width = (double)0.3125F;
                              length = (double)1024.0F;
                              RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                              if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                                 i = width / (double)-2.0F;
                                 j = width / (double)2.0F;
                                 k = length - (double)0.5F;
                                 l = (ticks + partialTick) * speed;
                                 m = length + l;
                                 add(i, i, k, 0.0F, (float)l, -1);
                                 add(i, i, i, 0.0F, (float)m, -1);
                                 add(j, i, i, 1.0F, (float)m, -1);
                                 add(j, i, k, 1.0F, (float)l, -1);
                                 add(j, j, k, 0.0F, (float)l, -1);
                                 add(j, j, i, 0.0F, (float)m, -1);
                                 add(i, j, i, 1.0F, (float)m, -1);
                                 add(i, j, k, 1.0F, (float)l, -1);
                                 add(j, i, k, 0.0F, (float)l, -1);
                                 add(j, i, i, 0.0F, (float)m, -1);
                                 add(j, j, i, 1.0F, (float)m, -1);
                                 add(j, j, k, 1.0F, (float)l, -1);
                                 add(i, j, k, 0.0F, (float)l, -1);
                                 add(i, j, i, 0.0F, (float)m, -1);
                                 add(i, i, i, 1.0F, (float)m, -1);
                                 add(i, i, k, 1.0F, (float)l, -1);
                                 end();
                              }

                              RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                              renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), -0.04F, 0.04F, 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                              renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), 0.03F, 0.03F, 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                              release();
                           }
                        }

                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "detonate") && target(2)) {
                           rot = new Vec3((double)90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                           speed = 0.3;
                           width = (double)0.3125F;
                           length = (double)1024.0F;
                           RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                           if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                              i = width / (double)-2.0F;
                              j = width / (double)2.0F;
                              k = length - (double)0.5F;
                              l = (ticks + partialTick) * speed;
                              m = length + l;
                              add(i, i, k, 0.0F, (float)l, -1);
                              add(i, i, i, 0.0F, (float)m, -1);
                              add(j, i, i, 1.0F, (float)m, -1);
                              add(j, i, k, 1.0F, (float)l, -1);
                              add(j, j, k, 0.0F, (float)l, -1);
                              add(j, j, i, 0.0F, (float)m, -1);
                              add(i, j, i, 1.0F, (float)m, -1);
                              add(i, j, k, 1.0F, (float)l, -1);
                              add(j, i, k, 0.0F, (float)l, -1);
                              add(j, i, i, 0.0F, (float)m, -1);
                              add(j, j, i, 1.0F, (float)m, -1);
                              add(j, j, k, 1.0F, (float)l, -1);
                              add(i, j, k, 0.0F, (float)l, -1);
                              add(i, j, i, 0.0F, (float)m, -1);
                              add(i, i, i, 1.0F, (float)m, -1);
                              add(i, i, k, 1.0F, (float)l, -1);
                              end();
                           }

                           RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                           renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), -0.04F, 0.04F, 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                           renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), 0.03F, 0.03F, 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                           release();
                        }

                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "detonate")) {
                           if (((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") > (double)7.5F && ((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") < (double)20.0F) {
                              rot = new Vec3((double)-90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                              if (target(2)) {
                                 speed = 0.3;
                                 width = (double)0.3125F;
                                 length = (double)1024.0F;
                                 RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                                 if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                                    i = width / (double)-2.0F;
                                    j = width / (double)2.0F;
                                    k = length - (double)0.5F;
                                    l = (ticks + partialTick) * speed;
                                    m = length + l;
                                    add(i, i, k, 0.0F, (float)l, -1);
                                    add(i, i, i, 0.0F, (float)m, -1);
                                    add(j, i, i, 1.0F, (float)m, -1);
                                    add(j, i, k, 1.0F, (float)l, -1);
                                    add(j, j, k, 0.0F, (float)l, -1);
                                    add(j, j, i, 0.0F, (float)m, -1);
                                    add(i, j, i, 1.0F, (float)m, -1);
                                    add(i, j, k, 1.0F, (float)l, -1);
                                    add(j, i, k, 0.0F, (float)l, -1);
                                    add(j, i, i, 0.0F, (float)m, -1);
                                    add(j, j, i, 1.0F, (float)m, -1);
                                    add(j, j, k, 1.0F, (float)l, -1);
                                    add(i, j, k, 0.0F, (float)l, -1);
                                    add(i, j, i, 0.0F, (float)m, -1);
                                    add(i, i, i, 1.0F, (float)m, -1);
                                    add(i, i, k, 1.0F, (float)l, -1);
                                    end();
                                 }

                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)(-1.6 * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.02), (float)(1.6 * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.02), 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)((double)1.0F * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.01), (float)((double)1.0F * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.01), 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 release();
                              }
                           } else if (((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") > (double)20.0F) {
                              rot = new Vec3((double)-90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                              if (target(2)) {
                                 speed = 0.3;
                                 width = (double)0.3125F;
                                 length = (double)1024.0F;
                                 RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                                 if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                                    i = width / (double)-2.0F;
                                    j = width / (double)2.0F;
                                    k = length - (double)0.5F;
                                    l = (ticks + partialTick) * speed;
                                    m = length + l;
                                    add(i, i, k, 0.0F, (float)l, -1);
                                    add(i, i, i, 0.0F, (float)m, -1);
                                    add(j, i, i, 1.0F, (float)m, -1);
                                    add(j, i, k, 1.0F, (float)l, -1);
                                    add(j, j, k, 0.0F, (float)l, -1);
                                    add(j, j, i, 0.0F, (float)m, -1);
                                    add(i, j, i, 1.0F, (float)m, -1);
                                    add(i, j, k, 1.0F, (float)l, -1);
                                    add(j, i, k, 0.0F, (float)l, -1);
                                    add(j, i, i, 0.0F, (float)m, -1);
                                    add(j, j, i, 1.0F, (float)m, -1);
                                    add(j, j, k, 1.0F, (float)l, -1);
                                    add(i, j, k, 0.0F, (float)l, -1);
                                    add(i, j, i, 0.0F, (float)m, -1);
                                    add(i, i, i, 1.0F, (float)m, -1);
                                    add(i, i, k, 1.0F, (float)l, -1);
                                    end();
                                 }

                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)(3.6 / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.02), (float)(3.6 / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.02), 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)((double)3.0F / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.01), (float)((double)3.0F / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.01), 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 release();
                              }
                           }
                        }

                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "detonate")) {
                           if (((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") > (double)7.5F && ((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") < (double)20.0F) {
                              rot = new Vec3((double)90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                              if (target(2)) {
                                 speed = 0.3;
                                 width = (double)0.3125F;
                                 length = (double)1024.0F;
                                 RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                                 if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                                    i = width / (double)-2.0F;
                                    j = width / (double)2.0F;
                                    k = length - (double)0.5F;
                                    l = (ticks + partialTick) * speed;
                                    m = length + l;
                                    add(i, i, k, 0.0F, (float)l, -1);
                                    add(i, i, i, 0.0F, (float)m, -1);
                                    add(j, i, i, 1.0F, (float)m, -1);
                                    add(j, i, k, 1.0F, (float)l, -1);
                                    add(j, j, k, 0.0F, (float)l, -1);
                                    add(j, j, i, 0.0F, (float)m, -1);
                                    add(i, j, i, 1.0F, (float)m, -1);
                                    add(i, j, k, 1.0F, (float)l, -1);
                                    add(j, i, k, 0.0F, (float)l, -1);
                                    add(j, i, i, 0.0F, (float)m, -1);
                                    add(j, j, i, 1.0F, (float)m, -1);
                                    add(j, j, k, 1.0F, (float)l, -1);
                                    add(i, j, k, 0.0F, (float)l, -1);
                                    add(i, j, i, 0.0F, (float)m, -1);
                                    add(i, i, i, 1.0F, (float)m, -1);
                                    add(i, i, k, 1.0F, (float)l, -1);
                                    end();
                                 }

                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)(-1.6 * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.02), (float)(1.6 * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.02), 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)((double)1.0F * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.01), (float)((double)1.0F * (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) + 0.01), 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 release();
                              }
                           } else if (((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") > (double)20.0F) {
                              rot = new Vec3((double)-90.0F, (partialTick + ticks) / (double)2.0F, (double)0.0F);
                              if (target(2)) {
                                 speed = 0.3;
                                 width = (double)0.3125F;
                                 length = (double)1024.0F;
                                 RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/the_beam.png"));
                                 if (begin(Mode.QUADS, true, !Minecraft.m_91087_().m_91104_())) {
                                    i = width / (double)-2.0F;
                                    j = width / (double)2.0F;
                                    k = length - (double)0.5F;
                                    l = (ticks + partialTick) * speed;
                                    m = length + l;
                                    add(i, i, k, 0.0F, (float)l, -1);
                                    add(i, i, i, 0.0F, (float)m, -1);
                                    add(j, i, i, 1.0F, (float)m, -1);
                                    add(j, i, k, 1.0F, (float)l, -1);
                                    add(j, j, k, 0.0F, (float)l, -1);
                                    add(j, j, i, 0.0F, (float)m, -1);
                                    add(i, j, i, 1.0F, (float)m, -1);
                                    add(i, j, k, 1.0F, (float)l, -1);
                                    add(j, i, k, 0.0F, (float)l, -1);
                                    add(j, i, i, 0.0F, (float)m, -1);
                                    add(j, j, i, 1.0F, (float)m, -1);
                                    add(j, j, k, 1.0F, (float)l, -1);
                                    add(i, j, k, 0.0F, (float)l, -1);
                                    add(i, j, i, 0.0F, (float)m, -1);
                                    add(i, i, i, 1.0F, (float)m, -1);
                                    add(i, i, k, 1.0F, (float)l, -1);
                                    end();
                                 }

                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)(3.6 / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.02), (float)(3.6 / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.02), 1.0F, 1677721600 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 renderShape(shape(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_(), (float)rot.m_7098_(), (float)rot.m_7096_(), (float)rot.m_7094_(), (float)((double)3.0F / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.01), (float)((double)3.0F / (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)20.0F) + 0.01), 1.0F, -16777216 | (int)color.m_7096_() << 16 | (int)color.m_7098_() << 8 | (int)color.m_7094_());
                                 release();
                              }
                           }
                        }

                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "detonate") && ((<undefinedtype>)(new Object() {
                           public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                           }
                        })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") > (double)7.5F && ((<undefinedtype>)(new Object() {
                           public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                           }
                        })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") < (double)24.0F) {
                           pel = (double)((int)((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter"));
                           if (pel % (double)1.0F == (double)0.0F) {
                              for(int iter = 0; iter <= 360; iter += 18) {
                                 rotp = (new Vec3((double)5.0F, 0.1, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)iter);
                                 world.m_7106_((SimpleParticleType)CosmosModParticleTypes.THRUST_SMOKELARGE.get(), rotp.m_7096_() + pos.m_7096_() + Math.random(), rotp.m_7098_() + pos.m_7098_() - (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) / (double)2.0F + Math.random() - (double)3.0F, rotp.m_7094_() + pos.m_7094_() + Math.random(), rotp.m_7096_() / 4.8, rotp.m_7098_() + 0.1, rotp.m_7094_() / 4.8);
                                 world.m_7106_((SimpleParticleType)CosmosModParticleTypes.THRUSTEDLARGE.get(), rotp.m_7096_() + pos.m_7096_() + Math.random(), rotp.m_7098_() + pos.m_7098_() - (((<undefinedtype>)(new Object() {
                                    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                    }
                                 })).getValue(world, BlockPos.m_274561_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), "counter") - (double)7.5F) / (double)2.0F + Math.random() - (double)3.0F, rotp.m_7094_() + pos.m_7094_() + Math.random(), rotp.m_7096_() / 4.8, rotp.m_7098_() + 0.1, rotp.m_7094_() / 4.8);
                              }
                           }
                        }
                     }
                  }
               }
            }
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
