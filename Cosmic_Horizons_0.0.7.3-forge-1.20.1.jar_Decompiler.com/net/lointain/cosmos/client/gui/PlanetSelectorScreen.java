package net.lointain.cosmos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.PlanetSelectorButtonMessage;
import net.lointain.cosmos.procedures.BackgroundProcedure;
import net.lointain.cosmos.procedures.OriginDProcedure;
import net.lointain.cosmos.procedures.PSel2DProcedure;
import net.lointain.cosmos.procedures.PSel3DProcedure;
import net.lointain.cosmos.procedures.PSel4DProcedure;
import net.lointain.cosmos.procedures.PSel5DProcedure;
import net.lointain.cosmos.procedures.Sel2DProcedure;
import net.lointain.cosmos.procedures.Sel3DProcedure;
import net.lointain.cosmos.procedures.Sel4DProcedure;
import net.lointain.cosmos.procedures.SeldownDProcedure;
import net.lointain.cosmos.procedures.SelupDProcedure;
import net.lointain.cosmos.procedures.TravelDProcedure;
import net.lointain.cosmos.procedures.TraveldownDProcedure;
import net.lointain.cosmos.procedures.TravelupDProcedure;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
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

public class PlanetSelectorScreen extends AbstractContainerScreen<PlanetSelectorMenu> {
   private static final HashMap<String, Object> guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   EditBox XPOS;
   EditBox YPOS;
   EditBox ZPOS;
   ImageButton imagebutton_steel_button_small;
   ImageButton imagebutton_down_hover;
   ImageButton imagebutton_up_hover;
   ImageButton imagebutton_steel_button;
   ImageButton imagebutton_steel_button1;
   ImageButton imagebutton_steel_button2;
   ImageButton imagebutton_steel_button3;
   ImageButton imagebutton_steel_button_small1;
   ImageButton imagebutton_down_hover1;
   ImageButton imagebutton_up_hover1;
   ImageButton imagebutton_steel_button_small2;
   ImageButton imagebutton_planet_sel_button;
   ImageButton imagebutton_planet_sel_button1;
   ImageButton imagebutton_planet_sel_button2;
   ImageButton imagebutton_planet_sel_button3;
   ImageButton imagebutton_planet_sel_button4;

   public PlanetSelectorScreen(PlanetSelectorMenu container, Inventory inventory, Component text) {
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
      this.XPOS.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.YPOS.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.ZPOS.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(new ResourceLocation(BackgroundProcedure.execute(this.world, this.entity)), this.f_97735_ + -93, this.f_97736_ + -113, 0.0F, 0.0F, 270, 215, 270, 215);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/steel_text.png"), this.f_97735_ + 33, this.f_97736_ + 75, 0.0F, 0.0F, 100, 18, 100, 18);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/steel_text.png"), this.f_97735_ + 33, this.f_97736_ + 57, 0.0F, 0.0F, 100, 18, 100, 18);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/steel_text.png"), this.f_97735_ + 33, this.f_97736_ + 39, 0.0F, 0.0F, 100, 18, 100, 18);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/lightspeed_sel.png"), this.f_97735_ + -178, this.f_97736_ + -61, 0.0F, 0.0F, 80, 126, 80, 126);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 107, this.f_97736_ + -96, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 107, this.f_97736_ + -72, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 107, this.f_97736_ + -48, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 107, this.f_97736_ + -24, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 107, this.f_97736_ + 0, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 143, this.f_97736_ + 17, 0.0F, 0.0F, 28, 14, 28, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field.png"), this.f_97735_ + 25, this.f_97736_ + 16, 0.0F, 0.0F, 65, 14, 65, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_selector_screen.png"), this.f_97735_ + -100, this.f_97736_ + -118, 0.0F, 0.0F, 286, 225, 286, 225);
      RenderSystem.disableBlend();
   }

   public boolean m_7933_(int key, int b, int c) {
      if (key == 256) {
         this.f_96541_.f_91074_.m_6915_();
         return true;
      } else if (this.XPOS.m_93696_()) {
         return this.XPOS.m_7933_(key, b, c);
      } else if (this.YPOS.m_93696_()) {
         return this.YPOS.m_7933_(key, b, c);
      } else {
         return this.ZPOS.m_93696_() ? this.ZPOS.m_7933_(key, b, c) : super.m_7933_(key, b, c);
      }
   }

   public void m_181908_() {
      super.m_181908_();
      this.XPOS.m_94120_();
      this.YPOS.m_94120_();
      this.ZPOS.m_94120_();
   }

   public void m_6574_(Minecraft minecraft, int width, int height) {
      String XPOSValue = this.XPOS.m_94155_();
      String YPOSValue = this.YPOS.m_94155_();
      String ZPOSValue = this.ZPOS.m_94155_();
      super.m_6574_(minecraft, width, height);
      this.XPOS.m_94144_(XPOSValue);
      this.YPOS.m_94144_(YPOSValue);
      this.ZPOS.m_94144_(ZPOSValue);
   }

   protected void m_280003_(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_travel3"), -14, 62, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_travel"), -15, 62, -39322, false);
      if (OriginDProcedure.execute(this.world, this.entity)) {
         guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_origin1"), -14, 80, -6750208, false);
      }

      if (OriginDProcedure.execute(this.world, this.entity)) {
         guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_origin"), -15, 80, -39322, false);
      }

      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_type1"), 128, -105, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_type"), 127, -105, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_conditions"), 114, -81, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_conditions1"), 113, -81, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_atmosphere"), 111, -57, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_atmosphere1"), 110, -57, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_life"), 117, 21, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_life1"), 116, 21, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_size"), 129, -33, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_size1"), 128, -33, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_category"), 118, -9, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_category1"), 117, -9, -39322, false);
      if (TravelDProcedure.execute(this.world, this.entity)) {
         guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_travel2"), -14, 20, -6750208, false);
      }

      if (TravelDProcedure.execute(this.world, this.entity)) {
         guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.planet_selector.label_travel1"), -15, 20, -39322, false);
      }

   }

   public void m_7856_() {
      super.m_7856_();
      this.XPOS = new EditBox(this.f_96547_, this.f_97735_ + 38, this.f_97736_ + 43, 90, 10, Component.m_237115_("gui.cosmos.planet_selector.XPOS")) {
         public void m_94164_(String text) {
            super.m_94164_(text);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.XPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }

         public void m_94192_(int pos) {
            super.m_94192_(pos);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.XPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }
      };
      this.XPOS.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.XPOS").getString());
      this.XPOS.m_94199_(32767);
      guistate.put("text:XPOS", this.XPOS);
      this.m_7787_(this.XPOS);
      this.YPOS = new EditBox(this.f_96547_, this.f_97735_ + 38, this.f_97736_ + 61, 90, 10, Component.m_237115_("gui.cosmos.planet_selector.YPOS")) {
         public void m_94164_(String text) {
            super.m_94164_(text);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.YPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }

         public void m_94192_(int pos) {
            super.m_94192_(pos);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.YPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }
      };
      this.YPOS.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.YPOS").getString());
      this.YPOS.m_94199_(32767);
      guistate.put("text:YPOS", this.YPOS);
      this.m_7787_(this.YPOS);
      this.ZPOS = new EditBox(this.f_96547_, this.f_97735_ + 38, this.f_97736_ + 79, 90, 10, Component.m_237115_("gui.cosmos.planet_selector.ZPOS")) {
         public void m_94164_(String text) {
            super.m_94164_(text);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.ZPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }

         public void m_94192_(int pos) {
            super.m_94192_(pos);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.ZPOS").getString());
            } else {
               this.m_94167_((String)null);
            }

         }
      };
      this.ZPOS.m_94167_(Component.m_237115_("gui.cosmos.planet_selector.ZPOS").getString());
      this.ZPOS.m_94199_(32767);
      guistate.put("text:ZPOS", this.ZPOS);
      this.m_7787_(this.ZPOS);
      this.imagebutton_steel_button_small = new ImageButton(this.f_97735_ + -20, this.f_97736_ + 59, 41, 13, 0, 0, 13, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_small.png"), 41, 26, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(0, this.x, this.y, this.z));
         PlanetSelectorButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
      });
      guistate.put("button:imagebutton_steel_button_small", this.imagebutton_steel_button_small);
      this.m_142416_(this.imagebutton_steel_button_small);
      this.imagebutton_down_hover = new ImageButton(this.f_97735_ + -144, this.f_97736_ + -54, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_down_hover.png"), 10, 20, (e) -> {
         if (SelupDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(1, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (SelupDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_down_hover", this.imagebutton_down_hover);
      this.m_142416_(this.imagebutton_down_hover);
      this.imagebutton_up_hover = new ImageButton(this.f_97735_ + -144, this.f_97736_ + 49, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_up_hover.png"), 10, 20, (e) -> {
         if (SeldownDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(2, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (SeldownDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_up_hover", this.imagebutton_up_hover);
      this.m_142416_(this.imagebutton_up_hover);
      this.imagebutton_steel_button = new ImageButton(this.f_97735_ + -173, this.f_97736_ + -42, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button.png"), 71, 40, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(3, this.x, this.y, this.z));
         PlanetSelectorButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z);
      });
      guistate.put("button:imagebutton_steel_button", this.imagebutton_steel_button);
      this.m_142416_(this.imagebutton_steel_button);
      this.imagebutton_steel_button1 = new ImageButton(this.f_97735_ + -173, this.f_97736_ + -19, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button1.png"), 71, 40, (e) -> {
         if (Sel2DProcedure.execute(this.world)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(4, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 4, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Sel2DProcedure.execute(PlanetSelectorScreen.this.world)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button1", this.imagebutton_steel_button1);
      this.m_142416_(this.imagebutton_steel_button1);
      this.imagebutton_steel_button2 = new ImageButton(this.f_97735_ + -173, this.f_97736_ + 4, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button2.png"), 71, 40, (e) -> {
         if (Sel3DProcedure.execute(this.world)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(5, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 5, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Sel3DProcedure.execute(PlanetSelectorScreen.this.world)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button2", this.imagebutton_steel_button2);
      this.m_142416_(this.imagebutton_steel_button2);
      this.imagebutton_steel_button3 = new ImageButton(this.f_97735_ + -173, this.f_97736_ + 27, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button3.png"), 71, 40, (e) -> {
         if (Sel4DProcedure.execute(this.world)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(6, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 6, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Sel4DProcedure.execute(PlanetSelectorScreen.this.world)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button3", this.imagebutton_steel_button3);
      this.m_142416_(this.imagebutton_steel_button3);
      this.imagebutton_steel_button_small1 = new ImageButton(this.f_97735_ + -20, this.f_97736_ + 77, 41, 13, 0, 0, 13, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_small1.png"), 41, 26, (e) -> {
         if (OriginDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(7, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 7, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (OriginDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_small1", this.imagebutton_steel_button_small1);
      this.m_142416_(this.imagebutton_steel_button_small1);
      this.imagebutton_down_hover1 = new ImageButton(this.f_97735_ + -46, this.f_97736_ + -105, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_down_hover1.png"), 10, 20, (e) -> {
         if (TravelupDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(8, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 8, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (TravelupDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_down_hover1", this.imagebutton_down_hover1);
      this.m_142416_(this.imagebutton_down_hover1);
      this.imagebutton_up_hover1 = new ImageButton(this.f_97735_ + -46, this.f_97736_ + 21, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_up_hover1.png"), 10, 20, (e) -> {
         if (TraveldownDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(9, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 9, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (TraveldownDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_up_hover1", this.imagebutton_up_hover1);
      this.m_142416_(this.imagebutton_up_hover1);
      this.imagebutton_steel_button_small2 = new ImageButton(this.f_97735_ + -20, this.f_97736_ + 17, 41, 13, 0, 0, 13, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_small2.png"), 41, 26, (e) -> {
         if (TravelDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(10, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 10, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (TravelDProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_small2", this.imagebutton_steel_button_small2);
      this.m_142416_(this.imagebutton_steel_button_small2);
      this.imagebutton_planet_sel_button = new ImageButton(this.f_97735_ + -84, this.f_97736_ + -104, 34, 34, 0, 0, 34, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_planet_sel_button.png"), 34, 68, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(11, this.x, this.y, this.z));
         PlanetSelectorButtonMessage.handleButtonAction(this.entity, 11, this.x, this.y, this.z);
      });
      guistate.put("button:imagebutton_planet_sel_button", this.imagebutton_planet_sel_button);
      this.m_142416_(this.imagebutton_planet_sel_button);
      this.imagebutton_planet_sel_button1 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + -63, 34, 34, 0, 0, 34, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_planet_sel_button1.png"), 34, 68, (e) -> {
         if (PSel2DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(12, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 12, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PSel2DProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_planet_sel_button1", this.imagebutton_planet_sel_button1);
      this.m_142416_(this.imagebutton_planet_sel_button1);
      this.imagebutton_planet_sel_button2 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + -22, 34, 34, 0, 0, 34, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_planet_sel_button2.png"), 34, 68, (e) -> {
         if (PSel3DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(13, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 13, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PSel3DProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_planet_sel_button2", this.imagebutton_planet_sel_button2);
      this.m_142416_(this.imagebutton_planet_sel_button2);
      this.imagebutton_planet_sel_button3 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + 19, 34, 34, 0, 0, 34, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_planet_sel_button3.png"), 34, 68, (e) -> {
         if (PSel4DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(14, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 14, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PSel4DProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_planet_sel_button3", this.imagebutton_planet_sel_button3);
      this.m_142416_(this.imagebutton_planet_sel_button3);
      this.imagebutton_planet_sel_button4 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + 60, 34, 34, 0, 0, 34, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_planet_sel_button4.png"), 34, 68, (e) -> {
         if (PSel5DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlanetSelectorButtonMessage(15, this.x, this.y, this.z));
            PlanetSelectorButtonMessage.handleButtonAction(this.entity, 15, this.x, this.y, this.z);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PSel5DProcedure.execute(PlanetSelectorScreen.this.world, PlanetSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_planet_sel_button4", this.imagebutton_planet_sel_button4);
      this.m_142416_(this.imagebutton_planet_sel_button4);
   }

   static {
      guistate = PlanetSelectorMenu.guistate;
   }
}
