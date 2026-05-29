package com.sable.spaceenginemod.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.sable.spaceenginemod.SpaceengineS;

/**
 * Cancels void damage in the space and wormhole dimensions. Players that
 * fall off the bottom of the world there should be allowed to drift, not
 * be killed by the vanilla void-damage handler.
 *
 * <p>Ported from genesis 1.20.1. The mini-scale entity-shrink path is
 * intentionally NOT carried over.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void hurtMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source == this.damageSources().fellOutOfWorld()) {
            if (SpaceengineS.shouldCancelVoidDamage(this.level())) {
                cir.setReturnValue(false);
            }
        }
    }
}
