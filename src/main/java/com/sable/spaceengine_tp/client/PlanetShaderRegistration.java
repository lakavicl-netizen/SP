package com.sable.spaceengine_tp.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

public class PlanetShaderRegistration {
    private static ShaderInstance planetShader;

    public static ShaderInstance getPlanetShader() {
        return planetShader;
    }

    public static RenderType planetRenderType(ResourceLocation texture) {
        return RenderType.create(
            "planet_textured",
            DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL,
            VertexFormat.Mode.QUADS,
            256,
            RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(PlanetShaderRegistration::getPlanetShader))
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.NO_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.NO_LIGHTMAP)
                .setOverlayState(RenderStateShard.NO_OVERLAY)
                .createCompositeState(false)
        );
    }

    public static void onRegisterShaders(RegisterShadersEvent event) {
        try {
            event.registerShader(
                new ShaderInstance(
                    event.getResourceProvider(),
                    ResourceLocation.fromNamespaceAndPath("space_engine_s", "rendertype_planet"),
                    DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL
                ),
                shader -> planetShader = shader
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to register planet shader", e);
        }
    }
}
