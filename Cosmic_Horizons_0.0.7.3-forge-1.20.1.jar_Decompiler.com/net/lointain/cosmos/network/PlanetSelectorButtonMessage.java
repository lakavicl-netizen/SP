package net.lointain.cosmos.network;

import java.util.HashMap;
import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.procedures.OriginCProcedure;
import net.lointain.cosmos.procedures.PSel1CProcedure;
import net.lointain.cosmos.procedures.PSel2CProcedure;
import net.lointain.cosmos.procedures.PSel3CProcedure;
import net.lointain.cosmos.procedures.PSel4CProcedure;
import net.lointain.cosmos.procedures.PSel5CProcedure;
import net.lointain.cosmos.procedures.Sel1CProcedure;
import net.lointain.cosmos.procedures.Sel2CProcedure;
import net.lointain.cosmos.procedures.Sel3CProcedure;
import net.lointain.cosmos.procedures.Sel4CProcedure;
import net.lointain.cosmos.procedures.SeldownCProcedure;
import net.lointain.cosmos.procedures.SelupCProcedure;
import net.lointain.cosmos.procedures.TravelCProcedure;
import net.lointain.cosmos.procedures.TravelCoordProcedure;
import net.lointain.cosmos.procedures.TraveldownCProcedure;
import net.lointain.cosmos.procedures.TravelupCProcedure;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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
public class PlanetSelectorButtonMessage {
   private final int buttonID;
   private final int x;
   private final int y;
   private final int z;

   public PlanetSelectorButtonMessage(FriendlyByteBuf buffer) {
      this.buttonID = buffer.readInt();
      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
   }

   public PlanetSelectorButtonMessage(int buttonID, int x, int y, int z) {
      this.buttonID = buttonID;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public static void buffer(PlanetSelectorButtonMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.buttonID);
      buffer.writeInt(message.x);
      buffer.writeInt(message.y);
      buffer.writeInt(message.z);
   }

   public static void handler(PlanetSelectorButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
      NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
      context.enqueueWork(() -> {
         Player entity = context.getSender();
         int buttonID = message.buttonID;
         int x = message.x;
         int y = message.y;
         int z = message.z;
         handleButtonAction(entity, buttonID, x, y, z);
      });
      context.setPacketHandled(true);
   }

   public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
      Level world = entity.m_9236_();
      HashMap guistate = PlanetSelectorMenu.guistate;
      if (world.m_46805_(new BlockPos(x, y, z))) {
         if (buttonID == 0) {
            TravelCoordProcedure.execute(world, entity, guistate);
         }

         if (buttonID == 1) {
            SelupCProcedure.execute(world, entity);
         }

         if (buttonID == 2) {
            SeldownCProcedure.execute(world, entity);
         }

         if (buttonID == 3) {
            Sel1CProcedure.execute(entity);
         }

         if (buttonID == 4) {
            Sel2CProcedure.execute(entity);
         }

         if (buttonID == 5) {
            Sel3CProcedure.execute(entity);
         }

         if (buttonID == 6) {
            Sel4CProcedure.execute(entity);
         }

         if (buttonID == 7) {
            OriginCProcedure.execute(world, entity);
         }

         if (buttonID == 8) {
            TravelupCProcedure.execute(world, entity);
         }

         if (buttonID == 9) {
            TraveldownCProcedure.execute(world, entity);
         }

         if (buttonID == 10) {
            TravelCProcedure.execute(world, entity);
         }

         if (buttonID == 11) {
            PSel1CProcedure.execute(world, entity);
         }

         if (buttonID == 12) {
            PSel2CProcedure.execute(world, entity);
         }

         if (buttonID == 13) {
            PSel3CProcedure.execute(world, entity);
         }

         if (buttonID == 14) {
            PSel4CProcedure.execute(world, entity);
         }

         if (buttonID == 15) {
            PSel5CProcedure.execute(world, entity);
         }

      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      CosmosMod.addNetworkMessage(PlanetSelectorButtonMessage.class, PlanetSelectorButtonMessage::buffer, PlanetSelectorButtonMessage::new, PlanetSelectorButtonMessage::handler);
   }
}
