package com.sable.spaceenginemod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

/**
 * Fades out a biome's ambient particle spawns as the camera climbs above
 * the atmosphere (linearly between {@code y=300} and {@code y=360}).
 *
 * <p>Ported verbatim from genesis 1.20.1. The targeted ClientLevel
 * call ({@code doAnimateTick} -> {@code Biome#getAmbientParticle()}) is
 * unchanged in 1.21.1.
 */
@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {

    @Unique
    public final RandomSource genesis$random = RandomSource.create();

    @ModifyExpressionValue(
            method = "doAnimateTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/biome/Biome;getAmbientParticle()Ljava/util/Optional;"
            )
    )
    private Optional<AmbientParticleSettings> genesis$fadeBiomeParticles(
            Optional<AmbientParticleSettings> original
    ) {
        if (original.isEmpty()) {
            return original;
        }
        double camY = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().y;
        double densityFade = 1.0 - Mth.clamp((camY - 300.0) / 60.0, 0.0, 1.0);

        if (densityFade >= 1.0) {
            return original;
        }

        return this.genesis$random.nextFloat() < densityFade ? original : Optional.empty();
    }
}
