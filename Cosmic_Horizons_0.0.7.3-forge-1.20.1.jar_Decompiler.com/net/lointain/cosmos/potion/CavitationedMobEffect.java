package net.lointain.cosmos.potion;

import net.lointain.cosmos.procedures.CavitationedOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CavitationedMobEffect extends MobEffect {
   public CavitationedMobEffect() {
      super(MobEffectCategory.HARMFUL, -14994621);
   }

   public void m_6742_(LivingEntity entity, int amplifier) {
      CavitationedOnEffectActiveTickProcedure.execute(entity.m_9236_(), entity);
   }

   public boolean m_6584_(int duration, int amplifier) {
      return true;
   }
}
