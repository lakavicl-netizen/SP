package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;

public class UpCProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         double t_size = (double)0.0F;
         if (((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).search_bar.equals("")) {
            t_size = (double)((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world.size();
         } else {
            ListTag new_list = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).entry_world;

            for(int h = 0; h < new_list.size(); ++h) {
               Tag var7 = new_list.get(h);
               CompoundTag var10000;
               if (var7 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var7;
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
         }

         if (t_size > (double)4.0F && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter > (double)0.0F) {
            double _setval = ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).page_iter - (double)1.0F;
            entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
               capability.page_iter = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

      }
   }
}
