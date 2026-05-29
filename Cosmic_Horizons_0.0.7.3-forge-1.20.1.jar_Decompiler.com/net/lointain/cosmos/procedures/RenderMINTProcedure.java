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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
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
public class RenderMINTProcedure {
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
            RenderMINTProcedure.texture = texture;
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
      RenderMINTProcedure.scale = scale;
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   private static void system(boolean worldCoordinate) {
      RenderMINTProcedure.worldCoordinate = worldCoordinate;
   }

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         RenderMINTProcedure.targetStage = targetStage;
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
         execute(event, level, entity, (double)partialTick, (double)ticks);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableDepthTest();
      }

   }

   public static void execute(LevelAccessor world, Entity entity, double partialTick, double ticks) {
      execute((Event)null, world, entity, partialTick, ticks);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, double partialTick, double ticks) {
      if (entity != null) {
         new ArrayList();
         List<Object> ring_case_list = new ArrayList();
         List<Object> skyring_id = new ArrayList();
         Direction render_direction = Direction.NORTH;
         String type = "";
         boolean atmosphere_capability = false;
         boolean blackhole_logic = false;
         boolean effect_logic = false;
         VertexBuffer texture_cube = null;
         VertexBuffer empty_shape = null;
         VertexBuffer blackhole = null;
         VertexBuffer ring4 = null;
         VertexBuffer ring3 = null;
         VertexBuffer rings = null;
         VertexBuffer sky_box = null;
         VertexBuffer ring2 = null;
         VertexBuffer ring1 = null;
         VertexBuffer cube = null;
         VertexBuffer curated_texture_cube = null;
         Vec3 SkyOBJ_Scale = Vec3.f_82478_;
         Vec3 objPos = Vec3.f_82478_;
         Vec3 objRot = Vec3.f_82478_;
         Vec3 calc_rotated = Vec3.f_82478_;
         Vec3 SkyOBJ_Pos = Vec3.f_82478_;
         Vec3 rotated = Vec3.f_82478_;
         Vec3 SkyOBJ_Rot = Vec3.f_82478_;
         Vec3 objScale = Vec3.f_82478_;
         Vec3 midpoint = Vec3.f_82478_;
         Vec3 vertex4 = Vec3.f_82478_;
         Vec3 vertex1 = Vec3.f_82478_;
         Vec3 vertex3 = Vec3.f_82478_;
         Vec3 vertex2 = Vec3.f_82478_;
         Vec3 uv = Vec3.f_82478_;
         Vec3 vector3 = Vec3.f_82478_;
         Vec3 vector4 = Vec3.f_82478_;
         Vec3 vector1 = Vec3.f_82478_;
         Vec3 vector2 = Vec3.f_82478_;
         Vec3 model_color = Vec3.f_82478_;
         double skybox_roll = (double)0.0F;
         double skybox_pitch = (double)0.0F;
         double renderScale = (double)0.0F;
         double Dist_Yaw = (double)0.0F;
         double Day_Leveling = (double)0.0F;
         double skybox_yaw = (double)0.0F;
         double Dist_Pitch = (double)0.0F;
         double skybox_alpha = (double)0.0F;
         double Dist_Roll = (double)0.0F;
         double skyobject_alpha = (double)0.0F;
         double color = (double)0.0F;
         double i = (double)0.0F;
         double j = (double)0.0F;
         double k = (double)0.0F;
         double l = (double)0.0F;
         double layer = (double)0.0F;
         double counter = (double)0.0F;
         double D_iter = (double)0.0F;
         double p = (double)0.0F;
         double q = (double)0.0F;
         double iter = (double)0.0F;
         double current_tick = (double)0.0F;
         double frames = (double)0.0F;
         double ring_rot = (double)0.0F;
         double height_alpha = (double)0.0F;
         double multiple = (double)0.0F;
         double main_color = (double)0.0F;
         double height_map_alpha = (double)0.0F;
         double height_map_scale = (double)0.0F;
         double model_intensity = (double)0.0F;
         double model_step = (double)0.0F;
         double model_speed = (double)0.0F;
         if (world.m_5776_()) {
            current_tick = ticks + partialTick;
            if (target(1)) {
               scale(1.0F);
               bob(true);
               if (CosmosModVariables.WorldVariables.get(world).skybox_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
                  system(true);
                  sky_box = SkyboxshapeProcedure.execute();
                  new CompoundTag();
                  Tag _stringTag = CosmosModVariables.WorldVariables.get(world).skybox_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  CompoundTag var10000;
                  if (_stringTag instanceof CompoundTag) {
                     CompoundTag _compoundTag = (CompoundTag)_stringTag;
                     var10000 = _compoundTag.m_6426_();
                  } else {
                     var10000 = new CompoundTag();
                  }

                  CompoundTag skybox = var10000;
                  _stringTag = CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  if (_stringTag instanceof CompoundTag) {
                     CompoundTag _compoundTag = (CompoundTag)_stringTag;
                     var10000 = _compoundTag.m_6426_();
                  } else {
                     var10000 = new CompoundTag();
                  }

                  CompoundTag atmospheric_data = var10000;
                  double var708 = entity.m_20318_((float)partialTick).m_7098_();
                  _stringTag = atmospheric_data.m_128423_("atmosphere_y");
                  double var10001;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10001 = _doubleTag.m_7061_();
                  } else {
                     var10001 = (double)0.0F;
                  }

                  height_alpha = Mth.m_14008_((var708 - (var10001 - (double)255.0F - (double)25.0F)) * (double)1.0F, (double)0.0F, (double)255.0F);
                  float var710;
                  if (skybox.m_128441_("rotation_plane")) {
                     _stringTag = skybox.m_128423_("rotation_plane");
                     String var709;
                     if (_stringTag instanceof StringTag) {
                        StringTag _stringTag = (StringTag)_stringTag;
                        var709 = _stringTag.m_7916_();
                     } else {
                        var709 = "";
                     }

                     var710 = var709.equals("yaw") ? world.m_46942_((float)partialTick) * 360.0F : 0.0F;
                  } else {
                     var710 = 0.0F;
                  }

                  skybox_yaw = (double)var710;
                  if (skybox.m_128441_("rotation_plane")) {
                     _stringTag = skybox.m_128423_("rotation_plane");
                     String var711;
                     if (_stringTag instanceof StringTag) {
                        StringTag _stringTag = (StringTag)_stringTag;
                        var711 = _stringTag.m_7916_();
                     } else {
                        var711 = "";
                     }

                     var710 = var711.equals("pitch") ? world.m_46942_((float)partialTick) * 360.0F : 0.0F;
                  } else {
                     var710 = 0.0F;
                  }

                  skybox_pitch = (double)var710;
                  if (skybox.m_128441_("rotation_plane")) {
                     _stringTag = skybox.m_128423_("rotation_plane");
                     String var713;
                     if (_stringTag instanceof StringTag) {
                        StringTag _stringTag = (StringTag)_stringTag;
                        var713 = _stringTag.m_7916_();
                     } else {
                        var713 = "";
                     }

                     var710 = var713.equals("roll") ? world.m_46942_((float)partialTick) * 360.0F : 0.0F;
                  } else {
                     var710 = 0.0F;
                  }

                  skybox_roll = (double)var710;
                  if (skybox.m_128441_("fade")) {
                     Tag var130 = skybox.m_128423_("fade");
                     String var715;
                     if (var130 instanceof StringTag) {
                        StringTag _stringTag = (StringTag)var130;
                        var715 = _stringTag.m_7916_();
                     } else {
                        var715 = "";
                     }

                     if (var715.equals("night")) {
                        var710 = world instanceof ClientLevel ? ((ClientLevel)world).m_104811_((float)partialTick) : 0.0F;
                     } else {
                        var130 = skybox.m_128423_("fade");
                        if (var130 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var130;
                           var715 = _stringTag.m_7916_();
                        } else {
                           var715 = "";
                        }

                        var710 = var715.equals("day") ? 1.0F - (world instanceof ClientLevel ? ((ClientLevel)world).m_104811_((float)partialTick) : 0.0F) : 1.0F;
                     }
                  } else {
                     var710 = 1.0F;
                  }

                  skybox_alpha = (double)var710;
                  ResourceLocation var833 = new ResourceLocation;
                  _stringTag = skybox.m_128423_("texture_id");
                  String var10003;
                  if (_stringTag instanceof StringTag) {
                     StringTag _stringTag = (StringTag)_stringTag;
                     var10003 = _stringTag.m_7916_();
                  } else {
                     var10003 = "";
                  }

                  var833.<init>("cosmos:textures/" + var10003 + ".png");
                  RenderSystem.setShaderTexture(0, var833);
                  scale(1.0F);
                  double var834 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7096_();
                  double var10002 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7098_();
                  double var964 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7094_();
                  _stringTag = skybox.m_128423_("yaw");
                  double var10004;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10004 = _doubleTag.m_7061_();
                  } else {
                     var10004 = (double)0.0F;
                  }

                  float var1011 = (float)(var10004 + skybox_yaw);
                  _stringTag = skybox.m_128423_("pitch");
                  double var10005;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10005 = _doubleTag.m_7061_();
                  } else {
                     var10005 = (double)0.0F;
                  }

                  float var1052 = (float)(var10005 + skybox_pitch);
                  _stringTag = skybox.m_128423_("roll");
                  double var10006;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10006 = _doubleTag.m_7061_();
                  } else {
                     var10006 = (double)0.0F;
                  }

                  float var1084 = (float)(var10006 + skybox_roll);
                  float var10007 = Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F;
                  float var10008 = Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F;
                  float var10009 = Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F;
                  _stringTag = skybox.m_128423_("alpha");
                  double var10011;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10011 = _doubleTag.m_7061_();
                  } else {
                     var10011 = (double)0.0F;
                  }

                  renderShape(sky_box, var834, var10002, var964, var1011, var1052, var1084, var10007, var10008, var10009, (int)Mth.m_14008_(skybox_alpha * var10011, (double)0.0F, (double)255.0F) << 24 | 16711680 | '\uff00' | 255);
                  if (CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
                     double var718 = entity.m_20318_((float)partialTick).m_7098_();
                     _stringTag = atmospheric_data.m_128423_("atmosphere_y");
                     if (_stringTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_stringTag;
                        var834 = _doubleTag.m_7061_();
                     } else {
                        var834 = (double)0.0F;
                     }

                     if (var718 > var834 - (double)255.0F - (double)25.0F) {
                        var834 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7096_();
                        var10002 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7098_();
                        var964 = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_7094_();
                        Tag var307 = skybox.m_128423_("yaw");
                        double var1012;
                        if (var307 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var307;
                           var1012 = _doubleTag.m_7061_();
                        } else {
                           var1012 = (double)0.0F;
                        }

                        float var1013 = (float)(var1012 + skybox_yaw);
                        var307 = skybox.m_128423_("pitch");
                        double var1053;
                        if (var307 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var307;
                           var1053 = _doubleTag.m_7061_();
                        } else {
                           var1053 = (double)0.0F;
                        }

                        float var1054 = (float)(var1053 + skybox_pitch);
                        var307 = skybox.m_128423_("roll");
                        double var1085;
                        if (var307 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var307;
                           var1085 = _doubleTag.m_7061_();
                        } else {
                           var1085 = (double)0.0F;
                        }

                        renderShape(sky_box, var834, var10002, var964, var1013, var1054, (float)(var1085 + skybox_roll), Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F, Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F, Minecraft.m_91087_().f_91063_.m_109152_() * 2.0F, (int)height_alpha << 24 | 16711680 | '\uff00' | 255);
                     }
                  }
               }

               if (CosmosModVariables.WorldVariables.get(world).sky_object_data.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
                  texture_cube = TexturedcubeProcedure.execute();
                  curated_texture_cube = TexturedCubeCuratedProcedure.execute();
                  blackhole = BlackholeProcedure.execute();
                  ring1 = Ring1Procedure.execute();
                  ring2 = Ring2Procedure.execute();
                  ring3 = Ring3Procedure.execute();
                  ring4 = Ring4Procedure.execute();
                  rings = RingsProcedure.execute();
                  system(false);
                  scale(Minecraft.m_91087_().f_91063_.m_109152_() / 1.0F);
                  Tag var302 = CosmosModVariables.WorldVariables.get(world).sky_object_data.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  ListTag var719;
                  if (var302 instanceof ListTag) {
                     ListTag _listTag = (ListTag)var302;
                     var719 = _listTag.m_6426_();
                  } else {
                     var719 = new ListTag();
                  }

                  for(Tag dataelementiterator : var719) {
                     CompoundTag var720;
                     if (dataelementiterator instanceof CompoundTag) {
                        CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
                        var720 = _compoundTag.m_6426_();
                     } else {
                        var720 = new CompoundTag();
                     }

                     CompoundTag sky_object = var720;
                     atmosphere_capability = false;
                     float var722;
                     if (sky_object.m_128441_("fade")) {
                        Tag var132 = sky_object.m_128423_("fade");
                        String var721;
                        if (var132 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var132;
                           var721 = _stringTag.m_7916_();
                        } else {
                           var721 = "";
                        }

                        if (var721.equals("night")) {
                           var722 = world instanceof ClientLevel ? ((ClientLevel)world).m_104811_((float)partialTick) : 0.0F;
                        } else {
                           var132 = sky_object.m_128423_("fade");
                           if (var132 instanceof StringTag) {
                              StringTag _stringTag = (StringTag)var132;
                              var721 = _stringTag.m_7916_();
                           } else {
                              var721 = "";
                           }

                           var722 = var721.equals("day") ? 1.0F - (world instanceof ClientLevel ? ((ClientLevel)world).m_104811_((float)partialTick) : 0.0F) : 1.0F;
                        }
                     } else {
                        var722 = 1.0F;
                     }

                     skyobject_alpha = (double)var722;
                     ring_case_list.clear();
                     skyring_id.clear();
                     Tag _doubleTag = sky_object.m_128423_("type");
                     String var724;
                     if (_doubleTag instanceof StringTag) {
                        StringTag _stringTag = (StringTag)_doubleTag;
                        var724 = _stringTag.m_7916_();
                     } else {
                        var724 = "";
                     }

                     if (var724.equals("ring")) {
                        bob(false);
                        SkyOBJ_Pos = new Vec3((double)0.0F, -0.0085, (double)0.0F);
                        Vec3 var744 = new Vec3;
                        Tag var434 = sky_object.m_128423_("pitch");
                        double var926;
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var926 = _doubleTag.m_7061_();
                        } else {
                           var926 = (double)0.0F;
                        }

                        var434 = sky_object.m_128423_("yaw");
                        double var973;
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var973 = _doubleTag.m_7061_();
                        } else {
                           var973 = (double)0.0F;
                        }

                        var434 = sky_object.m_128423_("roll");
                        double var1018;
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var1018 = _doubleTag.m_7061_();
                        } else {
                           var1018 = (double)0.0F;
                        }

                        var744.<init>(var926, var973, var1018);
                        SkyOBJ_Rot = var744;
                        var744 = new Vec3;
                        var434 = sky_object.m_128423_("scale_radius");
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var926 = _doubleTag.m_7061_();
                        } else {
                           var926 = (double)0.0F;
                        }

                        var434 = sky_object.m_128423_("scale_radius");
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var973 = _doubleTag.m_7061_();
                        } else {
                           var973 = (double)0.0F;
                        }

                        var434 = sky_object.m_128423_("scale_radius");
                        if (var434 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var434;
                           var1018 = _doubleTag.m_7061_();
                        } else {
                           var1018 = (double)0.0F;
                        }

                        var744.<init>(var926, var973, var1018);
                        SkyOBJ_Scale = var744;
                        var434 = sky_object.m_128423_("additive");
                        boolean var746;
                        if (var434 instanceof ByteTag) {
                           ByteTag _byteTag = (ByteTag)var434;
                           var746 = _byteTag.m_7063_() == 1;
                        } else {
                           var746 = false;
                        }

                        if (var746) {
                           RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                        } else {
                           RenderSystem.defaultBlendFunc();
                        }

                        ResourceLocation var861 = new ResourceLocation;
                        var434 = sky_object.m_128423_("texture_id");
                        String var975;
                        if (var434 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var434;
                           var975 = _stringTag.m_7916_();
                        } else {
                           var975 = "";
                        }

                        var861.<init>("cosmos:textures/" + var975 + ".png");
                        RenderSystem.setShaderTexture(0, var861);
                     } else {
                        bob(true);
                        Day_Leveling = (double)(world.m_46942_((float)partialTick) * 360.0F);
                        Tag var133 = sky_object.m_128423_("yaw_speed");
                        double var837;
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        double var725 = Day_Leveling * var837;
                        var133 = sky_object.m_128423_("yaw");
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        Dist_Yaw = var725 + var837;
                        var133 = sky_object.m_128423_("pitch_speed");
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        var725 = Day_Leveling * var837;
                        var133 = sky_object.m_128423_("pitch");
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        Dist_Pitch = var725 + var837;
                        var133 = sky_object.m_128423_("roll_speed");
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        var725 = Day_Leveling * var837;
                        var133 = sky_object.m_128423_("roll");
                        if (var133 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var133;
                           var837 = _doubleTag.m_7061_();
                        } else {
                           var837 = (double)0.0F;
                        }

                        Dist_Roll = var725 + var837;
                        rotated = (new Vec3((double)0.0F, (double)1.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)Dist_Roll).m_82496_(-0.017453292F * (float)Dist_Pitch).m_82524_(((float)Math.PI / 180F) * (float)Dist_Yaw);
                        calc_rotated = rotated.m_82490_((double)(Minecraft.m_91087_().f_91063_.m_109152_() / 2.0F));
                        SkyOBJ_Pos = rotated;
                        Vec3 var728 = new Vec3;
                        Tag kj = sky_object.m_128423_("object_pitch");
                        double var916;
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var916 = _doubleTag.m_7061_();
                        } else {
                           var916 = (double)0.0F;
                        }

                        kj = sky_object.m_128423_("object_yaw");
                        double var966;
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var966 = _doubleTag.m_7061_();
                        } else {
                           var966 = (double)0.0F;
                        }

                        kj = sky_object.m_128423_("object_roll");
                        double var1014;
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var1014 = _doubleTag.m_7061_();
                        } else {
                           var1014 = (double)0.0F;
                        }

                        var728.<init>(var916, var966, var1014);
                        SkyOBJ_Rot = var728;
                        var728 = new Vec3;
                        kj = sky_object.m_128423_("scale");
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var916 = _doubleTag.m_7061_();
                        } else {
                           var916 = (double)0.0F;
                        }

                        kj = sky_object.m_128423_("scale");
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var966 = _doubleTag.m_7061_();
                        } else {
                           var966 = (double)0.0F;
                        }

                        kj = sky_object.m_128423_("scale");
                        if (kj instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)kj;
                           var1014 = _doubleTag.m_7061_();
                        } else {
                           var1014 = (double)0.0F;
                        }

                        var728.<init>(var916, var966, var1014);
                        SkyOBJ_Scale = var728;
                        if (sky_object.m_128441_("core_color") && sky_object.m_128441_("bloom_color")) {
                           label1725: {
                              RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                              i = (double)0.0F;
                              kj = sky_object.m_128423_("core_color");
                              CompoundTag var730;
                              if (kj instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)kj;
                                 var730 = _compoundTag.m_6426_();
                              } else {
                                 var730 = new CompoundTag();
                              }

                              CompoundTag color_core = var730;
                              kj = sky_object.m_128423_("bloom_color");
                              if (kj instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)kj;
                                 var730 = _compoundTag.m_6426_();
                              } else {
                                 var730 = new CompoundTag();
                              }

                              CompoundTag color_bloom = var730;
                              Tag var137 = color_bloom.m_128423_("r");
                              if (var137 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var137;
                                 var837 = _doubleTag.m_7061_();
                              } else {
                                 var837 = (double)0.0F;
                              }

                              int var732 = -16777216 | (int)var837 << 16;
                              var137 = color_bloom.m_128423_("g");
                              if (var137 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var137;
                                 var837 = _doubleTag.m_7061_();
                              } else {
                                 var837 = (double)0.0F;
                              }

                              var732 |= (int)var837 << 8;
                              var137 = color_bloom.m_128423_("b");
                              if (var137 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var137;
                                 var837 = _doubleTag.m_7061_();
                              } else {
                                 var837 = (double)0.0F;
                              }

                              if ((var732 | (int)var837) == -1) {
                                 var137 = color_core.m_128423_("r");
                                 if (var137 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var137;
                                    var837 = _doubleTag.m_7061_();
                                 } else {
                                    var837 = (double)0.0F;
                                 }

                                 var732 = -16777216 | (int)var837 << 16;
                                 var137 = color_core.m_128423_("g");
                                 if (var137 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var137;
                                    var837 = _doubleTag.m_7061_();
                                 } else {
                                    var837 = (double)0.0F;
                                 }

                                 var732 |= (int)var837 << 8;
                                 var137 = color_core.m_128423_("b");
                                 if (var137 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var137;
                                    var837 = _doubleTag.m_7061_();
                                 } else {
                                    var837 = (double)0.0F;
                                 }

                                 if ((var732 | (int)var837) == -16777216) {
                                    RenderSystem.defaultBlendFunc();
                                    break label1725;
                                 }
                              }

                              if (begin(Mode.QUADS, false, true)) {
                                 layer = (double)48.0F;

                                 for(int index0 = 0; index0 < (int)(layer + (double)1.0F); ++index0) {
                                    l = (double)0.5F / layer;
                                    j = i == layer ? (double)-0.5F + l * i : (double)0.5F - l * i;
                                    k = i == layer ? (double)0.5F - l * i : (double)-0.5F + l * i;
                                    if (i == layer) {
                                       Tag var144 = color_core.m_128423_("r");
                                       if (var144 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var144;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 = -16777216 | (int)var837 << 16;
                                       var144 = color_core.m_128423_("g");
                                       if (var144 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var144;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 |= (int)var837 << 8;
                                       var144 = color_core.m_128423_("b");
                                       if (var144 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var144;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 |= (int)var837;
                                    } else {
                                       Tag var690 = color_bloom.m_128423_("r");
                                       if (var690 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var690;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 = -16777216 | (int)var837 << 16;
                                       var690 = color_bloom.m_128423_("g");
                                       if (var690 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var690;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 |= (int)var837 << 8;
                                       var690 = color_bloom.m_128423_("b");
                                       if (var690 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var690;
                                          var837 = _doubleTag.m_7061_();
                                       } else {
                                          var837 = (double)0.0F;
                                       }

                                       var732 |= (int)var837;
                                    }

                                    color = (double)var732;
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
                        } else {
                           RenderSystem.defaultBlendFunc();
                           ResourceLocation var843 = new ResourceLocation;
                           kj = sky_object.m_128423_("texture_id");
                           String var968;
                           if (kj instanceof StringTag) {
                              StringTag _stringTag = (StringTag)kj;
                              var968 = _stringTag.m_7916_();
                           } else {
                              var968 = "";
                           }

                           var843.<init>("cosmos:textures/" + var968 + ".png");
                           RenderSystem.setShaderTexture(0, var843);
                        }

                        if (sky_object.m_128441_("ring_data")) {
                           kj = sky_object.m_128423_("ring_data");
                           CompoundTag var741;
                           if (kj instanceof CompoundTag) {
                              CompoundTag _compoundTag = (CompoundTag)kj;
                              var741 = _compoundTag.m_6426_();
                           } else {
                              var741 = new CompoundTag();
                           }

                           CompoundTag ringed_data = var741;
                           ring_case_list.add(calc_rotated.m_82554_(new Vec3((double)0.0F, (double)0.0F, (double)0.0F)));
                           skyring_id.add("0");

                           for(String keyiterator : ringed_data.m_128431_()) {
                              Tag _stringTag = ringed_data.m_128423_(keyiterator);
                              if (_stringTag instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)_stringTag;
                                 var741 = _compoundTag.m_6426_();
                              } else {
                                 var741 = new CompoundTag();
                              }

                              CompoundTag ring_mint = var741;
                              Vec3 var918 = new Vec3;
                              _stringTag = ring_mint.m_128423_("radius");
                              if (_stringTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringTag;
                                 var1014 = _doubleTag.m_7061_();
                              } else {
                                 var1014 = (double)0.0F;
                              }

                              var918.<init>(var1014, (double)0.0F, (double)0.0F);
                              ring_case_list.add(calc_rotated.m_82546_(var918).m_82535_(-0.017453292F * (float)(-SkyOBJ_Rot.m_7094_())).m_82496_(-0.017453292F * (float)SkyOBJ_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-SkyOBJ_Rot.m_7098_())).m_82554_(new Vec3((double)0.0F, (double)0.0F, (double)0.0F)));
                              Tag _stringValue = ring_mint.m_128423_("texture_id");
                              String var856;
                              if (_stringValue instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)_stringValue;
                                 var856 = _stringTag.m_7916_();
                              } else {
                                 var856 = "";
                              }

                              DecimalFormat var919 = new DecimalFormat("##.##");
                              _stringValue = ring_mint.m_128423_("scale_radius");
                              if (_stringValue instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringValue;
                                 var966 = _doubleTag.m_7061_();
                              } else {
                                 var966 = (double)0.0F;
                              }

                              skyring_id.add("`" + var856 + "*1|" + var919.format(var966) + "?");
                              Vec3 var920 = new Vec3;
                              _stringTag = ring_mint.m_128423_("radius");
                              if (_stringTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringTag;
                                 var1014 = _doubleTag.m_7061_();
                              } else {
                                 var1014 = (double)0.0F;
                              }

                              var920.<init>(var1014, (double)0.0F, (double)0.0F);
                              ring_case_list.add(calc_rotated.m_82549_(var920).m_82535_(-0.017453292F * (float)(-SkyOBJ_Rot.m_7094_())).m_82496_(-0.017453292F * (float)SkyOBJ_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-SkyOBJ_Rot.m_7098_())).m_82554_(new Vec3((double)0.0F, (double)0.0F, (double)0.0F)));
                              _stringValue = ring_mint.m_128423_("texture_id");
                              if (_stringValue instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)_stringValue;
                                 var856 = _stringTag.m_7916_();
                              } else {
                                 var856 = "";
                              }

                              DecimalFormat var921 = new DecimalFormat("##.##");
                              _stringValue = ring_mint.m_128423_("scale_radius");
                              if (_stringValue instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringValue;
                                 var966 = _doubleTag.m_7061_();
                              } else {
                                 var966 = (double)0.0F;
                              }

                              skyring_id.add("`" + var856 + "*2|" + var921.format(var966) + "?");
                              Vec3 var922 = new Vec3;
                              _stringTag = ring_mint.m_128423_("radius");
                              double var1086;
                              if (_stringTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringTag;
                                 var1086 = _doubleTag.m_7061_();
                              } else {
                                 var1086 = (double)0.0F;
                              }

                              var922.<init>((double)0.0F, (double)0.0F, var1086);
                              ring_case_list.add(calc_rotated.m_82546_(var922).m_82535_(-0.017453292F * (float)(-SkyOBJ_Rot.m_7094_())).m_82496_(-0.017453292F * (float)SkyOBJ_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-SkyOBJ_Rot.m_7098_())).m_82554_(new Vec3((double)0.0F, (double)0.0F, (double)0.0F)));
                              _stringValue = ring_mint.m_128423_("texture_id");
                              if (_stringValue instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)_stringValue;
                                 var856 = _stringTag.m_7916_();
                              } else {
                                 var856 = "";
                              }

                              DecimalFormat var923 = new DecimalFormat("##.##");
                              _stringValue = ring_mint.m_128423_("scale_radius");
                              if (_stringValue instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringValue;
                                 var966 = _doubleTag.m_7061_();
                              } else {
                                 var966 = (double)0.0F;
                              }

                              skyring_id.add("`" + var856 + "*3|" + var923.format(var966) + "?");
                              Vec3 var924 = new Vec3;
                              _stringTag = ring_mint.m_128423_("radius");
                              if (_stringTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringTag;
                                 var1086 = _doubleTag.m_7061_();
                              } else {
                                 var1086 = (double)0.0F;
                              }

                              var924.<init>((double)0.0F, (double)0.0F, var1086);
                              ring_case_list.add(calc_rotated.m_82549_(var924).m_82535_(-0.017453292F * (float)(-SkyOBJ_Rot.m_7094_())).m_82496_(-0.017453292F * (float)SkyOBJ_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-SkyOBJ_Rot.m_7098_())).m_82554_(new Vec3((double)0.0F, (double)0.0F, (double)0.0F)));
                              _stringValue = ring_mint.m_128423_("texture_id");
                              if (_stringValue instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)_stringValue;
                                 var856 = _stringTag.m_7916_();
                              } else {
                                 var856 = "";
                              }

                              DecimalFormat var925 = new DecimalFormat("##.##");
                              _stringValue = ring_mint.m_128423_("scale_radius");
                              if (_stringValue instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_stringValue;
                                 var966 = _doubleTag.m_7061_();
                              } else {
                                 var966 = (double)0.0F;
                              }

                              skyring_id.add("`" + var856 + "*4|" + var925.format(var966) + "?");
                           }

                           for(int ki = 0; ki < skyring_id.size() - 1; ++ki) {
                              for(int kj = 0; kj < skyring_id.size() - ki - 1; ++kj) {
                                 Object var611 = ring_case_list.get(kj);
                                 double var743;
                                 if (var611 instanceof Number) {
                                    Number _doubleValue = (Number)var611;
                                    var743 = _doubleValue.doubleValue();
                                 } else {
                                    var743 = (double)0.0F;
                                 }

                                 var611 = ring_case_list.get(kj + 1);
                                 if (var611 instanceof Number) {
                                    Number _doubleValue = (Number)var611;
                                    var837 = _doubleValue.doubleValue();
                                 } else {
                                    var837 = (double)0.0F;
                                 }

                                 if (var743 < var837) {
                                    Collections.swap(ring_case_list, kj, kj + 1);
                                    Collections.swap(skyring_id, kj, kj + 1);
                                 }
                              }
                           }
                        }
                     }

                     for(int ring_case = 0; ring_case < (skyring_id.isEmpty() ? 1 : skyring_id.size()); ++ring_case) {
                        effect_logic = false;
                        if (!skyring_id.isEmpty()) {
                           Object seq = skyring_id.get(ring_case);
                           if (seq instanceof String) {
                              String _stringValue = (String)seq;
                              var724 = _stringValue;
                           } else {
                              var724 = "";
                           }

                           if (var724.equals("0")) {
                              effect_logic = true;
                              Vec3 var748 = new Vec3;
                              Tag _doubleTag = sky_object.m_128423_("scale");
                              double var928;
                              if (_doubleTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                 var928 = _doubleTag.m_7061_();
                              } else {
                                 var928 = (double)0.0F;
                              }

                              _doubleTag = sky_object.m_128423_("scale");
                              double var976;
                              if (_doubleTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                 var976 = _doubleTag.m_7061_();
                              } else {
                                 var976 = (double)0.0F;
                              }

                              _doubleTag = sky_object.m_128423_("scale");
                              double var1020;
                              if (_doubleTag instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                 var1020 = _doubleTag.m_7061_();
                              } else {
                                 var1020 = (double)0.0F;
                              }

                              var748.<init>(var928, var976, var1020);
                              SkyOBJ_Scale = var748;
                              if (!sky_object.m_128441_("core_color") && !sky_object.m_128441_("bloom_color")) {
                                 ResourceLocation var862 = new ResourceLocation;
                                 _doubleTag = sky_object.m_128423_("texture_id");
                                 String var977;
                                 if (_doubleTag instanceof StringTag) {
                                    StringTag _stringTag = (StringTag)_doubleTag;
                                    var977 = _stringTag.m_7916_();
                                 } else {
                                    var977 = "";
                                 }

                                 var862.<init>("cosmos:textures/" + var977 + ".png");
                                 RenderSystem.setShaderTexture(0, var862);
                              }
                           } else {
                              Vec3 var749 = new Vec3;
                              Object var929 = new Object() {
                                 double convert(String s) {
                                    try {
                                       return Double.parseDouble(s.trim());
                                    } catch (Exception var3) {
                                       return (double)0.0F;
                                    }
                                 }
                              };
                              Object _doubleTag = skyring_id.get(ring_case);
                              String var978;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var978 = _stringValue;
                              } else {
                                 var978 = "";
                              }

                              _doubleTag = skyring_id.get(ring_case);
                              String var1021;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1021 = _stringValue;
                              } else {
                                 var1021 = "";
                              }

                              int var1022 = var1021.indexOf("|") + "|".length();
                              _doubleTag = skyring_id.get(ring_case);
                              String var1055;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1055 = _stringValue;
                              } else {
                                 var1055 = "";
                              }

                              double var930 = ((<undefinedtype>)var929).convert(var978.substring(var1022, var1055.indexOf("?")));
                              Object var979 = new Object() {
                                 double convert(String s) {
                                    try {
                                       return Double.parseDouble(s.trim());
                                    } catch (Exception var3) {
                                       return (double)0.0F;
                                    }
                                 }
                              };
                              _doubleTag = skyring_id.get(ring_case);
                              String var1023;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1023 = _stringValue;
                              } else {
                                 var1023 = "";
                              }

                              _doubleTag = skyring_id.get(ring_case);
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1055 = _stringValue;
                              } else {
                                 var1055 = "";
                              }

                              int var1057 = var1055.indexOf("|") + "|".length();
                              _doubleTag = skyring_id.get(ring_case);
                              String var1088;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1088 = _stringValue;
                              } else {
                                 var1088 = "";
                              }

                              double var980 = ((<undefinedtype>)var979).convert(var1023.substring(var1057, var1088.indexOf("?")));
                              Object var1024 = new Object() {
                                 double convert(String s) {
                                    try {
                                       return Double.parseDouble(s.trim());
                                    } catch (Exception var3) {
                                       return (double)0.0F;
                                    }
                                 }
                              };
                              _doubleTag = skyring_id.get(ring_case);
                              String var1058;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1058 = _stringValue;
                              } else {
                                 var1058 = "";
                              }

                              _doubleTag = skyring_id.get(ring_case);
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1088 = _stringValue;
                              } else {
                                 var1088 = "";
                              }

                              int var1090 = var1088.indexOf("|") + "|".length();
                              _doubleTag = skyring_id.get(ring_case);
                              String var1095;
                              if (_doubleTag instanceof String) {
                                 String _stringValue = (String)_doubleTag;
                                 var1095 = _stringValue;
                              } else {
                                 var1095 = "";
                              }

                              var749.<init>(var930, var980, ((<undefinedtype>)var1024).convert(var1058.substring(var1090, var1095.indexOf("?"))));
                              SkyOBJ_Scale = var749;
                              ResourceLocation var863 = new ResourceLocation;
                              Object var613 = skyring_id.get(ring_case);
                              String var981;
                              if (var613 instanceof String) {
                                 String _stringValue = (String)var613;
                                 var981 = _stringValue;
                              } else {
                                 var981 = "";
                              }

                              var613 = skyring_id.get(ring_case);
                              String var1025;
                              if (var613 instanceof String) {
                                 String _stringValue = (String)var613;
                                 var1025 = _stringValue;
                              } else {
                                 var1025 = "";
                              }

                              int var1026 = var1025.indexOf("`") + "`".length();
                              var613 = skyring_id.get(ring_case);
                              if (var613 instanceof String) {
                                 String _stringValue = (String)var613;
                                 var1058 = _stringValue;
                              } else {
                                 var1058 = "";
                              }

                              var863.<init>("cosmos:textures/" + var981.substring(var1026, var1058.indexOf("*")) + ".png");
                              RenderSystem.setShaderTexture(0, var863);
                           }
                        } else {
                           effect_logic = true;
                        }

                        VertexBuffer var753;
                        if (blackhole_logic) {
                           var753 = blackhole;
                        } else {
                           Tag var145 = sky_object.m_128423_("type");
                           String var750;
                           if (var145 instanceof StringTag) {
                              StringTag _stringTag = (StringTag)var145;
                              var750 = _stringTag.m_7916_();
                           } else {
                              var750 = "";
                           }

                           if (var750.equals("ring")) {
                              var753 = rings;
                           } else if (skyring_id.isEmpty()) {
                              var753 = sky_object.m_128441_("core_color") && sky_object.m_128441_("bloom_color") ? shape() : texture_cube;
                           } else {
                              Object var694 = skyring_id.get(ring_case);
                              if (var694 instanceof String) {
                                 String _stringValue = (String)var694;
                                 var750 = _stringValue;
                              } else {
                                 var750 = "";
                              }

                              if (var750.equals("0")) {
                                 var753 = sky_object.m_128441_("core_color") && sky_object.m_128441_("bloom_color") ? shape() : texture_cube;
                              } else {
                                 var694 = skyring_id.get(ring_case);
                                 if (var694 instanceof String) {
                                    String _stringValue = (String)var694;
                                    var750 = _stringValue;
                                 } else {
                                    var750 = "";
                                 }

                                 var694 = skyring_id.get(ring_case);
                                 String var864;
                                 if (var694 instanceof String) {
                                    String _stringValue = (String)var694;
                                    var864 = _stringValue;
                                 } else {
                                    var864 = "";
                                 }

                                 int var865 = var864.indexOf("*") + "*".length();
                                 var694 = skyring_id.get(ring_case);
                                 String var931;
                                 if (var694 instanceof String) {
                                    String _stringValue = (String)var694;
                                    var931 = _stringValue;
                                 } else {
                                    var931 = "";
                                 }

                                 if (var750.substring(var865, var931.indexOf("|")).equals("1")) {
                                    var753 = ring1;
                                 } else {
                                    var694 = skyring_id.get(ring_case);
                                    if (var694 instanceof String) {
                                       String _stringValue = (String)var694;
                                       var750 = _stringValue;
                                    } else {
                                       var750 = "";
                                    }

                                    var694 = skyring_id.get(ring_case);
                                    String var866;
                                    if (var694 instanceof String) {
                                       String _stringValue = (String)var694;
                                       var866 = _stringValue;
                                    } else {
                                       var866 = "";
                                    }

                                    int var867 = var866.indexOf("*") + "*".length();
                                    var694 = skyring_id.get(ring_case);
                                    if (var694 instanceof String) {
                                       String _stringValue = (String)var694;
                                       var931 = _stringValue;
                                    } else {
                                       var931 = "";
                                    }

                                    if (var750.substring(var867, var931.indexOf("|")).equals("2")) {
                                       var753 = ring2;
                                    } else {
                                       var694 = skyring_id.get(ring_case);
                                       if (var694 instanceof String) {
                                          String _stringValue = (String)var694;
                                          var750 = _stringValue;
                                       } else {
                                          var750 = "";
                                       }

                                       var694 = skyring_id.get(ring_case);
                                       String var868;
                                       if (var694 instanceof String) {
                                          String _stringValue = (String)var694;
                                          var868 = _stringValue;
                                       } else {
                                          var868 = "";
                                       }

                                       int var869 = var868.indexOf("*") + "*".length();
                                       var694 = skyring_id.get(ring_case);
                                       if (var694 instanceof String) {
                                          String _stringValue = (String)var694;
                                          var931 = _stringValue;
                                       } else {
                                          var931 = "";
                                       }

                                       if (var750.substring(var869, var931.indexOf("|")).equals("3")) {
                                          var753 = ring3;
                                       } else {
                                          var694 = skyring_id.get(ring_case);
                                          if (var694 instanceof String) {
                                             String _stringValue = (String)var694;
                                             var750 = _stringValue;
                                          } else {
                                             var750 = "";
                                          }

                                          var694 = skyring_id.get(ring_case);
                                          String var870;
                                          if (var694 instanceof String) {
                                             String _stringValue = (String)var694;
                                             var870 = _stringValue;
                                          } else {
                                             var870 = "";
                                          }

                                          int var871 = var870.indexOf("*") + "*".length();
                                          var694 = skyring_id.get(ring_case);
                                          if (var694 instanceof String) {
                                             String _stringValue = (String)var694;
                                             var931 = _stringValue;
                                          } else {
                                             var931 = "";
                                          }

                                          var753 = var750.substring(var871, var931.indexOf("|")).equals("4") ? ring4 : ring4;
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        renderShape(var753, SkyOBJ_Pos.m_7096_(), SkyOBJ_Pos.m_7098_(), SkyOBJ_Pos.m_7094_(), (float)SkyOBJ_Rot.m_7098_(), (float)SkyOBJ_Rot.m_7096_(), (float)SkyOBJ_Rot.m_7094_(), (float)SkyOBJ_Scale.m_7096_(), (float)SkyOBJ_Scale.m_7098_(), (float)SkyOBJ_Scale.m_7094_(), (int)(skyobject_alpha * (double)255.0F) << 24 | 16711680 | '\uff00' | 255);
                        if (effect_logic) {
                           if (sky_object.m_128441_("cloud_data")) {
                              Tag var458 = sky_object.m_128423_("cloud_data");
                              CompoundTag var757;
                              if (var458 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var458;
                                 var757 = _compoundTag.m_6426_();
                              } else {
                                 var757 = new CompoundTag();
                              }

                              CompoundTag cloud_data = var757;
                              var458 = cloud_data.m_128423_("cloud_color");
                              if (var458 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var458;
                                 var757 = _compoundTag.m_6426_();
                              } else {
                                 var757 = new CompoundTag();
                              }

                              CompoundTag cloud_color_map = var757;
                              var458 = cloud_data.m_128423_("tick_delay");
                              double var872;
                              if (var458 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var458;
                                 var872 = _doubleTag.m_7061_();
                              } else {
                                 var872 = (double)0.0F;
                              }

                              iter = current_tick / var872;
                              var458 = cloud_data.m_128423_("frames");
                              double var759;
                              if (var458 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var458;
                                 var759 = _doubleTag.m_7061_();
                              } else {
                                 var759 = (double)0.0F;
                              }

                              frames = var759;
                              iter = Mth.m_14008_(iter % frames, (double)1.0F, frames);
                              counter = (double)0.0F;
                              ResourceLocation var873 = new ResourceLocation;
                              var458 = cloud_data.m_128423_("animation_folder");
                              String var982;
                              if (var458 instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)var458;
                                 var982 = _stringTag.m_7916_();
                              } else {
                                 var982 = "";
                              }

                              var873.<init>("cosmos:textures/" + var982 + Integer.toString((int)iter) + ".png");
                              RenderSystem.setShaderTexture(0, var873);
                              double var874 = SkyOBJ_Pos.m_7096_();
                              double var935 = SkyOBJ_Pos.m_7098_();
                              double var983 = SkyOBJ_Pos.m_7094_();
                              float var1027 = (float)SkyOBJ_Rot.m_7098_();
                              float var1060 = (float)SkyOBJ_Rot.m_7096_();
                              float var1091 = (float)SkyOBJ_Rot.m_7094_();
                              float var1096 = (float)(SkyOBJ_Scale.m_7096_() + 0.001);
                              float var1100 = (float)(SkyOBJ_Scale.m_7098_() + 0.001);
                              float var1104 = (float)(SkyOBJ_Scale.m_7094_() + 0.001);
                              Tag var617 = cloud_color_map.m_128423_("alpha");
                              double var10010;
                              if (var617 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var617;
                                 var10010 = _doubleTag.m_7061_();
                              } else {
                                 var10010 = (double)0.0F;
                              }

                              int var1108 = (int)var10010 << 24;
                              var617 = cloud_color_map.m_128423_("r");
                              double var1123;
                              if (var617 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var617;
                                 var1123 = _doubleTag.m_7061_();
                              } else {
                                 var1123 = (double)0.0F;
                              }

                              var1108 |= (int)var1123 << 16;
                              var617 = cloud_color_map.m_128423_("g");
                              if (var617 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var617;
                                 var1123 = _doubleTag.m_7061_();
                              } else {
                                 var1123 = (double)0.0F;
                              }

                              var1108 |= (int)var1123 << 8;
                              var617 = cloud_color_map.m_128423_("b");
                              if (var617 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var617;
                                 var1123 = _doubleTag.m_7061_();
                              } else {
                                 var1123 = (double)0.0F;
                              }

                              renderShape(curated_texture_cube, var874, var935, var983, var1027, var1060, var1091, var1096, var1100, var1104, var1108 | (int)var1123);
                           }

                           if (sky_object.m_128441_("atmosphere_color")) {
                              Tag var464 = sky_object.m_128423_("atmosphere_color");
                              CompoundTag var760;
                              if (var464 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var464;
                                 var760 = _compoundTag.m_6426_();
                              } else {
                                 var760 = new CompoundTag();
                              }

                              CompoundTag atmospheric_color = var760;
                              if (begin(Mode.QUADS, false, true)) {
                                 for(double m = (double)0.0F; m <= 0.06; m += 0.0065) {
                                    counter = (double)0.0F;
                                    p = (double)0.5F + m;
                                    q = (double)-0.5F - m;

                                    for(Direction directioniterator : Direction.values()) {
                                       D_iter = counter * (double)4.0F;
                                       vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(q, p, q));
                                       add(vertex1.m_7096_(), vertex1.m_7098_(), vertex1.m_7094_(), 0.0F, 0.0F, -1);
                                       vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(q, p, p));
                                       add(vertex2.m_7096_(), vertex2.m_7098_(), vertex2.m_7094_(), 0.0F, 0.0F, -1);
                                       vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(p, p, p));
                                       add(vertex3.m_7096_(), vertex3.m_7098_(), vertex3.m_7094_(), 0.0F, 0.0F, -1);
                                       vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(p, p, q));
                                       add(vertex4.m_7096_(), vertex4.m_7098_(), vertex4.m_7094_(), 0.0F, 0.0F, -1);
                                       ++counter;
                                    }
                                 }

                                 end();
                              }

                              counter = (double)0.0F;
                              VertexBuffer var761 = shape();
                              double var875 = SkyOBJ_Pos.m_7096_();
                              double var936 = SkyOBJ_Pos.m_7098_();
                              double var984 = SkyOBJ_Pos.m_7094_();
                              float var1028 = (float)SkyOBJ_Rot.m_7098_();
                              float var1061 = (float)SkyOBJ_Rot.m_7096_();
                              float var1092 = (float)SkyOBJ_Rot.m_7094_();
                              float var1097 = (float)SkyOBJ_Scale.m_7096_();
                              float var1101 = (float)SkyOBJ_Scale.m_7098_();
                              float var1105 = (float)SkyOBJ_Scale.m_7094_();
                              Tag var622 = atmospheric_color.m_128423_("alpha");
                              double var1111;
                              if (var622 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var622;
                                 var1111 = _doubleTag.m_7061_();
                              } else {
                                 var1111 = (double)0.0F;
                              }

                              int var1112 = (int)Mth.m_14008_(var1111 / (double)4.0F, (double)0.0F, (double)255.0F) << 24;
                              var622 = atmospheric_color.m_128423_("r");
                              double var1126;
                              if (var622 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var622;
                                 var1126 = _doubleTag.m_7061_();
                              } else {
                                 var1126 = (double)0.0F;
                              }

                              var1112 |= (int)var1126 << 16;
                              var622 = atmospheric_color.m_128423_("g");
                              if (var622 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var622;
                                 var1126 = _doubleTag.m_7061_();
                              } else {
                                 var1126 = (double)0.0F;
                              }

                              var1112 |= (int)var1126 << 8;
                              var622 = atmospheric_color.m_128423_("b");
                              if (var622 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var622;
                                 var1126 = _doubleTag.m_7061_();
                              } else {
                                 var1126 = (double)0.0F;
                              }

                              renderShape(var761, var875, var936, var984, var1028, var1061, var1092, var1097, var1101, var1105, var1112 | (int)var1126);
                           }
                        }
                     }
                  }
               }

               scale(1.0F);
               if (CosmosModVariables.WorldVariables.get(world).render_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
                  system(true);
                  cube = CubeProcedure.execute();
                  texture_cube = TexturedcubeProcedure.execute();
                  blackhole = BlackholeProcedure.execute();
                  ring1 = Ring1Procedure.execute();
                  ring2 = Ring2Procedure.execute();
                  ring3 = Ring3Procedure.execute();
                  ring4 = Ring4Procedure.execute();
                  Tag var304 = CosmosModVariables.WorldVariables.get(world).render_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  ListTag var762;
                  if (var304 instanceof ListTag) {
                     ListTag _listTag = (ListTag)var304;
                     var762 = _listTag.m_6426_();
                  } else {
                     var762 = new ListTag();
                  }

                  ListTag objectList = var762;

                  for(Object _listValueIterator : DistanceOrderProviderProcedure.execute(CosmosModVariables.WorldVariables.get(world).global_position_map, (double)-1.0F, entity.m_9236_().m_46472_().m_135782_().toString(), Minecraft.m_91087_().f_91063_.m_109153_().m_90583_())) {
                     double var876;
                     if (_listValueIterator instanceof Number) {
                        Number _doubleValue = (Number)_listValueIterator;
                        var876 = _doubleValue.doubleValue();
                     } else {
                        var876 = (double)0.0F;
                     }

                     Tag var466 = objectList.get((int)var876);
                     CompoundTag var763;
                     if (var466 instanceof CompoundTag) {
                        CompoundTag _compoundTag = (CompoundTag)var466;
                        var763 = _compoundTag.m_6426_();
                     } else {
                        var763 = new CompoundTag();
                     }

                     CompoundTag mint = var763;
                     Tag _compoundTag = mint.m_128423_("type");
                     String var764;
                     if (_compoundTag instanceof StringTag) {
                        StringTag _stringTag = (StringTag)_compoundTag;
                        var764 = _stringTag.m_7916_();
                     } else {
                        var764 = "";
                     }

                     type = var764;
                     Vec3 var765 = new Vec3;
                     _compoundTag = mint.m_128423_("x");
                     double var937;
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var937 = _doubleTag.m_7061_();
                     } else {
                        var937 = (double)0.0F;
                     }

                     _compoundTag = mint.m_128423_("y");
                     double var985;
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var985 = _doubleTag.m_7061_();
                     } else {
                        var985 = (double)0.0F;
                     }

                     _compoundTag = mint.m_128423_("z");
                     double var1029;
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var1029 = _doubleTag.m_7061_();
                     } else {
                        var1029 = (double)0.0F;
                     }

                     var765.<init>(var937, var985, var1029);
                     objPos = var765;
                     var765 = new Vec3;
                     _compoundTag = mint.m_128423_("pitch");
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var937 = _doubleTag.m_7061_();
                     } else {
                        var937 = (double)0.0F;
                     }

                     _compoundTag = mint.m_128423_("yaw");
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var985 = _doubleTag.m_7061_();
                     } else {
                        var985 = (double)0.0F;
                     }

                     _compoundTag = mint.m_128423_("roll");
                     if (_compoundTag instanceof DoubleTag) {
                        DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                        var1029 = _doubleTag.m_7061_();
                     } else {
                        var1029 = (double)0.0F;
                     }

                     var765.<init>(var937, var985, var1029);
                     objRot = var765;
                     Tag var660 = mint.m_128423_("function");
                     String var767;
                     if (var660 instanceof StringTag) {
                        StringTag _stringTag = (StringTag)var660;
                        var767 = _stringTag.m_7916_();
                     } else {
                        var767 = "";
                     }

                     Vec3 var768;
                     if (var767.equals("ring")) {
                        var768 = new Vec3;
                        var660 = mint.m_128423_("scale_radius");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var937 = _doubleTag.m_7061_();
                        } else {
                           var937 = (double)0.0F;
                        }

                        var660 = mint.m_128423_("scale_radius");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var985 = _doubleTag.m_7061_();
                        } else {
                           var985 = (double)0.0F;
                        }

                        var660 = mint.m_128423_("scale_radius");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var1029 = _doubleTag.m_7061_();
                        } else {
                           var1029 = (double)0.0F;
                        }

                        var768.<init>(var937, var985, var1029);
                     } else {
                        var768 = new Vec3;
                        var660 = mint.m_128423_("scale");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var937 = _doubleTag.m_7061_();
                        } else {
                           var937 = (double)0.0F;
                        }

                        var660 = mint.m_128423_("scale");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var985 = _doubleTag.m_7061_();
                        } else {
                           var985 = (double)0.0F;
                        }

                        var660 = mint.m_128423_("scale");
                        if (var660 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var660;
                           var1029 = _doubleTag.m_7061_();
                        } else {
                           var1029 = (double)0.0F;
                        }

                        var768.<init>(var937, var985, var1029);
                     }

                     label1751: {
                        objScale = var768;
                        if (!type.equals("planet")) {
                           _compoundTag = mint.m_128423_("function");
                           String var769;
                           if (_compoundTag instanceof StringTag) {
                              StringTag _stringTag = (StringTag)_compoundTag;
                              var769 = _stringTag.m_7916_();
                           } else {
                              var769 = "";
                           }

                           if (!var769.equals("ring")) {
                              if (type.equals("sun")) {
                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                                 i = (double)0.0F;
                                 var466 = mint.m_128423_("core_color");
                                 CompoundTag var794;
                                 if (var466 instanceof CompoundTag) {
                                    CompoundTag _compoundTag = (CompoundTag)var466;
                                    var794 = _compoundTag.m_6426_();
                                 } else {
                                    var794 = new CompoundTag();
                                 }

                                 CompoundTag color_core = var794;
                                 var466 = mint.m_128423_("bloom_color");
                                 if (var466 instanceof CompoundTag) {
                                    CompoundTag _compoundTag = (CompoundTag)var466;
                                    var794 = _compoundTag.m_6426_();
                                 } else {
                                    var794 = new CompoundTag();
                                 }

                                 CompoundTag color_bloom = var794;
                                 if (!begin(Mode.QUADS, false, true)) {
                                    break label1751;
                                 }

                                 var466 = mint.m_128423_("layer");
                                 double var796;
                                 if (var466 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var466;
                                    var796 = _doubleTag.m_7061_();
                                 } else {
                                    var796 = (double)0.0F;
                                 }

                                 layer = var796;

                                 for(int index1 = 0; index1 < (int)(layer + (double)1.0F); ++index1) {
                                    l = (double)0.5F / layer;
                                    j = i == layer ? (double)-0.5F + l * i : (double)0.5F - l * i;
                                    k = i == layer ? (double)0.5F - l * i : (double)-0.5F + l * i;
                                    int var799;
                                    if (i == layer) {
                                       Tag var677 = color_core.m_128423_("r");
                                       if (var677 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var677;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 = -16777216 | (int)var876 << 16;
                                       var677 = color_core.m_128423_("g");
                                       if (var677 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var677;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 |= (int)var876 << 8;
                                       var677 = color_core.m_128423_("b");
                                       if (var677 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var677;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 |= (int)var876;
                                    } else {
                                       Tag var680 = color_bloom.m_128423_("r");
                                       if (var680 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var680;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 = -16777216 | (int)var876 << 16;
                                       var680 = color_bloom.m_128423_("g");
                                       if (var680 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var680;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 |= (int)var876 << 8;
                                       var680 = color_bloom.m_128423_("b");
                                       if (var680 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var680;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var799 |= (int)var876;
                                    }

                                    color = (double)var799;
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
                                 break label1751;
                              }

                              if (!type.equals("blackhole")) {
                                 break label1751;
                              }

                              if (mint.m_128441_("model_type")) {
                                 var466 = mint.m_128423_("model_type");
                                 if (var466 instanceof StringTag) {
                                    StringTag _stringTag = (StringTag)var466;
                                    var769 = _stringTag.m_7916_();
                                 } else {
                                    var769 = "";
                                 }

                                 if (var769.equals("black_hole")) {
                                    Tag var542 = mint.m_128423_("model_data");
                                    CompoundTag var788;
                                    if (var542 instanceof CompoundTag) {
                                       CompoundTag _compoundTag = (CompoundTag)var542;
                                       var788 = _compoundTag.m_6426_();
                                    } else {
                                       var788 = new CompoundTag();
                                    }

                                    CompoundTag model_data = var788;
                                    var542 = model_data.m_128423_("color");
                                    if (var542 instanceof CompoundTag) {
                                       CompoundTag _compoundTag = (CompoundTag)var542;
                                       var788 = _compoundTag.m_6426_();
                                    } else {
                                       var788 = new CompoundTag();
                                    }

                                    CompoundTag model_color_data = var788;
                                    Vec3 var790 = new Vec3;
                                    var542 = model_color_data.m_128423_("r");
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var937 = _doubleTag.m_7061_();
                                    } else {
                                       var937 = (double)0.0F;
                                    }

                                    var542 = model_color_data.m_128423_("g");
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var985 = _doubleTag.m_7061_();
                                    } else {
                                       var985 = (double)0.0F;
                                    }

                                    var542 = model_color_data.m_128423_("b");
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var1029 = _doubleTag.m_7061_();
                                    } else {
                                       var1029 = (double)0.0F;
                                    }

                                    var790.<init>(var937, var985, var1029);
                                    model_color = var790;
                                    var542 = model_data.m_128423_("intensity");
                                    double var791;
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var791 = _doubleTag.m_7061_();
                                    } else {
                                       var791 = (double)0.0F;
                                    }

                                    model_intensity = var791;
                                    var542 = model_data.m_128423_("step");
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var791 = _doubleTag.m_7061_();
                                    } else {
                                       var791 = (double)0.0F;
                                    }

                                    model_step = var791;
                                    var542 = model_data.m_128423_("speed");
                                    if (var542 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var542;
                                       var791 = _doubleTag.m_7061_();
                                    } else {
                                       var791 = (double)0.0F;
                                    }

                                    model_speed = var791;
                                    BHProcedure.scheduleRenderBlackhole(objPos, objRot, model_color, objScale.m_7096_(), model_intensity, model_step, model_speed);
                                 }
                                 break label1751;
                              }

                              var466 = mint.m_128423_("bloom_color");
                              CompoundTag var783;
                              if (var466 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var466;
                                 var783 = _compoundTag.m_6426_();
                              } else {
                                 var783 = new CompoundTag();
                              }

                              CompoundTag color_bloom = var783;
                              i = (double)0.0F;
                              if (begin(Mode.QUADS, false, true)) {
                                 layer = (double)64.0F;

                                 for(int index2 = 0; index2 < (int)(layer / (double)4.0F + (double)1.0F); ++index2) {
                                    l = (double)0.5F / layer;
                                    j = i == layer ? (double)-0.5F + l * i : (double)0.5F - l * i;
                                    k = i == layer ? (double)0.5F - l * i : (double)-0.5F + l * i;
                                    int var784;
                                    if (i == layer) {
                                       var784 = -1;
                                    } else {
                                       Tag var628 = color_bloom.m_128423_("r");
                                       if (var628 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var628;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var784 = -16777216 | (int)var876 << 16;
                                       var628 = color_bloom.m_128423_("g");
                                       if (var628 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var628;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var784 |= (int)var876 << 8;
                                       var628 = color_bloom.m_128423_("b");
                                       if (var628 instanceof DoubleTag) {
                                          DoubleTag _doubleTag = (DoubleTag)var628;
                                          var876 = _doubleTag.m_7061_();
                                       } else {
                                          var876 = (double)0.0F;
                                       }

                                       var784 |= (int)var876;
                                    }

                                    color = (double)var784;
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

                              RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                              renderShape(shape(), objPos.m_7096_(), objPos.m_7098_(), objPos.m_7094_(), (float)objRot.m_7098_(), (float)objRot.m_7096_(), (float)objRot.m_7094_(), (float)(objScale.m_7096_() * (double)1.25F), (float)(objScale.m_7098_() * (double)1.25F), (float)(objScale.m_7094_() * (double)1.25F), -1);
                              RenderSystem.defaultBlendFunc();
                              break label1751;
                           }
                        }

                        RenderSystem.defaultBlendFunc();
                        ResourceLocation var877 = new ResourceLocation;
                        var466 = mint.m_128423_("texture_id");
                        String var989;
                        if (var466 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var466;
                           var989 = _stringTag.m_7916_();
                        } else {
                           var989 = "";
                        }

                        var877.<init>("cosmos:textures/" + var989 + ".png");
                        RenderSystem.setShaderTexture(0, var877);
                        atmosphere_capability = false;
                        if (mint.m_128441_("opaque")) {
                           var466 = mint.m_128423_("opaque");
                           boolean var770;
                           if (var466 instanceof ByteTag) {
                              ByteTag _byteTag = (ByteTag)var466;
                              var770 = _byteTag.m_7063_() == 1;
                           } else {
                              var770 = false;
                           }

                           if (var770) {
                              counter = (double)0.0F;
                              Tag var533 = mint.m_128423_("light_data");
                              CompoundTag var771;
                              if (var533 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var533;
                                 var771 = _compoundTag.m_6426_();
                              } else {
                                 var771 = new CompoundTag();
                              }

                              CompoundTag light_data = var771;
                              if (begin(Mode.QUADS, true, true)) {
                                 for(Direction directioniterator : Direction.values()) {
                                    ArrayList<Vec3> texture_pos = LightCubeMapProcedure.execute(directioniterator);
                                    D_iter = counter * (double)4.0F;
                                    vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
                                    double var772 = vertex1.m_7096_();
                                    double var878 = vertex1.m_7098_();
                                    var937 = vertex1.m_7094_();
                                    float var990 = (float)((Vec3)texture_pos.get(0)).m_7096_();
                                    float var1033 = (float)((Vec3)texture_pos.get(0)).m_7094_();
                                    var660 = light_data.m_128423_(Integer.toString((int)((double)0.0F + D_iter)));
                                    double var1062;
                                    if (var660 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var660;
                                       var1062 = _doubleTag.m_7061_();
                                    } else {
                                       var1062 = (double)0.0F;
                                    }

                                    add(var772, var878, var937, var990, var1033, (int)var1062);
                                    vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
                                    var772 = vertex2.m_7096_();
                                    var878 = vertex2.m_7098_();
                                    var937 = vertex2.m_7094_();
                                    var990 = (float)((Vec3)texture_pos.get(1)).m_7096_();
                                    var1033 = (float)((Vec3)texture_pos.get(1)).m_7094_();
                                    var660 = light_data.m_128423_(Integer.toString((int)((double)1.0F + D_iter)));
                                    if (var660 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var660;
                                       var1062 = _doubleTag.m_7061_();
                                    } else {
                                       var1062 = (double)0.0F;
                                    }

                                    add(var772, var878, var937, var990, var1033, (int)var1062);
                                    vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)0.5F));
                                    var772 = vertex3.m_7096_();
                                    var878 = vertex3.m_7098_();
                                    var937 = vertex3.m_7094_();
                                    var990 = (float)((Vec3)texture_pos.get(2)).m_7096_();
                                    var1033 = (float)((Vec3)texture_pos.get(2)).m_7094_();
                                    var660 = light_data.m_128423_(Integer.toString((int)((double)2.0F + D_iter)));
                                    if (var660 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var660;
                                       var1062 = _doubleTag.m_7061_();
                                    } else {
                                       var1062 = (double)0.0F;
                                    }

                                    add(var772, var878, var937, var990, var1033, (int)var1062);
                                    vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
                                    var772 = vertex4.m_7096_();
                                    var878 = vertex4.m_7098_();
                                    var937 = vertex4.m_7094_();
                                    var990 = (float)((Vec3)texture_pos.get(3)).m_7096_();
                                    var1033 = (float)((Vec3)texture_pos.get(3)).m_7094_();
                                    var660 = light_data.m_128423_(Integer.toString((int)((double)3.0F + D_iter)));
                                    if (var660 instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)var660;
                                       var1062 = _doubleTag.m_7061_();
                                    } else {
                                       var1062 = (double)0.0F;
                                    }

                                    add(var772, var878, var937, var990, var1033, (int)var1062);
                                    ++counter;
                                 }

                                 end();
                              }

                              atmosphere_capability = true;
                           }
                        }

                        var466 = mint.m_128423_("function");
                        String var776;
                        if (var466 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var466;
                           var776 = _stringTag.m_7916_();
                        } else {
                           var776 = "";
                        }

                        if (var776.equals("ring")) {
                           if (mint.m_128441_("additive")) {
                              Tag var535 = mint.m_128423_("additive");
                              boolean var777;
                              if (var535 instanceof ByteTag) {
                                 ByteTag _byteTag = (ByteTag)var535;
                                 var777 = _byteTag.m_7063_() == 1;
                              } else {
                                 var777 = false;
                              }

                              if (var777) {
                                 RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
                              }
                           }

                           if (mint.m_128441_("light_data")) {
                              Tag var536 = mint.m_128423_("light_data");
                              CompoundTag var778;
                              if (var536 instanceof CompoundTag) {
                                 CompoundTag _compoundTag = (CompoundTag)var536;
                                 var778 = _compoundTag.m_6426_();
                              } else {
                                 var778 = new CompoundTag();
                              }

                              CompoundTag light_data = var778;
                              if (begin(Mode.QUADS, true, true)) {
                                 ring_rot = type.equals("ring1") ? (double)0.0F : (type.equals("ring2") ? (double)180.0F : (type.equals("ring3") ? (double)270.0F : (double)90.0F));

                                 for(int seq = 0; seq < 4; ++seq) {
                                    vector1 = seq == 1 ? (new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 2 ? (new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 3 ? (new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot)));
                                    vector2 = seq == 1 ? (new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 2 ? (new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 3 ? (new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot)));
                                    vector3 = seq == 1 ? (new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 2 ? (new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 3 ? (new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot)));
                                    vector4 = seq == 1 ? (new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 2 ? (new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (seq == 3 ? (new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot) : (new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot)));
                                    uv = new Vec3(seq > 1 ? (double)0.5F : (double)0.5F, seq > 1 ? (double)1.0F : (double)0.0F, (double)0.0F);
                                    double var779 = vector1.m_7096_();
                                    double var882 = vector1.m_7098_();
                                    var937 = vector1.m_7094_();
                                    float var994 = (float)uv.m_7096_();
                                    float var1037 = (float)uv.m_7098_();
                                    Tag _doubleTag = light_data.m_128423_(Integer.toString(0 + seq * 4));
                                    double var1066;
                                    if (_doubleTag instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                       var1066 = _doubleTag.m_7061_();
                                    } else {
                                       var1066 = (double)0.0F;
                                    }

                                    add(var779, var882, var937, var994, var1037, (int)var1066);
                                    uv = new Vec3(seq > 1 ? (double)0.75F : (double)1.0F, seq > 1 ? (double)1.0F : (double)0.0F, (double)0.0F);
                                    var779 = vector2.m_7096_();
                                    var882 = vector2.m_7098_();
                                    var937 = vector2.m_7094_();
                                    var994 = (float)uv.m_7096_();
                                    var1037 = (float)uv.m_7098_();
                                    _doubleTag = light_data.m_128423_(Integer.toString(1 + seq * 4));
                                    if (_doubleTag instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                       var1066 = _doubleTag.m_7061_();
                                    } else {
                                       var1066 = (double)0.0F;
                                    }

                                    add(var779, var882, var937, var994, var1037, (int)var1066);
                                    uv = new Vec3(seq > 1 ? (double)1.0F : (double)0.75F, seq > 1 ? (double)0.0F : (double)1.0F, (double)0.0F);
                                    var779 = vector3.m_7096_();
                                    var882 = vector3.m_7098_();
                                    var937 = vector3.m_7094_();
                                    var994 = (float)uv.m_7096_();
                                    var1037 = (float)uv.m_7098_();
                                    _doubleTag = light_data.m_128423_(Integer.toString(2 + seq * 4));
                                    if (_doubleTag instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                       var1066 = _doubleTag.m_7061_();
                                    } else {
                                       var1066 = (double)0.0F;
                                    }

                                    add(var779, var882, var937, var994, var1037, (int)var1066);
                                    uv = new Vec3(seq > 1 ? (double)0.5F : (double)0.5F, seq > 1 ? (double)0.0F : (double)1.0F, (double)0.0F);
                                    var779 = vector4.m_7096_();
                                    var882 = vector4.m_7098_();
                                    var937 = vector4.m_7094_();
                                    var994 = (float)uv.m_7096_();
                                    var1037 = (float)uv.m_7098_();
                                    _doubleTag = light_data.m_128423_(Integer.toString(3 + seq * 4));
                                    if (_doubleTag instanceof DoubleTag) {
                                       DoubleTag _doubleTag = (DoubleTag)_doubleTag;
                                       var1066 = _doubleTag.m_7061_();
                                    } else {
                                       var1066 = (double)0.0F;
                                    }

                                    add(var779, var882, var937, var994, var1037, (int)var1066);
                                 }

                                 end();
                              }
                           }
                        }

                        RenderSystem.depthMask(true);
                        RenderSystem.enableDepthTest();
                     }

                     double var802 = (double)1.0F / Minecraft.m_91087_().f_91063_.m_109153_().m_90583_().m_82554_(objPos);
                     multiple = (double)1.0F;
                     if (mint.m_128441_("multiple")) {
                        _compoundTag = mint.m_128423_("multiple");
                        if (_compoundTag instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                           var802 = _doubleTag.m_7061_();
                        } else {
                           var802 = (double)0.0F;
                        }

                        multiple = var802;
                     }

                     if (type.equals("blackhole") && !mint.m_128441_("model_type")) {
                        main_color = (double)-1.6777216E7F;
                     } else if (mint.m_128441_("custom_color")) {
                        _compoundTag = mint.m_128423_("custom_color");
                        CompoundTag var804;
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var804 = _compoundTag.m_6426_();
                        } else {
                           var804 = new CompoundTag();
                        }

                        CompoundTag custom_color = var804;
                        Tag var582 = custom_color.m_128423_("alpha");
                        double var805;
                        if (var582 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var582;
                           var805 = _doubleTag.m_7061_();
                        } else {
                           var805 = (double)0.0F;
                        }

                        int var806 = (int)var805 << 24;
                        var582 = custom_color.m_128423_("r");
                        if (var582 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var582;
                           var876 = _doubleTag.m_7061_();
                        } else {
                           var876 = (double)0.0F;
                        }

                        var806 |= (int)var876 << 16;
                        var582 = custom_color.m_128423_("g");
                        if (var582 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var582;
                           var876 = _doubleTag.m_7061_();
                        } else {
                           var876 = (double)0.0F;
                        }

                        var806 |= (int)var876 << 8;
                        var582 = custom_color.m_128423_("b");
                        if (var582 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var582;
                           var876 = _doubleTag.m_7061_();
                        } else {
                           var876 = (double)0.0F;
                        }

                        main_color = (double)(var806 | (int)var876);
                     } else {
                        main_color = (double)-1.0F;
                     }

                     for(int index3 = 0; index3 < (int)multiple; ++index3) {
                        Tag var552 = mint.m_128423_("function");
                        String var809;
                        if (var552 instanceof StringTag) {
                           StringTag _stringTag = (StringTag)var552;
                           var809 = _stringTag.m_7916_();
                        } else {
                           var809 = "";
                        }

                        VertexBuffer var810;
                        if (var809.equals("ring") && mint.m_128441_("light_data")) {
                           var810 = shape();
                        } else if (type.equals("ring1")) {
                           var810 = ring1;
                        } else if (type.equals("ring2")) {
                           var810 = ring2;
                        } else if (type.equals("ring3")) {
                           var810 = ring3;
                        } else if (type.equals("ring4")) {
                           var810 = ring4;
                        } else if (type.equals("sun")) {
                           var810 = shape();
                        } else if (type.equals("blackhole")) {
                           var810 = !mint.m_128441_("model_type") ? cube : empty_shape;
                        } else if (type.equals("planet")) {
                           if (mint.m_128441_("opaque")) {
                              var552 = mint.m_128423_("opaque");
                              boolean var811;
                              if (var552 instanceof ByteTag) {
                                 ByteTag _byteTag = (ByteTag)var552;
                                 var811 = _byteTag.m_7063_() == 1;
                              } else {
                                 var811 = false;
                              }

                              var810 = var811 ? shape() : texture_cube;
                           } else {
                              var810 = texture_cube;
                           }
                        } else {
                           var810 = empty_shape;
                        }

                        renderShape(var810, objPos.m_7096_(), objPos.m_7098_(), objPos.m_7094_(), (float)objRot.m_7098_(), (float)objRot.m_7096_(), (float)objRot.m_7094_(), (float)objScale.m_7096_(), (float)objScale.m_7098_(), (float)objScale.m_7094_(), (int)main_color);
                     }

                     if (mint.m_128441_("inverse_texture_id")) {
                        _compoundTag = mint.m_128423_("i_alpha_data");
                        CompoundTag var812;
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var812 = _compoundTag.m_6426_();
                        } else {
                           var812 = new CompoundTag();
                        }

                        CompoundTag alpha_data = var812;
                        counter = (double)0.0F;
                        if (begin(Mode.QUADS, true, true)) {
                           for(Direction directioniterator : Direction.values()) {
                              ArrayList<Vec3> texture_pos = LightCubeMapProcedure.execute(directioniterator);
                              D_iter = counter * (double)4.0F;
                              vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
                              double var813 = vertex1.m_7096_();
                              var876 = vertex1.m_7098_();
                              var937 = vertex1.m_7094_();
                              float var999 = (float)((Vec3)texture_pos.get(0)).m_7096_();
                              float var1042 = (float)((Vec3)texture_pos.get(0)).m_7094_();
                              Tag var632 = alpha_data.m_128423_(Integer.toString((int)((double)0.0F + D_iter)));
                              double var1070;
                              if (var632 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var632;
                                 var1070 = _doubleTag.m_7061_();
                              } else {
                                 var1070 = (double)0.0F;
                              }

                              add(var813, var876, var937, var999, var1042, (int)var1070 << 24 | 16711680 | '\uff00' | 255);
                              vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
                              var813 = vertex2.m_7096_();
                              var876 = vertex2.m_7098_();
                              var937 = vertex2.m_7094_();
                              var999 = (float)((Vec3)texture_pos.get(1)).m_7096_();
                              var1042 = (float)((Vec3)texture_pos.get(1)).m_7094_();
                              var632 = alpha_data.m_128423_(Integer.toString((int)((double)1.0F + D_iter)));
                              if (var632 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var632;
                                 var1070 = _doubleTag.m_7061_();
                              } else {
                                 var1070 = (double)0.0F;
                              }

                              add(var813, var876, var937, var999, var1042, (int)var1070 << 24 | 16711680 | '\uff00' | 255);
                              vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)0.5F));
                              var813 = vertex3.m_7096_();
                              var876 = vertex3.m_7098_();
                              var937 = vertex3.m_7094_();
                              var999 = (float)((Vec3)texture_pos.get(2)).m_7096_();
                              var1042 = (float)((Vec3)texture_pos.get(2)).m_7094_();
                              var632 = alpha_data.m_128423_(Integer.toString((int)((double)2.0F + D_iter)));
                              if (var632 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var632;
                                 var1070 = _doubleTag.m_7061_();
                              } else {
                                 var1070 = (double)0.0F;
                              }

                              add(var813, var876, var937, var999, var1042, (int)var1070 << 24 | 16711680 | '\uff00' | 255);
                              vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
                              var813 = vertex4.m_7096_();
                              var876 = vertex4.m_7098_();
                              var937 = vertex4.m_7094_();
                              var999 = (float)((Vec3)texture_pos.get(3)).m_7096_();
                              var1042 = (float)((Vec3)texture_pos.get(3)).m_7094_();
                              var632 = alpha_data.m_128423_(Integer.toString((int)((double)3.0F + D_iter)));
                              if (var632 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var632;
                                 var1070 = _doubleTag.m_7061_();
                              } else {
                                 var1070 = (double)0.0F;
                              }

                              add(var813, var876, var937, var999, var1042, (int)var1070 << 24 | 16711680 | '\uff00' | 255);
                              ++counter;
                           }

                           end();
                        }

                        ResourceLocation var902 = new ResourceLocation;
                        _compoundTag = mint.m_128423_("inverse_texture_id");
                        String var1003;
                        if (_compoundTag instanceof StringTag) {
                           StringTag _stringTag = (StringTag)_compoundTag;
                           var1003 = _stringTag.m_7916_();
                        } else {
                           var1003 = "";
                        }

                        var902.<init>("cosmos:textures/" + var1003 + ".png");
                        RenderSystem.setShaderTexture(0, var902);
                        renderShape(shape(), objPos.m_7096_(), objPos.m_7098_(), objPos.m_7094_(), (float)objRot.m_7098_(), (float)objRot.m_7096_(), (float)objRot.m_7094_(), (float)objScale.m_7096_(), (float)objScale.m_7098_(), (float)objScale.m_7094_(), -1);
                        counter = (double)0.0F;
                     }

                     if (mint.m_128441_("cloud_data")) {
                        _compoundTag = mint.m_128423_("light_data");
                        CompoundTag var817;
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var817 = _compoundTag.m_6426_();
                        } else {
                           var817 = new CompoundTag();
                        }

                        CompoundTag light_data = var817;
                        _compoundTag = mint.m_128423_("cloud_data");
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var817 = _compoundTag.m_6426_();
                        } else {
                           var817 = new CompoundTag();
                        }

                        CompoundTag cloud_data = var817;
                        _compoundTag = cloud_data.m_128423_("cloud_color");
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var817 = _compoundTag.m_6426_();
                        } else {
                           var817 = new CompoundTag();
                        }

                        CompoundTag cloud_color_map = var817;
                        _compoundTag = cloud_data.m_128423_("tick_delay");
                        if (_compoundTag instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                           var876 = _doubleTag.m_7061_();
                        } else {
                           var876 = (double)0.0F;
                        }

                        iter = current_tick / var876;
                        _compoundTag = cloud_data.m_128423_("frames");
                        double var820;
                        if (_compoundTag instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)_compoundTag;
                           var820 = _doubleTag.m_7061_();
                        } else {
                           var820 = (double)0.0F;
                        }

                        frames = var820;
                        iter = Mth.m_14008_(iter % frames, (double)1.0F, frames);
                        counter = (double)0.0F;
                        if (begin(Mode.QUADS, true, true)) {
                           for(Direction directioniterator : Direction.values()) {
                              ArrayList<Vec3> texture_pos = AnimatedLightCubeMapProcedure.execute(directioniterator);
                              D_iter = counter * (double)4.0F;
                              vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
                              var820 = vertex1.m_7096_();
                              var876 = vertex1.m_7098_();
                              var937 = vertex1.m_7094_();
                              float var1004 = (float)((Vec3)texture_pos.get(0)).m_7096_();
                              float var1046 = (float)((Vec3)texture_pos.get(0)).m_7094_();
                              Tag var636 = light_data.m_128423_(Integer.toString((int)((double)0.0F + D_iter)));
                              double var1074;
                              if (var636 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var636;
                                 var1074 = _doubleTag.m_7061_();
                              } else {
                                 var1074 = (double)0.0F;
                              }

                              add(var820, var876, var937, var1004, var1046, (int)var1074);
                              vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
                              var820 = vertex2.m_7096_();
                              var876 = vertex2.m_7098_();
                              var937 = vertex2.m_7094_();
                              var1004 = (float)((Vec3)texture_pos.get(1)).m_7096_();
                              var1046 = (float)((Vec3)texture_pos.get(1)).m_7094_();
                              var636 = light_data.m_128423_(Integer.toString((int)((double)1.0F + D_iter)));
                              if (var636 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var636;
                                 var1074 = _doubleTag.m_7061_();
                              } else {
                                 var1074 = (double)0.0F;
                              }

                              add(var820, var876, var937, var1004, var1046, (int)var1074);
                              vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)0.5F));
                              var820 = vertex3.m_7096_();
                              var876 = vertex3.m_7098_();
                              var937 = vertex3.m_7094_();
                              var1004 = (float)((Vec3)texture_pos.get(2)).m_7096_();
                              var1046 = (float)((Vec3)texture_pos.get(2)).m_7094_();
                              var636 = light_data.m_128423_(Integer.toString((int)((double)2.0F + D_iter)));
                              if (var636 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var636;
                                 var1074 = _doubleTag.m_7061_();
                              } else {
                                 var1074 = (double)0.0F;
                              }

                              add(var820, var876, var937, var1004, var1046, (int)var1074);
                              vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
                              var820 = vertex4.m_7096_();
                              var876 = vertex4.m_7098_();
                              var937 = vertex4.m_7094_();
                              var1004 = (float)((Vec3)texture_pos.get(3)).m_7096_();
                              var1046 = (float)((Vec3)texture_pos.get(3)).m_7094_();
                              var636 = light_data.m_128423_(Integer.toString((int)((double)3.0F + D_iter)));
                              if (var636 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var636;
                                 var1074 = _doubleTag.m_7061_();
                              } else {
                                 var1074 = (double)0.0F;
                              }

                              add(var820, var876, var937, var1004, var1046, (int)var1074);
                              ++counter;
                           }

                           end();
                        }

                        ResourceLocation var908 = new ResourceLocation;
                        _compoundTag = cloud_data.m_128423_("animation_folder");
                        String var1008;
                        if (_compoundTag instanceof StringTag) {
                           StringTag _stringTag = (StringTag)_compoundTag;
                           var1008 = _stringTag.m_7916_();
                        } else {
                           var1008 = "";
                        }

                        var908.<init>("cosmos:textures/" + var1008 + Integer.toString((int)iter) + ".png");
                        RenderSystem.setShaderTexture(0, var908);
                        VertexBuffer var825 = shape();
                        double var909 = objPos.m_7096_();
                        var937 = objPos.m_7098_();
                        double var1009 = objPos.m_7094_();
                        float var1050 = (float)objRot.m_7098_();
                        float var1078 = (float)objRot.m_7096_();
                        float var1093 = (float)objRot.m_7094_();
                        float var1098 = (float)(objScale.m_7096_() + (double)10.0F);
                        float var1102 = (float)(objScale.m_7098_() + (double)10.0F);
                        float var1106 = (float)(objScale.m_7094_() + (double)10.0F);
                        Tag var594 = cloud_color_map.m_128423_("alpha");
                        double var1115;
                        if (var594 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var594;
                           var1115 = _doubleTag.m_7061_();
                        } else {
                           var1115 = (double)0.0F;
                        }

                        int var1116 = (int)var1115 << 24;
                        var594 = cloud_color_map.m_128423_("r");
                        double var1129;
                        if (var594 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var594;
                           var1129 = _doubleTag.m_7061_();
                        } else {
                           var1129 = (double)0.0F;
                        }

                        var1116 |= (int)var1129 << 16;
                        var594 = cloud_color_map.m_128423_("g");
                        if (var594 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var594;
                           var1129 = _doubleTag.m_7061_();
                        } else {
                           var1129 = (double)0.0F;
                        }

                        var1116 |= (int)var1129 << 8;
                        var594 = cloud_color_map.m_128423_("b");
                        if (var594 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var594;
                           var1129 = _doubleTag.m_7061_();
                        } else {
                           var1129 = (double)0.0F;
                        }

                        renderShape(var825, var909, var937, var1009, var1050, var1078, var1093, var1098, var1102, var1106, var1116 | (int)var1129);
                     }

                     RenderSystem.depthMask(false);
                     RenderSystem.disableDepthTest();
                     if (atmosphere_capability && mint.m_128441_("atmosphere_color")) {
                        _compoundTag = mint.m_128423_("alpha_data");
                        CompoundTag var826;
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var826 = _compoundTag.m_6426_();
                        } else {
                           var826 = new CompoundTag();
                        }

                        CompoundTag alpha_data = var826;
                        _compoundTag = mint.m_128423_("atmosphere_color");
                        if (_compoundTag instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)_compoundTag;
                           var826 = _compoundTag.m_6426_();
                        } else {
                           var826 = new CompoundTag();
                        }

                        CompoundTag atmospheric_color = var826;
                        if (begin(Mode.QUADS, false, true)) {
                           for(double m = (double)0.0F; m <= 0.06; m += 0.0065) {
                              counter = (double)0.0F;
                              p = (double)0.5F + m;
                              q = (double)-0.5F - m;

                              for(Direction directioniterator : Direction.values()) {
                                 D_iter = counter * (double)4.0F;
                                 vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(q, p, q));
                                 double var828 = vertex1.m_7096_();
                                 var876 = vertex1.m_7098_();
                                 var937 = vertex1.m_7094_();
                                 var660 = alpha_data.m_128423_(Integer.toString((int)((double)0.0F + D_iter)));
                                 double var1079;
                                 if (var660 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var660;
                                    var1079 = _doubleTag.m_7061_();
                                 } else {
                                    var1079 = (double)0.0F;
                                 }

                                 add(var828, var876, var937, 0.0F, 0.0F, (int)var1079 << 24 | 16711680 | '\uff00' | 255);
                                 vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(q, p, p));
                                 var828 = vertex2.m_7096_();
                                 var876 = vertex2.m_7098_();
                                 var937 = vertex2.m_7094_();
                                 var660 = alpha_data.m_128423_(Integer.toString((int)((double)1.0F + D_iter)));
                                 if (var660 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var660;
                                    var1079 = _doubleTag.m_7061_();
                                 } else {
                                    var1079 = (double)0.0F;
                                 }

                                 add(var828, var876, var937, 0.0F, 0.0F, (int)var1079 << 24 | 16711680 | '\uff00' | 255);
                                 vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(p, p, p));
                                 var828 = vertex3.m_7096_();
                                 var876 = vertex3.m_7098_();
                                 var937 = vertex3.m_7094_();
                                 var660 = alpha_data.m_128423_(Integer.toString((int)((double)2.0F + D_iter)));
                                 if (var660 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var660;
                                    var1079 = _doubleTag.m_7061_();
                                 } else {
                                    var1079 = (double)0.0F;
                                 }

                                 add(var828, var876, var937, 0.0F, 0.0F, (int)var1079 << 24 | 16711680 | '\uff00' | 255);
                                 vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3(p, p, q));
                                 var828 = vertex4.m_7096_();
                                 var876 = vertex4.m_7098_();
                                 var937 = vertex4.m_7094_();
                                 var660 = alpha_data.m_128423_(Integer.toString((int)((double)3.0F + D_iter)));
                                 if (var660 instanceof DoubleTag) {
                                    DoubleTag _doubleTag = (DoubleTag)var660;
                                    var1079 = _doubleTag.m_7061_();
                                 } else {
                                    var1079 = (double)0.0F;
                                 }

                                 add(var828, var876, var937, 0.0F, 0.0F, (int)var1079 << 24 | 16711680 | '\uff00' | 255);
                                 ++counter;
                              }
                           }

                           end();
                        }

                        counter = (double)0.0F;
                        VertexBuffer var832 = shape();
                        var876 = objPos.m_7096_();
                        var937 = objPos.m_7098_();
                        var985 = objPos.m_7094_();
                        float var1051 = (float)objRot.m_7098_();
                        float var1083 = (float)objRot.m_7096_();
                        float var1094 = (float)objRot.m_7094_();
                        float var1099 = (float)objScale.m_7096_();
                        float var1103 = (float)objScale.m_7098_();
                        float var1107 = (float)objScale.m_7094_();
                        Tag var599 = atmospheric_color.m_128423_("alpha");
                        double var1119;
                        if (var599 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var599;
                           var1119 = _doubleTag.m_7061_();
                        } else {
                           var1119 = (double)0.0F;
                        }

                        int var1120 = (int)var1119 << 24;
                        var599 = atmospheric_color.m_128423_("r");
                        double var1132;
                        if (var599 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var599;
                           var1132 = _doubleTag.m_7061_();
                        } else {
                           var1132 = (double)0.0F;
                        }

                        var1120 |= (int)var1132 << 16;
                        var599 = atmospheric_color.m_128423_("g");
                        if (var599 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var599;
                           var1132 = _doubleTag.m_7061_();
                        } else {
                           var1132 = (double)0.0F;
                        }

                        var1120 |= (int)var1132 << 8;
                        var599 = atmospheric_color.m_128423_("b");
                        if (var599 instanceof DoubleTag) {
                           DoubleTag _doubleTag = (DoubleTag)var599;
                           var1132 = _doubleTag.m_7061_();
                        } else {
                           var1132 = (double)0.0F;
                        }

                        renderShape(var832, var876, var937, var985, var1051, var1083, var1094, var1099, var1103, var1107, var1120 | (int)var1132);
                     }
                  }
               }

               release();
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
