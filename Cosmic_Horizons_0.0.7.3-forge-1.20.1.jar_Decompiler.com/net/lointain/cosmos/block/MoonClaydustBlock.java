package net.lointain.cosmos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MoonClaydustBlock extends Block {
   public MoonClaydustBlock() {
      super(Properties.m_284310_().m_60918_(SoundType.f_56717_).m_60913_(0.5F, 3.0F).m_60953_((s) -> 1));
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}
