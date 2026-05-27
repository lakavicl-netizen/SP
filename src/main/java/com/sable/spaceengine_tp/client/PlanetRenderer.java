package com.sable.spaceengine_tp.client;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

@EventBusSubscriber(value = Dist.CLIENT)
public class PlanetRenderer {
    private static final Vec3 PLANET_POS = new Vec3(0, 128, 0);
    private static final float HALF_SIZE_SPACE = 96.0f;
    private static final float HALF_SIZE_OVERWORLD = 24.0f;
    private static final ResourceLocation OVERWORLD_PLANET_TEXTURE = ResourceLocation.fromNamespaceAndPath("space_engine_s", "textures/planets/minecraft/overworld.png");

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        Camera camera = event.getCamera();
        if (level == null) return;

        Vec3 renderPos;
        float halfSize;
        if (level.dimension() == Level.OVERWORLD) {
            renderPos = new Vec3(camera.getPosition().x, camera.getPosition().y + 180, camera.getPosition().z);
            halfSize = HALF_SIZE_OVERWORLD;
        } else {
            renderPos = PLANET_POS;
            halfSize = HALF_SIZE_SPACE;
        }

        ShaderInstance shader = PlanetShaderRegistration.getPlanetShader();
        if (shader != null) {
            shader.safeGetUniform("LightDirection").set(0.4f, -0.2f, -0.9f);
            shader.safeGetUniform("SkyColor").set(0.01f, 0.01f, 0.03f);
        }

        Matrix4f matrix = new Matrix4f(event.getPoseStack().last().pose());
        Vec3 camPos = camera.getPosition();
        matrix.translate((float) (renderPos.x - camPos.x), (float) (renderPos.y - camPos.y), (float) (renderPos.z - camPos.z));

        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer consumer = bufferSource.getBuffer(PlanetShaderRegistration.planetRenderType(OVERWORLD_PLANET_TEXTURE));

        renderTexturedCube(consumer, matrix, halfSize);
        bufferSource.endBatch();
    }

    private static void renderTexturedCube(VertexConsumer buffer, Matrix4f matrix, float r) {
        float t = 1.0f / 3.0f;
        float t2 = 2.0f / 3.0f;

        addFace(buffer, matrix, -r,-r,-r, r,-r,-r, r, r,-r, -r, r,-r, 0f,0f, t,0.5f, 0,0,-1); // north
        addFace(buffer, matrix, -r,-r, r, r,-r, r, r, r, r, -r, r, r, t2,0f, 1f,0.5f, 0,0,1); // south
        addFace(buffer, matrix, -r,-r,-r, -r,-r, r, -r, r, r, -r, r,-r, t,0f, t2,0.5f, -1,0,0); // west
        addFace(buffer, matrix, r,-r, r, r,-r,-r, r, r,-r, r, r, r, 0f,0.5f, t,1f, 1,0,0); // east
        addFace(buffer, matrix, -r, r, r, r, r, r, r, r,-r, -r, r,-r, t2,0.5f, 1f,1f, 0,1,0); // up
        addFace(buffer, matrix, -r,-r,-r, r,-r,-r, r,-r, r, -r,-r, r, t,0.5f, t2,1f, 0,-1,0); // down
    }

    private static void addFace(VertexConsumer b, Matrix4f m, float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,float x4,float y4,float z4,float u1,float v1,float u2,float v2,float nx,float ny,float nz) {
        addVertex(b, m, x1,y1,z1, u1,v2, nx,ny,nz);
        addVertex(b, m, x2,y2,z2, u2,v2, nx,ny,nz);
        addVertex(b, m, x3,y3,z3, u2,v1, nx,ny,nz);
        addVertex(b, m, x4,y4,z4, u1,v1, nx,ny,nz);
    }

    private static void addVertex(VertexConsumer b, Matrix4f m, float x,float y,float z,float u,float v,float nx,float ny,float nz) {
        b.addVertex(m, x, y, z).setUv(u, v).setColor(255, 255, 255, 255).setNormal(nx, ny, nz);
    }
}
