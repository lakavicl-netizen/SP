package com.sable.spaceenginemod.content.sound;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class SpaceSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SpaceengineS.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_ENGINE_START =
            register("void_engine_start");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_ENGINE_TRAVEL =
            register("void_engine_travel");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_ENGINE_STOP =
            register("void_engine_stop");
    public static final DeferredHolder<SoundEvent, SoundEvent> WORMHOLE_AMBIANCE =
            register("wormhole_ambiance");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIASMA_HISS =
            register("miasma_hiss");
    public static final DeferredHolder<SoundEvent, SoundEvent> VOID_CORE_ORE_SOUND =
            register("void_core_ore_sound");

    private SpaceSounds() {}

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(
                        ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, name)));
    }
}
