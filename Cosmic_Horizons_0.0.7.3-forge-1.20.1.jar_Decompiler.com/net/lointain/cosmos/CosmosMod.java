package net.lointain.cosmos;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.lointain.cosmos.init.CosmosModBlockEntities;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.init.CosmosModEntities;
import net.lointain.cosmos.init.CosmosModFluidTypes;
import net.lointain.cosmos.init.CosmosModFluids;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.init.CosmosModMenus;
import net.lointain.cosmos.init.CosmosModMobEffects;
import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.lointain.cosmos.init.CosmosModSounds;
import net.lointain.cosmos.init.CosmosModTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("cosmos")
public class CosmosMod {
   public static final Logger LOGGER = LogManager.getLogger(CosmosMod.class);
   public static final String MODID = "cosmos";
   private static final String PROTOCOL_VERSION = "1";
   public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("cosmos", "cosmos"), () -> "1", "1"::equals, "1"::equals);
   private static int messageID = 0;
   private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue();

   public CosmosMod() {
      MinecraftForge.EVENT_BUS.register(this);
      IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
      CosmosModSounds.REGISTRY.register(bus);
      CosmosModBlocks.REGISTRY.register(bus);
      CosmosModBlockEntities.REGISTRY.register(bus);
      CosmosModItems.REGISTRY.register(bus);
      CosmosModEntities.REGISTRY.register(bus);
      CosmosModTabs.REGISTRY.register(bus);
      CosmosModMobEffects.REGISTRY.register(bus);
      CosmosModParticleTypes.REGISTRY.register(bus);
      CosmosModMenus.REGISTRY.register(bus);
      CosmosModFluids.REGISTRY.register(bus);
      CosmosModFluidTypes.REGISTRY.register(bus);

      for(ResourceLocation key : BuiltInRegistries.f_257002_.m_6566_()) {
         System.out.println("Registered Density Function: " + key);
      }

   }

   public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
      PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
      ++messageID;
   }

   public static void queueServerWork(int tick, Runnable action) {
      if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
         workQueue.add(new AbstractMap.SimpleEntry(action, tick));
      }

   }

   @SubscribeEvent
   public void tick(TickEvent.ServerTickEvent event) {
      if (event.phase == Phase.END) {
         List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList();
         workQueue.forEach((work) -> {
            work.setValue((Integer)work.getValue() - 1);
            if ((Integer)work.getValue() == 0) {
               actions.add(work);
            }

         });
         actions.forEach((e) -> ((Runnable)e.getKey()).run());
         workQueue.removeAll(actions);
      }

   }

   public static class TextboxSetMessage {
      private final String textboxid;
      private final String data;

      public TextboxSetMessage(FriendlyByteBuf buffer) {
         this.textboxid = buffer.m_130238_().getString();
         this.data = buffer.m_130238_().getString();
      }

      public TextboxSetMessage(String textboxid, String data) {
         this.textboxid = textboxid;
         this.data = data;
      }

      public static void buffer(TextboxSetMessage message, FriendlyByteBuf buffer) {
         buffer.m_130083_(Component.m_237113_(message.textboxid));
         buffer.m_130083_(Component.m_237113_(message.data));
      }

      public static void handler(TextboxSetMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
         NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
         context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
               Screen currentScreen = Minecraft.m_91087_().f_91080_;
               Map<String, EditBox> textFieldsMap = new HashMap();
               if (currentScreen != null) {
                  Field[] fields = currentScreen.getClass().getDeclaredFields();

                  for(Field field : fields) {
                     if (EditBox.class.isAssignableFrom(field.getType())) {
                        try {
                           field.setAccessible(true);
                           EditBox textField = (EditBox)field.get(currentScreen);
                           if (textField != null) {
                              textFieldsMap.put(field.getName(), textField);
                           }
                        } catch (IllegalAccessException ex) {
                           StringWriter sw = new StringWriter();
                           PrintWriter pw = new PrintWriter(sw);
                           ex.printStackTrace(pw);
                           String exceptionAsString = sw.toString();
                           CosmosMod.LOGGER.error(exceptionAsString);
                        }
                     }
                  }
               }

               if (textFieldsMap.get(message.textboxid) != null) {
                  ((EditBox)textFieldsMap.get(message.textboxid)).m_94144_(message.data);
               }
            }

         });
         context.setPacketHandled(true);
      }
   }

   @EventBusSubscriber(
      bus = Bus.MOD
   )
   public static class initer {
      @SubscribeEvent
      public static void init(FMLCommonSetupEvent event) {
         CosmosMod.addNetworkMessage(TextboxSetMessage.class, TextboxSetMessage::buffer, TextboxSetMessage::new, TextboxSetMessage::handler);
      }
   }
}
