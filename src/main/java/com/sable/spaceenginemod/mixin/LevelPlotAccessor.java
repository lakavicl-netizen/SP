package com.sable.spaceenginemod.mixin;

import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.sublevel.plot.LevelPlot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = LevelPlot.class, remap = false)
public interface LevelPlotAccessor {
    @Accessor("container")
    SubLevelContainer spaceengine_tp$getContainer();

    @Accessor("logSize")
    int spaceengine_tp$getLogSize();

    @Accessor("biome")
    ResourceKey<Biome> spaceengine_tp$getBiome();

    @Accessor("biome")
    void spaceengine_tp$setBiome(ResourceKey<Biome> biome);

    @Accessor("expandPlotIfNecessary")
    boolean spaceengine_tp$getExpandPlotIfNecessary();

    @Accessor("expandPlotIfNecessary")
    void spaceengine_tp$setExpandPlotIfNecessary(boolean expand);
}
