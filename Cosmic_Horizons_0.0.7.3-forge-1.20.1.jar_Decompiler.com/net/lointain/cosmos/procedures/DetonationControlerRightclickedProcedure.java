package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class DetonationControlerRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         double targetZ = (double)0.0F;
         double targetY = (double)0.0F;
         double targetX = (double)0.0F;
         if (world.m_8055_(new BlockPos(entity.m_9236_().m_45547_(new ClipContext(entity.m_20299_(1.0F), entity.m_20299_(1.0F).m_82549_(entity.m_20252_(1.0F).m_82490_((double)5.0F)), Block.OUTLINE, Fluid.NONE, entity)).m_82425_().m_123342_(), entity.m_9236_().m_45547_(new ClipContext(entity.m_20299_(1.0F), entity.m_20299_(1.0F).m_82549_(entity.m_20252_(1.0F).m_82490_((double)5.0F)), Block.OUTLINE, Fluid.NONE, entity)).m_82425_().m_123342_(), entity.m_9236_().m_45547_(new ClipContext(entity.m_20299_(1.0F), entity.m_20299_(1.0F).m_82549_(entity.m_20252_(1.0F).m_82490_((double)5.0F)), Block.OUTLINE, Fluid.NONE, entity)).m_82425_().m_123343_())).m_60734_() != CosmosModBlocks.DETONATOR_TARGET.get()) {
            targetX = itemstack.m_41784_().m_128459_("targetX");
            targetY = itemstack.m_41784_().m_128459_("targetY");
            targetZ = itemstack.m_41784_().m_128459_("targetZ");
            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(itemstack.m_41784_().m_128459_("targetX"), itemstack.m_41784_().m_128459_("targetY"), itemstack.m_41784_().m_128459_("targetZ"));
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_("detonate", true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            if (itemstack.m_41720_() == CosmosModItems.DETONATION_CONTROLER.get()) {
               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_button.click_on")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_button.click_on")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }

               itemstack.m_41784_().m_128379_("button", true);
            }
         }

      }
   }
}
