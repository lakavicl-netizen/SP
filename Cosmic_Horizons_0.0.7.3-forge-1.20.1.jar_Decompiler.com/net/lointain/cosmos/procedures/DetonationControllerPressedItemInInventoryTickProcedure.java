package net.lointain.cosmos.procedures;

import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.init.CosmosModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;

public class DetonationControllerPressedItemInInventoryTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack, double slot) {
      if (entity != null) {
         double targetZ = (double)0.0F;
         double targetY = (double)0.0F;
         double targetX = (double)0.0F;
         if (itemstack.m_41720_() == CosmosModItems.DETONATION_CONTROLER.get() && itemstack.m_41784_().m_128471_("button")) {
            targetX = itemstack.m_41784_().m_128459_("targetX");
            targetY = itemstack.m_41784_().m_128459_("targetY");
            targetZ = itemstack.m_41784_().m_128459_("targetZ");
            int _slotid = (int)slot;
            ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.DETONATION_CONTROLLER_PRESSED.get())).m_41777_();
            _setstack.m_41764_(1);
            entity.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
               if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot) {
                  _modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
               }

            });
            CosmosMod.queueServerWork(20, () -> {
               int _slotid = (int)slot;
               ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.DETONATION_CONTROLER.get())).m_41777_();
               _setstack.m_41764_(1);
               entity.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null).ifPresent((capability) -> {
                  if (capability instanceof IItemHandlerModifiable _modHandlerEntSetSlot) {
                     _modHandlerEntSetSlot.setStackInSlot(_slotid, _setstack);
                  }

               });
               if (world instanceof Level _level) {
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_button.click_off")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_button.click_off")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }

            });
            itemstack.m_41784_().m_128379_("button", false);
         }

      }
   }
}
