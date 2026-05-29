package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.math.Axis;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

@EventBusSubscriber({Dist.CLIENT})
public class PlanetBarRendererProcedure {
   private static Map<EntityType, Entity> data = new HashMap();
   private static GuiGraphics guiGraphics = null;
   private static float partialTick = 0.0F;
   private static int currentStage = 0;
   private static int targetStage = 0;

   private static boolean target(int targetStage) {
      if (targetStage == currentStage) {
         PlanetBarRendererProcedure.targetStage = targetStage;
         return true;
      } else if (targetStage == 1 && currentStage != 0) {
         PlanetBarRendererProcedure.targetStage = currentStage;
         return true;
      } else {
         return false;
      }
   }

   private static void release() {
      targetStage = 0;
   }

   public static void renderEntity(LevelAccessor levelAccessor, EntityType type, double x, double y, double depth, float yaw, float pitch, float roll, float scale, boolean modelOnly) {
      if (currentStage != 0 && currentStage == targetStage) {
         if (type != null) {
            if (levelAccessor instanceof ClientLevel) {
               ClientLevel level = (ClientLevel)levelAccessor;
               if (Minecraft.m_91087_().f_91063_.m_109153_() != null) {
                  Entity entity = null;
                  if (data.containsKey(type)) {
                     entity = (Entity)data.get(type);
                  } else {
                     entity = type.m_20615_(level);
                     data.put(type, entity);
                  }

                  renderEntity(entity, 0.0F, x, y, depth, yaw, pitch, roll, scale, modelOnly);
               }
            }

         }
      }
   }

   public static void renderEntity(LevelAccessor levelAccessor, Entity entity, double x, double y, double depth, float yaw, float pitch, float roll, float scale, boolean modelOnly) {
      if (currentStage != 0 && currentStage == targetStage) {
         Minecraft minecraft = Minecraft.m_91087_();
         if (levelAccessor instanceof ClientLevel && minecraft.f_91063_.m_109153_() != null) {
            renderEntity(entity, partialTick, x, y, depth, yaw, pitch, roll, scale, modelOnly);
         }

      }
   }

   private static void renderEntity(Entity entity, float partialTick, double x, double y, double depth, float yaw, float pitch, float roll, float scale, boolean modelOnly) {
      if (entity != null) {
         float offset = entity.m_20206_() / 2.0F;
         float yRotO = entity.f_19859_;
         float yRot = entity.m_146908_();
         float xRotO = entity.f_19860_;
         float xRot = entity.m_146909_();
         entity.f_19859_ = 180.0F;
         entity.m_146922_(180.0F);
         entity.f_19860_ = 0.0F;
         entity.m_146926_(0.0F);
         PoseStack poseStack = guiGraphics.m_280168_();
         poseStack.m_85836_();
         poseStack.m_85837_(x, y, -depth);
         poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
         poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
         poseStack.m_252781_(Axis.f_252393_.m_252977_(roll + 180.0F));
         poseStack.m_252931_((new Matrix4f()).scaling(scale, scale, -scale));
         poseStack.m_252880_(0.0F, -offset, 0.0F);
         Lighting.m_166384_();
         if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            float yBodyRotO = livingEntity.f_20884_;
            float yBodyRot = livingEntity.f_20883_;
            float yHeadRotO = livingEntity.f_20886_;
            float yHeadRot = livingEntity.f_20885_;
            livingEntity.f_20884_ = 180.0F;
            livingEntity.f_20883_ = 180.0F;
            livingEntity.f_20886_ = 180.0F;
            livingEntity.f_20885_ = 180.0F;
            renderEntity(livingEntity, partialTick, poseStack, modelOnly);
            livingEntity.f_20884_ = yBodyRotO;
            livingEntity.f_20883_ = yBodyRot;
            livingEntity.f_20886_ = yHeadRotO;
            livingEntity.f_20885_ = yHeadRot;
         } else {
            renderEntity(entity, partialTick, poseStack, modelOnly);
         }

         Lighting.m_84931_();
         poseStack.m_85849_();
         entity.f_19859_ = yRotO;
         entity.m_146922_(yRot);
         entity.f_19860_ = xRotO;
         entity.m_146926_(xRot);
      }
   }

   private static void renderEntity(Entity entity, float partialTick, PoseStack poseStack, boolean modelOnly) {
      Minecraft minecraft = Minecraft.m_91087_();
      if (modelOnly) {
         boolean customNameVisible = entity.m_20151_();
         entity.m_20340_(false);
         minecraft.m_91290_().m_114382_(entity).m_7392_(entity, 0.0F, partialTick, poseStack, guiGraphics.m_280091_(), 15728880);
         guiGraphics.m_280262_();
         entity.m_20340_(customNameVisible);
      } else {
         EntityRenderDispatcher renderer = minecraft.m_91290_();
         renderer.m_114468_(false);
         renderer.m_114384_(entity, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, partialTick, poseStack, guiGraphics.m_280091_(), 15728880);
         guiGraphics.m_280262_();
         renderer.m_114468_(true);
      }

   }

   public static void renderItem(LevelAccessor levelAccessor, ItemStack itemStack, double x, double y, double depth, float yaw, float pitch, float roll, float scale) {
      if (currentStage != 0 && currentStage == targetStage) {
         if (!itemStack.m_41619_()) {
            Minecraft minecraft = Minecraft.m_91087_();
            if (levelAccessor instanceof ClientLevel) {
               ClientLevel level = (ClientLevel)levelAccessor;
               if (minecraft.f_91063_.m_109153_() != null) {
                  ItemRenderer renderer = minecraft.m_91291_();
                  BakedModel bakedModel = renderer.m_174264_(itemStack, level, (LivingEntity)null, 0);
                  PoseStack poseStack = guiGraphics.m_280168_();
                  poseStack.m_85836_();
                  poseStack.m_85837_(x, y, -depth);
                  poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
                  poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
                  poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
                  poseStack.m_252931_((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
                  poseStack.m_85841_(scale, scale, scale);
                  if (!bakedModel.m_7547_()) {
                     Lighting.m_84930_();
                  }

                  renderer.m_115143_(itemStack, ItemDisplayContext.GUI, false, poseStack, guiGraphics.m_280091_(), 15728880, OverlayTexture.f_118083_, bakedModel);
                  guiGraphics.m_280262_();
                  Lighting.m_84931_();
                  poseStack.m_85849_();
               }
            }

         }
      }
   }

   public static void renderRectangle(float x1, float y1, float x2, float y2, float depth, int color) {
      if (currentStage != 0 && currentStage == targetStage) {
         float x3;
         float x4;
         if (x1 <= x2) {
            x3 = x1;
            x4 = x2;
         } else {
            x3 = x2;
            x4 = x1;
         }

         float y3;
         float y4;
         if (y1 <= y2) {
            y3 = y1;
            y4 = y2;
         } else {
            y3 = y2;
            y4 = y1;
         }

         int red = color >> 16 & 255;
         int green = color >> 8 & 255;
         int blue = color & 255;
         int alpha = color >>> 24;
         Matrix4f matrix4f = guiGraphics.m_280168_().m_85850_().m_252922_();
         VertexConsumer vertexConsumer = guiGraphics.m_280091_().m_6299_(RenderType.m_285907_());
         vertexConsumer.m_252986_(matrix4f, x3, y3, -depth).m_6122_(red, green, blue, alpha).m_5752_();
         vertexConsumer.m_252986_(matrix4f, x3, y4, -depth).m_6122_(red, green, blue, alpha).m_5752_();
         vertexConsumer.m_252986_(matrix4f, x4, y4, -depth).m_6122_(red, green, blue, alpha).m_5752_();
         vertexConsumer.m_252986_(matrix4f, x4, y3, -depth).m_6122_(red, green, blue, alpha).m_5752_();
      }
   }

   public static void renderShape(VertexBuffer vertexBuffer, double x, double y, double depth, float yaw, float pitch, float roll, float xScale, float yScale, float zScale, int color) {
      if (currentStage != 0 && currentStage == targetStage) {
         if (vertexBuffer != null) {
            PoseStack poseStack = guiGraphics.m_280168_();
            poseStack.m_85836_();
            poseStack.m_85837_(x, y, -depth);
            poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
            poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
            poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
            poseStack.m_85841_(xScale, yScale, zScale);
            PoseStack modelViewStack = RenderSystem.getModelViewStack();
            modelViewStack.m_85836_();
            modelViewStack.m_252931_(poseStack.m_85850_().m_252922_());
            RenderSystem.setShaderColor((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >>> 24) / 255.0F);
            vertexBuffer.m_85921_();
            vertexBuffer.m_253207_(modelViewStack.m_85850_().m_252922_(), RenderSystem.getProjectionMatrix(), vertexBuffer.m_166892_().hasUV(0) ? GameRenderer.m_172820_() : GameRenderer.m_172811_());
            VertexBuffer.m_85931_();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            modelViewStack.m_85849_();
            poseStack.m_85849_();
         }
      }
   }

   public static void renderTexts(String texts, float x, float y, float depth, float angle, float scale, int color, int alignment) {
      if (currentStage != 0 && currentStage == targetStage) {
         Font font = Minecraft.m_91087_().f_91062_;
         float offsetX = 0.0F;
         float offsetY = 0.0F;
         switch (alignment) {
            case 0:
               offsetX = (float)(font.m_92895_(texts) - 1) * 0.5F;
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * 0.5F;
               break;
            case 1:
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * 0.5F;
               break;
            case 2:
               offsetX = (float)(font.m_92895_(texts) - 1) * -0.5F;
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * 0.5F;
               break;
            case 3:
               offsetX = (float)(font.m_92895_(texts) - 1) * 0.5F;
            case 4:
            default:
               break;
            case 5:
               offsetX = (float)(font.m_92895_(texts) - 1) * -0.5F;
               break;
            case 6:
               offsetX = (float)(font.m_92895_(texts) - 1) * 0.5F;
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * -0.5F;
               break;
            case 7:
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * -0.5F;
               break;
            case 8:
               offsetX = (float)(font.m_92895_(texts) - 1) * -0.5F;
               Objects.requireNonNull(font);
               offsetY = (float)(9 - 1) * -0.5F;
         }

         PoseStack poseStack = guiGraphics.m_280168_();
         poseStack.m_85836_();
         poseStack.m_252880_(x + offsetX * scale, y + offsetY * scale, -depth);
         poseStack.m_252781_(Axis.f_252393_.m_252977_(angle));
         poseStack.m_85841_(scale, scale, 1.0F);
         float var10001 = (float)(font.m_92895_(texts) - 1) * -0.5F;
         Objects.requireNonNull(font);
         poseStack.m_252880_(var10001, (float)(9 - 1) * -0.5F, 0.0F);
         Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
         font.m_271703_(texts, 0.0F, 0.0F, color, false, matrix4f, guiGraphics.m_280091_(), DisplayMode.NORMAL, 0, 15728880);
         poseStack.m_85849_();
      }
   }

   public static void renderTexture(float x, float y, float depth, float angle, float scale, int color, int alignment) {
      if (currentStage != 0 && currentStage == targetStage) {
         float offsetX = 0.0F;
         float offsetY = 0.0F;
         switch (alignment) {
            case 0:
               offsetX = 0.5F;
               offsetY = 0.5F;
               break;
            case 1:
               offsetY = 0.5F;
               break;
            case 2:
               offsetX = -0.5F;
               offsetY = 0.5F;
               break;
            case 3:
               offsetX = 0.5F;
            case 4:
            default:
               break;
            case 5:
               offsetX = -0.5F;
               break;
            case 6:
               offsetX = 0.5F;
               offsetY = -0.5F;
               break;
            case 7:
               offsetY = -0.5F;
               break;
            case 8:
               offsetX = -0.5F;
               offsetY = -0.5F;
         }

         RenderSystem.bindTexture(RenderSystem.getShaderTexture(0));
         int width = GL11.glGetTexLevelParameteri(3553, 0, 4096);
         int height = GL11.glGetTexLevelParameteri(3553, 0, 4097);
         int red = color >> 16 & 255;
         int green = color >> 8 & 255;
         int blue = color & 255;
         int alpha = color >>> 24;
         PoseStack poseStack = guiGraphics.m_280168_();
         poseStack.m_85836_();
         poseStack.m_252880_(x, y, -depth);
         poseStack.m_252781_(Axis.f_252393_.m_252977_(angle));
         poseStack.m_85841_((float)width * scale, (float)height * scale, 1.0F);
         poseStack.m_252880_(offsetX, offsetY, 0.0F);
         Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
         RenderSystem.setShader(GameRenderer::m_172820_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85819_);
         bufferBuilder.m_252986_(matrix4f, -0.5F, -0.5F, 0.0F).m_7421_(0.0F, 0.0F).m_6122_(red, green, blue, alpha).m_5752_();
         bufferBuilder.m_252986_(matrix4f, -0.5F, 0.5F, 0.0F).m_7421_(0.0F, 1.0F).m_6122_(red, green, blue, alpha).m_5752_();
         bufferBuilder.m_252986_(matrix4f, 0.5F, 0.5F, 0.0F).m_7421_(1.0F, 1.0F).m_6122_(red, green, blue, alpha).m_5752_();
         bufferBuilder.m_252986_(matrix4f, 0.5F, -0.5F, 0.0F).m_7421_(1.0F, 0.0F).m_6122_(red, green, blue, alpha).m_5752_();
         BufferUploader.m_231202_(bufferBuilder.m_231175_());
         poseStack.m_85849_();
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void renderGUI(RenderGuiEvent.Pre event) {
      currentStage = 2;
      guiGraphics = event.getGuiGraphics();
      partialTick = event.getPartialTick();
      renderOverlays(event);
      currentStage = 0;
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void renderScreen(ScreenEvent.Render.Post event) {
      currentStage = 3;
      guiGraphics = event.getGuiGraphics();
      partialTick = event.getPartialTick();
      renderOverlays(event);
      currentStage = 0;
   }

   private static void renderOverlays(Event event) {
      Minecraft minecraft = Minecraft.m_91087_();
      double scale = minecraft.m_91268_().m_85449_();
      if (scale > (double)0.0F) {
         RenderSystem.depthMask(true);
         RenderSystem.enableDepthTest();
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         execute(event, (double)partialTick);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
      }

   }

   public static void execute(double partialTick) {
      execute((Event)null, partialTick);
   }

   private static void execute(@Nullable Event event, double partialTick) {
      boolean dimention_check = false;
      double centreY = (double)0.0F;
      centreY = (double)(Minecraft.m_91087_().m_91268_().m_85446_() / 2);
      if (Minecraft.m_91087_().f_91074_ != null) {
         Entity entity = Minecraft.m_91087_().f_91074_;
         double x = entity.m_20185_();
         double y = entity.m_20186_();
         double z = entity.m_20189_();
         LevelAccessor world = entity.m_9236_();
         ResourceKey<Level> dimension = entity.m_9236_().m_46472_();
         if (target(1)) {
            if (entity.m_20202_() instanceof RocketSeatEntity) {
               dimention_check = false;
               if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
                  Tag var17 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  String var10000;
                  if (var17 instanceof StringTag) {
                     StringTag _stringTag = (StringTag)var17;
                     var10000 = _stringTag.m_7916_();
                  } else {
                     var10000 = "";
                  }

                  if (var10000.equals("planet")) {
                     dimention_check = true;
                  } else {
                     dimention_check = false;
                  }
               } else {
                  dimention_check = false;
               }

               if (dimention_check) {
                  Tag var29 = CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
                  CompoundTag var37;
                  if (var29 instanceof CompoundTag) {
                     CompoundTag _compoundTag = (CompoundTag)var29;
                     var37 = _compoundTag.m_6426_();
                  } else {
                     var37 = new CompoundTag();
                  }

                  CompoundTag atmospheric_data = var37;
                  ResourceLocation var10001 = new ResourceLocation;
                  var29 = atmospheric_data.m_128423_("overlay_texture_id");
                  String var10003;
                  if (var29 instanceof StringTag) {
                     StringTag _stringTag = (StringTag)var29;
                     var10003 = _stringTag.m_7916_();
                  } else {
                     var10003 = "";
                  }

                  var10001.<init>("cosmos:textures/" + var10003 + ".png");
                  RenderSystem.setShaderTexture(0, var10001);
                  renderTexture(12.0F, (float)(centreY + (double)23.0F), 100.0F, 0.0F, 1.0F, -1, 3);
                  RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/planet_bar.png"));
                  renderTexture(8.0F, (float)(centreY + (double)23.0F), 100.0F, 0.0F, 1.0F, -1, 3);
                  if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).thrust) {
                     RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/steel_ship_on_bit.png"));
                  } else {
                     RenderSystem.setShaderTexture(0, new ResourceLocation("cosmos:textures/steel_ship_off_bit.png"));
                  }

                  double var38 = entity.m_20202_().m_20318_((float)partialTick).m_7098_();
                  Tag var21 = atmospheric_data.m_128423_("ship_min_y");
                  double var10002;
                  if (var21 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var21;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  var38 -= var10002;
                  var10002 = centreY + (double)-37.0F;
                  double var47 = centreY + (double)23.0F + (double)63.0F;
                  var21 = atmospheric_data.m_128423_("shipbit_y");
                  double var10004;
                  if (var21 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var21;
                     var10004 = _doubleTag.m_7061_();
                  } else {
                     var10004 = (double)0.0F;
                  }

                  var38 *= var10002 - (var47 - var10004);
                  var21 = atmospheric_data.m_128423_("atmosphere_y");
                  if (var21 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var21;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  var21 = atmospheric_data.m_128423_("ship_min_y");
                  if (var21 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var21;
                     var47 = _doubleTag.m_7061_();
                  } else {
                     var47 = (double)0.0F;
                  }

                  var38 = var38 / (var10002 - var47) + centreY + (double)23.0F + (double)63.0F;
                  var21 = atmospheric_data.m_128423_("shipbit_y");
                  if (var21 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var21;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  var38 -= var10002;
                  var10002 = centreY + (double)-37.0F;
                  var47 = centreY + (double)23.0F + (double)63.0F;
                  var29 = atmospheric_data.m_128423_("shipbit_y");
                  if (var29 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var29;
                     var10004 = _doubleTag.m_7061_();
                  } else {
                     var10004 = (double)0.0F;
                  }

                  renderTexture(18.0F, (float)Mth.m_14008_(var38, var10002, var47 - var10004), 100.0F, 0.0F, 1.0F, -1, 3);
               }
            }

            release();
         }
      }

   }
}
