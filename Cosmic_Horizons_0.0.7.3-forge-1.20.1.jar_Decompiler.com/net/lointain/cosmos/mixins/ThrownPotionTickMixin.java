package net.lointain.cosmos.mixins;

import net.lointain.cosmos.ThrownPotionTickEvent;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ThrownPotion.class})
public abstract class ThrownPotionTickMixin {
   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new ThrownPotionTickEvent((ThrownPotion)this));
   }
}
