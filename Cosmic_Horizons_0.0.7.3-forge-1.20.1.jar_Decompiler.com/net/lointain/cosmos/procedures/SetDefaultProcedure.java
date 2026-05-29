package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class SetDefaultProcedure {
   @SubscribeEvent
   public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
      execute(event, event.getEntity().m_9236_(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         new JsonObject();
         new JsonObject();
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
         String _setval = "^";
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.landing_coords = _setval;
            capability.syncPlayerVariables(entity);
         });
         _setval = "";
         entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
            capability.search_bar = _setval;
            capability.syncPlayerVariables(entity);
         });
         CompoundTag gravity_map = new CompoundTag();
         CompoundTag friction_map = new CompoundTag();

         for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
            JsonObject var10000;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
            } else {
               var10000 = new JsonObject();
            }

            JsonObject data = var10000;
            if (data.has("dimensional_data")) {
               JsonObject gravity_data = data.get("dimensional_data").getAsJsonObject();
               if (gravity_data.has("gravity")) {
                  gravity_map.m_128365_(data.get("attached_dimention_id").getAsString(), FloatTag.m_128566_((float)(gravity_data.get("gravity").getAsDouble() / (double)100.0F)));
                  friction_map.m_128365_(data.get("attached_dimention_id").getAsString(), FloatTag.m_128566_((float)gravity_data.get("air_resistance").getAsDouble()));
               }
            }
         }

         CosmosModVariables.WorldVariables.get(world).gravity_data = gravity_map;
         CosmosModVariables.WorldVariables.get(world).syncData(world);
         CosmosModVariables.WorldVariables.get(world).friction_data = friction_map;
         CosmosModVariables.WorldVariables.get(world).syncData(world);
      }
   }
}
