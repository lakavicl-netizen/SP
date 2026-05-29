package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Syssel1Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         new JsonObject();
         boolean travel_logic = false;
         Tag var11 = CosmosModVariables.WorldVariables.get(world).projection_list.get((int)((double)0.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter));
         JsonObject var10000;
         if (var11 instanceof StringTag) {
            StringTag _stringTag = (StringTag)var11;
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject data = var10000;

         for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
            String var25 = data.get("attached_dimention_id").getAsString();
            String var10001;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10001 = _stringTag.m_7916_();
            } else {
               var10001 = "";
            }

            if (var25.equals(var10001)) {
               travel_logic = true;
               break;
            }

            travel_logic = false;
         }

         if (travel_logic) {
            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128359_("system", data.get("attached_dimention_id").getAsString());
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128359_("planet", "none");
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            double _setval = (double)0.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_sel_index = _setval;
               capability.syncPlayerVariables(entity);
            });
            _setval = (double)0.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.planet_page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
