package net.lointain.cosmos.network;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.procedures.CloseGUIProcedure;
import net.lointain.cosmos.procedures.PagedownCProcedure;
import net.lointain.cosmos.procedures.PageupCProcedure;
import net.lointain.cosmos.procedures.PitchspeeddownProcedure;
import net.lointain.cosmos.procedures.PitchspeedupProcedure;
import net.lointain.cosmos.procedures.PlanetPagedownCProcedure;
import net.lointain.cosmos.procedures.PlanetPageupCProcedure;
import net.lointain.cosmos.procedures.PlanetSel1CProcedure;
import net.lointain.cosmos.procedures.PlanetSel2CProcedure;
import net.lointain.cosmos.procedures.PlanetSel3CProcedure;
import net.lointain.cosmos.procedures.PlanetSel4CProcedure;
import net.lointain.cosmos.procedures.ResetprojectionProcedure;
import net.lointain.cosmos.procedures.RollspeeddownProcedure;
import net.lointain.cosmos.procedures.RollspeedupProcedure;
import net.lointain.cosmos.procedures.SizedownProcedure;
import net.lointain.cosmos.procedures.SizeupProcedure;
import net.lointain.cosmos.procedures.Syssel1Procedure;
import net.lointain.cosmos.procedures.Syssel2Procedure;
import net.lointain.cosmos.procedures.YawspeeddownProcedure;
import net.lointain.cosmos.procedures.YawspeedupProcedure;
import net.lointain.cosmos.procedures.YoffsetdownProcedure;
import net.lointain.cosmos.procedures.YoffsetupProcedure;
import net.lointain.cosmos.world.inventory.ProjectionSelectorMenu;
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
public class ProjectionSelectorButtonMessage {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;
   private HashMap<String, String> textstate;

   public ProjectionSelectorButtonMessage(FriendlyByteBuf buffer) {
      this.buttonID = buffer.readInt();
      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
      this.textstate = readTextState(buffer);
   }

   public ProjectionSelectorButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
      this.textstate = textstate;
   }

   public static void buffer(ProjectionSelectorButtonMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.buttonID);
      buffer.writeInt(message.x);
      buffer.writeInt(message.y);
      buffer.writeInt(message.z);
      writeTextState(message.textstate, buffer);
   }

   public static void handler(ProjectionSelectorButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
      HashMap guistate = ProjectionSelectorMenu.guistate;

      for(Map.Entry<String, String> entry : textstate.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         guistate.put(key, value);
      }

      if (world.m_46805_(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            Syssel1Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 1) {
            Syssel2Procedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 2) {
            PagedownCProcedure.execute(world, entity);
         }

         if (buttonID == 3) {
            PageupCProcedure.execute(world, entity);
         }

         if (buttonID == 4) {
            PlanetSel3CProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 5) {
            PlanetSel4CProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 6) {
            PlanetSel2CProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 7) {
            PlanetSel1CProcedure.execute(world, (double)x, (double)y, (double)z, entity);
         }

         if (buttonID == 8) {
            SizeupProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 9) {
            YawspeedupProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 10) {
            PitchspeedupProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 11) {
            RollspeedupProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 12) {
            SizedownProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 13) {
            YawspeeddownProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 14) {
            PitchspeeddownProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 15) {
            RollspeeddownProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 16) {
            ResetprojectionProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 17) {
            CloseGUIProcedure.execute(entity);
         }

         if (buttonID == 18) {
            PlanetPageupCProcedure.execute(world, entity);
         }

         if (buttonID == 19) {
            PlanetPagedownCProcedure.execute(world, entity);
         }

         if (buttonID == 20) {
            YoffsetupProcedure.execute(world, (double)x, (double)y, (double)z);
         }

         if (buttonID == 21) {
            YoffsetdownProcedure.execute(world, (double)x, (double)y, (double)z);
         }

      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      CosmosMod.addNetworkMessage(ProjectionSelectorButtonMessage.class, ProjectionSelectorButtonMessage::buffer, ProjectionSelectorButtonMessage::new, ProjectionSelectorButtonMessage::handler);
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
