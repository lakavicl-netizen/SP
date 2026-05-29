package net.lointain.cosmos.client.renderer;

import net.lointain.cosmos.client.model.ModelTNT1;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RocketSeatRenderer extends MobRenderer<RocketSeatEntity, ModelTNT1<RocketSeatEntity>> {
   public RocketSeatRenderer(EntityRendererProvider.Context context) {
      super(context, new ModelTNT1(context.m_174023_(ModelTNT1.LAYER_LOCATION)), 0.5F);
   }

   public ResourceLocation getTextureLocation(RocketSeatEntity entity) {
      return new ResourceLocation("cosmos:textures/entities/rocket_rider.png");
   }

   protected boolean isBodyVisible(RocketSeatEntity entity) {
      return false;
   }
}
