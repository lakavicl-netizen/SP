package net.lointain.cosmos.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class BrightFireEntityCollidesInTheBlockProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         entity.m_20254_(15);
         entity.m_6469_(new DamageSource(world.m_9598_().m_175515_(Registries.f_268580_).m_246971_(DamageTypes.f_268631_)), 4.0F);
      }
   }
}
