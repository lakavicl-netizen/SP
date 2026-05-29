package net.lointain.cosmos.init;

import net.lointain.cosmos.potion.CavitationedMobEffect;
import net.lointain.cosmos.potion.OxygendeprivedMobEffect;
import net.lointain.cosmos.potion.SulphuricSensationMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CosmosModMobEffects {
   public static final DeferredRegister<MobEffect> REGISTRY;
   public static final RegistryObject<MobEffect> CAVITATIONED;
   public static final RegistryObject<MobEffect> OXYGENDEPRIVED;
   public static final RegistryObject<MobEffect> SULPHURIC_SENSATION;

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "cosmos");
      CAVITATIONED = REGISTRY.register("cavitationed", () -> new CavitationedMobEffect());
      OXYGENDEPRIVED = REGISTRY.register("oxygendeprived", () -> new OxygendeprivedMobEffect());
      SULPHURIC_SENSATION = REGISTRY.register("sulphuric_sensation", () -> new SulphuricSensationMobEffect());
   }
}
