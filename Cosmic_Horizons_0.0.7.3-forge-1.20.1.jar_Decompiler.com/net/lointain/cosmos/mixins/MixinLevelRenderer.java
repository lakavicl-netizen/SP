package net.lointain.cosmos.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lointain.cosmos.procedures.RenderUtilProcedure;
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
public class MixinLevelRenderer {
   @Inject(
      method = {"renderLevel"},
      at = {@At("HEAD")}
   )
   private void beginRenderLevel(PoseStack poseStack, float tickDelta, long startTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projection, CallbackInfo callback) {
      RenderUtilProcedure.CURRENT_MODELVIEW = poseStack.m_85850_().m_252922_();
   }
}
