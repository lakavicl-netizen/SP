package net.lointain.cosmos.init;

import net.lointain.cosmos.client.renderer.EntityAshedTNTRenderer;
import net.lointain.cosmos.client.renderer.EntitycorrodedsulphertntRenderer;
import net.lointain.cosmos.client.renderer.NickelRoverRenderer;
import net.lointain.cosmos.client.renderer.RocketSeatRenderer;
import net.lointain.cosmos.client.renderer.SteelRoverRenderer;
import net.lointain.cosmos.client.renderer.TitaniumRoverRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class CosmosModEntityRenderers {
   @SubscribeEvent
   public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)CosmosModEntities.ENTITYCORRODEDSULPHERTNT.get(), EntitycorrodedsulphertntRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.ENTITY_ASHED_TNT.get(), EntityAshedTNTRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.SULPHURIC_SHARDS.get(), ThrownItemRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.ROCKET_SEAT.get(), RocketSeatRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.STEEL_ROVER.get(), SteelRoverRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.TITANIUM_ROVER.get(), TitaniumRoverRenderer::new);
      event.registerEntityRenderer((EntityType)CosmosModEntities.NICKEL_ROVER.get(), NickelRoverRenderer::new);
   }
}
