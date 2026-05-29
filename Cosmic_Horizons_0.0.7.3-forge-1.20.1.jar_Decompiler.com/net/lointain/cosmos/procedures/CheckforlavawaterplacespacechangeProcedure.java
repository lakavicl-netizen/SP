package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CheckforlavawaterplacespacechangeProcedure {
   @SubscribeEvent
   public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
      if (event.getHand() == event.getEntity().m_7655_()) {
         execute(event, event.getLevel(), (double)event.getPos().m_123341_(), (double)event.getPos().m_123342_(), (double)event.getPos().m_123343_(), event.getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute((Event)null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         boolean dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var11 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var10000;
            if (var11 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var11;
               var10000 = _stringTag.m_7916_();
            } else {
               var10000 = "";
            }

            if (var10000.equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         if (dimention_check) {
            CosmosMod.queueServerWork(1, () -> {
               ItemStack var10000;
               if (entity instanceof LivingEntity _livEnt) {
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               label100: {
                  if (var10000.m_41720_() != Items.f_42447_) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21205_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() != Items.f_42447_) {
                        break label100;
                     }
                  }

                  int horizontalRadiusSquare = (int)(((LivingEntity)entity).m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_() + (double)2.0F) - 1;
                  int verticalRadiusSquare = (int)(((LivingEntity)entity).m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_() + (double)2.0F) - 1;
                  int yIterationsSquare = verticalRadiusSquare;

                  for(int i = -verticalRadiusSquare; i <= yIterationsSquare; ++i) {
                     for(int xi = -horizontalRadiusSquare; xi <= horizontalRadiusSquare; ++xi) {
                        for(int zi = -horizontalRadiusSquare; zi <= horizontalRadiusSquare; ++zi) {
                           if (world.m_6425_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi)).m_76188_().m_60734_() == Blocks.f_49990_) {
                              world.m_7731_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi), Blocks.f_50016_.m_49966_(), 3);
                              world.m_7731_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi), Blocks.f_50126_.m_49966_(), 3);
                           }
                        }
                     }
                  }
               }

               if (entity instanceof LivingEntity _livEnt) {
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != Items.f_42448_) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() != Items.f_42448_) {
                     return;
                  }
               }

               int horizontalRadiusSquare = (int)(((LivingEntity)entity).m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_() + (double)2.0F) - 1;
               int verticalRadiusSquare = (int)(((LivingEntity)entity).m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_() + (double)2.0F) - 1;
               int yIterationsSquare = verticalRadiusSquare;

               for(int i = -verticalRadiusSquare; i <= yIterationsSquare; ++i) {
                  for(int xi = -horizontalRadiusSquare; xi <= horizontalRadiusSquare; ++xi) {
                     for(int zi = -horizontalRadiusSquare; zi <= horizontalRadiusSquare; ++zi) {
                        if (world.m_6425_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi)).m_76188_().m_60734_() == Blocks.f_49991_) {
                           world.m_7731_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi), Blocks.f_50016_.m_49966_(), 3);
                           world.m_7731_(BlockPos.m_274561_(x + (double)xi, y + (double)i, z + (double)zi), Blocks.f_50080_.m_49966_(), 3);
                        }
                     }
                  }
               }

            });
         }

      }
   }
}
