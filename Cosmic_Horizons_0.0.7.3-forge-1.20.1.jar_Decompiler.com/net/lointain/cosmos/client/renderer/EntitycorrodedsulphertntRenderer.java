package net.lointain.cosmos.client.renderer;

import net.lointain.cosmos.client.model.ModelTNT1;
import net.lointain.cosmos.entity.EntitycorrodedsulphertntEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EntitycorrodedsulphertntRenderer extends MobRenderer<EntitycorrodedsulphertntEntity, ModelTNT1<EntitycorrodedsulphertntEntity>> {
   public EntitycorrodedsulphertntRenderer(EntityRendererProvider.Context context) {
      super(context, new ModelTNT1(context.m_174023_(ModelTNT1.LAYER_LOCATION)), 0.5F);
   }

   public ResourceLocation getTextureLocation(EntitycorrodedsulphertntEntity entity) {
      return new ResourceLocation("cosmos:textures/entities/tnt1.png");
   }
}
