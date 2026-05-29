package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class SpacecompassRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
      if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == Blocks.f_50729_) {
         itemstack.m_41663_(Enchantments.f_44968_, 0);
         itemstack.m_41784_().m_128347_("X", x);
         itemstack.m_41784_().m_128347_("Y", y);
         itemstack.m_41784_().m_128347_("Z", z);
         itemstack.m_41784_().m_128347_("HideFlags", (double)1.0F);
      }

   }
}
