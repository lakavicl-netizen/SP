package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class FrictionDataProviderProcedure {
   public static double execute(LevelAccessor world, String dimension) {
      if (dimension == null) {
         return (double)0.0F;
      } else {
         double num = (double)0.0F;
         if (CosmosModVariables.WorldVariables.get(world).friction_data.m_128441_(dimension)) {
            Tag var6 = CosmosModVariables.WorldVariables.get(world).friction_data.m_128423_(dimension);
            double var10000;
            if (var6 instanceof FloatTag) {
               FloatTag _floatTag = (FloatTag)var6;
               var10000 = (double)_floatTag.m_7057_();
            } else {
               var10000 = (double)0.0F;
            }

            return var10000;
         } else {
            return 0.98;
         }
      }
   }
}
