package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtinguishedLanternOnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         boolean dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var10 = CosmosModVariables.WorldVariables.get(world).dimension_type.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            String var10000;
            if (var10 instanceof StringTag) {
               StringTag _stringTag = (StringTag)var10;
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

         if (!dimention_check) {
            ItemStack var17;
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var17 = _livEnt.m_21205_();
            } else {
               var17 = ItemStack.f_41583_;
            }

            if (var17.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var17 = _livEnt.m_21206_();
               } else {
                  var17 = ItemStack.f_41583_;
               }

               if (var17.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var17 = _livEnt.m_21205_();
                  } else {
                     var17 = ItemStack.f_41583_;
                  }

                  if (var17.m_41720_() != Items.f_42409_) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var17 = _livEnt.m_21206_();
                     } else {
                        var17 = ItemStack.f_41583_;
                     }

                     if (var17.m_41720_() != Items.f_42409_) {
                        return;
                     }
                  }
               }
            }

            world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
            if (world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F);
               } else {
                  _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
               }
            }

            world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50681_.m_49966_(), 3);
         }

      }
   }
}
