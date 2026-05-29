package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;

public class Vis1Procedure {
   public static boolean execute(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         double t_size = (double)0.0F;
         ListTag final_list;
         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).search_bar.equals("")) {
            final_list = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world;
         } else {
            ListTag new_list = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world;

            for(int h = 0; h < new_list.size(); ++h) {
               Tag var8 = new_list.get(h);
               CompoundTag var10000;
               if (var8 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var8;
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

            final_list = new_list;
         }

         t_size = (double)final_list.size();
         return t_size > (double)0.0F;
      }
   }
}
