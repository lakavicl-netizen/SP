package com.sable.spaceenginemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.StateShards;
import team.lodestar.lodestone.systems.rendering.shader.ShaderHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds {@link ShaderHolder} instances for the mod's custom shaders and
 * lazy-initialised {@link LodestoneRenderType render types}. Ported from the
 * Forge 1.20.1 version; the underlying Lodestone API surface moved from
 * {@code LodestoneRenderTypeRegistry}+{@code LodestoneShaderRegistry} to
 * {@link LodestoneRenderTypes} and self-registering shaders.
 */
@EventBusSubscriber(value = Dist.CLIENT, modid = SpaceengineS.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ShaderRegistry {

    public static final ShaderHolder SUN_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "sun"), DefaultVertexFormat.POSITION_COLOR);
    public static final ShaderHolder BLACKHOLE_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "blackhole"), DefaultVertexFormat.POSITION_COLOR);
    public static final ShaderHolder PLANET_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "planet"), DefaultVertexFormat.POSITION_TEX_COLOR);
    public static final ShaderHolder PLANET_ATMOSPHERE_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "planet_atmosphere"), DefaultVertexFormat.POSITION_COLOR);
    public static final ShaderHolder PLANET_TEXTURED_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "planet_textured"), DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);
    public static final ShaderHolder PLANET_MASK_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "planet_mask"), DefaultVertexFormat.POSITION_COLOR);
    public static final ShaderHolder PLANET_SHADOW_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "planet_shadow"), DefaultVertexFormat.POSITION_COLOR);
    public static final ShaderHolder WORMHOLE_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "wormhole"), DefaultVertexFormat.POSITION_TEX_COLOR);
    public static final ShaderHolder STAR_GLOW_SHADER = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "star_glow"), DefaultVertexFormat.POSITION_COLOR);

    @SubscribeEvent
    public static void shaderRegistry(RegisterShadersEvent event) {
        SUN_SHADER.register(event);
        BLACKHOLE_SHADER.register(event);
        PLANET_SHADER.register(event);
        PLANET_ATMOSPHERE_SHADER.register(event);
        PLANET_TEXTURED_SHADER.register(event);
        PLANET_MASK_SHADER.register(event);
        PLANET_SHADOW_SHADER.register(event);
        WORMHOLE_SHADER.register(event);
        STAR_GLOW_SHADER.register(event);
    }

    private static LodestoneRenderType SUN_RENDER_TYPE;
    private static LodestoneRenderType BLACKHOLE_RENDER_TYPE;
    private static LodestoneRenderType PLANET_RENDER_TYPE;
    private static LodestoneRenderType PLANET_ATMOSPHERE_RENDER_TYPE;
    private static LodestoneRenderType PLANET_MASK_RENDER_TYPE;
    private static LodestoneRenderType PLANET_SHADOW_RENDER_TYPE;
    private static final ConcurrentHashMap<ResourceLocation, LodestoneRenderType> TEXTURED_PLANET_RENDER_TYPES = new ConcurrentHashMap<>();

    public static LodestoneRenderType getSunRenderType() {
        if (SUN_RENDER_TYPE == null) {
            SUN_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null, "sun_render_type", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, b -> b
                    .setShaderState(SUN_SHADER)
                    .setTransparencyState(StateShards.NORMAL_TRANSPARENCY)
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return SUN_RENDER_TYPE;
    }

    public static LodestoneRenderType getBlackholeRenderType() {
        if (BLACKHOLE_RENDER_TYPE == null) {
            BLACKHOLE_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null, "blackhole_render_type", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, b -> b
                    .setShaderState(BLACKHOLE_SHADER)
                    .setTransparencyState(StateShards.NORMAL_TRANSPARENCY)
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return BLACKHOLE_RENDER_TYPE;
    }

    public static LodestoneRenderType getPlanetRenderType() {
        if (PLANET_RENDER_TYPE == null) {
            PLANET_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null, "planet_render_type", DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS, b -> b
                    .setShaderState(PLANET_SHADER)
                    .setTransparencyState(new RenderStateShard.TransparencyStateShard("no_transparency", RenderSystem::disableBlend, () -> {}))
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, true))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return PLANET_RENDER_TYPE;
    }

    public static LodestoneRenderType getPlanetAtmosphereRenderType() {
        if (PLANET_ATMOSPHERE_RENDER_TYPE == null) {
            PLANET_ATMOSPHERE_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null, "planet_atmosphere_render_type", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, b -> b
                    .setShaderState(PLANET_ATMOSPHERE_SHADER)
                    .setTransparencyState(StateShards.NORMAL_TRANSPARENCY)
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return PLANET_ATMOSPHERE_RENDER_TYPE;
    }

    public static LodestoneRenderType getPlanetMaskRenderType() {
        if (PLANET_MASK_RENDER_TYPE == null) {
            PLANET_MASK_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null, "planet_mask_render_type", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, b -> b
                    .setShaderState(PLANET_MASK_SHADER)
                    .setTransparencyState(new RenderStateShard.TransparencyStateShard("no_transparency", RenderSystem::disableBlend, () -> {}))
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, true))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return PLANET_MASK_RENDER_TYPE;
    }

    public static LodestoneRenderType getPlanetShadowRenderType() {
        if (PLANET_SHADOW_RENDER_TYPE == null) {
            PLANET_SHADOW_RENDER_TYPE = LodestoneRenderTypes.createGenericRenderType(null,
                "planet_shadow_render_type",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.TRIANGLES,
                b -> b
                    .setShaderState(PLANET_SHADOW_SHADER)
                    .setTransparencyState(StateShards.NORMAL_TRANSPARENCY)
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
                    .setCullState(LodestoneRenderTypes.CULL)
            );
        }
        return PLANET_SHADOW_RENDER_TYPE;
    }

    /**
     * Gets a render type for a textured planet.
     *
     * @param textureLocation The texture location (without textures/ prefix or .png extension)
     * @return A render type that uses the textured planet shader with the specified texture
     */
    public static LodestoneRenderType getTexturedPlanetRenderType(ResourceLocation textureLocation) {
        return TEXTURED_PLANET_RENDER_TYPES.computeIfAbsent(textureLocation, loc -> {
            // Build the full texture path
            ResourceLocation fullTexturePath = ResourceLocation.fromNamespaceAndPath(
                loc.getNamespace(),
                "textures/" + loc.getPath() + ".png"
            );

            return LodestoneRenderTypes.createGenericRenderType(null,
                "planet_textured_" + loc.getNamespace() + "_" + loc.getPath().replace("/", "_"),
                DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
                VertexFormat.Mode.QUADS,
                b -> b
                    .setShaderState(PLANET_TEXTURED_SHADER)
                    .setTransparencyState(new RenderStateShard.TransparencyStateShard("no_transparency", RenderSystem::disableBlend, () -> {}))
                    .setDepthTestState(new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, true))
                    .setCullState(LodestoneRenderTypes.CULL)
                    .setTextureState(new RenderStateShard.TextureStateShard(fullTexturePath, false, false))
            );
        });
    }

    /**
     * Clears cached textured planet render types. Call when resources are reloaded.
     */
    public static void clearTexturedPlanetRenderTypes() {
        TEXTURED_PLANET_RENDER_TYPES.clear();
    }
}
