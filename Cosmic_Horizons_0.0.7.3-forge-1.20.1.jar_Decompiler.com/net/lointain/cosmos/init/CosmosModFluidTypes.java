package net.lointain.cosmos.init;

import net.lointain.cosmos.fluid.types.CrudeOilFluidType;
import net.lointain.cosmos.fluid.types.SulphuricAcidFluidType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries.Keys;

public class CosmosModFluidTypes {
   public static final DeferredRegister<FluidType> REGISTRY;
   public static final RegistryObject<FluidType> SULPHURIC_ACID_TYPE;
   public static final RegistryObject<FluidType> CRUDE_OIL_TYPE;

   static {
      REGISTRY = DeferredRegister.create(Keys.FLUID_TYPES, "cosmos");
      SULPHURIC_ACID_TYPE = REGISTRY.register("sulphuric_acid", () -> new SulphuricAcidFluidType());
      CRUDE_OIL_TYPE = REGISTRY.register("crude_oil", () -> new CrudeOilFluidType());
   }
}
