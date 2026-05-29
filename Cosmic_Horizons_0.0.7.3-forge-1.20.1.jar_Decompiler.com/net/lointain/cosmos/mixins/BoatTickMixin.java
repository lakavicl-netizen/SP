package net.lointain.cosmos.mixins;

import net.lointain.cosmos.BoatTickEvent;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Boat.class})
public abstract class BoatTickMixin {
   @Inject(
      method = {"floatBoat"},
      at = {@At("TAIL")}
   )
   private void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new BoatTickEvent((Boat)this));
   }
}
