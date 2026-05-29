package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.DimensionSpecialEffects.SkyType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class SetupProcedure {
   private static RegisterDimensionSpecialEffectsEvent provider = null;

   public static void register(ResourceKey<Level> dimension, DimensionSpecialEffects effects) {
      provider.register(dimension.m_135782_(), effects);
   }

   public static DimensionSpecialEffects createOverworldEffects(boolean constantWhiteLight, boolean constantAmbientLight, final boolean fog) {
      return new CosmosModDimensionSpecialEffects(192.0F, true, SkyType.NORMAL, constantWhiteLight, constantAmbientLight) {
         public Vec3 m_5927_(Vec3 color, float sunHeight) {
            return color.m_82542_((double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.91F + 0.09F));
         }

         public boolean m_5781_(int x, int y) {
            return fog;
         }
      };
   }

   public static DimensionSpecialEffects createNetherEffects(boolean constantWhiteLight, boolean constantAmbientLight, final boolean fog) {
      return new CosmosModDimensionSpecialEffects(Float.NaN, true, SkyType.NONE, constantWhiteLight, constantAmbientLight) {
         public Vec3 m_5927_(Vec3 color, float sunHeight) {
            return color;
         }

         public boolean m_5781_(int x, int y) {
            return fog;
         }
      };
   }

   public static DimensionSpecialEffects createEndEffects(boolean constantWhiteLight, boolean constantAmbientLight, final boolean fog) {
      return new CosmosModDimensionSpecialEffects(Float.NaN, false, SkyType.END, constantWhiteLight, constantAmbientLight) {
         public Vec3 m_5927_(Vec3 color, float sunHeight) {
            return color.m_82490_((double)0.15F);
         }

         public boolean m_5781_(int x, int y) {
            return fog;
         }
      };
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void setupDimensions(RegisterDimensionSpecialEffectsEvent event) {
      provider = event;
      execute(event);
   }

   public static void execute() {
      execute((Event)null);
   }

   private static void execute(@Nullable Event event) {
      new File("");
      new File("");
      String id = "";
      String kid = "";
      String texts = "";
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      register(Level.f_46428_, createOverworldEffects(false, false, false));
      register(ResourceKey.m_135785_(Registries.f_256858_, new ResourceLocation("cosmos:solar_system")), createOverworldEffects(false, false, false));
   }

   public abstract static class CosmosModDimensionSpecialEffects extends DimensionSpecialEffects {
      public static final Set<Predicate<Object[]>> CUSTOM_CLOUDS = new HashSet();
      public static final Set<Predicate<Object[]>> CUSTOM_SKY = new HashSet();
      public static final Set<Predicate<Object[]>> CUSTOM_WEATHER = new HashSet();
      public static final Set<Predicate<Object[]>> CUSTOM_EFFECTS = new HashSet();
      public static final Set<Consumer<Object[]>> CUSTOM_LIGHTS = new HashSet();

      public CosmosModDimensionSpecialEffects(float cloudHeight, boolean hasGround, DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
         super(cloudHeight, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
      }

      public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
         if (CUSTOM_CLOUDS != null && !CUSTOM_CLOUDS.isEmpty()) {
            boolean flag = false;
            Object[] objects = new Object[]{level, ticks, partialTick, poseStack, camX, camY, camZ, projectionMatrix};

            for(Predicate<Object[]> predicate : CUSTOM_CLOUDS) {
               RenderSystem.depthMask(true);
               RenderSystem.enableDepthTest();
               RenderSystem.disableCull();
               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               flag |= predicate.test(objects);
            }

            if (flag) {
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableBlend();
               RenderSystem.enableCull();
               RenderSystem.enableDepthTest();
               RenderSystem.depthMask(true);
            }

            return flag;
         } else {
            return false;
         }
      }

      public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
         if (CUSTOM_SKY != null && !CUSTOM_SKY.isEmpty()) {
            boolean flag = false;
            Object[] objects = new Object[]{level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog};

            for(Predicate<Object[]> predicate : CUSTOM_SKY) {
               RenderSystem.depthMask(false);
               RenderSystem.enableDepthTest();
               RenderSystem.enableCull();
               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               flag |= predicate.test(objects);
            }

            if (flag) {
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableBlend();
               RenderSystem.enableCull();
               RenderSystem.enableDepthTest();
               RenderSystem.depthMask(true);
            }

            return flag;
         } else {
            return false;
         }
      }

      public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
         if (CUSTOM_WEATHER != null && !CUSTOM_WEATHER.isEmpty()) {
            boolean flag = false;
            Object[] objects = new Object[]{level, ticks, partialTick, lightTexture, camX, camY, camZ};
            lightTexture.m_109896_();

            for(Predicate<Object[]> predicate : CUSTOM_WEATHER) {
               RenderSystem.depthMask(Minecraft.m_91085_());
               RenderSystem.enableDepthTest();
               RenderSystem.disableCull();
               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               flag |= predicate.test(objects);
            }

            if (flag) {
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableBlend();
               RenderSystem.enableCull();
               RenderSystem.enableDepthTest();
               RenderSystem.depthMask(Minecraft.m_91085_());
            }

            lightTexture.m_109891_();
            return flag;
         } else {
            return false;
         }
      }

      public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
         if (CUSTOM_EFFECTS != null && !CUSTOM_EFFECTS.isEmpty()) {
            boolean flag = false;
            Object[] objects = new Object[]{level, ticks, camera};

            for(Predicate<Object[]> predicate : CUSTOM_EFFECTS) {
               flag |= predicate.test(objects);
            }

            return flag;
         } else {
            return false;
         }
      }

      public void adjustLightmapColors(ClientLevel level, float partialTick, float skyDarken, float blockLightRedFlicker, float skyLight, int pixelX, int pixelY, Vector3f colors) {
         if (CUSTOM_LIGHTS != null && !CUSTOM_LIGHTS.isEmpty()) {
            Object[] objects = new Object[]{level, partialTick, skyDarken, blockLightRedFlicker, skyLight, pixelX, pixelY, colors};

            for(Consumer<Object[]> consumer : CUSTOM_LIGHTS) {
               consumer.accept(objects);
            }
         }

      }
   }
}
