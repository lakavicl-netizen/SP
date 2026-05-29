package com.sable.spaceenginemod.client;

import com.mojang.blaze3d.shaders.Uniform;
import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.space.Celestial;
import com.sable.spaceenginemod.space.SpaceLevel;
import com.sable.spaceenginemod.space.type.BuiltinCelestialTypes;

import com.sable.spaceenginemod.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import team.lodestar.lodestone.systems.postprocess.PostProcessor;

import java.util.function.Predicate;

public class SpaceInvertPostProcessor extends PostProcessor {
    public static final SpaceInvertPostProcessor INSTANCE = new SpaceInvertPostProcessor();

    @Override
    public ResourceLocation getPostChainLocation() {
        return ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, "space_invert");
    }

    @Override
    public void init() {
        super.init();
        // Register custom samplers with the effect instances
        if (effects != null) {
            for (EffectInstance effect : effects) {
                effect.setSampler("PlanetMaskSampler", PlanetMaskTarget::getColorTextureId);
                effect.setSampler("PlanetDepthSampler", PlanetMaskTarget::getDepthTextureId);
            }
        }
    }

    @Override
    public void beforeProcess(Matrix4f viewModelMatrix) {
        if (effects == null) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        LocalPlayer localPlayer = mc.player;
        if (level == null || localPlayer == null) {
            return;
        }

        long ticks = SpaceengineS.getTicks(level);
        float partialTick = mc.getTimer().getGameTimeDeltaPartialTick(false);
        Vec3 camPos = localPlayer.getPosition(partialTick);
        Vector3d camPosJoml = new Vector3d(camPos.x, camPos.y, camPos.z);

        Registry<Celestial> registry = SpaceengineS.getCelestialRegistry(level);
        Vector3dc starPos = null;
        Pair<Celestial, Double> result = SpaceLevel.nearestCelestialWhere(
                registry,
                camPosJoml,
                ticks,
                partialTick,
                Predicate.isEqual(BuiltinCelestialTypes.STAR)
        );
        if (result != null) {
            starPos = result.getFirst().getPosition(ticks, partialTick, registry);
        }

        float camX = (float) camPos.x;
        float camY = (float) camPos.y;
        float camZ = (float) camPos.z;
        float starX = starPos != null ? (float) starPos.x() : 0.0f;
        float starY = starPos != null ? (float) starPos.y() : 0.0f;
        float starZ = starPos != null ? (float) starPos.z() : 0.0f;

        for (EffectInstance effect : effects) {
            Uniform cameraUniform = effect.getUniform("cameraPos");
            if (cameraUniform != null) {
                cameraUniform.set(camX, camY, camZ);
            }
            Uniform uniform = effect.getUniform("starPos");
            if (uniform != null) {
                uniform.set(starX, starY, starZ);
            }
        }
    }

    @Override
    public void afterProcess() {
        // Samplers are handled automatically by Minecraft's EffectInstance
    }
}
