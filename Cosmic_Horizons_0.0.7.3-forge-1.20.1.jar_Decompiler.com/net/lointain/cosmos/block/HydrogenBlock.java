package net.lointain.cosmos.block;

import net.lointain.cosmos.procedures.GasedCollidesInTheBlockProcedure;
import net.lointain.cosmos.procedures.NitrogenOnTickUpdateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.HitResult;

public class HydrogenBlock extends Block {
   public HydrogenBlock() {
      super(Properties.m_284310_().m_278183_().m_60918_(SoundType.f_56747_).m_60913_(-1.0F, 3600000.0F).m_60977_());
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }

   public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
      return new ItemStack(Blocks.f_50016_);
   }

   public void m_213897_(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
      super.m_213897_(blockstate, world, pos, random);
      int x = pos.m_123341_();
      int y = pos.m_123342_();
      int z = pos.m_123343_();
      NitrogenOnTickUpdateProcedure.execute(world, (double)x, (double)y, (double)z);
   }

   public void m_7892_(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.m_7892_(blockstate, world, pos, entity);
      GasedCollidesInTheBlockProcedure.execute(world, entity);
   }
}
