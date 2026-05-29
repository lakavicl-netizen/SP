package net.lointain.cosmos.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CosmosModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY;
   public static final RegistryObject<SoundEvent> COSMOS;
   public static final RegistryObject<SoundEvent> THRUST;
   public static final RegistryObject<SoundEvent> LASER;

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "cosmos");
      COSMOS = REGISTRY.register("cosmos", () -> SoundEvent.m_262824_(new ResourceLocation("cosmos", "cosmos")));
      THRUST = REGISTRY.register("thrust", () -> SoundEvent.m_262824_(new ResourceLocation("cosmos", "thrust")));
      LASER = REGISTRY.register("laser", () -> SoundEvent.m_262824_(new ResourceLocation("cosmos", "laser")));
   }
}
