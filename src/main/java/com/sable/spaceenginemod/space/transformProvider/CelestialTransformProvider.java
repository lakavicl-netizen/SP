package com.sable.spaceenginemod.space.transformProvider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaterniondc;
import org.joml.Vector3d;
import com.sable.spaceenginemod.space.Celestial;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows custom position and rotation for celestial bodies.
 * <br>
 * Implementations must be registered via {@link #register(ResourceLocation, MapCodec)} before use.
 *
 * <p>1.21.1 note: the DataFixerUpper dispatch API requires a {@link MapCodec} per
 * dispatch target (so the type field can be inlined into the dispatched record).
 * Built-in providers expose a {@code MAP_CODEC} field; legacy {@code Codec}-only
 * registrations should be wrapped with {@link MapCodec#assumeMapUnsafe(Codec)}.
 */
public interface CelestialTransformProvider {

    Vector3d getPosition(long ticks, float subticks, Registry<Celestial> registry);

    Quaterniondc getRotation(long ticks, float subticks, Registry<Celestial> registry);

    ResourceLocation getType();

    // Registry for CelestialTransformProvider codecs
    Map<ResourceLocation, MapCodec<? extends CelestialTransformProvider>> REGISTRY = new HashMap<>();

    /**
     * Register a CelestialTransformProvider type with its codec.
     *
     * @param type The unique identifier for this provider type
     * @param codec The MapCodec to serialize/deserialize this provider type
     */
    static void register(ResourceLocation type, MapCodec<? extends CelestialTransformProvider> codec) {
        REGISTRY.put(type, codec);
    }

    /** Back-compat overload for callers still passing a flat {@link Codec}. */
    static void register(ResourceLocation type, Codec<? extends CelestialTransformProvider> codec) {
        REGISTRY.put(type, MapCodec.assumeMapUnsafe(codec));
    }

    /**
     * Dispatch codec that handles serialization of any registered CelestialTransformProvider type.
     */
    Codec<CelestialTransformProvider> DISPATCH_CODEC = ResourceLocation.CODEC.dispatchStable(
        CelestialTransformProvider::getType,
        type -> {
            MapCodec<? extends CelestialTransformProvider> codec = REGISTRY.get(type);
            if (codec == null) {
                throw new IllegalArgumentException("Unknown CelestialTransformProvider type: " + type);
            }
            return codec;
        }
    );
}
