package net.lointain.cosmos.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Minecraft.class})
public abstract class ClientPacketListenerMixin {
   @Shadow
   private static Minecraft f_90981_;

   @Shadow
   public abstract void m_91152_(@Nullable Screen var1);

   @Inject(
      method = {"setScreen"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void setScreenHandler(Screen screen, CallbackInfo ci) {
      System.out.println(screen);
      if (screen instanceof ReceivingLevelScreen || screen instanceof ProgressScreen) {
         ci.cancel();
         this.m_91152_((Screen)null);
      }

   }
}
