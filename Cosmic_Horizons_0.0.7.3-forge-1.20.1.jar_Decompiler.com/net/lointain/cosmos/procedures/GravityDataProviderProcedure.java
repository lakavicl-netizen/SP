package net.lointain.cosmos.procedures;

import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.LevelAccessor;

public class GravityDataProviderProcedure {
   public static double execute(LevelAccessor world, String dimension) {
      if (dimension == null) {
         return (double)0.0F;
      } else if (CosmosModVariables.WorldVariables.get(world).gravity_data.m_128441_(dimension)) {
         Tag var3 = CosmosModVariables.WorldVariables.get(world).gravity_data.m_128423_(dimension);
         double var10000;
         if (var3 instanceof FloatTag) {
            FloatTag _floatTag = (FloatTag)var3;
            var10000 = (double)_floatTag.m_7057_();
         } else {
            var10000 = (double)0.0F;
         }

         return var10000;
      } else {
         return (double)100.0F;
      }
   }
}
