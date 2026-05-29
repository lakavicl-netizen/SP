package net.lointain.cosmos.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CosmosModParticleTypes {
   public static final DeferredRegister<ParticleType<?>> REGISTRY;
   public static final RegistryObject<SimpleParticleType> MOON_DUST;
   public static final RegistryObject<SimpleParticleType> SULPHURIN;
   public static final RegistryObject<SimpleParticleType> THRUSTED;
   public static final RegistryObject<SimpleParticleType> THRUST_SMOKE;
   public static final RegistryObject<SimpleParticleType> THRUSTEDLARGE;
   public static final RegistryObject<SimpleParticleType> THRUST_SMOKELARGE;
   public static final RegistryObject<SimpleParticleType> BLUETHRUSTED;
   public static final RegistryObject<SimpleParticleType> BLUETHRUSTEDLARGE;
   public static final RegistryObject<SimpleParticleType> BLUE_THRUSTEDSMALL;
   public static final RegistryObject<SimpleParticleType> THRSUTSMOKESMALL;
   public static final RegistryObject<SimpleParticleType> DARK_THRUST;
   public static final RegistryObject<SimpleParticleType> NICKLE_DUST;
   public static final RegistryObject<SimpleParticleType> SULPHURICDRIP;
   public static final RegistryObject<SimpleParticleType> AIR_THRUST;
   public static final RegistryObject<SimpleParticleType> REDBLAST;
   public static final RegistryObject<SimpleParticleType> OPAQUE_SMOKE;
   public static final RegistryObject<SimpleParticleType> PLUME;

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "cosmos");
      MOON_DUST = REGISTRY.register("moon_dust", () -> new SimpleParticleType(true));
      SULPHURIN = REGISTRY.register("sulphurin", () -> new SimpleParticleType(true));
      THRUSTED = REGISTRY.register("thrusted", () -> new SimpleParticleType(true));
      THRUST_SMOKE = REGISTRY.register("thrust_smoke", () -> new SimpleParticleType(true));
      THRUSTEDLARGE = REGISTRY.register("thrustedlarge", () -> new SimpleParticleType(true));
      THRUST_SMOKELARGE = REGISTRY.register("thrust_smokelarge", () -> new SimpleParticleType(true));
      BLUETHRUSTED = REGISTRY.register("bluethrusted", () -> new SimpleParticleType(true));
      BLUETHRUSTEDLARGE = REGISTRY.register("bluethrustedlarge", () -> new SimpleParticleType(true));
      BLUE_THRUSTEDSMALL = REGISTRY.register("blue_thrustedsmall", () -> new SimpleParticleType(true));
      THRSUTSMOKESMALL = REGISTRY.register("thrsutsmokesmall", () -> new SimpleParticleType(true));
      DARK_THRUST = REGISTRY.register("dark_thrust", () -> new SimpleParticleType(true));
      NICKLE_DUST = REGISTRY.register("nickle_dust", () -> new SimpleParticleType(true));
      SULPHURICDRIP = REGISTRY.register("sulphuricdrip", () -> new SimpleParticleType(false));
      AIR_THRUST = REGISTRY.register("air_thrust", () -> new SimpleParticleType(true));
      REDBLAST = REGISTRY.register("redblast", () -> new SimpleParticleType(true));
      OPAQUE_SMOKE = REGISTRY.register("opaque_smoke", () -> new SimpleParticleType(true));
      PLUME = REGISTRY.register("plume", () -> new SimpleParticleType(true));
   }
}
