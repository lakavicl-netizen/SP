package net.lointain.cosmos.client.renderer;

import net.lointain.cosmos.client.model.Modelrover;
import net.lointain.cosmos.entity.SteelRoverEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SteelRoverRenderer extends MobRenderer<SteelRoverEntity, Modelrover<SteelRoverEntity>> {
   public SteelRoverRenderer(EntityRendererProvider.Context context) {
      super(context, new Modelrover(context.m_174023_(Modelrover.LAYER_LOCATION)), 0.5F);
   }

   public ResourceLocation getTextureLocation(SteelRoverEntity entity) {
      return new ResourceLocation("cosmos:textures/entities/rover_steel.png");
   }
}
