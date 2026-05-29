package net.lointain.cosmos.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.ProjectionSelectorButtonMessage;
import net.lointain.cosmos.procedures.PagedownDProcedure;
import net.lointain.cosmos.procedures.PageupDProcedure;
import net.lointain.cosmos.procedures.PitchSpeedProcedure;
import net.lointain.cosmos.procedures.PitchspeeddownDProcedure;
import net.lointain.cosmos.procedures.PitchspeedupDProcedure;
import net.lointain.cosmos.procedures.PlanetPagedownDProcedure;
import net.lointain.cosmos.procedures.PlanetPageupDProcedure;
import net.lointain.cosmos.procedures.PlanetSel2DProcedure;
import net.lointain.cosmos.procedures.PlanetSel3DProcedure;
import net.lointain.cosmos.procedures.PlanetSel4DProcedure;
import net.lointain.cosmos.procedures.RollSpeedProcedure;
import net.lointain.cosmos.procedures.RollspeeddownDProcedure;
import net.lointain.cosmos.procedures.RollspeedupDProcedure;
import net.lointain.cosmos.procedures.SizeUpDProcedure;
import net.lointain.cosmos.procedures.SizedownDProcedure;
import net.lointain.cosmos.procedures.SizingProcedure;
import net.lointain.cosmos.procedures.Syssel1DProcedure;
import net.lointain.cosmos.procedures.Syssel2DProcedure;
import net.lointain.cosmos.procedures.YawspeedProcedure;
import net.lointain.cosmos.procedures.YawspeeddownDProcedure;
import net.lointain.cosmos.procedures.YawspeedupDProcedure;
import net.lointain.cosmos.procedures.YoffsetProcedure;
import net.lointain.cosmos.procedures.YoffsetdownDProcedure;
import net.lointain.cosmos.procedures.YoffsetupDProcedure;
import net.lointain.cosmos.world.inventory.ProjectionSelectorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ProjectionSelectorScreen extends AbstractContainerScreen<ProjectionSelectorMenu> {
   private static final HashMap<String, Object> guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   private static final HashMap<String, String> textstate;
   ImageButton imagebutton_steel_button;
   ImageButton imagebutton_steel_button1;
   ImageButton imagebutton_right_hover;
   ImageButton imagebutton_left_hover;
   ImageButton imagebutton_steel_button2;
   ImageButton imagebutton_steel_button3;
   ImageButton imagebutton_steel_button4;
   ImageButton imagebutton_steel_button5;
   ImageButton imagebutton_right_pressed;
   ImageButton imagebutton_right_pressed1;
   ImageButton imagebutton_right_pressed2;
   ImageButton imagebutton_right_pressed3;
   ImageButton imagebutton_left_hover1;
   ImageButton imagebutton_left_hover2;
   ImageButton imagebutton_left_hover3;
   ImageButton imagebutton_left_hover4;
   ImageButton imagebutton_steel_button_small1;
   ImageButton imagebutton_close;
   ImageButton imagebutton_down_hover;
   ImageButton imagebutton_up_hover;
   ImageButton imagebutton_right_pressed4;
   ImageButton imagebutton_left_hover5;
   private static final ResourceLocation texture;

   public ProjectionSelectorScreen(ProjectionSelectorMenu container, Inventory inventory, Component text) {
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
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(texture, this.f_97735_, this.f_97736_, 0.0F, 0.0F, this.f_97726_, this.f_97727_, this.f_97726_, this.f_97727_);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/edit_bar.png"), this.f_97735_ + 91, this.f_97736_ + -91, 0.0F, 0.0F, 80, 172, 80, 172);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/land_sel.png"), this.f_97735_ + -99, this.f_97736_ + -92, 0.0F, 0.0F, 200, 174, 200, 174);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 120, this.f_97736_ + -74, 0.0F, 0.0F, 28, 14, 28, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 120, this.f_97736_ + -15, 0.0F, 0.0F, 28, 14, 28, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 120, this.f_97736_ + 12, 0.0F, 0.0F, 28, 14, 28, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 120, this.f_97736_ + 39, 0.0F, 0.0F, 28, 14, 28, 14);
      guiGraphics.m_280163_(new ResourceLocation("cosmos:textures/screens/planet_field_mini.png"), this.f_97735_ + 120, this.f_97736_ + -41, 0.0F, 0.0F, 28, 14, 28, 14);
      RenderSystem.disableBlend();
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
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_size"), 124, -83, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_size1"), 123, -83, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_yaw_speed"), 111, -26, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_yaw_speed1"), 110, -26, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_pitch_speed"), 106, 1, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_roll_speed"), 109, 28, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_pitch_speed1"), 105, 1, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_roll_speed1"), 108, 28, -39322, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_reset1"), 123, 63, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_reset"), 122, 63, -39322, false);
      guiGraphics.m_280056_(this.f_96547_, SizingProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z), 125, -71, -3355444, false);
      guiGraphics.m_280056_(this.f_96547_, YawspeedProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z), 127, -12, -3355444, false);
      guiGraphics.m_280056_(this.f_96547_, PitchSpeedProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z), 127, 15, -3355444, false);
      guiGraphics.m_280056_(this.f_96547_, RollSpeedProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z), 127, 42, -3355444, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_y_offset"), 115, -51, -6750208, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.cosmos.projection_selector.label_y_offset1"), 114, -51, -39322, false);
      guiGraphics.m_280056_(this.f_96547_, YoffsetProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z), 125, -38, -3355444, false);
   }

   public void m_7856_() {
      super.m_7856_();
      this.imagebutton_steel_button = new ImageButton(this.f_97735_ + -71, this.f_97736_ + -80, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button.png"), 71, 40, (e) -> {
         if (Syssel1DProcedure.execute(this.world)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(0, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Syssel1DProcedure.execute(ProjectionSelectorScreen.this.world)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button", this.imagebutton_steel_button);
      this.m_142416_(this.imagebutton_steel_button);
      this.imagebutton_steel_button1 = new ImageButton(this.f_97735_ + 3, this.f_97736_ + -80, 71, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button1.png"), 71, 40, (e) -> {
         if (Syssel2DProcedure.execute(this.world)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(1, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (Syssel2DProcedure.execute(ProjectionSelectorScreen.this.world)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button1", this.imagebutton_steel_button1);
      this.m_142416_(this.imagebutton_steel_button1);
      this.imagebutton_right_hover = new ImageButton(this.f_97735_ + 78, this.f_97736_ + -75, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_hover.png"), 10, 20, (e) -> {
         if (PagedownDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(2, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PagedownDProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_hover", this.imagebutton_right_hover);
      this.m_142416_(this.imagebutton_right_hover);
      this.imagebutton_left_hover = new ImageButton(this.f_97735_ + -85, this.f_97736_ + -75, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover.png"), 10, 20, (e) -> {
         if (PageupDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(3, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PageupDProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover", this.imagebutton_left_hover);
      this.m_142416_(this.imagebutton_left_hover);
      this.imagebutton_steel_button2 = new ImageButton(this.f_97735_ + -66, this.f_97736_ + 13, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button2.png"), 137, 40, (e) -> {
         if (PlanetSel3DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(4, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 4, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PlanetSel3DProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button2", this.imagebutton_steel_button2);
      this.m_142416_(this.imagebutton_steel_button2);
      this.imagebutton_steel_button3 = new ImageButton(this.f_97735_ + -66, this.f_97736_ + 40, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button3.png"), 137, 40, (e) -> {
         if (PlanetSel4DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(5, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 5, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PlanetSel4DProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button3", this.imagebutton_steel_button3);
      this.m_142416_(this.imagebutton_steel_button3);
      this.imagebutton_steel_button4 = new ImageButton(this.f_97735_ + -66, this.f_97736_ + -14, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button4.png"), 137, 40, (e) -> {
         if (PlanetSel2DProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(6, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 6, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PlanetSel2DProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_steel_button4", this.imagebutton_steel_button4);
      this.m_142416_(this.imagebutton_steel_button4);
      this.imagebutton_steel_button5 = new ImageButton(this.f_97735_ + -66, this.f_97736_ + -41, 137, 20, 0, 0, 20, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button5.png"), 137, 40, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(7, this.x, this.y, this.z, textstate));
         ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 7, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_steel_button5", this.imagebutton_steel_button5);
      this.m_142416_(this.imagebutton_steel_button5);
      this.imagebutton_right_pressed = new ImageButton(this.f_97735_ + 153, this.f_97736_ + -72, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_pressed.png"), 10, 20, (e) -> {
         if (SizeUpDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(8, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 8, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (SizeUpDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_pressed", this.imagebutton_right_pressed);
      this.m_142416_(this.imagebutton_right_pressed);
      this.imagebutton_right_pressed1 = new ImageButton(this.f_97735_ + 152, this.f_97736_ + -13, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_pressed1.png"), 10, 20, (e) -> {
         if (YawspeedupDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(9, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 9, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (YawspeedupDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_pressed1", this.imagebutton_right_pressed1);
      this.m_142416_(this.imagebutton_right_pressed1);
      this.imagebutton_right_pressed2 = new ImageButton(this.f_97735_ + 152, this.f_97736_ + 14, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_pressed2.png"), 10, 20, (e) -> {
         if (PitchspeedupDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(10, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 10, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PitchspeedupDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_pressed2", this.imagebutton_right_pressed2);
      this.m_142416_(this.imagebutton_right_pressed2);
      this.imagebutton_right_pressed3 = new ImageButton(this.f_97735_ + 152, this.f_97736_ + 41, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_pressed3.png"), 10, 20, (e) -> {
         if (RollspeedupDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(11, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 11, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (RollspeedupDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_pressed3", this.imagebutton_right_pressed3);
      this.m_142416_(this.imagebutton_right_pressed3);
      this.imagebutton_left_hover1 = new ImageButton(this.f_97735_ + 106, this.f_97736_ + -72, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover1.png"), 10, 20, (e) -> {
         if (SizedownDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(12, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 12, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (SizedownDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover1", this.imagebutton_left_hover1);
      this.m_142416_(this.imagebutton_left_hover1);
      this.imagebutton_left_hover2 = new ImageButton(this.f_97735_ + 106, this.f_97736_ + -13, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover2.png"), 10, 20, (e) -> {
         if (YawspeeddownDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(13, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 13, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (YawspeeddownDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover2", this.imagebutton_left_hover2);
      this.m_142416_(this.imagebutton_left_hover2);
      this.imagebutton_left_hover3 = new ImageButton(this.f_97735_ + 106, this.f_97736_ + 14, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover3.png"), 10, 20, (e) -> {
         if (PitchspeeddownDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(14, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 14, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PitchspeeddownDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover3", this.imagebutton_left_hover3);
      this.m_142416_(this.imagebutton_left_hover3);
      this.imagebutton_left_hover4 = new ImageButton(this.f_97735_ + 106, this.f_97736_ + 41, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover4.png"), 10, 20, (e) -> {
         if (RollspeeddownDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(15, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 15, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (RollspeeddownDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover4", this.imagebutton_left_hover4);
      this.m_142416_(this.imagebutton_left_hover4);
      this.imagebutton_steel_button_small1 = new ImageButton(this.f_97735_ + 114, this.f_97736_ + 60, 41, 13, 0, 0, 13, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_steel_button_small1.png"), 41, 26, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(16, this.x, this.y, this.z, textstate));
         ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 16, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_steel_button_small1", this.imagebutton_steel_button_small1);
      this.m_142416_(this.imagebutton_steel_button_small1);
      this.imagebutton_close = new ImageButton(this.f_97735_ + 81, this.f_97736_ + -52, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_close.png"), 10, 20, (e) -> {
         CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(17, this.x, this.y, this.z, textstate));
         ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 17, this.x, this.y, this.z, textstate);
      });
      guistate.put("button:imagebutton_close", this.imagebutton_close);
      this.m_142416_(this.imagebutton_close);
      this.imagebutton_down_hover = new ImageButton(this.f_97735_ + -3, this.f_97736_ + -53, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_down_hover.png"), 10, 20, (e) -> {
         if (PlanetPageupDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(18, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 18, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PlanetPageupDProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_down_hover", this.imagebutton_down_hover);
      this.m_142416_(this.imagebutton_down_hover);
      this.imagebutton_up_hover = new ImageButton(this.f_97735_ + -3, this.f_97736_ + 63, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_up_hover.png"), 10, 20, (e) -> {
         if (PlanetPagedownDProcedure.execute(this.world, this.entity)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(19, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 19, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (PlanetPagedownDProcedure.execute(ProjectionSelectorScreen.this.world, ProjectionSelectorScreen.this.entity)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_up_hover", this.imagebutton_up_hover);
      this.m_142416_(this.imagebutton_up_hover);
      this.imagebutton_right_pressed4 = new ImageButton(this.f_97735_ + 153, this.f_97736_ + -39, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_right_pressed4.png"), 10, 20, (e) -> {
         if (YoffsetupDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(20, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 20, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (YoffsetupDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_right_pressed4", this.imagebutton_right_pressed4);
      this.m_142416_(this.imagebutton_right_pressed4);
      this.imagebutton_left_hover5 = new ImageButton(this.f_97735_ + 106, this.f_97736_ + -39, 10, 10, 0, 0, 10, new ResourceLocation("cosmos:textures/screens/atlas/imagebutton_left_hover5.png"), 10, 20, (e) -> {
         if (YoffsetdownDProcedure.execute(this.world, (double)this.x, (double)this.y, (double)this.z)) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ProjectionSelectorButtonMessage(21, this.x, this.y, this.z, textstate));
            ProjectionSelectorButtonMessage.handleButtonAction(this.entity, 21, this.x, this.y, this.z, textstate);
         }

      }) {
         public void m_88315_(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
            if (YoffsetdownDProcedure.execute(ProjectionSelectorScreen.this.world, (double)ProjectionSelectorScreen.this.x, (double)ProjectionSelectorScreen.this.y, (double)ProjectionSelectorScreen.this.z)) {
               super.m_88315_(guiGraphics, gx, gy, ticks);
            }

         }
      };
      guistate.put("button:imagebutton_left_hover5", this.imagebutton_left_hover5);
      this.m_142416_(this.imagebutton_left_hover5);
   }

   static {
      guistate = ProjectionSelectorMenu.guistate;
      textstate = new HashMap();
      texture = new ResourceLocation("cosmos:textures/screens/projection_selector.png");
   }
}
