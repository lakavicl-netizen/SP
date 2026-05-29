package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.server.ServerLifecycleHooks;

@EventBusSubscriber
public class TestRestartProcedure {
   private static long lastCheckTime = 0L;

   @SubscribeEvent
   public static void onClientTick(TickEvent.ClientTickEvent event) {
      if (event.phase == Phase.END && Minecraft.m_91087_().f_91073_ != null) {
         long currentTime = System.currentTimeMillis();
         if (currentTime - lastCheckTime >= 5500L) {
            if (CosmosModVariables.WorldVariables.get(Minecraft.m_91087_().f_91073_).datapack == null || CosmosModVariables.WorldVariables.get(Minecraft.m_91087_().f_91073_).datapack.isEmpty()) {
               System.out.println("Datapack is empty on client side. Reloading datapack...");
               reloadDatapack();
            }

            lastCheckTime = currentTime;
         }
      }

   }

   private static void reloadDatapack() {
      MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
      if (server != null) {
         TestRestartProcedure.DatapackReloadProcedure.reloadJsonObjects(server);
      }

   }

   @SubscribeEvent
   public static void onCommand(ServerStartedEvent event) {
      MinecraftServer server = event.getServer();
      if (server != null) {
         TestRestartProcedure.DatapackReloadProcedure.reloadJsonObjects(server);
      }

   }

   @SubscribeEvent
   public static void onDatapackSync(OnDatapackSyncEvent event) {
      MinecraftServer server = event.getPlayerList().m_7873_();
      if (server != null) {
         TestRestartProcedure.DatapackReloadProcedure.reloadJsonObjects(server);
      }

   }

   @SubscribeEvent
   public static void onLevelLoad(LevelEvent.Load event) {
      LevelAccessor var2 = event.getLevel();
      if (var2 instanceof ServerLevel serverLevel) {
         if (CosmosModVariables.WorldVariables.get(serverLevel).datapack == null || CosmosModVariables.WorldVariables.get(serverLevel).datapack.isEmpty()) {
            TestRestartProcedure.DatapackReloadProcedure.reloadJsonObjects(serverLevel.m_7654_());
         }
      }

   }

   public static void execute(@Nullable String command) {
      if ("reload".equals(command)) {
         reloadDatapack();
      }

   }

   public static class DatapackReloadProcedure {
      private static final Map<String, JsonObject> jsonObjects = new HashMap();
      private static boolean isReloading = false;

      public static synchronized void reloadJsonObjects(MinecraftServer server) {
         if (isReloading) {
            System.out.println("Reload already in progress. Skipping this reload.");
         } else {
            isReloading = true;

            try {
               ResourceManager resourceManager = server.m_177941_();
               CustomResourceOutput customResourceOutput = new CustomResourceOutput();
               jsonObjects.clear();

               for(String location : List.of("cosmos:cosmic_data")) {
                  ResourceLocation resourceLocation = new ResourceLocation(location);
                  resourceManager.m_7536_().forEach((packResources) -> packResources.m_8031_(PackType.SERVER_DATA, resourceLocation.m_135827_(), resourceLocation.m_135815_(), customResourceOutput));
               }

               for(ServerLevel level : server.m_129785_()) {
                  ListTag objectList = new ListTag();

                  for(Map.Entry<String, JsonObject> entry : jsonObjects.entrySet()) {
                     objectList.add(StringTag.m_129297_((new Gson()).toJson((JsonElement)entry.getValue())));
                  }

                  CosmosModVariables.WorldVariables.get(level).datapack = objectList;
                  CosmosModVariables.WorldVariables.get(level).syncData(level);
                  InitialiseregistryProcedure.execute(level);
               }
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               isReloading = false;
            }

         }
      }

      private static class CustomResourceOutput implements PackResources.ResourceOutput {
         public void accept(ResourceLocation resourceLocation, IoSupplier<InputStream> ioSupplier) {
            try {
               InputStream inputStream = (InputStream)ioSupplier.m_247737_();

               try {
                  JsonObject jsonObject = (JsonObject)(new Gson()).fromJson((String)(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n")), JsonObject.class);
                  String fullPath = resourceLocation.m_135815_();
                  String fileName = fullPath.substring(fullPath.lastIndexOf(47) + 1, fullPath.lastIndexOf(46));
                  TestRestartProcedure.DatapackReloadProcedure.jsonObjects.put(fileName, jsonObject);
               } catch (Throwable var8) {
                  if (inputStream != null) {
                     try {
                        inputStream.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }
                  }

                  throw var8;
               }

               if (inputStream != null) {
                  inputStream.close();
               }
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      }
   }
}
