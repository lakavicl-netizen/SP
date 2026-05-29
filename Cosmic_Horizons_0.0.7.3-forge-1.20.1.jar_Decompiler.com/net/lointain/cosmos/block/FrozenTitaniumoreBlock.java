package net.lointain.cosmos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FrozenTitaniumoreBlock extends Block {
   public FrozenTitaniumoreBlock() {
      super(Properties.m_284310_().m_60918_(SoundType.f_56743_).m_60913_(25.0F, 50.0F).m_60953_((s) -> 2).m_60999_().m_60982_((bs, br, bp) -> true).m_60991_((bs, br, bp) -> true));
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}
