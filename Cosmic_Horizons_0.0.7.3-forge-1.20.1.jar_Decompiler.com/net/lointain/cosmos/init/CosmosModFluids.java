package net.lointain.cosmos.init;

import net.lointain.cosmos.fluid.CrudeOilFluid;
import net.lointain.cosmos.fluid.SulphuricAcidFluid;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CosmosModFluids {
   public static final DeferredRegister<Fluid> REGISTRY;
   public static final RegistryObject<FlowingFluid> SULPHURIC_ACID;
   public static final RegistryObject<FlowingFluid> FLOWING_SULPHURIC_ACID;
   public static final RegistryObject<FlowingFluid> CRUDE_OIL;
   public static final RegistryObject<FlowingFluid> FLOWING_CRUDE_OIL;

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, "cosmos");
      SULPHURIC_ACID = REGISTRY.register("sulphuric_acid", () -> new SulphuricAcidFluid.Source());
      FLOWING_SULPHURIC_ACID = REGISTRY.register("flowing_sulphuric_acid", () -> new SulphuricAcidFluid.Flowing());
      CRUDE_OIL = REGISTRY.register("crude_oil", () -> new CrudeOilFluid.Source());
      FLOWING_CRUDE_OIL = REGISTRY.register("flowing_crude_oil", () -> new CrudeOilFluid.Flowing());
   }

   @EventBusSubscriber(
      bus = Bus.MOD,
      value = {Dist.CLIENT}
   )
   public static class FluidsClientSideHandler {
      @SubscribeEvent
      public static void clientSetup(FMLClientSetupEvent event) {
         ItemBlockRenderTypes.setRenderLayer((Fluid)CosmosModFluids.SULPHURIC_ACID.get(), RenderType.m_110466_());
         ItemBlockRenderTypes.setRenderLayer((Fluid)CosmosModFluids.FLOWING_SULPHURIC_ACID.get(), RenderType.m_110466_());
         ItemBlockRenderTypes.setRenderLayer((Fluid)CosmosModFluids.CRUDE_OIL.get(), RenderType.m_110466_());
         ItemBlockRenderTypes.setRenderLayer((Fluid)CosmosModFluids.FLOWING_CRUDE_OIL.get(), RenderType.m_110466_());
      }
   }
}
