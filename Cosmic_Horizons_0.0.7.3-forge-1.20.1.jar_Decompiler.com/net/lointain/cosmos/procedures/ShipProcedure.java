package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent.Stage;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@EventBusSubscriber({Dist.CLIENT})
public class ShipProcedure {
   private static RenderLevelStageEvent provider = null;
   private static Map<EntityType, Entity> data = new HashMap();
   private static float textWidth = 1.0F;
   private static float textHeight = 1.0F;
   private static int textColor = -1;
   private static int backColor = 0;

   public static void setBackColor(int color) {
      backColor = color;
   }

   public static void setTextColor(int color) {
      textColor = color;
   }

   public static void setScale(float width, float height) {
      textWidth = width;
      textHeight = height;
   }

   public static void renderBlock(BlockState blockState, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
      BlockPos blockPos = BlockPos.m_274561_(x, y, z);
      Vec3 pos = provider.getCamera().m_90583_();
      int packedLight = glowing ? 15728880 : LevelRenderer.m_109541_(Minecraft.m_91087_().f_91073_, blockPos);
      PoseStack poseStack = provider.getPoseStack();
      poseStack.m_85836_();
      poseStack.m_85837_(x - pos.m_7096_(), y - pos.m_7098_(), z - pos.m_7094_());
      poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
      poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
      poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
      poseStack.m_85841_(scale, scale, scale);
      poseStack.m_252880_(-0.5F, -0.5F, -0.5F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      renderBlockModel(blockState, blockPos, poseStack, packedLight);
      renderBlockEntity(blockState, blockPos, poseStack, packedLight);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   private static void renderBlockEntity(BlockState blockState, BlockPos blockPos, PoseStack poseStack, int packedLight) {
      Block var5 = blockState.m_60734_();
      if (var5 instanceof EntityBlock entityBlock) {
         Minecraft minecraft = Minecraft.m_91087_();
         ClientLevel level = minecraft.f_91073_;
         BlockEntity blockEntity = entityBlock.m_142194_(blockPos, blockState);
         if (blockEntity != null) {
            BlockEntityRenderer blockEntityRenderer = minecraft.m_167982_().m_112265_(blockEntity);
            if (blockEntityRenderer != null) {
               MultiBufferSource.BufferSource bufferSource = minecraft.m_91269_().m_110104_();
               blockEntity.m_142339_(level);
               blockEntityRenderer.m_6922_(blockEntity, 0.0F, poseStack, bufferSource, packedLight, OverlayTexture.f_118083_);
            }
         }
      }

   }

   private static void renderBlockModel(BlockState blockState, BlockPos blockPos, PoseStack poseStack, int packedLight) {
      if (blockState.m_60799_() == RenderShape.MODEL) {
         Minecraft minecraft = Minecraft.m_91087_();
         ClientLevel level = minecraft.f_91073_;
         MultiBufferSource.BufferSource bufferSource = minecraft.m_91269_().m_110104_();
         BlockRenderDispatcher dispatcher = minecraft.m_91289_();
         ModelBlockRenderer renderer = dispatcher.m_110937_();
         BakedModel bakedModel = dispatcher.m_110910_(blockState);
         ModelData modelData = bakedModel.getModelData(level, blockPos, blockState, ModelData.builder().build());
         PoseStack.Pose pose = poseStack.m_85850_();
         int color = minecraft.m_91298_().m_92582_(blockState, level, blockPos);
         float red = (float)(color >> 16 & 255) / 255.0F;
         float green = (float)(color >> 8 & 255) / 255.0F;
         float blue = (float)(color & 255) / 255.0F;

         for(RenderType renderType : bakedModel.getRenderTypes(blockState, RandomSource.m_216335_(42L), modelData)) {
            renderer.renderModel(pose, bufferSource.m_6299_(Sheets.m_110792_()), blockState, bakedModel, red, green, blue, packedLight, OverlayTexture.f_118083_, modelData, renderType);
         }
      }

   }

   public static void renderEntity(EntityType type, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
      if (type != null) {
         ClientLevel level = Minecraft.m_91087_().f_91073_;
         Entity entity;
         if (data.containsKey(type)) {
            entity = (Entity)data.get(type);
            if (entity.m_9236_() != level) {
               entity = type.m_20615_(level);
               data.put(type, entity);
            }
         } else {
            entity = type.m_20615_(level);
            data.put(type, entity);
         }

         renderEntity(entity, 0.0F, x, y, z, yaw, pitch, roll, scale, glowing ? 15728880 : LevelRenderer.m_109541_(level, BlockPos.m_274561_(x, y, z)));
      }
   }

   public static void renderEntity(Entity entity, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
      float partialTick = provider.getPartialTick();
      int packedLight = glowing ? 15728880 : Minecraft.m_91087_().m_91290_().m_114394_(entity, partialTick);
      renderEntity(entity, partialTick, x, y, z, yaw, pitch, roll, scale, packedLight);
   }

   private static void renderEntity(Entity entity, float partialTick, double x, double y, double z, float yaw, float pitch, float roll, float scale, int packedLight) {
      if (entity != null) {
         Minecraft minecraft = Minecraft.m_91087_();
         MultiBufferSource.BufferSource bufferSource = minecraft.m_91269_().m_110104_();
         EntityRenderer renderer = minecraft.m_91290_().m_114382_(entity);
         Vec3 pos = provider.getCamera().m_90583_();
         float offset = entity.m_20206_() / 2.0F * scale;
         PoseStack poseStack = provider.getPoseStack();
         poseStack.m_85836_();
         poseStack.m_85837_(x - pos.m_7096_(), y + (double)offset - pos.m_7098_(), z - pos.m_7094_());
         poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
         poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
         poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
         poseStack.m_252880_(0.0F, -offset, 0.0F);
         poseStack.m_85841_(scale, scale, scale);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         renderer.m_7392_(entity, entity.m_5675_(partialTick), partialTick, poseStack, bufferSource, packedLight);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         poseStack.m_85849_();
      }
   }

   public static void renderItem(ItemStack itemStack, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean flipping, boolean glowing) {
      Minecraft minecraft = Minecraft.m_91087_();
      MultiBufferSource.BufferSource bufferSource = minecraft.m_91269_().m_110104_();
      ItemRenderer renderer = minecraft.m_91291_();
      Vec3 pos = provider.getCamera().m_90583_();
      int packedLight = glowing ? 15728880 : LevelRenderer.m_109541_(minecraft.f_91073_, BlockPos.m_274561_(x, y, z));
      PoseStack poseStack = provider.getPoseStack();
      poseStack.m_85836_();
      poseStack.m_85837_(x - pos.m_7096_(), y - pos.m_7098_(), z - pos.m_7094_());
      poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
      poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
      poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
      poseStack.m_85841_(scale, scale, scale);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      renderer.m_269491_((LivingEntity)null, itemStack, ItemDisplayContext.FIXED, flipping, poseStack, bufferSource, minecraft.f_91073_, packedLight, OverlayTexture.f_118083_, 0);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   public static void renderLine(double x1, double y1, double z1, double x2, double y2, double z2, int color) {
      MultiBufferSource.BufferSource bufferSource = Minecraft.m_91087_().m_91269_().m_110104_();
      Vec3 pos = provider.getCamera().m_90583_();
      Vector3f normal = (new Vec3(x2 - x1, y2 - y1, z2 - z1)).m_82541_().m_252839_();
      Matrix4f matrix4f = provider.getPoseStack().m_85850_().m_252922_();
      VertexConsumer vertexConsumer = bufferSource.m_6299_(RenderType.m_110504_());
      vertexConsumer.m_252986_(matrix4f, (float)(x1 - pos.m_7096_()), (float)(y1 - pos.m_7098_()), (float)(z1 - pos.m_7094_())).m_193479_(color).m_5601_(normal.x(), normal.y(), normal.z()).m_5752_();
      vertexConsumer.m_252986_(matrix4f, (float)(x2 - pos.m_7096_()), (float)(y2 - pos.m_7098_()), (float)(z2 - pos.m_7094_())).m_193479_(color).m_5601_(normal.x(), normal.y(), normal.z()).m_5752_();
   }

   public static void renderTexts(String texts, double x, double y, double z, float yaw, float pitch, float roll, boolean glowing) {
      Minecraft minecraft = Minecraft.m_91087_();
      MultiBufferSource.BufferSource bufferSource = minecraft.m_91269_().m_110104_();
      Font font = minecraft.f_91062_;
      Vec3 pos = provider.getCamera().m_90583_();
      int packedLight = glowing ? 15728880 : LevelRenderer.m_109541_(minecraft.f_91073_, BlockPos.m_274561_(x, y, z));
      PoseStack poseStack = provider.getPoseStack();
      poseStack.m_85836_();
      poseStack.m_85837_(x - pos.m_7096_(), y - pos.m_7098_(), z - pos.m_7094_());
      poseStack.m_252781_(Axis.f_252392_.m_252977_(yaw));
      poseStack.m_252781_(Axis.f_252529_.m_252977_(pitch));
      poseStack.m_252781_(Axis.f_252393_.m_252977_(roll));
      poseStack.m_85841_(textWidth, -textHeight, 1.0F);
      float var10001 = (float)(font.m_92895_(texts) - 1) * -0.5F;
      Objects.requireNonNull(font);
      poseStack.m_252880_(var10001, (float)(9 - 1) * -0.5F, 0.0F);
      Matrix4f matrix4f = poseStack.m_85850_().m_252922_();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (backColor != 0) {
         font.m_271703_(texts, 0.0F, 0.0F, 0, false, matrix4f, bufferSource, DisplayMode.SEE_THROUGH, backColor, packedLight);
      }

      font.m_271703_(texts, 0.0F, 0.0F, textColor, false, matrix4f, bufferSource, DisplayMode.NORMAL, 0, packedLight);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.m_85849_();
   }

   @SubscribeEvent
   public static void renderModels(RenderLevelStageEvent event) {
      provider = event;
      if (provider.getStage() == Stage.AFTER_ENTITIES) {
         ClientLevel level = Minecraft.m_91087_().f_91073_;
         Entity entity = provider.getCamera().m_90592_();
         Vec3 pos = entity.m_20318_(provider.getPartialTick());
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         execute(provider, level, entity, (double)provider.getPartialTick());
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
      }

   }

   public static void execute(LevelAccessor world, Entity entity, double partialTick) {
      execute((Event)null, world, entity, partialTick);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, double partialTick) {
      if (entity != null) {
         boolean dimention_check = false;
         List<Object> steel = new ArrayList();
         List<Object> titanium = new ArrayList();
         List<Object> nickel = new ArrayList();
         List<Object> types = new ArrayList();
         new ArrayList();
         BlockState steel_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState steel_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState steel_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState steel_ship_3 = Blocks.f_50016_.m_49966_();
         BlockState titanium_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState titanium_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState titanium_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState titanium_ship_3 = Blocks.f_50016_.m_49966_();
         BlockState nickel_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState nickel_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState nickel_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState nickel_ship_3 = Blocks.f_50016_.m_49966_();
         BlockState upright_steel_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState upright_steel_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState upright_steel_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState upright_steel_ship_3 = Blocks.f_50016_.m_49966_();
         BlockState upright_titanium_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState upright_titanium_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState upright_titanium_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState upright_titanium_ship_3 = Blocks.f_50016_.m_49966_();
         BlockState upright_nickel_ship_0 = Blocks.f_50016_.m_49966_();
         BlockState upright_nickel_ship_1 = Blocks.f_50016_.m_49966_();
         BlockState upright_nickel_ship_2 = Blocks.f_50016_.m_49966_();
         BlockState upright_nickel_ship_3 = Blocks.f_50016_.m_49966_();
         double custom_yaw = (double)0.0F;
         double ship_type = (double)0.0F;
         double ship_engine = (double)0.0F;
         double method = (double)0.0F;
         steel_ship_0 = ((Block)CosmosModBlocks.STEELSPACESHIP.get()).m_49966_();
         steel_ship_1 = ((Block)CosmosModBlocks.STEELSPACESHIP_2.get()).m_49966_();
         steel_ship_2 = ((Block)CosmosModBlocks.STEELSPACESHIP_3.get()).m_49966_();
         steel_ship_3 = ((Block)CosmosModBlocks.STEELSPACESHIP_4.get()).m_49966_();
         upright_steel_ship_0 = ((Block)CosmosModBlocks.STEEL_SPACESHIPUPRIGHT.get()).m_49966_();
         upright_steel_ship_1 = ((Block)CosmosModBlocks.STEELSPACESHIPUPRIGHT_2.get()).m_49966_();
         upright_steel_ship_2 = ((Block)CosmosModBlocks.STEELSPACESHIPUPRIGHT_3.get()).m_49966_();
         upright_steel_ship_3 = ((Block)CosmosModBlocks.STEELSPACESHIPUPRIGHT_4.get()).m_49966_();
         titanium_ship_0 = ((Block)CosmosModBlocks.TITANIUMSPACESHIP.get()).m_49966_();
         titanium_ship_1 = ((Block)CosmosModBlocks.TITANIUMSPACESHIP_2.get()).m_49966_();
         titanium_ship_2 = ((Block)CosmosModBlocks.TITANIUMSPACESHIP_3.get()).m_49966_();
         titanium_ship_3 = ((Block)CosmosModBlocks.TITANIUMSPACESHIP_4.get()).m_49966_();
         upright_titanium_ship_0 = ((Block)CosmosModBlocks.TITANIUM_SPACESHIPUPRIGHT.get()).m_49966_();
         upright_titanium_ship_1 = ((Block)CosmosModBlocks.TITANIUMSPACESHIPUPRIGHT_2.get()).m_49966_();
         upright_titanium_ship_2 = ((Block)CosmosModBlocks.TITANIUMSPACESHIPUPRIGHT_3.get()).m_49966_();
         upright_titanium_ship_3 = ((Block)CosmosModBlocks.TITANIUMSPACESHIPUPRIGHT_4.get()).m_49966_();
         nickel_ship_0 = ((Block)CosmosModBlocks.NICKLESPACESHIP.get()).m_49966_();
         nickel_ship_1 = ((Block)CosmosModBlocks.NICKLESPACESHIP_2.get()).m_49966_();
         nickel_ship_2 = ((Block)CosmosModBlocks.NICKLESPACESHIP_3.get()).m_49966_();
         nickel_ship_3 = ((Block)CosmosModBlocks.NICKLESPACESHIP_4.get()).m_49966_();
         upright_nickel_ship_0 = ((Block)CosmosModBlocks.NICKLESPACESHIPUPRIGHT.get()).m_49966_();
         upright_nickel_ship_1 = ((Block)CosmosModBlocks.NICKLESPACESHIPUPRIGHT_2.get()).m_49966_();
         upright_nickel_ship_2 = ((Block)CosmosModBlocks.NICKLESPACESHIPUPRIGHT_3.get()).m_49966_();
         upright_nickel_ship_3 = ((Block)CosmosModBlocks.NICKLESPACESHIPUPRIGHT_4.get()).m_49966_();
         steel.add(steel_ship_0);
         steel.add(steel_ship_1);
         steel.add(steel_ship_2);
         steel.add(steel_ship_3);
         steel.add(upright_steel_ship_0);
         steel.add(upright_steel_ship_1);
         steel.add(upright_steel_ship_2);
         steel.add(upright_steel_ship_3);
         titanium.add(titanium_ship_0);
         titanium.add(titanium_ship_1);
         titanium.add(titanium_ship_2);
         titanium.add(titanium_ship_3);
         titanium.add(upright_titanium_ship_0);
         titanium.add(upright_titanium_ship_1);
         titanium.add(upright_titanium_ship_2);
         titanium.add(upright_titanium_ship_3);
         nickel.add(nickel_ship_0);
         nickel.add(nickel_ship_1);
         nickel.add(nickel_ship_2);
         nickel.add(nickel_ship_3);
         nickel.add(upright_nickel_ship_0);
         nickel.add(upright_nickel_ship_1);
         nickel.add(upright_nickel_ship_2);
         nickel.add(upright_nickel_ship_3);
         types.add(steel);
         types.add(titanium);
         types.add(nickel);
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var44 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var10000;
            if (var44 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var44;
               var10000 = _stringTag.m_7916_();
            } else {
               var10000 = "";
            }

            if (var10000.equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         if (entity.m_20202_() instanceof RocketSeatEntity) {
            Entity var46 = entity.m_20202_();
            double var114;
            if (var46 instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)var46;
               var114 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
            } else {
               var114 = (double)0.0F;
            }

            double _setval = var114;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.roll = _setval;
               capability.syncPlayerVariables(entity);
            });
            var46 = entity.m_20202_();
            if (var46 instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)var46;
               var114 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
            } else {
               var114 = (double)0.0F;
            }

            _setval = var114;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.pitch = _setval;
               capability.syncPlayerVariables(entity);
            });
            int var117;
            if (dimention_check) {
               Entity var90 = entity.m_20202_();
               if (var90 instanceof RocketSeatEntity) {
                  RocketSeatEntity _datEntI = (RocketSeatEntity)var90;
                  var117 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
               } else {
                  var117 = 0;
               }

               var117 /= 1;
            } else {
               var117 = 0;
            }

            custom_yaw = (double)var117;
            Entity var91 = entity.m_20202_();
            double var118;
            if (var91 instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)var91;
               var118 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
            } else {
               var118 = (double)0.0F;
            }

            ship_type = var118;
            var91 = entity.m_20202_();
            if (var91 instanceof RocketSeatEntity) {
               RocketSeatEntity _datEntI = (RocketSeatEntity)var91;
               var118 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
            } else {
               var118 = (double)0.0F;
            }

            ship_engine = var118;

            for(int type = 0; type < types.size(); ++type) {
               if (ship_type == (double)type) {
                  Object _blockstateValue = types.get(type);
                  Object var120;
                  if (_blockstateValue instanceof List) {
                     List _listValue = (List)_blockstateValue;
                     var120 = _listValue;
                  } else {
                     var120 = new ArrayList();
                  }

                  List<Object> render_list = var120;

                  for(int engine = 0; engine < ((List)render_list).size() / 2; ++engine) {
                     if (ship_engine == (double)engine) {
                        method = dimention_check ? (double)0.0F : (double)(((List)render_list).size() / 2);
                        Object var106 = ((List)render_list).get((int)((double)engine + method));
                        BlockState var121;
                        if (var106 instanceof BlockState) {
                           BlockState _blockstateValue = (BlockState)var106;
                           var121 = _blockstateValue;
                        } else {
                           var121 = Blocks.f_50016_.m_49966_();
                        }

                        double var10001 = entity.m_20318_((float)partialTick).m_7096_();
                        double var10002 = entity.m_20318_((float)partialTick).m_7098_() + 0.64;
                        double var10003 = entity.m_20318_((float)partialTick).m_7094_();
                        float var10004 = (float)custom_yaw;
                        Entity var107 = entity.m_20202_();
                        int var10005;
                        if (var107 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntI = (RocketSeatEntity)var107;
                           var10005 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                        } else {
                           var10005 = 0;
                        }

                        float var132 = (float)(var10005 / 1);
                        var107 = entity.m_20202_();
                        int var10006;
                        if (var107 instanceof RocketSeatEntity) {
                           RocketSeatEntity _datEntI = (RocketSeatEntity)var107;
                           var10006 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                        } else {
                           var10006 = 0;
                        }

                        renderBlock(var121, var10001, var10002, var10003, var10004, var132, (float)(var10006 / 1), 1.25F, false);
                     }
                  }
               }
            }
         }

         if (world instanceof ClientLevel) {
            for(Entity entityiterator : ((ClientLevel)world).m_104735_()) {
               if (entityiterator.m_146895_() != entity && entityiterator instanceof RocketSeatEntity) {
                  int var123;
                  if (dimention_check) {
                     if (entityiterator instanceof RocketSeatEntity) {
                        RocketSeatEntity _datEntI = (RocketSeatEntity)entityiterator;
                        var123 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_yaw);
                     } else {
                        var123 = 0;
                     }

                     var123 /= 1;
                  } else {
                     var123 = 0;
                  }

                  custom_yaw = (double)var123;
                  double var124;
                  if (entityiterator instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entityiterator;
                     var124 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_type);
                  } else {
                     var124 = (double)0.0F;
                  }

                  ship_type = var124;
                  if (entityiterator instanceof RocketSeatEntity) {
                     RocketSeatEntity _datEntI = (RocketSeatEntity)entityiterator;
                     var124 = (double)(Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_engine);
                  } else {
                     var124 = (double)0.0F;
                  }

                  ship_engine = var124;

                  for(int type = 0; type < types.size(); ++type) {
                     if (ship_type == (double)type) {
                        Object var47 = types.get(type);
                        Object var126;
                        if (var47 instanceof List) {
                           List _listValue = (List)var47;
                           var126 = _listValue;
                        } else {
                           var126 = new ArrayList();
                        }

                        List<Object> var50 = var126;

                        for(int engine = 0; engine < ((List)var50).size() / 2; ++engine) {
                           if (ship_engine == (double)engine) {
                              method = dimention_check ? (double)0.0F : (double)(((List)var50).size() / 2);
                              Object var48 = ((List)var50).get((int)((double)engine + method));
                              BlockState var127;
                              if (var48 instanceof BlockState) {
                                 BlockState _blockstateValue = (BlockState)var48;
                                 var127 = _blockstateValue;
                              } else {
                                 var127 = Blocks.f_50016_.m_49966_();
                              }

                              double var128 = entityiterator.m_20185_();
                              double var129 = entityiterator.m_20186_() + 0.64;
                              double var130 = entityiterator.m_20189_();
                              float var131 = (float)custom_yaw;
                              int var133;
                              if (entityiterator instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntI = (RocketSeatEntity)entityiterator;
                                 var133 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_pitch);
                              } else {
                                 var133 = 0;
                              }

                              float var134 = (float)(var133 / 1);
                              int var135;
                              if (entityiterator instanceof RocketSeatEntity) {
                                 RocketSeatEntity _datEntI = (RocketSeatEntity)entityiterator;
                                 var135 = (Integer)_datEntI.m_20088_().m_135370_(RocketSeatEntity.DATA_roll);
                              } else {
                                 var135 = 0;
                              }

                              renderBlock(var127, var128, var129, var130, var131, var134, (float)(var135 / 1), 1.25F, false);
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
