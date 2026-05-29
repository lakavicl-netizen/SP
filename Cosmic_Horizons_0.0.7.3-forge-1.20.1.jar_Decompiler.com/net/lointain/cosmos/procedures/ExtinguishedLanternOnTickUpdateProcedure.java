package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtinguishedLanternOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.EXTINGUISHED_LANTERN.get() || world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() != Blocks.f_50016_ && world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() != Blocks.f_50626_ && world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() != Blocks.f_50627_) {
         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.EXTINGUISHED_LANTERN_HANGING.get() && (world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50626_ || world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50627_)) {
            if (world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.break")), SoundSource.NEUTRAL, 1.0F, 1.0F);
               } else {
                  _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.break")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
               }
            }

            BlockPos _pos = BlockPos.m_274561_(x, y, z);
            Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
            world.m_46961_(_pos, false);
         }
      } else {
         if (world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.break")), SoundSource.NEUTRAL, 1.0F, 1.0F);
            } else {
               _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.break")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
            }
         }

         BlockPos _pos = BlockPos.m_274561_(x, y, z);
         Block.m_49892_(world.m_8055_(_pos), world, BlockPos.m_274561_(x + (double)0.5F, y + (double)0.5F, z + (double)0.5F), (BlockEntity)null);
         world.m_46961_(_pos, false);
      }

   }
}
