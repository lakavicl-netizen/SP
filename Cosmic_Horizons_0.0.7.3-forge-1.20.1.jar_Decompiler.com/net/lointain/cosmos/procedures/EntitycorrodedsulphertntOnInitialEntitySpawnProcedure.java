package net.lointain.cosmos.procedures;

import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class EntitycorrodedsulphertntOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double particleRadius = (double)0.0F;
         double particleAmount = (double)0.0F;
         CosmosMod.queueServerWork(35, () -> {
            for(int index0 = 0; index0 < 3; ++index0) {
               world.m_7106_((SimpleParticleType)CosmosModParticleTypes.SULPHURIN.get(), x + (double)0.0F + Mth.m_216263_(RandomSource.m_216327_(), (double)-1.0F, (double)1.0F) * (double)8.0F, y + (double)0.0F + Mth.m_216263_(RandomSource.m_216327_(), (double)-1.0F, (double)1.0F) * (double)8.0F, z + (double)0.0F + Mth.m_216263_(RandomSource.m_216327_(), (double)-1.0F, (double)1.0F) * (double)8.0F, Mth.m_216263_(RandomSource.m_216327_(), -0.001, 0.001), Mth.m_216263_(RandomSource.m_216327_(), -0.001, 0.001), Mth.m_216263_(RandomSource.m_216327_(), -0.001, 0.001));
            }

            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_254849_((Entity)null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), 8.0F, ExplosionInteraction.TNT);
               }
            }

            if (!entity.m_9236_().m_5776_()) {
               entity.m_146870_();
            }

         });
      }
   }
}
