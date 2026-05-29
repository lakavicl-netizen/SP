package com.sable.spaceenginemod.worldgen;

import com.mojang.serialization.MapCodec;
import com.sable.spaceenginemod.SpaceengineS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sable.spaceenginemod.SpaceengineS.ASTEROID_RULE_ID;
import static com.sable.spaceenginemod.SpaceengineS.MOD_ID;

/**
 * Worldgen codec registry. Ported from {@code shipwrights.genesis.worldgen.WorldGenRegistry}.
 *
 * <p>In 1.21.1 the {@link Registries#DENSITY_FUNCTION_TYPE} and
 * {@link Registries#MATERIAL_RULE} registries hold {@link MapCodec} values
 * directly (not plain {@code Codec}s), so each {@code KeyDispatchDataCodec}
 * is unwrapped via {@code .codec()} to obtain the underlying {@code MapCodec}.
 */
public final class WorldGenRegistry {

    public static final DeferredRegister<MapCodec<? extends DensityFunction>> DENSITY_FUNCTION_TYPES =
            DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, MOD_ID);

    public static final DeferredRegister<MapCodec<? extends SurfaceRules.RuleSource>> MATERIAL_RULES =
            DeferredRegister.create(Registries.MATERIAL_RULE, MOD_ID);

    public static final DeferredHolder<MapCodec<? extends DensityFunction>, MapCodec<RandomNoise>> RANDOM_NOISE =
            DENSITY_FUNCTION_TYPES.register("random_noise", () -> RandomNoise.MAP_CODEC);

    public static final DeferredHolder<MapCodec<? extends DensityFunction>, MapCodec<AsteroidBelt>> ASTEROID_BELT =
            DENSITY_FUNCTION_TYPES.register("asteroid_belt", () -> AsteroidBelt.MAP_CODEC);

    public static final DeferredHolder<MapCodec<? extends DensityFunction>, MapCodec<RadialGradientDensity>> RADIAL_GRADIENT =
            DENSITY_FUNCTION_TYPES.register("radial_gradient", () -> RadialGradientDensity.MAP_CODEC);

    public static final DeferredHolder<MapCodec<? extends DensityFunction>, MapCodec<CraterNoise>> CRATER =
            DENSITY_FUNCTION_TYPES.register(CraterNoise.resourceLocation.getPath(), () -> CraterNoise.MAP_CODEC);

    public static final DeferredHolder<MapCodec<? extends DensityFunction>, MapCodec<MultiCraterNoise>> MULTI_CRATER =
            DENSITY_FUNCTION_TYPES.register(MultiCraterNoise.resourceLocation.getPath(), () -> MultiCraterNoise.MAP_CODEC);

    public static final DeferredHolder<MapCodec<? extends SurfaceRules.RuleSource>, MapCodec<AsteroidBlockSurfaceRule>> ASTEROID_RULE =
            MATERIAL_RULES.register(ASTEROID_RULE_ID.getPath(), () -> AsteroidBlockSurfaceRule.CODEC.codec());

    private WorldGenRegistry() {}

    public static void init(IEventBus modEventBus) {
        // Sanity check: the resource locations the source code used to register
        // crater types must match this mod's namespace, otherwise registration
        // would silently target the wrong registry path.
        assertNamespace(CraterNoise.resourceLocation);
        assertNamespace(MultiCraterNoise.resourceLocation);
        assertNamespace(ASTEROID_RULE_ID);

        DENSITY_FUNCTION_TYPES.register(modEventBus);
        MATERIAL_RULES.register(modEventBus);
    }

    private static void assertNamespace(ResourceLocation id) {
        if (!id.getNamespace().equals(SpaceengineS.MODID)) {
            throw new IllegalStateException(
                    "Worldgen id " + id + " is not in the " + SpaceengineS.MODID + " namespace");
        }
    }
}
