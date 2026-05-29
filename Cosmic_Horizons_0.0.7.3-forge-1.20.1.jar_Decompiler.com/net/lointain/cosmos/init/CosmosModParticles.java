package net.lointain.cosmos.init;

import net.lointain.cosmos.client.particle.AirThrustParticle;
import net.lointain.cosmos.client.particle.BlueThrustedsmallParticle;
import net.lointain.cosmos.client.particle.BluethrustedParticle;
import net.lointain.cosmos.client.particle.BluethrustedlargeParticle;
import net.lointain.cosmos.client.particle.DarkThrustParticle;
import net.lointain.cosmos.client.particle.MoonDustParticle;
import net.lointain.cosmos.client.particle.NickleDustParticle;
import net.lointain.cosmos.client.particle.OpaqueSmokeParticle;
import net.lointain.cosmos.client.particle.PlumeParticle;
import net.lointain.cosmos.client.particle.RedblastParticle;
import net.lointain.cosmos.client.particle.SulphuricdripParticle;
import net.lointain.cosmos.client.particle.SulphurinParticle;
import net.lointain.cosmos.client.particle.ThrsutsmokesmallParticle;
import net.lointain.cosmos.client.particle.ThrustSmokeParticle;
import net.lointain.cosmos.client.particle.ThrustSmokelargeParticle;
import net.lointain.cosmos.client.particle.ThrustedParticle;
import net.lointain.cosmos.client.particle.ThrustedlargeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class CosmosModParticles {
   @SubscribeEvent
   public static void registerParticles(RegisterParticleProvidersEvent event) {
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.MOON_DUST.get(), MoonDustParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.SULPHURIN.get(), SulphurinParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.THRUSTED.get(), ThrustedParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.THRUST_SMOKE.get(), ThrustSmokeParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.THRUSTEDLARGE.get(), ThrustedlargeParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.THRUST_SMOKELARGE.get(), ThrustSmokelargeParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.BLUETHRUSTED.get(), BluethrustedParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.BLUETHRUSTEDLARGE.get(), BluethrustedlargeParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.BLUE_THRUSTEDSMALL.get(), BlueThrustedsmallParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.THRSUTSMOKESMALL.get(), ThrsutsmokesmallParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.DARK_THRUST.get(), DarkThrustParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.NICKLE_DUST.get(), NickleDustParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.SULPHURICDRIP.get(), SulphuricdripParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.AIR_THRUST.get(), AirThrustParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.REDBLAST.get(), RedblastParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.OPAQUE_SMOKE.get(), OpaqueSmokeParticle::provider);
      event.registerSpriteSet((ParticleType)CosmosModParticleTypes.PLUME.get(), PlumeParticle::provider);
   }
}
