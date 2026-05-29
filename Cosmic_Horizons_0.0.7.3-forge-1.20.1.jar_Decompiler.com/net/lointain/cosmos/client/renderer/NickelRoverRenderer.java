package net.lointain.cosmos.client.renderer;

import net.lointain.cosmos.client.model.Modelrover;
import net.lointain.cosmos.entity.NickelRoverEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NickelRoverRenderer extends MobRenderer<NickelRoverEntity, Modelrover<NickelRoverEntity>> {
   public NickelRoverRenderer(EntityRendererProvider.Context context) {
      super(context, new Modelrover(context.m_174023_(Modelrover.LAYER_LOCATION)), 0.5F);
   }

   public ResourceLocation getTextureLocation(NickelRoverEntity entity) {
      return new ResourceLocation("cosmos:textures/entities/rover_nickel.png");
   }
}
