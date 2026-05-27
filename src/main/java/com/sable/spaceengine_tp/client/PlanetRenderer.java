package com.sable.spaceengine_tp.client;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@EventBusSubscriber(value = Dist.CLIENT)
public class PlanetRenderer {
    private static final Vec3 PLANET_POS = new Vec3(0, 128, 0);
    private static final float HALF_SIZE = 10.0f;

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        Camera camera = event.getCamera();
        if (level == null) return;

        Vec3 renderPos;
        if (level.dimension() == Level.OVERWORLD) {
            renderPos = new Vec3(camera.getPosition().x, camera.getPosition().y + 200, camera.getPosition().z);
        } else {
            renderPos = PLANET_POS;
        }

        ShaderInstance shader = PlanetShaderRegistration.getPlanetShader();
        if (shader != null) {
            shader.safeGetUniform("LightDirection").set(0.5f, -0.3f, -0.8f);
            shader.safeGetUniform("SkyColor").set(0.0f, 0.0f, 0.0f);
        }

        // Copy matrix and translate to world position (genesis approach)
        Matrix4f matrix = new Matrix4f(event.getPoseStack().last().pose());
        Vec3 camPos = camera.getPosition();
        matrix.translate((float)(renderPos.x - camPos.x), (float)(renderPos.y - camPos.y), (float)(renderPos.z - camPos.z));

        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer consumer = bufferSource.getBuffer(PlanetShaderRegistration.PLANET_RENDER_TYPE);

        float r = HALF_SIZE;
        addCubeFace(consumer, matrix, r, 1, 0, 0);
        addCubeFace(consumer, matrix, r, -1, 0, 0);
        addCubeFace(consumer, matrix, r, 0, 1, 0);
        addCubeFace(consumer, matrix, r, 0, -1, 0);
        addCubeFace(consumer, matrix, r, 0, 0, 1);
        addCubeFace(consumer, matrix, r, 0, 0, -1);

        bufferSource.endBatch(PlanetShaderRegistration.PLANET_RENDER_TYPE);
    }

    private static void addCubeFace(VertexConsumer consumer, Matrix4f matrix, float r, int nx, int ny, int nz) {
        Vector3f right = new Vector3f();
        Vector3f up = new Vector3f();
        computeBasis(nx, ny, nz, right, up);

        Vector3f[] verts = new Vector3f[4];
        verts[0] = new Vector3f(right).mul(r).add(new Vector3f(up).mul(r));
        verts[1] = new Vector3f(right).mul(r).sub(new Vector3f(up).mul(r));
        verts[2] = new Vector3f(right).mul(-r).sub(new Vector3f(up).mul(r));
        verts[3] = new Vector3f(right).mul(-r).add(new Vector3f(up).mul(r));

        for (Vector3f v : verts) {
            float sx = v.x() + nx * r;
            float sy = v.y() + ny * r;
            float sz = v.z() + nz * r;
            float nxNorm = sx / r;
            float nyNorm = sy / r;
            float nzNorm = sz / r;
            consumer.addVertex(matrix, sx, sy, sz).setColor(0.0f, 0.6f, 0.0f, 1.0f).setNormal(nxNorm, nyNorm, nzNorm);
        }
    }

    private static void computeBasis(int nx, int ny, int nz, Vector3f right, Vector3f up) {
        Vector3f normal = new Vector3f(nx, ny, nz);
        if (Math.abs(nx) > 0.5f) {
            right.set(0, 0, -1).cross(normal).normalize();
        } else {
            right.set(1, 0, 0).cross(normal).normalize();
        }
        normal.cross(right, up).normalize();
    }
}
