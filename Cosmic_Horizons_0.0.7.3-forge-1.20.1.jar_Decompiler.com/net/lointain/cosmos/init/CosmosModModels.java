package net.lointain.cosmos.init;

import net.lointain.cosmos.client.model.ModelCustomModel;
import net.lointain.cosmos.client.model.ModelTNT1;
import net.lointain.cosmos.client.model.Modelflashlight_helmet;
import net.lointain.cosmos.client.model.Modelrover;
import net.lointain.cosmos.client.model.Modelspace_suit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class CosmosModModels {
   @SubscribeEvent
   public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
      event.registerLayerDefinition(Modelflashlight_helmet.LAYER_LOCATION, Modelflashlight_helmet::createBodyLayer);
      event.registerLayerDefinition(Modelspace_suit.LAYER_LOCATION, Modelspace_suit::createBodyLayer);
      event.registerLayerDefinition(ModelTNT1.LAYER_LOCATION, ModelTNT1::createBodyLayer);
      event.registerLayerDefinition(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
      event.registerLayerDefinition(Modelrover.LAYER_LOCATION, Modelrover::createBodyLayer);
   }
}
