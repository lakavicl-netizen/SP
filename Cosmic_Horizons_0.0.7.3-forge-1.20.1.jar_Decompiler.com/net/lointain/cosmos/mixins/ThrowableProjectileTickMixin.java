package net.lointain.cosmos.mixins;

import net.lointain.cosmos.ThrowableProjectileTickEvent;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ThrowableProjectile.class})
public abstract class ThrowableProjectileTickMixin {
   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new ThrowableProjectileTickEvent((ThrowableProjectile)this));
   }
}
