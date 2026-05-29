package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class SulphuricAcidMobplayerCollidesBlockProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity) {
            LivingEntity _entity = (LivingEntity)entity;
            if (!_entity.m_9236_().m_5776_()) {
               _entity.m_7292_(new MobEffectInstance((MobEffect)CosmosModMobEffects.SULPHURIC_SENSATION.get(), 15, 1));
            }
         }

      }
   }
}
