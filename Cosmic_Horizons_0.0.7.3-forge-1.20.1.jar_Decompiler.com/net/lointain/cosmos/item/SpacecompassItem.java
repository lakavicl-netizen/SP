package net.lointain.cosmos.item;

import net.lointain.cosmos.procedures.SpacecompassItemInHandTickProcedure;
import net.lointain.cosmos.procedures.SpacecompassRightclickedOnBlockProcedure;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SpacecompassItem extends Item {
   public SpacecompassItem() {
      super((new Item.Properties()).m_41487_(1).m_41497_(Rarity.RARE));
   }

   public InteractionResult m_6225_(UseOnContext context) {
      super.m_6225_(context);
      SpacecompassRightclickedOnBlockProcedure.execute(context.m_43725_(), (double)context.m_8083_().m_123341_(), (double)context.m_8083_().m_123342_(), (double)context.m_8083_().m_123343_(), context.m_43722_());
      return InteractionResult.SUCCESS;
   }

   public void m_6883_(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.m_6883_(itemstack, world, entity, slot, selected);
      if (selected) {
         SpacecompassItemInHandTickProcedure.execute(entity, itemstack);
      }

   }
}
