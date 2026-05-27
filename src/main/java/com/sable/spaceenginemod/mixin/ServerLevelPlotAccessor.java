package com.sable.spaceenginemod.mixin;

import dev.ryanhcode.sable.sublevel.plot.ServerLevelPlot;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ServerLevelPlot.class, remap = false)
public interface ServerLevelPlotAccessor {
    @Accessor("lightEngine")
    LevelLightEngine spaceengine_tp$getLightEngine();

    @Invoker("newNonLitChunk")
    void spaceengine_tp$invokeNewNonLitChunk(ChunkPos pos);

    @Invoker("logLoadingErrors")
    static void spaceengine_tp$invokeLogLoadingErrors(ChunkPos chunkPos, int y, String errorText) {}

    @Invoker("lightChunk")
    void spaceengine_tp$invokeLightChunk(LevelChunk chunk);
}
