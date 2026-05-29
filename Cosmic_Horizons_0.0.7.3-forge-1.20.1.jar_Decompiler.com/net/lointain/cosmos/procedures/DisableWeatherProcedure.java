package net.lointain.cosmos.procedures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.DimensionSpecialEffectsManager;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class DisableWeatherProcedure {
   private static float partialTick = 0.0F;
   private static int ticks = 0;
   private static final Predicate<Object[]> PREDICATE = (params) -> {
      ticks = (Integer)params[1];
      partialTick = (Float)params[2];
      Minecraft minecraft = Minecraft.m_91087_();
      Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
      if (entity != null) {
         ClientLevel level = minecraft.f_91073_;
         Vec3 pos = entity.m_20318_(partialTick);
         return execute((Event)null, level, entity);
      } else {
         return false;
      }
   };

   public static void renderRain(float speed, int x, int z, int range, int color, boolean constant) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
      double camX = pos.m_7096_();
      double camY = pos.m_7098_();
      double camZ = pos.m_7094_();
      int iCamX = Mth.m_14107_(camX);
      int iCamY = Mth.m_14107_(camY);
      int iCamZ = Mth.m_14107_(camZ);
      int height = level.m_6924_(Types.MOTION_BLOCKING, x, z);
      int length = Minecraft.m_91405_() ? 10 : 5;
      int btm = iCamY - (range > length ? range : length);
      int top = iCamY + (range > length ? range : length);
      if (btm < height) {
         btm = height;
      }

      float red = (float)(color >> 16 & 255) / 255.0F;
      float green = (float)(color >> 8 & 255) / 255.0F;
      float blue = (float)(color & 255) / 255.0F;
      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= level.m_46722_(partialTick);
      }

      if (top > height) {
         int idx = x - iCamX;
         int idz = z - iCamZ;
         double r = (double)Mth.m_14116_((float)(idx * idx + idz * idz));
         double vecX = (double)(-idz) / r * (double)0.5F;
         double vecZ = (double)idx / r * (double)0.5F;
         RandomSource randomSource = RandomSource.m_216335_((long)(x * x * 3121 + x * 45238971 ^ z * z * 418711 + z * 13761));
         int counts = ticks + x * x * 3121 + x * 45238971 + z * z * 418711 + z * 13761 & 31;
         float anime = -(((float)counts + partialTick) * speed) / 32.0F * (3.0F + randomSource.m_188501_());
         double dx = (double)((float)x + 0.5F) - camX;
         double dz = (double)((float)z + 0.5F) - camZ;
         if (range > 0) {
            float coef = (float)Math.sqrt(dx * dx + dz * dz) / (float)range;
            alpha *= (1.0F - coef * coef) * 0.5F + 0.5F;
            alpha = alpha < 0.0F ? 0.0F : alpha;
         }

         int y = iCamY < height ? height : iCamY;
         int packedLight = LevelRenderer.m_109541_(level, new BlockPos(x, y, z));
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::m_172829_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85813_);
         bufferBuilder.m_5483_((double)x - camX - vecX + (double)0.5F, (double)top - camY, (double)z - camZ - vecZ + (double)0.5F).m_7421_(0.0F, (float)btm * 0.25F + anime).m_85950_(red, green, blue, alpha).m_85969_(packedLight).m_5752_();
         bufferBuilder.m_5483_((double)x - camX - vecX + (double)0.5F, (double)btm - camY, (double)z - camZ - vecZ + (double)0.5F).m_7421_(0.0F, (float)top * 0.25F + anime).m_85950_(red, green, blue, alpha).m_85969_(packedLight).m_5752_();
         bufferBuilder.m_5483_((double)x - camX + vecX + (double)0.5F, (double)btm - camY, (double)z - camZ + vecZ + (double)0.5F).m_7421_(1.0F, (float)top * 0.25F + anime).m_85950_(red, green, blue, alpha).m_85969_(packedLight).m_5752_();
         bufferBuilder.m_5483_((double)x - camX + vecX + (double)0.5F, (double)top - camY, (double)z - camZ + vecZ + (double)0.5F).m_7421_(1.0F, (float)btm * 0.25F + anime).m_85950_(red, green, blue, alpha).m_85969_(packedLight).m_5752_();
         BufferUploader.m_231202_(bufferBuilder.m_231175_());
      }

   }

   public static void renderSnow(float speed, int x, int z, int range, int color, boolean constant) {
      Minecraft minecraft = Minecraft.m_91087_();
      ClientLevel level = minecraft.f_91073_;
      Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
      double camX = pos.m_7096_();
      double camY = pos.m_7098_();
      double camZ = pos.m_7094_();
      int iCamX = Mth.m_14107_(camX);
      int iCamY = Mth.m_14107_(camY);
      int iCamZ = Mth.m_14107_(camZ);
      int height = level.m_6924_(Types.MOTION_BLOCKING, x, z);
      int length = Minecraft.m_91405_() ? 10 : 5;
      int btm = iCamY - (range > length ? range : length);
      int top = iCamY + (range > length ? range : length);
      if (btm < height) {
         btm = height;
      }

      float red = (float)(color >> 16 & 255) / 255.0F;
      float green = (float)(color >> 8 & 255) / 255.0F;
      float blue = (float)(color & 255) / 255.0F;
      float alpha = (float)(color >>> 24) / 255.0F;
      if (!constant) {
         alpha *= level.m_46722_(partialTick);
      }

      if (top > height) {
         RandomSource randomSource = RandomSource.m_216335_((long)(x * x * 3121 + x * 45238971 ^ z * z * 418711 + z * 13761));
         int idx = x - iCamX;
         int idz = z - iCamZ;
         double r = (double)Mth.m_14116_((float)(idx * idx + idz * idz));
         double vecX = (double)(-idz) / r * (double)0.5F;
         double vecZ = (double)idx / r * (double)0.5F;
         float anime = -((float)(ticks & 511) + partialTick) / 512.0F;
         float time = (float)ticks + partialTick;
         float du = (float)(randomSource.m_188500_() + randomSource.m_188583_() * (double)time * 0.01) * speed;
         float dv = (float)(randomSource.m_188500_() + randomSource.m_188583_() * (double)time * 0.001) * (speed / 10.0F);
         double dx = (double)((float)x + 0.5F) - camX;
         double dz = (double)((float)z + 0.5F) - camZ;
         if (range > 0) {
            float coef = (float)Math.sqrt(dx * dx + dz * dz) / (float)range;
            alpha *= (1.0F - coef * coef) * 0.5F + 0.5F;
            alpha = alpha < 0.0F ? 0.0F : alpha;
         }

         int y = iCamY < height ? height : iCamY;
         int packedLight = LevelRenderer.m_109541_(level, new BlockPos(x, y, z));
         int sky = ((packedLight & '\uffff') * 3 + 240) / 4;
         int block = ((packedLight >> 16 & '\uffff') * 3 + 240) / 4;
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.setShader(GameRenderer::m_172829_);
         BufferBuilder bufferBuilder = Tesselator.m_85913_().m_85915_();
         bufferBuilder.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85813_);
         bufferBuilder.m_5483_((double)x - camX - vecX + (double)0.5F, (double)top - camY, (double)z - camZ - vecZ + (double)0.5F).m_7421_(0.0F + du, (float)btm * 0.25F + anime + dv).m_85950_(red, green, blue, alpha).m_7120_(sky, block).m_5752_();
         bufferBuilder.m_5483_((double)x - camX - vecX + (double)0.5F, (double)btm - camY, (double)z - camZ - vecZ + (double)0.5F).m_7421_(0.0F + du, (float)top * 0.25F + anime + dv).m_85950_(red, green, blue, alpha).m_7120_(sky, block).m_5752_();
         bufferBuilder.m_5483_((double)x - camX + vecX + (double)0.5F, (double)btm - camY, (double)z - camZ + vecZ + (double)0.5F).m_7421_(1.0F + du, (float)top * 0.25F + anime + dv).m_85950_(red, green, blue, alpha).m_7120_(sky, block).m_5752_();
         bufferBuilder.m_5483_((double)x - camX + vecX + (double)0.5F, (double)top - camY, (double)z - camZ + vecZ + (double)0.5F).m_7421_(1.0F + du, (float)btm * 0.25F + anime + dv).m_85950_(red, green, blue, alpha).m_7120_(sky, block).m_5752_();
         BufferUploader.m_231202_(bufferBuilder.m_231175_());
      }

   }

   public static void renderWeather(boolean rain, boolean snow, float speed, int range, int color, boolean constant) {
      if (range > 0) {
         Minecraft minecraft = Minecraft.m_91087_();
         ClientLevel level = minecraft.f_91073_;
         Vec3 pos = minecraft.f_91063_.m_109153_().m_90583_();
         int ix = Mth.m_14107_(pos.m_7096_());
         int iy = Mth.m_14107_(pos.m_7098_());
         int iz = Mth.m_14107_(pos.m_7094_());

         for(int i = -range; i <= range; ++i) {
            for(int j = -range; j <= range; ++j) {
               BlockPos blockPos = new BlockPos(ix + i, iy, iz + j);
               Biome biome = (Biome)level.m_204166_(blockPos).m_203334_();
               if (biome.m_264473_()) {
                  Biome.Precipitation precipitation = biome.m_264600_(blockPos);
                  if (rain && precipitation == Precipitation.RAIN) {
                     RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft:textures/environment/rain.png"));
                     renderRain(speed, ix + i, iz + j, range, color, constant);
                  }

                  if (snow && precipitation == Precipitation.SNOW) {
                     RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft:textures/environment/snow.png"));
                     renderSnow(speed, ix + i, iz + j, range, color, constant);
                  }
               }
            }
         }
      }

   }

   @SubscribeEvent
   public static void weatherSetup(FMLClientSetupEvent event) {
      try {
         Field field = DimensionSpecialEffectsManager.class.getDeclaredField("EFFECTS");
         field.setAccessible(true);
         UnmodifiableIterator var2 = ((ImmutableMap)field.get((Object)null)).values().iterator();

         while(var2.hasNext()) {
            DimensionSpecialEffects dimensionSpecialEffects = (DimensionSpecialEffects)var2.next();
            if (dimensionSpecialEffects instanceof SetupProcedure.CosmosModDimensionSpecialEffects cosmosSpecialEffects) {
               Class<?> effects = dimensionSpecialEffects.getClass();
               ((Set)effects.getField("CUSTOM_WEATHER").get((Object)null)).add(PREDICATE);
            }
         }
      } catch (Exception var6) {
      }

   }

   public static boolean execute(LevelAccessor world, Entity entity) {
      return execute((Event)null, world, entity);
   }

   private static boolean execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         if (CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var5 = CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (var5 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var5;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag dimensional_data = var10000;
            if (world.m_6106_().m_6533_()) {
               if (dimensional_data.m_128441_("weather")) {
                  var5 = dimensional_data.m_128423_("weather");
                  boolean var8;
                  if (var5 instanceof ByteTag) {
                     ByteTag _byteTag = (ByteTag)var5;
                     var8 = _byteTag.m_7063_() == 1;
                  } else {
                     var8 = false;
                  }

                  if (!var8) {
                     renderWeather(false, false, 1.0F, Minecraft.m_91405_() ? 10 : 5, -1, false);
                  } else {
                     renderWeather(true, true, 1.0F, Minecraft.m_91405_() ? 10 : 5, -1, false);
                  }
               } else {
                  renderWeather(true, true, 1.0F, Minecraft.m_91405_() ? 10 : 5, -1, false);
               }
            }
         }

         return true;
      }
   }
}
