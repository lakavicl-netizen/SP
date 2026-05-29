package com.sable.spaceenginemod.space.type;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import com.sable.spaceenginemod.space.properties.CelestialProperties;
import com.sable.spaceenginemod.space.properties.EmptyProperties;
import com.sable.spaceenginemod.space.properties.PlanetProperties;
import com.sable.spaceenginemod.space.renderer.CelestialRenderer;
import com.sable.spaceenginemod.space.renderer.PlanetRenderer;
import com.sable.spaceenginemod.space.renderer.StarRenderer;
import com.sable.spaceenginemod.space.renderer.BlackholeRenderer;
import com.sable.spaceenginemod.space.properties.StarProperties;

public class BuiltinCelestialTypes {

    public static CelestialType STAR = new CelestialType() {
        public boolean castsLight() { return true; }
        public boolean castsShadow() { return false; }
        public boolean isVisitable() { return false; }

        private static CelestialRenderer renderer = null;
        public @NotNull CelestialRenderer getRenderer() {
            if (renderer == null) renderer = new StarRenderer();
            return renderer;
        }

        public @NotNull ResourceLocation getID() {
            return ResourceLocation.parse("space_engine_s:star");
        }

        public @NotNull Codec<? extends CelestialProperties> propertiesCodec() {
            return StarProperties.CODEC;
        }
    };

    public static CelestialType BODY = new CelestialType() {
        public boolean castsLight() { return false; }
        public boolean castsShadow() { return true; }
        public boolean isVisitable() { return true; }

        private static CelestialRenderer renderer = null;
        public @NotNull CelestialRenderer getRenderer() {
            if (renderer == null) renderer = new PlanetRenderer();
            return renderer;
        }

        public @NotNull ResourceLocation getID() {
            return ResourceLocation.parse("space_engine_s:body");
        }

        public @NotNull Codec<? extends CelestialProperties> propertiesCodec() {
            return PlanetProperties.CODEC;
        }
    };

    public static CelestialType BLACKHOLE = new CelestialType() {
        public boolean castsLight() { return false; }
        public boolean castsShadow() { return false; }
        public boolean isVisitable() { return false; }

        private static CelestialRenderer renderer = null;
        public @NotNull CelestialRenderer getRenderer() {
            if (renderer == null) renderer = new BlackholeRenderer();
            return renderer;
        }

        public @NotNull ResourceLocation getID() {
            return ResourceLocation.parse("space_engine_s:blackhole");
        }

        public @NotNull Codec<? extends CelestialProperties> propertiesCodec() {
            return EmptyProperties.CODEC;
        }
    };

    public static void register() {
        CelestialType.register(STAR);
        CelestialType.register(BODY);
        CelestialType.register(BLACKHOLE);
    }
}
