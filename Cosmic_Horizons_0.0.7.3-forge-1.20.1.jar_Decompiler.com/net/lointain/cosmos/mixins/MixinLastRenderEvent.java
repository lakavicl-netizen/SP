package net.lointain.cosmos.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lointain.cosmos.procedures.BHProcedure;
import net.lointain.cosmos.procedures.LightingProcedure;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LevelRenderer.class})
public class MixinLastRenderEvent {
   @Inject(
      method = {"renderLevel(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lorg/joml/Matrix4f;)V"},
      at = {@At("RETURN")}
   )
   private void onRenderLevel(PoseStack poseStack, float partialTicks, long finishTimeNano, boolean renderOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projectionMatrix, CallbackInfo ci) {
      LightingProcedure.handleRender(poseStack, partialTicks, camera);
      BHProcedure.handleRender(poseStack, partialTicks, camera);
   }
}
