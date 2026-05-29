package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class SulphuricAcidNeighbourBlockChangesProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x, y + (double)1.0F, z), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y + (double)1.0F, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x, y + (double)1.0F, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

      if (!world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x, y - (double)1.0F, z), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y + (double)1.0F, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x, y + (double)1.0F, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

      if (!world.m_8055_(BlockPos.m_274561_(x + (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x + (double)1.0F, y, z), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x + (double)1.0F, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x + (double)1.0F, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

      if (!world.m_8055_(BlockPos.m_274561_(x - (double)1.0F, y, z)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x - (double)1.0F, y, z), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x - (double)1.0F, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x - (double)1.0F, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

      if (!world.m_8055_(BlockPos.m_274561_(x, y, z + (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x, y, z + (double)1.0F), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z + (double)1.0F), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x, y, z + (double)1.0F, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

      if (!world.m_8055_(BlockPos.m_274561_(x, y, z - (double)1.0F)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:nodest")))) {
         world.m_7731_(BlockPos.m_274561_(x, y, z - (double)1.0F), Blocks.f_50016_.m_49966_(), 3);
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z - (double)1.0F), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x, y, z - (double)1.0F, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }
      }

   }
}
