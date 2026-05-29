package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class Sel3Procedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         double t_size = (double)0.0F;
         ListTag final_list;
         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).search_bar.equals("")) {
            t_size = (double)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world.size();
            final_list = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world;
         } else {
            ListTag new_list = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world;

            for(int h = 0; h < new_list.size(); ++h) {
               Tag var9 = new_list.get(h);
               CompoundTag var10000;
               if (var9 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var9;
                  var10000 = _compoundTag.m_6426_();
               } else {
                  var10000 = new CompoundTag();
               }

               CompoundTag iter_map = var10000;

               for(String keyiterator : iter_map.m_128431_()) {
                  if (!keyiterator.toLowerCase().contains(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).search_bar.toLowerCase())) {
                     new_list.remove(h);
                  }
               }
            }

            t_size = (double)new_list.size();
            final_list = new_list;
         }

         if (t_size > (double)2.0F) {
            Tag var16 = final_list.get((int)((double)2.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter));
            CompoundTag var21;
            if (var16 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var16;
               var21 = _compoundTag.m_6426_();
            } else {
               var21 = new CompoundTag();
            }

            CompoundTag location_map = var21;

            for(String keyiterator : location_map.m_128431_()) {
               Tag var11 = location_map.m_128423_(keyiterator);
               String var22;
               if (var11 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var11;
                  var22 = _stringTag.m_7916_();
               } else {
                  var22 = "";
               }

               String _setval = var22;
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.landing_coords = _setval;
                  capability.syncPlayerVariables(entity);
               });
               if (entity instanceof Player) {
                  Player _player = (Player)entity;
                  if (!_player.m_9236_().m_5776_()) {
                     _player.m_5661_(Component.m_237113_("Landing On Spot \" " + keyiterator + " \""), true);
                  }
               }
            }
         }

      }
   }
}
