package net.lointain.cosmos.mixins;

import net.lointain.cosmos.ArrowTickEvent;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({AbstractArrow.class})
public abstract class ArrowTickMixin {
   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new ArrowTickEvent((AbstractArrow)this));
   }
}
