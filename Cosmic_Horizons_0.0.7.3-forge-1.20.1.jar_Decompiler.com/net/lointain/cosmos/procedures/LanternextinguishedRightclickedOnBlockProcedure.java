package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class LanternextinguishedRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Direction direction) {
      if (direction != null) {
         if (direction == Direction.UP) {
            if (world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50627_) {
               world.m_7731_(BlockPos.m_274561_(x, y + (double)1.0F, z), ((Block)CosmosModBlocks.EXTINGUISHED_LANTERN.get()).m_49966_(), 3);
               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         } else if (direction == Direction.DOWN && (world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y - (double)1.0F, z)).m_60734_() == Blocks.f_50627_)) {
            world.m_7731_(BlockPos.m_274561_(x, y - (double)1.0F, z), ((Block)CosmosModBlocks.EXTINGUISHED_LANTERN_HANGING.get()).m_49966_(), 3);
            if (world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.place")), SoundSource.NEUTRAL, 1.0F, 1.0F);
               } else {
                  _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.lantern.place")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
               }
            }
         }

      }
   }
}
