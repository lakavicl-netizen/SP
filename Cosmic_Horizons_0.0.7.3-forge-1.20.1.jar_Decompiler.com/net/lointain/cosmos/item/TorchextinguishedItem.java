package net.lointain.cosmos.item;

import net.lointain.cosmos.procedures.TorchextinguishedRightclickedOnBlockProcedure;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;

public class TorchextinguishedItem extends Item {
   public TorchextinguishedItem() {
      super((new Item.Properties()).m_41487_(64).m_41497_(Rarity.COMMON));
   }

   public InteractionResult m_6225_(UseOnContext context) {
      super.m_6225_(context);
      TorchextinguishedRightclickedOnBlockProcedure.execute(context.m_43725_(), (double)context.m_8083_().m_123341_(), (double)context.m_8083_().m_123342_(), (double)context.m_8083_().m_123343_(), context.m_43719_());
      return InteractionResult.SUCCESS;
   }
}
