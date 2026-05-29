package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class InitialiseMainRegistryProcedure {
   private static PackResources.ResourceOutput resourceOutput = new CustomResourceOutput();
   private static List<JsonObject> jsonObjects = new ArrayList();

   private static List<JsonObject> list() {
      return jsonObjects;
   }

   private static void load(LevelAccessor levelAccessor, List<String> locations) {
      if (jsonObjects.isEmpty() && levelAccessor instanceof ServerLevel serverLevel) {
         ResourceManager resourceManager = serverLevel.m_7654_().m_177941_();

         for(String location : locations) {
            ResourceLocation resourceLocation = new ResourceLocation(location);
            resourceManager.m_7536_().forEach((e) -> e.m_8031_(PackType.SERVER_DATA, resourceLocation.m_135827_(), resourceLocation.m_135815_(), resourceOutput));
         }
      }

   }

   private static void clear() {
      jsonObjects.clear();
   }

   @SubscribeEvent
   public static void loadJsonObjects(LevelEvent.Load event) {
      execute(event, event.getLevel());
   }

   public static void execute(LevelAccessor world) {
      execute((Event)null, world);
   }

   private static void execute(@Nullable Event event, LevelAccessor world) {
      new JsonObject();
      ListTag objectList = new ListTag();
      if (world instanceof ServerLevel _resourceContext) {
         final Map<String, JsonObject> fileNameToJsonMap = new HashMap();
         ResourceManager _resourceManager = _resourceContext.m_7654_().m_177941_();

         class CustomResourceOutput implements PackResources.ResourceOutput {
            private PackResources packResources;

            public void setPackResources(PackResources packResources) {
               this.packResources = packResources;
            }

            public void accept(ResourceLocation resourceLocation, IoSupplier<InputStream> ioSupplier) {
               try {
                  JsonObject jsonObject = (JsonObject)(new Gson()).fromJson((String)(new BufferedReader(new InputStreamReader((InputStream)ioSupplier.m_247737_(), StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n")), JsonObject.class);
                  String fullPath = resourceLocation.m_135815_();
                  String fileName = fullPath.substring(fullPath.lastIndexOf(47) + 1, fullPath.lastIndexOf(46));
                  fileNameToJsonMap.put(fileName, jsonObject);
               } catch (Exception e) {
                  e.printStackTrace();
               }

            }
         }

         CustomResourceOutput _customResourceOutput = new CustomResourceOutput();

         for(String location : Arrays.asList("cosmos:cosmic_data")) {
            ResourceLocation _resourceLocation = new ResourceLocation(location);
            _resourceManager.m_7536_().forEach((_packResources) -> {
               _customResourceOutput.setPackResources(_packResources);
               _packResources.m_8031_(PackType.SERVER_DATA, _resourceLocation.m_135827_(), _resourceLocation.m_135815_(), _customResourceOutput);
            });
         }

         for(Map.Entry<String, JsonObject> entry : fileNameToJsonMap.entrySet()) {
            String fileName = (String)entry.getKey();
            JsonObject jsonObject = (JsonObject)entry.getValue();
            objectList.m_7614_(objectList.size(), StringTag.m_129297_((new Gson()).toJson(jsonObject)));
         }
      }

      CosmosModVariables.WorldVariables.get(world).datapack = objectList;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      InitialiseregistryProcedure.execute(world);
   }

   private static class CustomResourceOutput implements PackResources.ResourceOutput {
      public void accept(ResourceLocation resourceLocation, IoSupplier<InputStream> ioSupplier) {
         try {
            JsonObject jsonObject = (JsonObject)(new Gson()).fromJson((String)(new BufferedReader(new InputStreamReader((InputStream)ioSupplier.m_247737_(), StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n")), JsonObject.class);
            InitialiseMainRegistryProcedure.jsonObjects.add(jsonObject);
         } catch (Exception var4) {
         }

      }
   }

   private static class StringWrapper {
      public String value;
   }
}
