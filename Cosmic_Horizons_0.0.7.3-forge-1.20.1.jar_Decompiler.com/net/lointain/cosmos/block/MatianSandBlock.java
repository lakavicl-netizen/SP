package net.lointain.cosmos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class MatianSandBlock extends Block {
   public MatianSandBlock() {
      super(Properties.m_284310_().m_280658_(NoteBlockInstrument.SNARE).m_60918_(SoundType.f_56746_).m_60913_(3.0F, 10.0F));
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}
