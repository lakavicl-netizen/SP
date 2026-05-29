package net.lointain.cosmos.mixins;

import net.lointain.cosmos.ThrownExperienceBottleTickEvent;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ThrownExperienceBottle.class})
public abstract class ThrownExperienceBottleTickMixin {
   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new ThrownExperienceBottleTickEvent((ThrownExperienceBottle)this));
   }
}
