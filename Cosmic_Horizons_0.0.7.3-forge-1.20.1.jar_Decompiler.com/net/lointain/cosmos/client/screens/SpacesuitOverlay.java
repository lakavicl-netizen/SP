package net.lointain.cosmos.client.screens;

import net.lointain.cosmos.procedures.ProvideplayerProcedure;
import net.lointain.cosmos.procedures.SpacesuitDisplayOverlayIngameProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class SpacesuitOverlay {
   @SubscribeEvent(
      priority = EventPriority.NORMAL
   )
   public static void eventHandler(RenderGuiEvent.Pre event) {
      int w = event.getWindow().m_85445_();
      int h = event.getWindow().m_85446_();
      Level world = null;
      double x = (double)0.0F;
      double y = (double)0.0F;
      double z = (double)0.0F;
      Player entity = Minecraft.m_91087_().f_91074_;
      if (entity != null) {
         world = entity.m_9236_();
         x = entity.m_20185_();
         y = entity.m_20186_();
         z = entity.m_20189_();
      }

      if (SpacesuitDisplayOverlayIngameProcedure.execute(entity)) {
         Entity var12 = ProvideplayerProcedure.execute(entity);
         if (var12 instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)var12;
            InventoryScreen.renderEntityInInventoryFollowsAngle(event.getGuiGraphics(), w - 33, h - 12, 30, 1.5F, 0.0F, livingEntity);
         }
      }

   }
}
