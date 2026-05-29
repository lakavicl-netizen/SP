package net.lointain.cosmos.block;

import net.lointain.cosmos.init.CosmosModFluids;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class CrudeOilBlock extends LiquidBlock {
   public CrudeOilBlock() {
      super(() -> (FlowingFluid)CosmosModFluids.CRUDE_OIL.get(), Properties.m_284310_().m_284180_(MapColor.f_283864_).m_60978_(100.0F).m_60910_().m_222994_().m_278788_().m_278166_(PushReaction.DESTROY).m_60918_(SoundType.f_279557_).m_280170_());
   }
}
