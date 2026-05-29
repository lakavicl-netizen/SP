package net.lointain.cosmos.mixins;

import net.lointain.cosmos.LivingEntityTickEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class})
public abstract class LivingEntityTickMixin {
   @Inject(
      method = {"travel"},
      at = {@At("HEAD")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new LivingEntityTickEvent((LivingEntity)this));
   }
}
