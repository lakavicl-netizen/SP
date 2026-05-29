package net.lointain.cosmos.procedures;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber
public class ConvertdeadProcedure {
   @SubscribeEvent
   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
      if (event.phase == Phase.END) {
         execute(event, event.player.m_9236_(), event.player);
      }

   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         label73: {
            boolean dimention_check = false;
            if (entity instanceof Player) {
               Player _playerHasItem = (Player)entity;
               if (_playerHasItem.m_150109_().m_36063_(new ItemStack(Items.f_42522_))) {
                  break label73;
               }
            }

            if (!(entity instanceof Player)) {
               return;
            }

            Player _playerHasItem = (Player)entity;
            if (!_playerHasItem.m_150109_().m_36063_(new ItemStack((ItemLike)CosmosModItems.DEADCOMPASS.get()))) {
               return;
            }
         }

         boolean var11;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var7 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var10000;
            if (var7 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var7;
               var10000 = _stringTag.m_7916_();
            } else {
               var10000 = "";
            }

            if (var10000.equals("space")) {
               var11 = true;
            } else {
               var11 = false;
            }
         } else {
            var11 = false;
         }

         AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference();
         LazyOptional var20 = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, (Direction)null);
         Objects.requireNonNull(_iitemhandlerref);
         var20.ifPresent(_iitemhandlerref::set);
         if (_iitemhandlerref.get() != null) {
            for(int _idx = 0; _idx < ((IItemHandler)_iitemhandlerref.get()).getSlots(); ++_idx) {
               ItemStack itemstackiterator = ((IItemHandler)_iitemhandlerref.get()).getStackInSlot(_idx).m_41777_();
               if (var11) {
                  if (itemstackiterator.m_41720_() == Items.f_42522_) {
                     if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        ItemStack _stktoremove = new ItemStack(Items.f_42522_);
                        _player.m_150109_().m_36022_((p) -> _stktoremove.m_41720_() == p.m_41720_(), 1, _player.f_36095_.m_39730_());
                     }

                     if (entity instanceof Player) {
                        Player _player = (Player)entity;
                        ItemStack _setstack = (new ItemStack((ItemLike)CosmosModItems.DEADCOMPASS.get())).m_41777_();
                        _setstack.m_41764_(1);
                        ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                     }
                  }
               } else if (!var11 && itemstackiterator.m_41720_() == CosmosModItems.DEADCOMPASS.get()) {
                  if (entity instanceof Player) {
                     Player _player = (Player)entity;
                     ItemStack _stktoremove = new ItemStack((ItemLike)CosmosModItems.DEADCOMPASS.get());
                     _player.m_150109_().m_36022_((p) -> _stktoremove.m_41720_() == p.m_41720_(), 1, _player.f_36095_.m_39730_());
                  }

                  if (entity instanceof Player) {
                     Player _player = (Player)entity;
                     ItemStack _setstack = (new ItemStack(Items.f_42522_)).m_41777_();
                     _setstack.m_41764_(1);
                     ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                  }
               }
            }
         }

      }
   }
}
