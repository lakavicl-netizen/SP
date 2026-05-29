package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class OriginCProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         double posX = (double)0.0F;
         double posY = (double)0.0F;
         double posZ = (double)0.0F;
         boolean check = false;
         boolean dimention_check = false;
         List<Object> required_dimensions = new ArrayList();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         String texts = "";
         String dimension = "";
         Vec3 position = Vec3.f_82478_;
         Tag dataelementiterator = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         JsonObject var10000;
         if (dataelementiterator instanceof StringTag) {
            StringTag _stringTag = (StringTag)dataelementiterator;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject read_json = var10000;
         if (read_json.get("unlocking_dimension").getAsString().equals("none")) {
            check = true;
         } else {
            for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
               String var10001;
               if (dataelementiterator instanceof StringTag) {
                  StringTag _stringTag = (StringTag)dataelementiterator;
                  var10001 = _stringTag.m_7916_();
               } else {
                  var10001 = "";
               }

               required_dimensions.add(var10001);
            }

            label115:
            for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
               if (dataelementiterator instanceof StringTag) {
                  StringTag _stringTag = (StringTag)dataelementiterator;
                  var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
               } else {
                  var10000 = new JsonObject();
               }

               JsonObject gui_iter = var10000;
               if (gui_iter.has("gui_data")) {
                  JsonObject gui_data = gui_iter.get("gui_data").getAsJsonObject();

                  for(int l = 0; l < gui_data.size(); ++l) {
                     JsonObject gui_iter_data = gui_data.get((String)gui_data.keySet().stream().toList().get(l)).getAsJsonObject();
                     if (read_json.get("unlocking_dimension").getAsString().equals(gui_iter_data.get("travel_dimension").getAsString())) {
                        JsonObject object_data = gui_iter_data.get("object_data").getAsJsonObject();

                        for(int m = 0; m < object_data.size(); ++m) {
                           JsonObject iter_data = object_data.get((String)object_data.keySet().stream().toList().get(m)).getAsJsonObject();
                           if (!required_dimensions.contains(iter_data.get("unlocking_dimension").getAsString())) {
                              check = false;
                              continue label115;
                           }

                           check = true;
                        }
                        break;
                     }
                  }
               }
            }
         }

         dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            dataelementiterator = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var52;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var52 = _stringTag.m_7916_();
            } else {
               var52 = "";
            }

            if (var52.equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         dataelementiterator = CosmosModVariables.WorldVariables.get(world).planet_sel_data.get((int)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_sel_index);
         if (dataelementiterator instanceof StringTag) {
            StringTag _stringTag = (StringTag)dataelementiterator;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject travel_data = var10000;
         if (check && dimention_check && entity.m_20202_() instanceof RocketSeatEntity) {
            posX = travel_data.get("origin_x").getAsDouble() + (double)Mth.m_216271_(RandomSource.m_216327_(), -20, 20);
            posY = travel_data.get("origin_y").getAsDouble() + (double)Mth.m_216271_(RandomSource.m_216327_(), -8, 8);
            posZ = travel_data.get("origin_z").getAsDouble() + (double)Mth.m_216271_(RandomSource.m_216327_(), -20, 20);
            dimension = travel_data.get("travel_dimension").getAsString();
            if (!entity.m_9236_().m_46472_().m_135782_().toString().equals(travel_data.get("travel_dimension").getAsString())) {
               if (entity instanceof Player) {
                  Player _player = (Player)entity;
                  _player.m_6915_();
               }

               String _setval = entity.m_20202_().m_20149_();
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.vehicle = _setval;
                  capability.syncPlayerVariables(entity);
               });
               if (world instanceof ServerLevel) {
                  ServerLevel _level = (ServerLevel)world;
                  _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20202_().m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
               }

               if (world instanceof ServerLevel) {
                  ServerLevel _level = (ServerLevel)world;
                  _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
               }

               CosmosMod.queueServerWork(20, () -> {
                  for(int index0 = 0; index0 < 70; ++index0) {
                     if (!entity.m_20159_() && !(entity.m_20202_() instanceof RocketSeatEntity) && !entity.m_9236_().m_5776_() && entity.m_20194_() != null) {
                        Commands var10000 = entity.m_20194_().m_129892_();
                        CommandSourceStack var10001 = new CommandSourceStack(CommandSource.f_80164_, entity.m_20182_(), entity.m_20155_(), entity.m_9236_() instanceof ServerLevel ? (ServerLevel)entity.m_9236_() : null, 4, entity.m_7755_().getString(), entity.m_5446_(), entity.m_9236_().m_7654_(), entity);
                        String var10002 = entity.m_20149_();
                        var10000.m_230957_(var10001, "ride " + var10002 + " mount " + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).vehicle);
                     }
                  }

               });
            } else if (entity.m_9236_().m_46472_().m_135782_().toString().equals(travel_data.get("travel_dimension").getAsString())) {
               if (entity instanceof Player) {
                  Player _player = (Player)entity;
                  _player.m_6915_();
               }

               Entity _ent = entity.m_20202_();
               _ent.m_6021_(posX, posY, posZ);
               if (_ent instanceof ServerPlayer) {
                  ServerPlayer _serverPlayer = (ServerPlayer)_ent;
                  _serverPlayer.f_8906_.m_9774_(posX, posY, posZ, _ent.m_146908_(), _ent.m_146909_());
               }
            }
         }

      }
   }
}
