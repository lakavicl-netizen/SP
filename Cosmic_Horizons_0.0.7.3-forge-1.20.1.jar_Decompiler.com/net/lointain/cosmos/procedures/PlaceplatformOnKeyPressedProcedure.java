package net.lointain.cosmos.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class PlaceplatformOnKeyPressedProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         double X = (double)0.0F;
         double Y = (double)0.0F;
         double Z = (double)0.0F;
         ItemStack var10000;
         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10000 = _livEnt.m_21205_();
         } else {
            var10000 = ItemStack.f_41583_;
         }

         if (!(var10000.m_41720_() instanceof BlockItem)) {
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21206_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (!(var10000.m_41720_() instanceof BlockItem)) {
               return;
            }
         }

         X = entity.m_20185_();
         Y = entity.m_20186_();
         Z = entity.m_20189_();
         BlockPos var10001 = BlockPos.m_274561_(X + (double)0.0F, Y - (double)3.0F, Z + (double)0.0F);
         ItemStack var10002;
         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10002 = _livEnt.m_21205_();
         } else {
            var10002 = ItemStack.f_41583_;
         }

         Item var12 = var10002.m_41720_();
         BlockState var17;
         if (var12 instanceof BlockItem) {
            BlockItem _bi = (BlockItem)var12;
            var17 = _bi.m_40614_().m_49966_();
         } else {
            var17 = Blocks.f_50016_.m_49966_();
         }

         world.m_7731_(var10001, var17, 3);
      }
   }
}
