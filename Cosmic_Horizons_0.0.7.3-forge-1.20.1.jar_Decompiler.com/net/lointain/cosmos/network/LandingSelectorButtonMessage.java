package net.lointain.cosmos.network;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.procedures.CloseGUIProcedure;
import net.lointain.cosmos.procedures.DownCProcedure;
import net.lointain.cosmos.procedures.LandoriginProcedure;
import net.lointain.cosmos.procedures.SearchBarTextProvideProcedure;
import net.lointain.cosmos.procedures.Sel1Procedure;
import net.lointain.cosmos.procedures.Sel2Procedure;
import net.lointain.cosmos.procedures.Sel3Procedure;
import net.lointain.cosmos.procedures.Sel4Procedure;
import net.lointain.cosmos.procedures.UpCProcedure;
import net.lointain.cosmos.world.inventory.LandingSelectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class LandingSelectorButtonMessage {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;
   private HashMap<String, String> textstate;

   public LandingSelectorButtonMessage(FriendlyByteBuf buffer) {
      this.buttonID = buffer.readInt();
      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
      this.textstate = readTextState(buffer);
   }

   public LandingSelectorButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
      this.textstate = textstate;
   }

   public static void buffer(LandingSelectorButtonMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.buttonID);
      buffer.writeInt(message.x);
      buffer.writeInt(message.y);
      buffer.writeInt(message.z);
      writeTextState(message.textstate, buffer);
   }

   public static void handler(LandingSelectorButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
      NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
      context.enqueueWork(() -> {
         Player entity = context.getSender();
         int buttonID = message.buttonID;
         int x = message.x;
         int y = message.y;
         int z = message.z;
         HashMap<String, String> textstate = message.textstate;
         handleButtonAction(entity, buttonID, x, y, z, textstate);
      });
      context.setPacketHandled(true);
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
      Level world = entity.m_9236_();
      HashMap guistate = LandingSelectorMenu.guistate;

      for(Map.Entry<String, String> entry : textstate.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         guistate.put(key, value);
      }

      if (world.m_46805_(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            LandoriginProcedure.execute(entity);
         }

         if (buttonID == 1) {
            Sel1Procedure.execute(entity);
         }

         if (buttonID == 2) {
            Sel2Procedure.execute(entity);
         }

         if (buttonID == 3) {
            Sel3Procedure.execute(entity);
         }

         if (buttonID == 4) {
            Sel4Procedure.execute(entity);
         }

         if (buttonID == 5) {
            DownCProcedure.execute(entity);
         }

         if (buttonID == 6) {
            UpCProcedure.execute(entity);
         }

         if (buttonID == 7) {
            CloseGUIProcedure.execute(entity);
         }

         if (buttonID == 8) {
            SearchBarTextProvideProcedure.execute(entity, guistate);
         }

      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      CosmosMod.addNetworkMessage(LandingSelectorButtonMessage.class, LandingSelectorButtonMessage::buffer, LandingSelectorButtonMessage::new, LandingSelectorButtonMessage::handler);
   }

   public static void writeTextState(HashMap<String, String> map, FriendlyByteBuf buffer) {
      buffer.writeInt(map.size());

      for(Map.Entry<String, String> entry : map.entrySet()) {
         buffer.m_130083_(Component.m_237113_((String)entry.getKey()));
         buffer.m_130083_(Component.m_237113_((String)entry.getValue()));
      }

   }

   public static HashMap<String, String> readTextState(FriendlyByteBuf buffer) {
      int size = buffer.readInt();
      HashMap<String, String> map = new HashMap();

      for(int i = 0; i < size; ++i) {
         String key = buffer.m_130238_().getString();
         String value = buffer.m_130238_().getString();
         map.put(key, value);
      }

      return map;
   }
}
