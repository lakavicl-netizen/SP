package net.lointain.cosmos.item;

import net.lointain.cosmos.procedures.LanternextinguishedRightclickedOnBlockProcedure;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;

public class LanternextinguishedItem extends Item {
   public LanternextinguishedItem() {
      super((new Item.Properties()).m_41487_(64).m_41497_(Rarity.COMMON));
   }

   public InteractionResult m_6225_(UseOnContext context) {
      super.m_6225_(context);
      LanternextinguishedRightclickedOnBlockProcedure.execute(context.m_43725_(), (double)context.m_8083_().m_123341_(), (double)context.m_8083_().m_123342_(), (double)context.m_8083_().m_123343_(), context.m_43719_());
      return InteractionResult.SUCCESS;
   }
}
