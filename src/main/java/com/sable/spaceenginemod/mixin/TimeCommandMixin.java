package com.sable.spaceenginemod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.sable.spaceenginemod.SpaceengineS;

@Mixin(TimeCommand.class)
public class TimeCommandMixin {

    @WrapMethod(method = "getDayTime")
    private static int getDayTimeWrap(ServerLevel level, Operation<Integer> original) {
        return Math.toIntExact(Math.min(Integer.MAX_VALUE, SpaceengineS.getTicks(level)));
    }

    @WrapOperation(method = "addTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getDayTime()J"))
    private static long fixAddTime(ServerLevel instance, Operation<Long> original) {
        return SpaceengineS.getTicks(instance);
    }
}
