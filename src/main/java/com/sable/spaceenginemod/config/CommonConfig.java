package com.sable.spaceenginemod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class CommonConfig {

    private static final ModConfigSpec.IntValue atmosphereExitHeight;
    private static final ModConfigSpec.IntValue atmosphereEntryHeight;

    public static final ModConfigSpec CONFIG_SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        atmosphereExitHeight = builder.defineInRange("atmosphereExitHeight", 2048, Integer.MIN_VALUE, Integer.MAX_VALUE);
        atmosphereEntryHeight = builder.defineInRange("atmosphereEntryHeight", 1440, Integer.MIN_VALUE, Integer.MAX_VALUE);
        CONFIG_SPEC = builder.build();
    }

    private CommonConfig() {}

    public static int getAtmosphereExitHeight() {
        return atmosphereExitHeight.get();
    }

    public static int getAtmosphereEntryHeight() {
        return atmosphereEntryHeight.get();
    }
}
