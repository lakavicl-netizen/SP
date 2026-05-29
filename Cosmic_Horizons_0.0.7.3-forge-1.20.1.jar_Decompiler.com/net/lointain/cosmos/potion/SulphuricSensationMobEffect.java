package net.lointain.cosmos.potion;

import net.lointain.cosmos.procedures.SulphuricSensationOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SulphuricSensationMobEffect extends MobEffect {
   public SulphuricSensationMobEffect() {
      super(MobEffectCategory.HARMFUL, -1);
   }

   public void m_6742_(LivingEntity entity, int amplifier) {
      SulphuricSensationOnEffectActiveTickProcedure.execute(entity.m_9236_(), entity);
   }

   public boolean m_6584_(int duration, int amplifier) {
      return true;
   }
}
