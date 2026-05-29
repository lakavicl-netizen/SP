package net.lointain.cosmos.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class DetonatorMarkOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      Vec3 rot = Vec3.f_82478_;
      double pel = (double)0.0F;
      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "wave")) {
         for(int iter = (int)(y + (double)250.0F); iter > (int)y; iter -= 3) {
            if (!world.m_8055_(BlockPos.m_274561_(x, (double)iter, z)).m_204336_(BlockTags.create(new ResourceLocation("cosmos:empty"))) && world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_254849_((Entity)null, x, (double)iter, z, 25.0F, ExplosionInteraction.TNT);
               }
            }
         }

         CosmosMod.queueServerWork(1, () -> {
            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_("wave", false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

         });
      }

      if (((<undefinedtype>)(new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.m_7702_(pos);
            return blockEntity != null ? blockEntity.getPersistentData().m_128471_(tag) : false;
         }
      })).getValue(world, BlockPos.m_274561_(x, y, z), "detonate")) {
         if (world.m_8055_(BlockPos.m_274561_(x, y, z)).m_60734_() == CosmosModBlocks.DETONATOR_TARGET.get()) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockState _bs = ((Block)CosmosModBlocks.DETONATOR_TARGET_ON.get()).m_49966_();
            BlockState _bso = world.m_8055_(_bp);
            UnmodifiableIterator var13 = _bso.m_61148_().entrySet().iterator();

            while(var13.hasNext()) {
               Map.Entry<Property<?>, Comparable<?>> entry = (Map.Entry)var13.next();
               Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
               if (_property != null && _bs.m_61143_(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                  } catch (Exception var18) {
                  }
               }
            }

            BlockEntity _be = world.m_7702_(_bp);
            CompoundTag _bnbt = null;
            if (_be != null) {
               _bnbt = _be.m_187480_();
               _be.m_7651_();
            }

            world.m_7731_(_bp, _bs, 3);
            if (_bnbt != null) {
               _be = world.m_7702_(_bp);
               if (_be != null) {
                  try {
                     _be.m_142466_(_bnbt);
                  } catch (Exception var17) {
                  }
               }
            }
         }

         if (!world.m_5776_()) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockEntity _blockEntity = world.m_7702_(_bp);
            BlockState _bs = world.m_8055_(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().m_128347_("counter", ((<undefinedtype>)(new Object() {
                  public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.m_7702_(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                  }
               })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") + (double)0.125F);
            }

            if (world instanceof Level) {
               Level _level = (Level)world;
               _level.m_7260_(_bp, _bs, _bs, 3);
            }
         }

         if (((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") > (double)7.5F) {
            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_("wave", true);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            pel = (double)((int)((<undefinedtype>)(new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "counter"));
            if (pel % (double)2.0F == (double)0.0F && world instanceof Level) {
               Level _level = (Level)world;
               if (!_level.m_5776_()) {
                  _level.m_254849_((Entity)null, x, y + (double)5.5F - (((<undefinedtype>)(new Object() {
                     public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") - (double)7.5F) / (double)2.0F, z, 6.0F, ExplosionInteraction.TNT);
               }
            }
         }

         if (((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") >= (double)25.0F) {
            if (!world.m_5776_()) {
               BlockPos _bp = BlockPos.m_274561_(x, y, z);
               BlockEntity _blockEntity = world.m_7702_(_bp);
               BlockState _bs = world.m_8055_(_bp);
               if (_blockEntity != null) {
                  _blockEntity.getPersistentData().m_128379_("detonate", false);
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  _level.m_7260_(_bp, _bs, _bs, 3);
               }
            }

            world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
         }

         if (((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") > (double)6.5F && ((<undefinedtype>)(new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.m_7702_(pos);
               return blockEntity != null ? blockEntity.getPersistentData().m_128459_(tag) : (double)-1.0F;
            }
         })).getValue(world, BlockPos.m_274561_(x, y, z), "counter") < 6.8 && world instanceof Level) {
            Level _level = (Level)world;
            if (!_level.m_5776_()) {
               _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cosmos:laser")), SoundSource.NEUTRAL, 9.0F, 9.0F);
            } else {
               _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cosmos:laser")), SoundSource.NEUTRAL, 9.0F, 9.0F, false);
            }
         }
      }

   }
}
