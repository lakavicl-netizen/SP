package net.lointain.cosmos.init;

import net.lointain.cosmos.world.density_function.CraterDensityFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(
   modid = "cosmos",
   bus = Bus.MOD
)
public class CosmosModDensityFunctions {
   private static final DeferredRegister<DensityFunction> DENSITY_FUNCTIONS;

   @SubscribeEvent
   public static void register(RegisterEvent event) {
      event.register(Registries.f_256746_, (helper) -> helper.register("crater", CraterDensityFunction.DATA_CODEC.codec()));
   }

   static {
      DENSITY_FUNCTIONS = DeferredRegister.create(Registries.f_257040_, "cosmos");
   }
}
