package com.sable.spaceenginemod.space.renderer;

import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.jetbrains.annotations.NotNull;
import com.sable.spaceenginemod.space.Celestial;
import com.sable.spaceenginemod.space.VantagePoint;

public interface CelestialRenderer {
    void invoke(@NotNull RenderLevelStageEvent event, @NotNull Celestial toRender, @NotNull VantagePoint vantagePoint);

    default void setup(@NotNull RenderLevelStageEvent event, @NotNull VantagePoint vantagePoint) {}

    default void teardown(@NotNull RenderLevelStageEvent event, @NotNull VantagePoint vantagePoint) {}
}
