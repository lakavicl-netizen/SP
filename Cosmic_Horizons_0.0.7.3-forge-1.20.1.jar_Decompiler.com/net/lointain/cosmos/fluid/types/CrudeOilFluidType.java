package net.lointain.cosmos.fluid.types;

import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidType.Properties;

public class CrudeOilFluidType extends FluidType {
   public CrudeOilFluidType() {
      super(Properties.create().fallDistanceModifier(0.0F).canExtinguish(true).supportsBoating(true).canHydrate(true).motionScale(0.007).temperature(340).sound(SoundActions.BUCKET_FILL, SoundEvents.f_11781_).sound(SoundActions.BUCKET_EMPTY, SoundEvents.f_11778_).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.f_11937_));
   }

   public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
      consumer.accept(new IClientFluidTypeExtensions() {
         private static final ResourceLocation STILL_TEXTURE = new ResourceLocation("cosmos:block/crude_oil");
         private static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("cosmos:block/crude_oil");

         public ResourceLocation getStillTexture() {
            return STILL_TEXTURE;
         }

         public ResourceLocation getFlowingTexture() {
            return FLOWING_TEXTURE;
         }
      });
   }
}
