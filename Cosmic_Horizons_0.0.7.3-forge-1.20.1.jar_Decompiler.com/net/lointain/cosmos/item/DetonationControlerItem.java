package net.lointain.cosmos.item;

import net.lointain.cosmos.procedures.DetonationControlerRightclickedOnBlockProcedure;
import net.lointain.cosmos.procedures.DetonationControlerRightclickedProcedure;
import net.lointain.cosmos.procedures.DetonationControllerPressedItemInInventoryTickProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class DetonationControlerItem extends Item {
   public DetonationControlerItem() {
      super((new Item.Properties()).m_41487_(1).m_41497_(Rarity.UNCOMMON));
   }

   public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
      DetonationControlerRightclickedProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, (ItemStack)ar.m_19095_());
      return ar;
   }

   public InteractionResult m_6225_(UseOnContext context) {
      super.m_6225_(context);
      DetonationControlerRightclickedOnBlockProcedure.execute(context.m_43725_(), (double)context.m_8083_().m_123341_(), (double)context.m_8083_().m_123342_(), (double)context.m_8083_().m_123343_(), context.m_43723_(), context.m_43722_());
      return InteractionResult.SUCCESS;
   }

   public void m_6883_(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.m_6883_(itemstack, world, entity, slot, selected);
      DetonationControllerPressedItemInInventoryTickProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, itemstack, (double)slot);
   }
}
