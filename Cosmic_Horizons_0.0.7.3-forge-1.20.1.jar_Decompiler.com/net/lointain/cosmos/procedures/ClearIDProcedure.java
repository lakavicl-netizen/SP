package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ClearIDProcedure {
   @SubscribeEvent
   public static void onBlockBreak(BlockEvent.BreakEvent event) {
      execute(event, event.getLevel(), (double)event.getPos().m_123341_(), (double)event.getPos().m_123342_(), (double)event.getPos().m_123343_());
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      execute((Event)null, world, x, y, z);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
      String dim = "";
      if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.STEEL_LANDING_PAD_ON.get() || world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.STEEL_LANDING_PAD_OFF.get()) {
         dim = ((Level)world).m_46472_().m_135782_().toString();
         Tag var12 = CosmosModVariables.MapVariables.get(world).antena_locations.m_128423_(dim);
         ListTag var10000;
         if (var12 instanceof ListTag) {
            ListTag _listTag = (ListTag)var12;
            var10000 = _listTag.m_6426_();
         } else {
            var10000 = new ListTag();
         }

         ListTag check_list = var10000;

         for(int l = 0; l < check_list.size(); ++l) {
            Tag var13 = check_list.get(l);
            CompoundTag var17;
            if (var13 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var13;
               var17 = _compoundTag.m_6426_();
            } else {
               var17 = new CompoundTag();
            }

            CompoundTag iter_map = var17;
            if (iter_map.m_128441_(((<undefinedtype>)(new Object() {
               public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "Antena_ID"))) {
               check_list.remove(l);
               CosmosModVariables.MapVariables.get(world).antena_locations.m_128473_(dim);
               CosmosModVariables.MapVariables.get(world).antena_locations.m_128365_(dim, check_list);
            }
         }
      }

   }
}
