package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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
public class ProjectionRendererProcedure {
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
            ProjectionRendererProcedure.texture = texture;
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
      ProjectionRendererProcedure.scale = scale;
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      ProjectionRendererProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         ProjectionRendererProcedure.targetStage = targetStage;
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
      VertexBuffer texture_cube = null;
      VertexBuffer blackhole = null;
      VertexBuffer rings = null;
      String texts = "";
      new JsonObject();
      new JsonObject();
      new JsonObject();
      JsonObject data = new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      Vec3 local = Vec3.f_82478_;
      Vec3 offset = Vec3.f_82478_;
      double scaling = (double)0.0F;
      double object_scaling = (double)0.0F;
      double sys_X = (double)0.0F;
      double sys_Z = (double)0.0F;
      double sys_off_X = (double)0.0F;
      double sys_off_Z = (double)0.0F;
      double counter = (double)0.0F;
      double color = (double)0.0F;
      double i = (double)0.0F;
      double j = (double)0.0F;
      double k = (double)0.0F;
      double l = (double)0.0F;
      double layer = (double)0.0F;
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
                     if (blockstateiterator.m_60734_() == CosmosModBlocks.PROJECTOR_TABLE.get() && target(2)) {
                        texture_cube = TexturedcubeProcedure.execute();
                        blackhole = BlackholeProcedure.execute();
                        rings = RingsProcedure.execute();
                        if (!((<undefinedtype>)(new Object() {
                           public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "system").equals("none") && ((<undefinedtype>)(new Object() {
                           public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "planet").equals("none")) {
                           for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).projection_list) {
                              sys_off_X = (double)0.0F;
                              sys_off_Z = (double)0.0F;
                              sys_X = (double)0.0F;
                              sys_Z = (double)0.0F;
                              counter = (double)0.0F;
                              String var110;
                              if (dataelementiterator instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)dataelementiterator;
                                 var110 = _stringTag.m_7916_();
                              } else {
                                 var110 = "";
                              }

                              texts = var110;

                              try {
                                 data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
                              } catch (Exception var64) {
                              }

                              if (data.has("planet_data") && ((<undefinedtype>)(new Object() {
                                 public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                    BlockEntity blockEntity = world.m_7702_(pos);
                                    return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                                 }
                              })).getValue(world, new BlockPos(positionx, positiony, positionz), "system").equals(data.get("attached_dimention_id").getAsString())) {
                                 JsonObject var77 = data.get("planet_data").getAsJsonObject();

                                 for(int h = 0; h <= var77.size() - 1; ++h) {
                                    JsonObject var75 = var77.get((String)var77.keySet().stream().toList().get(h)).getAsJsonObject();
                                    if (var75.get("glowing").getAsBoolean()) {
                                       sys_X += var75.get("x").getAsDouble();
                                       sys_Z += var75.get("z").getAsDouble();
                                       ++counter;
                                    }
                                 }

                                 sys_off_X = sys_X / counter;
                                 sys_off_Z = sys_Z / counter;
                              }
                           }

                           scaling = 9.0E-6 * (((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") / (double)2.5F);
                           object_scaling = (double)1.0F;
                           offset = new Vec3(sys_off_X * scaling, (double)0.0F * scaling, sys_off_Z * scaling);
                           local = new Vec3((double)positionx + (double)0.5F, (double)positiony + 2.3 + ((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, new BlockPos(positionx, positiony, positionz), "offsetY") + ((<undefinedtype>)(new Object() {
                              public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.m_7702_(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                              }
                           })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") / (double)5.0F, (double)positionz + (double)0.5F);

                           for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).projection_list) {
                              String var111;
                              if (dataelementiterator instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)dataelementiterator;
                                 var111 = _stringTag.m_7916_();
                              } else {
                                 var111 = "";
                              }

                              texts = var111;

                              try {
                                 data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
                              } catch (Exception var63) {
                              }

                              if (data.has("planet_data") && ((<undefinedtype>)(new Object() {
                                 public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                    BlockEntity blockEntity = world.m_7702_(pos);
                                    return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                                 }
                              })).getValue(world, new BlockPos(positionx, positiony, positionz), "system").equals(data.get("attached_dimention_id").getAsString())) {
                                 JsonObject var78 = data.get("planet_data").getAsJsonObject();

                                 for(int h = 0; h <= var78.size() - 1; ++h) {
                                    JsonObject var76 = var78.get((String)var78.keySet().stream().toList().get(h)).getAsJsonObject();
                                    i = (double)0.0F;
                                    if (var76.get("glowing").getAsBoolean() && var76.get("texture_id").getAsString().equals("none")) {
                                       JsonObject var74 = var76.get("core_color").getAsJsonObject();
                                       JsonObject var72 = var76.get("bloom_color").getAsJsonObject();
                                       if (var74.get("r").getAsDouble() == (double)0.0F && var74.get("g").getAsDouble() == (double)0.0F && var74.get("b").getAsDouble() == (double)0.0F && var72.get("r").getAsDouble() == (double)255.0F && var72.get("g").getAsDouble() == (double)255.0F && var72.get("b").getAsDouble() == (double)255.0F) {
                                          RenderSystem.defaultBlendFunc();
                                       } else {
                                          RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                          if (begin(Mode.QUADS, false, true)) {
                                             layer = var76.get("layer").getAsDouble();

                                             for(int index0 = 0; index0 < (int)(layer + (double)1.0F); ++index0) {
                                                l = (double)0.5F / layer;
                                                j = i == layer ? (double)-0.5F + l * i : (double)0.5F - l * i;
                                                k = i == layer ? (double)0.5F - l * i : (double)-0.5F + l * i;
                                                color = i == layer ? (double)(-16777216 | (int)var74.get("r").getAsDouble() << 16 | (int)var74.get("g").getAsDouble() << 8 | (int)var74.get("b").getAsDouble()) : (double)(-16777216 | (int)var72.get("r").getAsDouble() << 16 | (int)var72.get("g").getAsDouble() << 8 | (int)var72.get("b").getAsDouble());
                                                add(j, k, j, 0.0F, 0.0F, (int)color);
                                                add(j, k, k, 0.0F, 1.0F, (int)color);
                                                add(k, k, k, 1.0F, 1.0F, (int)color);
                                                add(k, k, j, 1.0F, 0.0F, (int)color);
                                                add(k, j, j, 0.0F, 0.0F, (int)color);
                                                add(k, j, k, 0.0F, 1.0F, (int)color);
                                                add(j, j, k, 1.0F, 1.0F, (int)color);
                                                add(j, j, j, 1.0F, 0.0F, (int)color);
                                                add(j, k, k, 0.0F, 0.0F, (int)color);
                                                add(j, j, k, 0.0F, 1.0F, (int)color);
                                                add(k, j, k, 1.0F, 1.0F, (int)color);
                                                add(k, k, k, 1.0F, 0.0F, (int)color);
                                                add(k, k, k, 0.0F, 0.0F, (int)color);
                                                add(k, j, k, 0.0F, 1.0F, (int)color);
                                                add(k, j, j, 1.0F, 1.0F, (int)color);
                                                add(k, k, j, 1.0F, 0.0F, (int)color);
                                                add(k, k, j, 0.0F, 0.0F, (int)color);
                                                add(k, j, j, 0.0F, 1.0F, (int)color);
                                                add(j, j, j, 1.0F, 1.0F, (int)color);
                                                add(j, k, j, 1.0F, 0.0F, (int)color);
                                                add(j, k, j, 0.0F, 0.0F, (int)color);
                                                add(j, j, j, 0.0F, 1.0F, (int)color);
                                                add(j, j, k, 1.0F, 1.0F, (int)color);
                                                add(j, k, k, 1.0F, 0.0F, (int)color);
                                                ++i;
                                             }

                                             end();
                                          }
                                       }

                                       renderShape(var74.get("r").getAsDouble() == (double)0.0F && var74.get("g").getAsDouble() == (double)0.0F && var74.get("b").getAsDouble() == (double)0.0F && var72.get("r").getAsDouble() == (double)255.0F && var72.get("g").getAsDouble() == (double)255.0F && var72.get("b").getAsDouble() == (double)255.0F ? blackhole : shape(), local.m_7096_() - offset.m_7096_() + var76.get("x").getAsDouble() * scaling, local.m_7098_() - offset.m_7098_() + var76.get("y").getAsDouble() * scaling, local.m_7094_() - offset.m_7094_() + var76.get("z").getAsDouble() * scaling, (float)var76.get("yaw").getAsDouble(), (float)var76.get("pitch").getAsDouble(), (float)var76.get("roll").getAsDouble(), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), -674693121);
                                       clear();
                                    }

                                    if (!var76.get("texture_id").getAsString().equals("none")) {
                                       RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                       RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/" + var76.get("texture_id").getAsString() + ".png"));
                                       renderShape(texture_cube, local.m_7096_() - offset.m_7096_() + var76.get("x").getAsDouble() * scaling, local.m_7098_() - offset.m_7098_() + var76.get("y").getAsDouble() * scaling, local.m_7094_() - offset.m_7094_() + var76.get("z").getAsDouble() * scaling, (float)var76.get("yaw").getAsDouble(), (float)var76.get("pitch").getAsDouble(), (float)var76.get("roll").getAsDouble(), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), (float)(var76.get("scale").getAsDouble() * scaling * object_scaling), -674693121);
                                    }

                                    if (var76.get("ringed").getAsBoolean()) {
                                       RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                       JsonObject var71 = var76.get("ring_data").getAsJsonObject();

                                       for(int e = 0; e < var71.size(); ++e) {
                                          JsonObject var73 = var71.get((String)var71.keySet().stream().toList().get(e)).getAsJsonObject();
                                          RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/" + var73.get("texture_id").getAsString() + ".png"));
                                          renderShape(rings, local.m_7096_() - offset.m_7096_() + var76.get("x").getAsDouble() * scaling, local.m_7098_() - offset.m_7098_() + var76.get("y").getAsDouble() * scaling, local.m_7094_() - offset.m_7094_() + var76.get("z").getAsDouble() * scaling, (float)var76.get("yaw").getAsDouble(), (float)var76.get("pitch").getAsDouble(), (float)var76.get("roll").getAsDouble(), (float)(var73.get("scale_radius").getAsDouble() * scaling * object_scaling), (float)(var73.get("scale_radius").getAsDouble() * scaling * object_scaling), (float)(var73.get("scale_radius").getAsDouble() * scaling * object_scaling), -674693121);
                                       }
                                    }
                                 }
                              }
                           }
                        } else if (!((<undefinedtype>)(new Object() {
                           public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "system").equals("none") && !((<undefinedtype>)(new Object() {
                           public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "planet").equals("none")) {
                           for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).projection_list) {
                              String var10000;
                              if (dataelementiterator instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)dataelementiterator;
                                 var10000 = _stringTag.m_7916_();
                              } else {
                                 var10000 = "";
                              }

                              texts = var10000;

                              try {
                                 data = (JsonObject)(new Gson()).fromJson(texts, JsonObject.class);
                              } catch (Exception var62) {
                              }

                              local = new Vec3((double)positionx + (double)0.5F, (double)positiony + (double)3.5F + ((<undefinedtype>)(new Object() {
                                 public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                    BlockEntity blockEntity = world.m_7702_(pos);
                                    return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                 }
                              })).getValue(world, new BlockPos(positionx, positiony, positionz), "offsetY") + ((<undefinedtype>)(new Object() {
                                 public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                    BlockEntity blockEntity = world.m_7702_(pos);
                                    return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                 }
                              })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") / (double)1.5F, (double)positionz + (double)0.5F);
                              if (data.has("planet_data")) {
                                 i = (double)0.0F;
                                 if (((<undefinedtype>)(new Object() {
                                    public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                       BlockEntity blockEntity = world.m_7702_(pos);
                                       return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                                    }
                                 })).getValue(world, new BlockPos(positionx, positiony, positionz), "system").equals(data.get("attached_dimention_id").getAsString())) {
                                    JsonObject planet_data = data.get("planet_data").getAsJsonObject();
                                    if (planet_data.has(((<undefinedtype>)(new Object() {
                                       public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                          BlockEntity blockEntity = world.m_7702_(pos);
                                          return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                                       }
                                    })).getValue(world, new BlockPos(positionx, positiony, positionz), "planet"))) {
                                       JsonObject mint = planet_data.get(((<undefinedtype>)(new Object() {
                                          public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                             BlockEntity blockEntity = world.m_7702_(pos);
                                             return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                                          }
                                       })).getValue(world, new BlockPos(positionx, positiony, positionz), "planet")).getAsJsonObject();
                                       if (mint.get("glowing").getAsBoolean() && mint.get("texture_id").getAsString().equals("none")) {
                                          JsonObject color_core = mint.get("core_color").getAsJsonObject();
                                          JsonObject color_bloom = mint.get("bloom_color").getAsJsonObject();
                                          if (color_core.get("r").getAsDouble() == (double)0.0F && color_core.get("g").getAsDouble() == (double)0.0F && color_core.get("b").getAsDouble() == (double)0.0F && color_bloom.get("r").getAsDouble() == (double)255.0F && color_bloom.get("g").getAsDouble() == (double)255.0F && color_bloom.get("b").getAsDouble() == (double)255.0F) {
                                             RenderSystem.defaultBlendFunc();
                                          } else {
                                             RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                             if (begin(Mode.QUADS, false, true)) {
                                                layer = mint.get("layer").getAsDouble();

                                                for(int index1 = 0; index1 < (int)(layer + (double)1.0F); ++index1) {
                                                   l = (double)0.5F / layer;
                                                   j = i == layer ? (double)-0.5F + l * i : (double)0.5F - l * i;
                                                   k = i == layer ? (double)0.5F - l * i : (double)-0.5F + l * i;
                                                   color = i == layer ? (double)(-16777216 | (int)color_core.get("r").getAsDouble() << 16 | (int)color_core.get("g").getAsDouble() << 8 | (int)color_core.get("b").getAsDouble()) : (double)(-16777216 | (int)color_bloom.get("r").getAsDouble() << 16 | (int)color_bloom.get("g").getAsDouble() << 8 | (int)color_bloom.get("b").getAsDouble());
                                                   add(j, k, j, 0.0F, 0.0F, (int)color);
                                                   add(j, k, k, 0.0F, 1.0F, (int)color);
                                                   add(k, k, k, 1.0F, 1.0F, (int)color);
                                                   add(k, k, j, 1.0F, 0.0F, (int)color);
                                                   add(k, j, j, 0.0F, 0.0F, (int)color);
                                                   add(k, j, k, 0.0F, 1.0F, (int)color);
                                                   add(j, j, k, 1.0F, 1.0F, (int)color);
                                                   add(j, j, j, 1.0F, 0.0F, (int)color);
                                                   add(j, k, k, 0.0F, 0.0F, (int)color);
                                                   add(j, j, k, 0.0F, 1.0F, (int)color);
                                                   add(k, j, k, 1.0F, 1.0F, (int)color);
                                                   add(k, k, k, 1.0F, 0.0F, (int)color);
                                                   add(k, k, k, 0.0F, 0.0F, (int)color);
                                                   add(k, j, k, 0.0F, 1.0F, (int)color);
                                                   add(k, j, j, 1.0F, 1.0F, (int)color);
                                                   add(k, k, j, 1.0F, 0.0F, (int)color);
                                                   add(k, k, j, 0.0F, 0.0F, (int)color);
                                                   add(k, j, j, 0.0F, 1.0F, (int)color);
                                                   add(j, j, j, 1.0F, 1.0F, (int)color);
                                                   add(j, k, j, 1.0F, 0.0F, (int)color);
                                                   add(j, k, j, 0.0F, 0.0F, (int)color);
                                                   add(j, j, j, 0.0F, 1.0F, (int)color);
                                                   add(j, j, k, 1.0F, 1.0F, (int)color);
                                                   add(j, k, k, 1.0F, 0.0F, (int)color);
                                                   ++i;
                                                }

                                                end();
                                             }
                                          }

                                          renderShape(color_core.get("r").getAsDouble() == (double)0.0F && color_core.get("g").getAsDouble() == (double)0.0F && color_core.get("b").getAsDouble() == (double)0.0F && color_bloom.get("r").getAsDouble() == (double)255.0F && color_bloom.get("g").getAsDouble() == (double)255.0F && color_bloom.get("b").getAsDouble() == (double)255.0F ? blackhole : shape(), local.m_7096_(), local.m_7098_(), local.m_7094_(), (float)(mint.get("yaw").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "yaw_speed")), (float)(mint.get("pitch").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "pitch_speed")), (float)(mint.get("roll").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "roll_speed")), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), -674693121);
                                       }

                                       if (!mint.get("texture_id").getAsString().equals("none")) {
                                          RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                          RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/" + mint.get("texture_id").getAsString() + ".png"));
                                          renderShape(texture_cube, local.m_7096_(), local.m_7098_(), local.m_7094_(), (float)(mint.get("yaw").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "yaw_speed")), (float)(mint.get("pitch").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "pitch_speed")), (float)(mint.get("roll").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "roll_speed")), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), (float)((<undefinedtype>)(new Object() {
                                             public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                BlockEntity blockEntity = world.m_7702_(pos);
                                                return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                             }
                                          })).getValue(world, new BlockPos(positionx, positiony, positionz), "size"), -3604481);
                                       }

                                       if (mint.get("ringed").getAsBoolean()) {
                                          RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                          JsonObject ringed_data = mint.get("ring_data").getAsJsonObject();

                                          for(int e = 0; e < ringed_data.size(); ++e) {
                                             JsonObject ring_mint = ringed_data.get((String)ringed_data.keySet().stream().toList().get(e)).getAsJsonObject();
                                             RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/" + ring_mint.get("texture_id").getAsString() + ".png"));
                                             renderShape(rings, local.m_7096_(), local.m_7098_(), local.m_7094_(), (float)(mint.get("yaw").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "yaw_speed")), (float)(mint.get("pitch").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "pitch_speed")), (float)(mint.get("roll").getAsDouble() + (ticks + partialTick) * ((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "roll_speed")), (float)(((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") * ring_mint.get("scale_radius").getAsDouble() / mint.get("scale").getAsDouble()), (float)(((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") * ring_mint.get("scale_radius").getAsDouble() / mint.get("scale").getAsDouble()), (float)(((<undefinedtype>)(new Object() {
                                                public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                                                   BlockEntity blockEntity = world.m_7702_(pos);
                                                   return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                                                }
                                             })).getValue(world, new BlockPos(positionx, positiony, positionz), "size") * ring_mint.get("scale_radius").getAsDouble() / mint.get("scale").getAsDouble()), -674693121);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        if (((<undefinedtype>)(new Object() {
                           public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
                           }
                        })).getValue(world, new BlockPos(positionx, positiony, positionz), "state")) {
                           if (begin(Mode.QUADS, true, true)) {
                              add((double)-0.5F, (double)1.0F, (double)-0.5F, 1.0F, 0.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)0.5F, 0.0F, 0.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)0.5F, 0.0F, 0.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)-0.5F, 1.0F, 0.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)-0.5F, 0.0F, 0.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)0.5F, 1.0F, 0.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)0.5F, 1.0F, 0.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)-0.5F, 0.0F, 0.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)-0.5F, 0.0F, 0.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)-0.5F, 1.0F, 0.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)-0.5F, 1.0F, 0.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)-0.5F, 0.0F, 0.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)0.5F, 0.0F, 0.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)0.5F, 1.0F, 0.0F, -1);
                              add((double)-0.5F, (double)1.0F, (double)0.5F, 1.0F, 0.0F, -1);
                              add((double)0.5F, (double)1.0F, (double)0.5F, 0.0F, 0.0F, -1);
                              add((double)0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
                              add((double)-0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
                              end();
                           }

                           RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/projection.png"));
                           renderShape(shape(), (double)positionx + (double)0.5F, (double)(positiony + 1), (double)positionz + (double)0.5F, 0.0F, 0.0F, 0.0F, 3.4F, 1.6F, 3.4F, -922746881);
                        }

                        release();
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
