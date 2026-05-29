package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TitaniumspakerRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if ((world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50016_ || world.m_8055_(BlockPos.m_274561_(x, y + (double)1.0F, z)).m_60734_() == Blocks.f_50627_) && !world.m_8055_(BlockPos.m_274561_(x, y, z)).m_204336_(BlockTags.create(new ResourceLocation("spacestrix:undes")))) {
         world.m_7731_(BlockPos.m_274561_(x, y + (double)1.0F, z), ((Block)CosmosModBlocks.BRIGHT_FIRE.get()).m_49966_(), 3);
      }

   }
}
