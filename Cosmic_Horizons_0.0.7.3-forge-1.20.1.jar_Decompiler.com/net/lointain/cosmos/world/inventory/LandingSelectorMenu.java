package net.lointain.cosmos.world.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.lointain.cosmos.init.CosmosModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class LandingSelectorMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
   public static final HashMap<String, Object> guistate = new HashMap();
   public final Level world;
   public final Player entity;
   public int x;
   public int y;
   public int z;
   private ContainerLevelAccess access;
   private IItemHandler internal;
   private final Map<Integer, Slot> customSlots;
   private boolean bound;
   private Supplier<Boolean> boundItemMatcher;
   private Entity boundEntity;
   private BlockEntity boundBlockEntity;

   public LandingSelectorMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
      super((MenuType)CosmosModMenus.LANDING_SELECTOR.get(), id);
      this.access = ContainerLevelAccess.f_39287_;
      this.customSlots = new HashMap();
      this.bound = false;
      this.boundItemMatcher = null;
      this.boundEntity = null;
      this.boundBlockEntity = null;
      this.entity = inv.f_35978_;
      this.world = inv.f_35978_.m_9236_();
      this.internal = new ItemStackHandler(0);
      BlockPos pos = null;
      if (extraData != null) {
         pos = extraData.m_130135_();
         this.x = pos.m_123341_();
         this.y = pos.m_123342_();
         this.z = pos.m_123343_();
         this.access = ContainerLevelAccess.m_39289_(this.world, pos);
      }

   }

   public boolean m_6875_(Player player) {
      if (this.bound) {
         if (this.boundItemMatcher != null) {
            return (Boolean)this.boundItemMatcher.get();
         }

         if (this.boundBlockEntity != null) {
            return AbstractContainerMenu.m_38889_(this.access, player, this.boundBlockEntity.m_58900_().m_60734_());
         }

         if (this.boundEntity != null) {
            return this.boundEntity.m_6084_();
         }
      }

      return true;
   }

   public ItemStack m_7648_(Player playerIn, int index) {
      return ItemStack.f_41583_;
   }

   public Map<Integer, Slot> get() {
      return this.customSlots;
   }
}
