package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModParticleTypes;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class T2thrustsurfaceProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         world.m_7106_((SimpleParticleType)CosmosModParticleTypes.BLUETHRUSTED.get(), entity.m_20202_().m_20185_() + (double)-1.25F + entity.m_20202_().m_20154_().f_82479_ * 2.6, entity.m_20202_().m_20186_() + 1.3 + entity.m_20202_().m_20154_().f_82480_ * (double)-3.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll / (double)40.0F, entity.m_20202_().m_20189_() + entity.m_20202_().m_20154_().f_82481_ * 2.6, entity.m_20202_().m_20154_().f_82479_ * 2.2, entity.m_20202_().m_20154_().f_82480_ * -1.2, entity.m_20202_().m_20154_().f_82481_ * 2.2);
         world.m_7106_((SimpleParticleType)CosmosModParticleTypes.BLUETHRUSTED.get(), entity.m_20202_().m_20185_() + (double)1.25F + entity.m_20202_().m_20154_().f_82479_ * 2.6, entity.m_20202_().m_20186_() + 1.3 + entity.m_20202_().m_20154_().f_82480_ * (double)-3.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll / (double)40.0F * (double)-1.0F, entity.m_20202_().m_20189_() + entity.m_20202_().m_20154_().f_82481_ * 2.6, entity.m_20202_().m_20154_().f_82479_ * 2.2, entity.m_20202_().m_20154_().f_82480_ * -1.2, entity.m_20202_().m_20154_().f_82481_ * 2.2);
         world.m_7106_((SimpleParticleType)CosmosModParticleTypes.THRUST_SMOKE.get(), entity.m_20202_().m_20185_() + (double)-1.25F + entity.m_20202_().m_20154_().f_82479_ * 2.6, entity.m_20202_().m_20186_() + 1.3 + entity.m_20202_().m_20154_().f_82480_ * (double)-3.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll / (double)40.0F, entity.m_20202_().m_20189_() + entity.m_20202_().m_20154_().f_82481_ * 2.6, entity.m_20202_().m_20154_().f_82479_ * 2.2, entity.m_20202_().m_20154_().f_82480_ * -1.2, entity.m_20202_().m_20154_().f_82481_ * 2.2);
         world.m_7106_((SimpleParticleType)CosmosModParticleTypes.THRUST_SMOKE.get(), entity.m_20202_().m_20185_() + (double)1.25F + entity.m_20202_().m_20154_().f_82479_ * 2.6, entity.m_20202_().m_20186_() + 1.3 + entity.m_20202_().m_20154_().f_82480_ * (double)-3.0F + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).roll / (double)40.0F * (double)-1.0F, entity.m_20202_().m_20189_() + entity.m_20202_().m_20154_().f_82481_ * 2.6, entity.m_20202_().m_20154_().f_82479_ * 2.2, entity.m_20202_().m_20154_().f_82480_ * -1.2, entity.m_20202_().m_20154_().f_82481_ * 2.2);
      }
   }
}
