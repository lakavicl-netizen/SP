package net.lointain.cosmos.procedures;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;

public class TravelCheckProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         String texts = "";
         new ArrayList();
         boolean check = false;
         boolean flek = false;
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         new JsonObject();
         check = false;

         for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
            String var10000 = entity.m_9236_().m_46472_().m_135782_().toString();
            String var10001;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var10001 = _stringTag.m_7916_();
            } else {
               var10001 = "";
            }

            if (var10000.equals(var10001)) {
               check = true;
               break;
            }
         }

         if (!check) {
            ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.m_7614_(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.size(), StringTag.m_129297_(entity.m_9236_().m_46472_().m_135782_().toString()));
         }

         flek = false;

         for(Tag dataelementiterator : ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions) {
            String var19;
            if (dataelementiterator instanceof StringTag) {
               StringTag _stringTag = (StringTag)dataelementiterator;
               var19 = _stringTag.m_7916_();
            } else {
               var19 = "";
            }

            if ("none".equals(var19)) {
               flek = true;
               break;
            }
         }

         if (!flek) {
            ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.m_7614_(((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).traveled_dimentions.size(), StringTag.m_129297_("none"));
         }

      }
   }
}
