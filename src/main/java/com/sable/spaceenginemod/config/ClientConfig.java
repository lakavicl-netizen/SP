package com.sable.spaceenginemod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ClientConfig {

    private static final ModConfigSpec.BooleanValue spaceShaderEnable;
    private static final boolean defaultSpaceShaderEnable = true;

    private static final ModConfigSpec.BooleanValue renderCurrentPlanet;
    private static final boolean defaultRenderCurrentPlanet = true;

    public static final ModConfigSpec CONFIG_SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        spaceShaderEnable = builder.define("EnableDynamicSpaceLighting", defaultSpaceShaderEnable);
        renderCurrentPlanet = builder.define("ShouldRenderCurrentPlanet", defaultRenderCurrentPlanet);
        CONFIG_SPEC = builder.build();
    }

    private ClientConfig() {}

    public static boolean enableSpaceLighting() {
        try {
            return spaceShaderEnable.get();
        } catch (Exception ignored) {
            return defaultSpaceShaderEnable;
        }
    }

    public static boolean shouldRenderCurrentPlanet() {
        try {
            return renderCurrentPlanet.get();
        } catch (Exception ignored) {
            return defaultRenderCurrentPlanet;
        }
    }
}
