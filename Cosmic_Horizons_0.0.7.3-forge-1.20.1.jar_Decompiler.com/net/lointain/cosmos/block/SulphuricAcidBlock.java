package net.lointain.cosmos.block;

import net.lointain.cosmos.init.CosmosModFluids;
import net.lointain.cosmos.procedures.SulphuricAcidMobplayerCollidesBlockProcedure;
import net.lointain.cosmos.procedures.SulphuricAcidNeighbourBlockChangesProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class SulphuricAcidBlock extends LiquidBlock {
   public SulphuricAcidBlock() {
      super(() -> (FlowingFluid)CosmosModFluids.SULPHURIC_ACID.get(), Properties.m_284310_().m_284180_(MapColor.f_283895_).m_60978_(100.0F).m_60982_((bs, br, bp) -> true).m_60991_((bs, br, bp) -> true).m_60910_().m_222994_().m_278788_().m_278166_(PushReaction.DESTROY).m_60918_(SoundType.f_279557_).m_280170_());
   }

   public void m_6861_(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
      super.m_6861_(blockstate, world, pos, neighborBlock, fromPos, moving);
      SulphuricAcidNeighbourBlockChangesProcedure.execute(world, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_());
   }

   public void m_7892_(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.m_7892_(blockstate, world, pos, entity);
      SulphuricAcidMobplayerCollidesBlockProcedure.execute(entity);
   }
}
