package com.sable.spaceenginemod.content.particle;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class SpaceParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SpaceengineS.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ZAP_BUBBLE_PARTICLES =
            PARTICLE_TYPES.register("zap_bubble_particles", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VERDITE_PARTICLES =
            PARTICLE_TYPES.register("verdite_particles", () -> new SimpleParticleType(true));

    private SpaceParticles() {}

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
