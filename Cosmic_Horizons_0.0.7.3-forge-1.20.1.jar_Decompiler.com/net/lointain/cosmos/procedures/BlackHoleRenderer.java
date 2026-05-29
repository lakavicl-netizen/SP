package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@EventBusSubscriber(
   modid = "cosmos",
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
@OnlyIn(Dist.CLIENT)
public class BlackHoleRenderer {
   public static PostChain blackHolePass;
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
         blackHolePass = new PostChain(Minecraft.m_91087_().m_91097_(), Minecraft.m_91087_().m_91098_(), Minecraft.m_91087_().m_91385_(), new ResourceLocation("cosmos", "shaders/post/black_hole.json"));
         passes = (List)getPrivateField(blackHolePass, passesFieldName);
         effect = (EffectInstance)getPrivateField(passes.get(0), effectFieldName);
         ((PostPass)passes.get(0)).m_110069_("depth", () -> Minecraft.m_91087_().m_91385_().m_83980_(), 1920, 1080);
         blackHolePass.m_110025_(1920, 1080);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public static void renderBlackhole(Vec3 position, Vec3 rotations, Vec3 color, float size, float brightness, float step, float speed, float ticks) {
      RenderSystem.disableBlend();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      int currentWidth = Minecraft.m_91087_().m_91268_().m_85441_();
      int currentHeight = Minecraft.m_91087_().m_91268_().m_85442_();
      if (currentWidth != previousWidth || currentHeight != previousHeight) {
         blackHolePass.m_110025_(currentWidth, currentHeight);
         previousWidth = currentWidth;
         previousHeight = currentHeight;
      }

      effect.m_108966_();
      Uniform projectionMatrix = effect.m_108952_("ProjectionMatrix");
      Uniform InvProjMatrix = effect.m_108952_("InvProjMatrix");
      Uniform InvViewModelMatrix = effect.m_108952_("InvViewModleMatrix");
      Uniform RotMatrix = effect.m_108952_("RotationMatrix");
      Uniform fullMatPUniform = effect.m_108952_("FullMatP");
      Uniform fullMatNUniform = effect.m_108952_("FullMatN");
      Uniform cameraPosition = effect.m_108952_("CameraPosition");
      Uniform blackholePosition = effect.m_108952_("BlackholePosition");
      Uniform rgb = effect.m_108952_("Color");
      Uniform time = effect.m_108952_("GameTime");
      Uniform Size = effect.m_108952_("Scale");
      Uniform Step = effect.m_108952_("_Steps");
      Uniform Speed = effect.m_108952_("_Speed");
      Uniform RenderDistance = effect.m_108952_("RenderDistance");
      Uniform Fov = effect.m_108952_("Fov");
      Uniform Brightness = effect.m_108952_("Intensity");
      if (projectionMatrix != null) {
         projectionMatrix.m_5679_(RenderSystem.getProjectionMatrix());
         projectionMatrix.m_85633_();
      }

      if (InvProjMatrix != null) {
         InvProjMatrix.m_5679_(RenderSystem.getProjectionMatrix().invert());
         InvProjMatrix.m_85633_();
      }

      if (InvViewModelMatrix != null) {
         Matrix4f modelViewMatrix = RenderUtilProcedure.CURRENT_MODELVIEW.invert();
         InvViewModelMatrix.m_5679_(modelViewMatrix);
         InvViewModelMatrix.m_85633_();
      }

      if (RotMatrix != null) {
         Camera cam = Minecraft.m_91087_().f_91063_.m_109153_();
         Vector3f cameraLeft = cam.m_252775_();
         Vector3f cameraUp = cam.m_253028_();
         Vector3f cameraLook = cam.m_253058_();
         Matrix4f rotationMatrix = new Matrix4f(-cameraLeft.x, -cameraLeft.y, -cameraLeft.z, 0.0F, cameraUp.x, cameraUp.y, cameraUp.z, 0.0F, cameraLook.x, cameraLook.y, cameraLook.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         RotMatrix.m_5679_(rotationMatrix);
         RotMatrix.m_85633_();
      }

      if (fullMatPUniform != null) {
         float roll = (float)rotations.f_82481_ / 45.0F;
         float pitch = -((float)rotations.f_82479_) / 45.0F;
         float yaw = (float)rotations.f_82480_ / 45.0F;
         Matrix4f rollMatP = new Matrix4f((float)Math.cos((double)roll), (float)Math.sin((double)roll), 0.0F, 0.0F, -((float)Math.sin((double)roll)), (float)Math.cos((double)roll), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f pitchMatP = new Matrix4f(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, (float)Math.cos((double)pitch), (float)Math.sin((double)pitch), 0.0F, 0.0F, -((float)Math.sin((double)pitch)), (float)Math.cos((double)pitch), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f yawMatP = new Matrix4f((float)Math.cos((double)yaw), 0.0F, -((float)Math.sin((double)yaw)), 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, (float)Math.sin((double)yaw), 0.0F, (float)Math.cos((double)yaw), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f fullMatP = (new Matrix4f(yawMatP)).mul(pitchMatP).mul(rollMatP);
         fullMatPUniform.m_5679_(fullMatP);
         fullMatPUniform.m_85633_();
      }

      if (fullMatNUniform != null) {
         float roll = (float)rotations.f_82481_ / 45.0F;
         float pitch = -((float)rotations.f_82479_) / 45.0F;
         float yaw = (float)rotations.f_82480_ / 45.0F;
         Matrix4f rollMatN = new Matrix4f((float)Math.cos((double)roll), -((float)Math.sin((double)roll)), 0.0F, 0.0F, (float)Math.sin((double)roll), (float)Math.cos((double)roll), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f pitchMatN = new Matrix4f(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, (float)Math.cos((double)pitch), -((float)Math.sin((double)pitch)), 0.0F, 0.0F, (float)Math.sin((double)pitch), (float)Math.cos((double)pitch), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f yawMatN = new Matrix4f((float)Math.cos((double)yaw), 0.0F, (float)Math.sin((double)yaw), 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -((float)Math.sin((double)yaw)), 0.0F, (float)Math.cos((double)yaw), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
         Matrix4f fullMatN = (new Matrix4f(rollMatN)).mul(pitchMatN).mul(yawMatN);
         fullMatNUniform.m_5679_(fullMatN);
         fullMatNUniform.m_85633_();
      }

      if (blackholePosition != null) {
         blackholePosition.m_142276_(position.m_252839_());
         blackholePosition.m_85633_();
      }

      if (cameraPosition != null) {
         Vec3 camera = Minecraft.m_91087_().f_91063_.m_109153_().m_90583_();
         Vector3f pos = camera.m_252839_();
         cameraPosition.m_142276_(pos);
         cameraPosition.m_85633_();
      }

      if (rgb != null) {
         rgb.m_142276_(color.m_252839_());
         rgb.m_85633_();
      }

      if (time != null) {
         time.m_5985_(ticks);
         time.m_85633_();
      }

      if (Size != null) {
         Size.m_5985_(size);
         Size.m_85633_();
      }

      if (Step != null) {
         Step.m_5985_(step);
         Step.m_85633_();
      }

      if (Speed != null) {
         Speed.m_5985_(speed);
         Speed.m_85633_();
      }

      if (Brightness != null) {
         Brightness.m_5985_(brightness);
         Brightness.m_85633_();
      }

      if (RenderDistance != null) {
         Minecraft minecraft = Minecraft.m_91087_();
         float Dist = Minecraft.m_91087_().f_91063_.m_109152_();
         RenderDistance.m_5985_(Dist);
         RenderDistance.m_85633_();
      }

      if (Fov != null) {
         Matrix4f projmat = RenderSystem.getProjectionMatrix().invert();
         float p11 = projmat.m11();
         double fov = (double)2.0F * Math.atan((double)1.0F / (double)p11);
         double finalfov = Math.toDegrees(fov);
         Fov.m_5985_((float)finalfov);
         Fov.m_85633_();
      }

      blackHolePass.m_110023_(0.0F);
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      Minecraft.m_91087_().m_91385_().m_83947_(true);
   }
}
