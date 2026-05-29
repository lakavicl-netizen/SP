package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class HurtbyWeatherProcedure {
   @SubscribeEvent
   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
      if (event.phase == Phase.END) {
         execute(event);
      }

   }

   public static void execute() {
      execute((Event)null);
   }

   private static void execute(@Nullable Event event) {
      new JsonObject();
      new JsonObject();
      new JsonObject();
      boolean logic = false;
      String generic = "";
      String special = "";
   }
}
