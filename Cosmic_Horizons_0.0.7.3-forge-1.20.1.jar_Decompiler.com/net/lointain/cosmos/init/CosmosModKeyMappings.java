package net.lointain.cosmos.init;

import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.CatchThrustMessage;
import net.lointain.cosmos.network.CustomCtrlMessage;
import net.lointain.cosmos.network.CustomShiftMessage;
import net.lointain.cosmos.network.DropThrustMessage;
import net.lointain.cosmos.network.OpenGUIMessage;
import net.lointain.cosmos.network.PitchdMessage;
import net.lointain.cosmos.network.PitchiMessage;
import net.lointain.cosmos.network.PlaceplatformMessage;
import net.lointain.cosmos.network.RolldMessage;
import net.lointain.cosmos.network.RolliMessage;
import net.lointain.cosmos.network.ThrustMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class CosmosModKeyMappings {
   public static final KeyMapping PITCHI = new KeyMapping("key.cosmos.pitchi", 83, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PitchiMessage(0, 0));
            PitchiMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.PITCHI_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.PITCHI_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new PitchiMessage(1, dt));
            PitchiMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping PITCHD = new KeyMapping("key.cosmos.pitchd", 87, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PitchdMessage(0, 0));
            PitchdMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.PITCHD_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.PITCHD_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new PitchdMessage(1, dt));
            PitchdMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping ROLLI = new KeyMapping("key.cosmos.rolli", 68, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new RolliMessage(0, 0));
            RolliMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.ROLLI_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.ROLLI_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new RolliMessage(1, dt));
            RolliMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping ROLLD = new KeyMapping("key.cosmos.rolld", 65, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new RolldMessage(0, 0));
            RolldMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.ROLLD_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.ROLLD_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new RolldMessage(1, dt));
            RolldMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping THRUST = new KeyMapping("key.cosmos.thrust", 32, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new ThrustMessage(0, 0));
            ThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.THRUST_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.THRUST_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new ThrustMessage(1, dt));
            ThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping DROP_THRUST = new KeyMapping("key.cosmos.drop_thrust", 264, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new DropThrustMessage(0, 0));
            DropThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.DROP_THRUST_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.DROP_THRUST_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new DropThrustMessage(1, dt));
            DropThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping CATCH_THRUST = new KeyMapping("key.cosmos.catch_thrust", 265, "key.categories.movement") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new CatchThrustMessage(0, 0));
            CatchThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.CATCH_THRUST_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.CATCH_THRUST_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new CatchThrustMessage(1, dt));
            CatchThrustMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping PLACEPLATFORM = new KeyMapping("key.cosmos.placeplatform", 78, "key.categories.misc") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new PlaceplatformMessage(0, 0));
            PlaceplatformMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping CUSTOM_CTRL = new KeyMapping("key.cosmos.custom_ctrl", 341, "key.categories.misc") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new CustomCtrlMessage(0, 0));
            CustomCtrlMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.CUSTOM_CTRL_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.CUSTOM_CTRL_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new CustomCtrlMessage(1, dt));
            CustomCtrlMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping CUSTOM_SHIFT = new KeyMapping("key.cosmos.custom_shift", 340, "key.categories.misc") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new CustomShiftMessage(0, 0));
            CustomShiftMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.CUSTOM_SHIFT_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.CUSTOM_SHIFT_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new CustomShiftMessage(1, dt));
            CustomShiftMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   public static final KeyMapping OPEN_GUI = new KeyMapping("key.cosmos.open_gui", 89, "key.categories.ui") {
      private boolean isDownOld = false;

      public void m_7249_(boolean isDown) {
         super.m_7249_(isDown);
         if (this.isDownOld != isDown && isDown) {
            CosmosMod.PACKET_HANDLER.sendToServer(new OpenGUIMessage(0, 0));
            OpenGUIMessage.pressAction(Minecraft.m_91087_().f_91074_, 0, 0);
            CosmosModKeyMappings.OPEN_GUI_LASTPRESS = System.currentTimeMillis();
         } else if (this.isDownOld != isDown && !isDown) {
            int dt = (int)(System.currentTimeMillis() - CosmosModKeyMappings.OPEN_GUI_LASTPRESS);
            CosmosMod.PACKET_HANDLER.sendToServer(new OpenGUIMessage(1, dt));
            OpenGUIMessage.pressAction(Minecraft.m_91087_().f_91074_, 1, dt);
         }

         this.isDownOld = isDown;
      }
   };
   private static long PITCHI_LASTPRESS = 0L;
   private static long PITCHD_LASTPRESS = 0L;
   private static long ROLLI_LASTPRESS = 0L;
   private static long ROLLD_LASTPRESS = 0L;
   private static long THRUST_LASTPRESS = 0L;
   private static long DROP_THRUST_LASTPRESS = 0L;
   private static long CATCH_THRUST_LASTPRESS = 0L;
   private static long CUSTOM_CTRL_LASTPRESS = 0L;
   private static long CUSTOM_SHIFT_LASTPRESS = 0L;
   private static long OPEN_GUI_LASTPRESS = 0L;

   @SubscribeEvent
   public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
      event.register(PITCHI);
      event.register(PITCHD);
      event.register(ROLLI);
      event.register(ROLLD);
      event.register(THRUST);
      event.register(DROP_THRUST);
      event.register(CATCH_THRUST);
      event.register(PLACEPLATFORM);
      event.register(CUSTOM_CTRL);
      event.register(CUSTOM_SHIFT);
      event.register(OPEN_GUI);
   }

   @EventBusSubscriber({Dist.CLIENT})
   public static class KeyEventListener {
      @SubscribeEvent
      public static void onClientTick(TickEvent.ClientTickEvent event) {
         if (Minecraft.m_91087_().f_91080_ == null) {
            CosmosModKeyMappings.PITCHI.m_90859_();
            CosmosModKeyMappings.PITCHD.m_90859_();
            CosmosModKeyMappings.ROLLI.m_90859_();
            CosmosModKeyMappings.ROLLD.m_90859_();
            CosmosModKeyMappings.THRUST.m_90859_();
            CosmosModKeyMappings.DROP_THRUST.m_90859_();
            CosmosModKeyMappings.CATCH_THRUST.m_90859_();
            CosmosModKeyMappings.PLACEPLATFORM.m_90859_();
            CosmosModKeyMappings.CUSTOM_CTRL.m_90859_();
            CosmosModKeyMappings.CUSTOM_SHIFT.m_90859_();
            CosmosModKeyMappings.OPEN_GUI.m_90859_();
         }

      }
   }
}
