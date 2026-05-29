package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLLoader;
import org.joml.Matrix4f;

@EventBusSubscriber(
   modid = "cosmos",
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class LightRendererer {
   public static PostChain flashLightPass;
   public static List<PostPass> passes;
   public static EffectInstance effect;
   public static TextureTarget copyDepth;
   private static int previousWidth = -1;
   private static int previousHeight = -1;

   public static Object getPrivateField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
      Class<?> clazz = object.getClass();
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(object);
   }

   @SubscribeEvent
   public static void onRegisterShader(RegisterShadersEvent event) {
      try {
         String passesFieldName = !FMLLoader.isProduction() ? "passes" : "f_110009_";
         String effectFieldName = !FMLLoader.isProduction() ? "effect" : "f_110054_";
         flashLightPass = new PostChain(Minecraft.m_91087_().m_91097_(), Minecraft.m_91087_().m_91098_(), Minecraft.m_91087_().m_91385_(), new ResourceLocation("cosmos", "shaders/post/flash_light.json"));
         passes = (List)getPrivateField(flashLightPass, passesFieldName);
         effect = (EffectInstance)getPrivateField(passes.get(0), effectFieldName);
         flashLightPass.m_110025_(1920, 1080);
         copyDepth = new TextureTarget(1920, 1080, true, false);
         ((PostPass)passes.get(0)).m_110069_("depth", () -> Minecraft.m_91087_().m_91385_().m_83980_(), 1920, 1080);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public static void renderLight(Vec3 position, float brightness, float linear, float quadratic, Vec3 color, PoseStack poseStack) {
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      int currentWidth = Minecraft.m_91087_().m_91268_().m_85441_();
      int currentHeight = Minecraft.m_91087_().m_91268_().m_85442_();
      if (currentWidth != previousWidth || currentHeight != previousHeight) {
         flashLightPass.m_110025_(currentWidth, currentHeight);
         previousWidth = currentWidth;
         previousHeight = currentHeight;
      }

      effect.m_108966_();
      Uniform invViewUniform = effect.m_108952_("InvViewMat");
      Uniform invProjUniform = effect.m_108952_("FlashLightInvProj");
      Uniform brightnessUniform = effect.m_108952_("Brightness");
      Uniform linearUniform = effect.m_108952_("Linear");
      Uniform quadraticUniform = effect.m_108952_("Quadratic");
      Uniform positionUniform = effect.m_108952_("Position");
      Uniform colorUniform = effect.m_108952_("LightColor");
      if (invViewUniform != null) {
         Vec3 camera = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_();
         PoseStack pose = new PoseStack();
         pose.m_252931_(poseStack.m_85850_().m_252922_());
         pose.m_252880_((float)(-camera.f_82479_), (float)(-camera.f_82480_), (float)(-camera.f_82481_));
         invViewUniform.m_5679_(pose.m_85850_().m_252922_().invert());
         invViewUniform.m_85633_();
      }

      if (invProjUniform != null) {
         invProjUniform.m_5679_((new Matrix4f(RenderSystem.getProjectionMatrix())).invert());
         invProjUniform.m_85633_();
      }

      if (brightnessUniform != null) {
         brightnessUniform.m_5985_(brightness);
         brightnessUniform.m_85633_();
      }

      if (linearUniform != null) {
         linearUniform.m_5985_(linear);
         linearUniform.m_85633_();
      }

      if (quadraticUniform != null) {
         quadraticUniform.m_5985_(quadratic);
         quadraticUniform.m_85633_();
      }

      if (positionUniform != null) {
         positionUniform.m_142276_(position.m_252839_());
         positionUniform.m_85633_();
      }

      if (colorUniform != null) {
         colorUniform.m_142276_(color.m_252839_());
         colorUniform.m_85633_();
      }

      flashLightPass.m_110023_(0.0F);
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.enableBlend();
      RenderSystem.resetTextureMatrix();
      Minecraft.m_91087_().m_91385_().m_83947_(false);
   }
}
