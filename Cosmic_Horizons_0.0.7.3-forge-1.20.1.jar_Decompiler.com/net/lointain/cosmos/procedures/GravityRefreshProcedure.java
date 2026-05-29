package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class GravityRefreshProcedure {
   @SubscribeEvent
   public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute((Event)null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         new JsonObject();
         new JsonObject();
         entity.m_20242_(false);
         if (entity instanceof Player || entity instanceof ServerPlayer) {
            boolean _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.pitch_i = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.pitch_d = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.roll_i = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.roll_d = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.thrust = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.thrust_drop = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.thrust_catch = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = false;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.open_gui = _setval;
               capability.syncPlayerVariables(entity);
            });
            if (entity instanceof Player) {
               Player _player = (Player)entity;
               if (!_player.m_9236_().m_5776_()) {
                  _player.m_5661_(Component.m_237113_("Type /unlock if ya wana unlock all planets in the menue which is opened by pressing Y while in Ship."), false);
               }
            }
         }

      }
   }
}
