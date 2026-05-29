package net.lointain.cosmos.potion;

import net.lointain.cosmos.procedures.OxygendeprivedOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class OxygendeprivedMobEffect extends MobEffect {
   public OxygendeprivedMobEffect() {
      super(MobEffectCategory.HARMFUL, -13421773);
   }

   public void m_6742_(LivingEntity entity, int amplifier) {
      OxygendeprivedOnEffectActiveTickProcedure.execute(entity.m_9236_(), entity);
   }

   public boolean m_6584_(int duration, int amplifier) {
      return true;
   }
}
