package net.lointain.cosmos.fluid.types;

import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidType.Properties;
import net.minecraftforge.registries.ForgeRegistries;

public class SulphuricAcidFluidType extends FluidType {
   public SulphuricAcidFluidType() {
      super(Properties.create().canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType((BlockPathTypes)null).motionScale(0.021).viscosity(5000).temperature(3000).sound(SoundActions.BUCKET_FILL, SoundEvents.f_11781_).sound(SoundActions.BUCKET_EMPTY, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bucket.empty_lava"))).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.f_11937_));
   }

   public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
      consumer.accept(new IClientFluidTypeExtensions() {
         private static final ResourceLocation STILL_TEXTURE = new ResourceLocation("cosmos:block/sulpheric_acid_still");
         private static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("cosmos:block/supheric_acid_flowing");

         public ResourceLocation getStillTexture() {
            return STILL_TEXTURE;
         }

         public ResourceLocation getFlowingTexture() {
            return FLOWING_TEXTURE;
         }
      });
   }
}
