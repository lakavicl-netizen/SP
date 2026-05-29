package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class CablemodleupdatermaainProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!world.m_5776_()) {
         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLE_N.get()) {
            CablemodleupdaterNProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEE.get()) {
            CablemodleupdaterEProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEI.get()) {
            CablemodleupdaterIProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEL.get()) {
            CablemodleupdaterLProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLELC.get()) {
            CablemodleupdaterLcProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLELCC.get()) {
            CablemodleupdaterLccProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLELT.get()) {
            CablemodleupdaterLtProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLELTC.get()) {
            CablemodleupdaterLtcProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLET.get()) {
            CablemodleupdaterTProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLETC.get()) {
            CablemodleupdaterTcProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLETX.get()) {
            CablemodleupdaterTxProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLETXC.get()) {
            CablemodleupdaterTxcProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLETXCC.get()) {
            CablemodleupdaterTxccProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEX.get()) {
            CablemodleupdaterXProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEXC.get()) {
            CablemodleupdaterXcProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLEF.get()) {
            CablemodleupdaterFProcedure.execute(world, x, y, z);
         }

         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() != CosmosModBlocks.CABLES.get()) {
            CablemodleupdaterSProcedure.execute(world, x, y, z);
         }
      }

   }
}
