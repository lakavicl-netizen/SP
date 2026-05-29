package com.sable.spaceenginemod.commands;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * Registers our brigadier argument types with the
 * {@link Registries#COMMAND_ARGUMENT_TYPE} registry.
 *
 * <p>Ported from {@code shipwrights.genesis.commands.GenesisCommandArguments}.
 * On NeoForge 1.21.1 the deferred-register API moved to
 * {@code net.neoforged.neoforge.registries.DeferredRegister} and exposes its
 * own {@code DeferredHolder} suppliers instead of Forge's {@code RegistryObject}.
 */
public final class SpaceCommandArguments {
    private SpaceCommandArguments() {}

    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES =
            DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, SpaceengineS.MODID);

    public static final Supplier<ArgumentTypeInfo<?, ?>> CELESTIAL =
            ARGUMENT_TYPES.register("celestial", () -> SingletonArgumentInfo.contextFree(CelestialArgument::celestial));

    public static void register(IEventBus bus) {
        ARGUMENT_TYPES.register(bus);
    }
}
