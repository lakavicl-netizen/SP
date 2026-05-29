package com.sable.spaceenginemod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.networking.SpaceNetworking;
import com.sable.spaceenginemod.networking.SyncTimeOffsetPacket;
import com.sable.spaceenginemod.time.SpaceTimeData;

/**
 * Server-side time + snow tweaks, ported from genesis. The Valkyrien Skies
 * ship-position branch in {@code genesis$shouldSnow} is intentionally dropped
 * (VS is not a dependency in space_engine_s).
 */
@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Shadow public abstract ServerLevel getLevel();

    @Inject(method = "setDayTime", at = @At("HEAD"))
    private void genesis$onSetDayTime(long newDayTime, CallbackInfo ci) {
        ServerLevel self = getLevel();
        if (!self.dimension().equals(Level.OVERWORLD)) return;

        long oldDayTime = SpaceengineS.getTicks(self);
        long delta = newDayTime - oldDayTime;
        if (delta == 0) return;

        SpaceTimeData data = SpaceTimeData.getOrCreate(self.getServer());
        data.addOffset(delta);
        SpaceNetworking.sendToAll(new SyncTimeOffsetPacket(data.getTimeOffset()));
    }

    // NOTE: The genesis 1.20.1 mixin wrapped `ServerLevel.tick(...)`'s call to
    // `this.getDayTime()` so it would use our time-offset-aware reading. In
    // 1.21.1 the `tick` body no longer calls `getDayTime()` directly — it goes
    // through `this.levelData.getDayTime()` (see 1.21.X ServerLevel.tick:353).
    // The wrap is therefore unnecessary in 1.21.1; time offset is still
    // applied via setDayTime delta + SpaceTimeData on the read side.

    @Inject(method = "addEntity", at = @At("HEAD"))
    private void addEntityMixin(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // refreshEntityScaling is now a no-op (player shrinking dropped); kept
        // for parity with the genesis call site, in case future scaling work
        // re-introduces it.
        SpaceengineS.refreshEntityScaling(entity, getLevel());
    }

    @Inject(method = "addPlayer", at = @At("HEAD"))
    private void addPlayerMixin(ServerPlayer arg, CallbackInfo ci) {
        SpaceengineS.refreshEntityScaling(arg, getLevel());
    }

    @WrapOperation(method = "tickPrecipitation", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean genesis$shouldSnow(Biome instance, LevelReader levelReader, BlockPos pos, Operation<Boolean> original) {
        ServerLevel level = getLevel();
        if (SpaceengineS.shouldCancelVoidDamage(level)) {
            return false;
        }
        return original.call(instance, level, pos);
    }
}
