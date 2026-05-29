package net.lointain.cosmos.item;

import net.lointain.cosmos.init.CosmosModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class SulphuricAcidItem extends BucketItem {
   public SulphuricAcidItem() {
      super(CosmosModFluids.SULPHURIC_ACID, (new Item.Properties()).m_41495_(Items.f_42446_).m_41487_(1).m_41497_(Rarity.COMMON));
   }
}
