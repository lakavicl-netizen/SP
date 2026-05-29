package net.lointain.cosmos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.LandingSelectorButtonMessage;
import net.lointain.cosmos.procedures.DownDProcedure;
import net.lointain.cosmos.procedures.UpDProcedure;
import net.lointain.cosmos.procedures.Vis1Procedure;
import net.lointain.cosmos.procedures.Vis2Procedure;
import net.lointain.cosmos.procedures.Vis3Procedure;
import net.lointain.cosmos.procedures.Vis4Procedure;
import net.lointain.cosmos.world.inventory.LandingSelectorMenu;
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

public class LandingSelectorScreen extends AbstractContainerScreen<LandingSelectorMenu> {
   private static final HashMap<String, Object> guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   private static final HashMap<String, String> textstate;
   public static EditBox reg;
   ImageButton imagebutton_steel_button_long1;
   ImageButton imagebutton_steel_button_long2;
   ImageButton imagebutton_steel_button_long3;
   ImageButton imagebutton_steel_button_long4;
   ImageButton imagebutton_steel_button_long5;
   ImageButton imagebutton_up_hover;
   ImageButton imagebutton_down_hover;
   ImageButton imagebutton_close;
   ImageButton imagebutton_steel_button_small;
   private static final ResourceLocation texture;

   public LandingSelectorScreen(LandingSelectorMenu container, Inventory inventory, Component text) {
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
      reg.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(texture, this.f_97735_, this.f_97736_, 0.0F, 0.0F, this.f_97726_, this.f_97727_, this.f_97726_, this.f_97727_);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/search_bar.png"), this.f_97735_ + -87, this.f_97736_ + -115, 0.0F, 0.0F, 175, 80, 175, 80);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/land_sel.png"), this.f_97735_ + -100, this.f_97736_ + -81, 0.0F, 0.0F, 200, 174, 200, 174);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/steel_text.png"), this.f_97735_ + -75, this.f_97736_ + -101, 0.0F, 0.0F, 100, 18, 100, 18);
      RenderSystem.disableBlend();
   }

   public boolean m_7933_(int key, int b, int c) {
      if (key == 256) {
         this.f_96541_.f_91074_.m_6915_();
         return true;
      } else {
         return reg.m_93696_() ? reg.m_7933_(key, b, c) : super.m_7933_(key, b, c);
      }
   }

   public void m_181908_() {
      super.m_181908_();
      reg.m_94120_();
   }

   public void m_6574_(Minecraft minecraft, int width, int height) {
      String regValue = reg.m_94155_();
      super.m_6574_(minecraft, width, height);
      reg.m_94144_(regValue);
   }

   protected void m_280003_(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.landing_selector.label_origin"), -30, -62, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.landing_selector.label_origin1"), -31, -62, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.landing_selector.label_search"), 38, -96, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.landing_selector.label_search1"), 37, -96, -39322, false);
   }

   public void m_7856_() {
      super.m_7856_();
      reg = new EditBox(this.f_96547_, this.f_97735_ + -70, this.f_97736_ + -97, 90, 10, Component.m_237115_("gui.cosmos.landing_selector.reg")) {
         public void m_94164_(String text) {
            super.m_94164_(text);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.landing_selector.reg").getString());
            } else {
               this.m_94167_((String)null);
            }

         }

         public void m_94192_(int pos) {
            super.m_94192_(pos);
            if (this.m_94155_().isEmpty()) {
               this.m_94167_(Component.m_237115_("gui.cosmos.landing_selector.reg").getString());
            } else {
               this.m_94167_((String)null);
            }

         }
      };
      reg.m_94167_(Component.m_237115_("gui.cosmos.landing_selector.reg").getString());
      reg.m_94199_(32767);
      guistate.put("text:reg", reg);
      this.m_7787_(reg);
      this.imagebutton_steel_button_long1 = new ImageButton(this.f_97735_ + -83, this.f_97736_ + -69, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_long1.png"), 137, 40, (e) -> {
         textstate.put("textin:reg", reg.m_94155_());
         CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(0, this.x, this.y, this.z, textstate));
         LandingSelectorButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_steel_button_long1", this.imagebutton_steel_button_long1);
      this.m_142416_(this.imagebutton_steel_button_long1);
      this.imagebutton_steel_button_long2 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + -29, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_long2.png"), 137, 40, (e) -> {
         if (Vis1Procedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(1, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Vis1Procedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_long2", this.imagebutton_steel_button_long2);
      this.m_142416_(this.imagebutton_steel_button_long2);
      this.imagebutton_steel_button_long3 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + -2, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_long3.png"), 137, 40, (e) -> {
         if (Vis2Procedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(2, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Vis2Procedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_long3", this.imagebutton_steel_button_long3);
      this.m_142416_(this.imagebutton_steel_button_long3);
      this.imagebutton_steel_button_long4 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + 25, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_long4.png"), 137, 40, (e) -> {
         if (Vis3Procedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(3, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Vis3Procedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_long4", this.imagebutton_steel_button_long4);
      this.m_142416_(this.imagebutton_steel_button_long4);
      this.imagebutton_steel_button_long5 = new ImageButton(this.f_97735_ + -84, this.f_97736_ + 52, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_long5.png"), 137, 40, (e) -> {
         if (Vis4Procedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(4, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 4, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Vis4Procedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button_long5", this.imagebutton_steel_button_long5);
      this.m_142416_(this.imagebutton_steel_button_long5);
      this.imagebutton_up_hover = new ImageButton(this.f_97735_ + -5, this.f_97736_ + 75, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_up_hover.png"), 10, 20, (e) -> {
         if (DownDProcedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(5, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 5, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (DownDProcedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_up_hover", this.imagebutton_up_hover);
      this.m_142416_(this.imagebutton_up_hover);
      this.imagebutton_down_hover = new ImageButton(this.f_97735_ + -7, this.f_97736_ + -41, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_down_hover.png"), 10, 20, (e) -> {
         if (UpDProcedure.execute(this.entity)) {
            textstate.put("textin:reg", reg.m_94155_());
            CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(6, this.x, this.y, this.z, textstate));
            LandingSelectorButtonMessage.handleButtonAction(this.entity, 6, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (UpDProcedure.execute(LandingSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_down_hover", this.imagebutton_down_hover);
      this.m_142416_(this.imagebutton_down_hover);
      this.imagebutton_close = new ImageButton(this.f_97735_ + 80, this.f_97736_ + -68, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_close.png"), 10, 20, (e) -> {
         textstate.put("textin:reg", reg.m_94155_());
         CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(7, this.x, this.y, this.z, textstate));
         LandingSelectorButtonMessage.handleButtonAction(this.entity, 7, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_close", this.imagebutton_close);
      this.m_142416_(this.imagebutton_close);
      this.imagebutton_steel_button_small = new ImageButton(this.f_97735_ + 33, this.f_97736_ + -99, 41, 13, 0, 0, 13, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_small.png"), 41, 26, (e) -> {
         textstate.put("textin:reg", reg.m_94155_());
         CosmosMod.PACKET_HANDLER.sendToServer(new LandingSelectorButtonMessage(8, this.x, this.y, this.z, textstate));
         LandingSelectorButtonMessage.handleButtonAction(this.entity, 8, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_steel_button_small", this.imagebutton_steel_button_small);
      this.m_142416_(this.imagebutton_steel_button_small);
   }

   static {
      guistate = LandingSelectorMenu.guistate;
      textstate = new HashMap();
      texture = new ResourceLocation("cosmos:textures/screens/landing_selector.png");
   }
}
