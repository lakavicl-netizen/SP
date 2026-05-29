package net.lointain.cosmos.fluid;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.init.CosmosModFluidTypes;
import net.lointain.cosmos.init.CosmosModFluids;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SulphuricAcidFluid extends ForgeFlowingFluid {
   public static final ForgeFlowingFluid.Properties PROPERTIES = (new ForgeFlowingFluid.Properties(() -> (FluidType)CosmosModFluidTypes.SULPHURIC_ACID_TYPE.get(), () -> (Fluid)CosmosModFluids.SULPHURIC_ACID.get(), () -> (Fluid)CosmosModFluids.FLOWING_SULPHURIC_ACID.get())).explosionResistance(100.0F).bucket(() -> (Item)CosmosModItems.SULPHURIC_ACID_BUCKET.get()).block(() -> (LiquidBlock)CosmosModBlocks.SULPHURIC_ACID.get());

   private SulphuricAcidFluid() {
      super(PROPERTIES);
   }

   public ParticleOptions m_7792_() {
      return (SimpleParticleType)CosmosModParticleTypes.SULPHURICDRIP.get();
   }

   public static class Source extends SulphuricAcidFluid {
      public int m_7430_(FluidState state) {
         return 8;
      }

      public boolean m_7444_(FluidState state) {
         return true;
      }
   }

   public static class Flowing extends SulphuricAcidFluid {
      protected void m_7180_(StateDefinition.Builder<Fluid, FluidState> builder) {
         super.m_7180_(builder);
         builder.m_61104_(new Property[]{f_75948_});
      }

      public int m_7430_(FluidState state) {
         return (Integer)state.m_61143_(f_75948_);
      }

      public boolean m_7444_(FluidState state) {
         return false;
      }
   }
}
