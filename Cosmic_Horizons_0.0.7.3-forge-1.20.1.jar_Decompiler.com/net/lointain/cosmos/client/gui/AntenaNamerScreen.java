package net.lointain.cosmos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.AntenaNamerButtonMessage;
import net.lointain.cosmos.world.inventory.AntenaNamerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AntenaNamerScreen extends AbstractContainerScreen<AntenaNamerMenu> {
   private static final HashMap<String, Object> guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   private static final HashMap<String, String> textstate;
   public static EditBox intel;
   ImageButton imagebutton_steel_button;
   private static final ResourceLocation texture;

   public AntenaNamerScreen(AntenaNamerMenu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.f_97726_ = 0;
      this.f_97727_ = 0;
   }

   public void m_88315_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.m_280273_(guiGraphics);
      super.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      intel.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(texture, this.f_97735_, this.f_97736_, 0.0F, 0.0F, this.f_97726_, this.f_97727_, this.f_97726_, this.f_97727_);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/landing_pad.png"), this.f_97735_ + -63, this.f_97736_ + -44, 0.0F, 0.0F, 125, 80, 125, 80);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/steel_text.png"), this.f_97735_ + -50, this.f_97736_ + -23, 0.0F, 0.0F, 100, 18, 100, 18);
      RenderSystem.disableBlend();
   }

   public boolean m_7933_(int key, int b, int c) {
      if (key == 256) {
         this.f_96541_.f_91074_.m_6915_();
         return true;
      } else {
         return intel.m_93696_() ? intel.m_7933_(key, b, c) : super.m_7933_(key, b, c);
      }
   }

   public void m_181908_() {
      super.m_181908_();
      intel.m_94120_();
   }

   public void m_6574_(Minecraft minecraft, int width, int height) {
      String intelValue = intel.m_94155_();
      super.m_6574_(minecraft, width, height);
      intel.m_94144_(intelValue);
   }

   protected void m_280003_(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.antena_namer.label_enter"), -12, 5, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.antena_namer.label_enter1"), -13, 5, -39322, false);
   }

   public void m_7856_() {
      super.m_7856_();
      intel = new EditBox(this.f_96547_, this.f_97735_ + -45, this.f_97736_ + -19, 90, 10, Component.m_237115_("gui.cosmos.antena_namer.intel")) {
         public void m_94164_(String text) {
            super.m_94164_(text);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.antena_namer.intel").getString());
            } else {
               this.m_94167_((String)null);
            }

         }

         public void m_94192_(int pos) {
            super.m_94192_(pos);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.antena_namer.intel").getString());
            } else {
               this.m_94167_((String)null);
            }

         }
      };
      intel.m_94167_(Component.m_237115_("gui.cosmos.antena_namer.intel").getString());
      intel.m_94199_(32767);
      guistate.put("text:intel", intel);
      this.m_7787_(intel);
      this.imagebutton_steel_button = new ImageButton(this.f_97735_ + -36, this.f_97736_ + -1, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button.png"), 71, 40, (e) -> {
         textstate.put("textin:intel", intel.m_94155_());
         CosmosMod.PACKET_HANDLER.sendToServer(new AntenaNamerButtonMessage(0, this.x, this.y, this.z, textstate));
         AntenaNamerButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_steel_button", this.imagebutton_steel_button);
      this.m_142416_(this.imagebutton_steel_button);
   }

   static {
      guistate = AntenaNamerMenu.guistate;
      textstate = new HashMap();
      texture = new ResourceLocation("cosmos:textures/screens/antena_namer.png");
   }
}
