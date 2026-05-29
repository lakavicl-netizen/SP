package net.lointain.cosmos.procedures;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;

public class CheckforlavaProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (((<undefinedtype>)(new Object() {
         public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicReference<ItemStack> _retval = new AtomicReference(ItemStack.f_41583_);
            BlockEntity _ent = world.m_7702_(pos);
            if (_ent != null) {
               _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> _retval.set(capability.getStackInSlot(slotid).m_41777_()));
            }

            return (ItemStack)_retval.get();
         }
      })).getItemStack(world, BlockPos.m_274561_(x, y, z), 0).m_41720_() == Items.f_42448_ && ((<undefinedtype>)(new Object() {
         public int getEnergyStored(LevelAccessor level, BlockPos pos) {
            AtomicInteger _retval = new AtomicInteger(0);
            BlockEntity _ent = level.m_7702_(pos);
            if (_ent != null) {
               _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> _retval.set(capability.getEnergyStored()));
            }

            return _retval.get();
         }
      })).getEnergyStored(world, BlockPos.m_274561_(x, y, z)) < 1200) {
         BlockEntity _ent = world.m_7702_(BlockPos.m_274561_(x, y, z));
         int _amount = 200;
         if (_ent != null) {
            _ent.getCapability(ForgeCapabilities.ENERGY, (Direction)null).ifPresent((capability) -> capability.receiveEnergy(_amount, false));
         }

         _ent = world.m_7702_(BlockPos.m_274561_(x, y, z));
         if (_ent != null) {
            _amount = 0;
            ItemStack _setstack = (new ItemStack(Items.f_42446_)).m_41777_();
            _setstack.m_41764_(1);
            _ent.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
               if (capability instanceof IItemHandlerModifiable) {
                  ((IItemHandlerModifiable)capability).setStackInSlot(0, _setstack);
               }

            });
         }
      }

   }
}
