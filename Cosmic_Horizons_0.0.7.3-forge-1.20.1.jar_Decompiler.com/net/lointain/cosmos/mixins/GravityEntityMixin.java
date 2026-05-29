package net.lointain.cosmos.mixins;

import net.lointain.cosmos.MinecartTntItemEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({AbstractMinecart.class, ItemEntity.class, PrimedTnt.class})
public abstract class GravityEntityMixin extends Entity {
   public GravityEntityMixin(EntityType<?> type, Level level) {
      super(type, level);
   }

   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   public void tick(CallbackInfo ci) {
      MinecraftForge.EVENT_BUS.post(new MinecartTntItemEvent(this));
   }
}
