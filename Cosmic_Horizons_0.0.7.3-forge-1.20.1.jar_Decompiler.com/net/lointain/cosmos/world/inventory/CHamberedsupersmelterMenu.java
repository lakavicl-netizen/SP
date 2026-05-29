package net.lointain.cosmos.world.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.client.gui.CHamberedsupersmelterScreen;
import net.lointain.cosmos.init.CosmosModMenus;
import net.lointain.cosmos.network.CHamberedsupersmelterSlotMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CHamberedsupersmelterMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
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

   public CHamberedsupersmelterMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
      super((MenuType)CosmosModMenus.C_HAMBEREDSUPERSMELTER.get(), id);
      this.access = ContainerLevelAccess.f_39287_;
      this.customSlots = new HashMap();
      this.bound = false;
      this.boundItemMatcher = null;
      this.boundEntity = null;
      this.boundBlockEntity = null;
      this.entity = inv.f_35978_;
      this.world = inv.f_35978_.m_9236_();
      this.internal = new ItemStackHandler(5);
      BlockPos pos = null;
      if (extraData != null) {
         pos = extraData.m_130135_();
         this.x = pos.m_123341_();
         this.y = pos.m_123342_();
         this.z = pos.m_123343_();
         this.access = ContainerLevelAccess.m_39289_(this.world, pos);
      }

      if (pos != null) {
         if (extraData.readableBytes() == 1) {
            byte hand = extraData.readByte();
            ItemStack itemstack = hand == 0 ? this.entity.m_21205_() : this.entity.m_21206_();
            this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.m_21205_() : this.entity.m_21206_());
            itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
               this.internal = capability;
               this.bound = true;
            });
         } else if (extraData.readableBytes() > 1) {
            extraData.readByte();
            this.boundEntity = this.world.m_6815_(extraData.m_130242_());
            if (this.boundEntity != null) {
               this.boundEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
                  this.internal = capability;
                  this.bound = true;
               });
            }
         } else {
            this.boundBlockEntity = this.world.m_7702_(pos);
            if (this.boundBlockEntity != null) {
               this.boundBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
                  this.internal = capability;
                  this.bound = true;
               });
            }
         }
      }

      this.customSlots.put(0, this.m_38897_(new SlotItemHandler(this.internal, 0, 87, 111) {
         private final int slot = 0;

         public void m_6654_() {
            super.m_6654_();
            CHamberedsupersmelterMenu.this.slotChanged(0, 0, 0);
         }

         public void m_142406_(Player entity, ItemStack stack) {
            super.m_142406_(entity, stack);
            CHamberedsupersmelterMenu.this.slotChanged(0, 1, 0);
         }

         public void m_40234_(ItemStack a, ItemStack b) {
            super.m_40234_(a, b);
            CHamberedsupersmelterMenu.this.slotChanged(0, 2, b.m_41613_() - a.m_41613_());
         }

         public boolean m_5857_(ItemStack stack) {
            return Items.f_42448_ == stack.m_41720_();
         }
      }));
      this.customSlots.put(2, this.m_38897_(new SlotItemHandler(this.internal, 2, 15, 30) {
         private final int slot = 2;
      }));
      this.customSlots.put(3, this.m_38897_(new SlotItemHandler(this.internal, 3, 159, 30) {
         private final int slot = 3;
      }));
      this.customSlots.put(4, this.m_38897_(new SlotItemHandler(this.internal, 4, 87, 57) {
         private final int slot = 4;

         public boolean m_5857_(ItemStack stack) {
            return false;
         }
      }));

      for(int si = 0; si < 3; ++si) {
         for(int sj = 0; sj < 9; ++sj) {
            this.m_38897_(new Slot(inv, sj + (si + 1) * 9, 16 + sj * 18, 146 + si * 18));
         }
      }

      for(int si = 0; si < 9; ++si) {
         this.m_38897_(new Slot(inv, si, 16 + si * 18, 204));
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
      ItemStack itemstack = ItemStack.f_41583_;
      Slot slot = (Slot)this.f_38839_.get(index);
      if (slot != null && slot.m_6657_()) {
         ItemStack itemstack1 = slot.m_7993_();
         itemstack = itemstack1.m_41777_();
         if (index < 4) {
            if (!this.m_38903_(itemstack1, 4, this.f_38839_.size(), true)) {
               return ItemStack.f_41583_;
            }

            slot.m_40234_(itemstack1, itemstack);
         } else if (!this.m_38903_(itemstack1, 0, 4, false)) {
            if (index < 31) {
               if (!this.m_38903_(itemstack1, 31, this.f_38839_.size(), true)) {
                  return ItemStack.f_41583_;
               }
            } else if (!this.m_38903_(itemstack1, 4, 31, false)) {
               return ItemStack.f_41583_;
            }

            return ItemStack.f_41583_;
         }

         if (itemstack1.m_41613_() == 0) {
            slot.m_5852_(ItemStack.f_41583_);
         } else {
            slot.m_6654_();
         }

         if (itemstack1.m_41613_() == itemstack.m_41613_()) {
            return ItemStack.f_41583_;
         }

         slot.m_142406_(playerIn, itemstack1);
      }

      return itemstack;
   }

   protected boolean m_38903_(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
      boolean flag = false;
      int i = p_38905_;
      if (p_38907_) {
         i = p_38906_ - 1;
      }

      if (p_38904_.m_41753_()) {
         while(!p_38904_.m_41619_()) {
            if (p_38907_) {
               if (i < p_38905_) {
                  break;
               }
            } else if (i >= p_38906_) {
               break;
            }

            Slot slot = (Slot)this.f_38839_.get(i);
            ItemStack itemstack = slot.m_7993_();
            if (slot.m_5857_(itemstack) && !itemstack.m_41619_() && ItemStack.m_150942_(p_38904_, itemstack)) {
               int j = itemstack.m_41613_() + p_38904_.m_41613_();
               int maxSize = Math.min(slot.m_6641_(), p_38904_.m_41741_());
               if (j <= maxSize) {
                  p_38904_.m_41764_(0);
                  itemstack.m_41764_(j);
                  slot.m_5852_(itemstack);
                  flag = true;
               } else if (itemstack.m_41613_() < maxSize) {
                  p_38904_.m_41774_(maxSize - itemstack.m_41613_());
                  itemstack.m_41764_(maxSize);
                  slot.m_5852_(itemstack);
                  flag = true;
               }
            }

            if (p_38907_) {
               --i;
            } else {
               ++i;
            }
         }
      }

      if (!p_38904_.m_41619_()) {
         if (p_38907_) {
            i = p_38906_ - 1;
         } else {
            i = p_38905_;
         }

         while(true) {
            if (p_38907_) {
               if (i < p_38905_) {
                  break;
               }
            } else if (i >= p_38906_) {
               break;
            }

            Slot slot1 = (Slot)this.f_38839_.get(i);
            ItemStack itemstack1 = slot1.m_7993_();
            if (itemstack1.m_41619_() && slot1.m_5857_(p_38904_)) {
               if (p_38904_.m_41613_() > slot1.m_6641_()) {
                  slot1.m_269060_(p_38904_.m_41620_(slot1.m_6641_()));
               } else {
                  slot1.m_269060_(p_38904_.m_41620_(p_38904_.m_41613_()));
               }

               slot1.m_6654_();
               flag = true;
               break;
            }

            if (p_38907_) {
               --i;
            } else {
               ++i;
            }
         }
      }

      return flag;
   }

   public void m_6877_(Player playerIn) {
      super.m_6877_(playerIn);
      if (!this.bound && playerIn instanceof ServerPlayer serverPlayer) {
         if (serverPlayer.m_6084_() && !serverPlayer.m_9232_()) {
            for(int i = 0; i < this.internal.getSlots(); ++i) {
               playerIn.m_150109_().m_150079_(this.internal.extractItem(i, this.internal.getStackInSlot(i).m_41613_(), false));
            }
         } else {
            for(int j = 0; j < this.internal.getSlots(); ++j) {
               playerIn.m_36176_(this.internal.extractItem(j, this.internal.getStackInSlot(j).m_41613_(), false), false);
            }
         }
      }

   }

   private void slotChanged(int slotid, int ctype, int meta) {
      if (this.world != null && this.world.m_5776_()) {
         CosmosMod.PACKET_HANDLER.sendToServer(new CHamberedsupersmelterSlotMessage(slotid, this.x, this.y, this.z, ctype, meta, CHamberedsupersmelterScreen.getTextboxValues()));
         CHamberedsupersmelterSlotMessage.handleSlotAction(this.entity, slotid, ctype, meta, this.x, this.y, this.z, CHamberedsupersmelterScreen.getTextboxValues());
      }

   }

   public Map<Integer, Slot> get() {
      return this.customSlots;
   }
}
