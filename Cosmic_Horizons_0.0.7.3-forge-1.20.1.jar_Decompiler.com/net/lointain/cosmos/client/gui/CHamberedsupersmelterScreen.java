package net.lointain.cosmos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.lointain.cosmos.procedures.St1Procedure;
import net.lointain.cosmos.procedures.St2Procedure;
import net.lointain.cosmos.procedures.St3Procedure;
import net.lointain.cosmos.procedures.St4Procedure;
import net.lointain.cosmos.procedures.St5Procedure;
import net.lointain.cosmos.procedures.St6Procedure;
import net.lointain.cosmos.world.inventory.CHamberedsupersmelterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CHamberedsupersmelterScreen extends AbstractContainerScreen<CHamberedsupersmelterMenu> {
   private static final HashMap<String, Object> guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   private static final HashMap<String, String> textstate;
   private static final ResourceLocation texture;

   public CHamberedsupersmelterScreen(CHamberedsupersmelterMenu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.f_97726_ = 191;
      this.f_97727_ = 228;
   }

   public void m_88315_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.m_280273_(guiGraphics);
      super.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(texture, this.f_97735_, this.f_97736_, 0.0F, 0.0F, this.f_97726_, this.f_97727_, this.f_97726_, this.f_97727_);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_bar_empty_s.png"), this.f_97735_ + 63, this.f_97736_ + 83, 0.0F, 0.0F, 64, 19, 64, 19);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/fuel_input.png"), this.f_97735_ + 85, this.f_97736_ + 109, 0.0F, 0.0F, 20, 20, 20, 20);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/fuel_input.png"), this.f_97735_ + 157, this.f_97736_ + 28, 0.0F, 0.0F, 20, 20, 20, 20);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/fuel_input.png"), this.f_97735_ + 13, this.f_97736_ + 28, 0.0F, 0.0F, 20, 20, 20, 20);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/fuel_input.png"), this.f_97735_ + 85, this.f_97736_ + 55, 0.0F, 0.0F, 20, 20, 20, 20);
      if (St1Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_s_1_1.png"), this.f_97735_ + 73, this.f_97736_ + 98, 0.0F, 0.0F, 46, 1, 46, 1);
      }

      if (St2Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_1_2.png"), this.f_97735_ + 73, this.f_97736_ + 95, 0.0F, 0.0F, 46, 4, 46, 4);
      }

      if (St3Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_1_3.png"), this.f_97735_ + 73, this.f_97736_ + 93, 0.0F, 0.0F, 46, 6, 46, 6);
      }

      if (St4Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_s_1_4.png"), this.f_97735_ + 73, this.f_97736_ + 90, 0.0F, 0.0F, 46, 9, 46, 9);
      }

      if (St5Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_s_1_5.png"), this.f_97735_ + 73, this.f_97736_ + 88, 0.0F, 0.0F, 46, 11, 46, 11);
      }

      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/mixing.png"), this.f_97735_ + 74, this.f_97736_ + 16, 0.0F, 0.0F, 42, 37, 42, 37);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/mixing_going.png"), this.f_97735_ + 88, this.f_97736_ + 22, 0.0F, 0.0F, 14, 4, 14, 4);
      if (St6Procedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
         guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lava_full_s.png"), this.f_97735_ + 73, this.f_97736_ + 86, 0.0F, 0.0F, 46, 13, 46, 13);
      }

      RenderSystem.disableBlend();
   }

   public static HashMap<String, String> getTextboxValues() {
      return textstate;
   }

   public boolean m_7933_(int key, int b, int c) {
      if (key == 256) {
         this.f_96541_.f_91074_.m_6915_();
         return true;
      } else {
         return super.m_7933_(key, b, c);
      }
   }

   protected void m_280003_(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.c_hamberedsupersmelter.label_chambered_super_smelter"), 38, 4, -12829636, false);
   }

   public void m_7856_() {
      super.m_7856_();
   }

   static {
      guistate = CHamberedsupersmelterMenu.guistate;
      textstate = new HashMap();
      texture = new ResourceLocation("cosmos:textures/screens/c_hamberedsupersmelter.png");
   }
}
