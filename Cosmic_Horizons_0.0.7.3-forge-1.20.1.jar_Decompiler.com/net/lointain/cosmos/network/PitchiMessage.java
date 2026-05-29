package net.lointain.cosmos.network;

import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.procedures.PitchiOnKeyPressedProcedure;
import net.lointain.cosmos.procedures.PitchiOnKeyReleasedProcedure;
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
public class PitchiMessage {
   int type;
   int pressedms;

   public PitchiMessage(int type, int pressedms) {
      this.type = type;
      this.pressedms = pressedms;
   }

   public PitchiMessage(FriendlyByteBuf buffer) {
      this.type = buffer.readInt();
      this.pressedms = buffer.readInt();
   }

   public static void buffer(PitchiMessage message, FriendlyByteBuf buffer) {
      buffer.writeInt(message.type);
      buffer.writeInt(message.pressedms);
   }

   public static void handler(PitchiMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
      NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
      context.enqueueWork(() -> pressAction(context.getSender(), message.type, message.pressedms));
      context.setPacketHandled(true);
   }

   public static void pressAction(Player entity, int type, int pressedms) {
      Level world = entity.m_9236_();
      double x = entity.m_20185_();
      double y = entity.m_20186_();
      double z = entity.m_20189_();
      if (world.m_46805_(entity.m_20183_())) {
         if (type == 0) {
            PitchiOnKeyPressedProcedure.execute(entity);
         }

         if (type == 1) {
            PitchiOnKeyReleasedProcedure.execute(entity);
         }

      }
   }

   @SubscribeEvent
   public static void registerMessage(FMLCommonSetupEvent event) {
      CosmosMod.addNetworkMessage(PitchiMessage.class, PitchiMessage::buffer, PitchiMessage::new, PitchiMessage::handler);
   }
}
