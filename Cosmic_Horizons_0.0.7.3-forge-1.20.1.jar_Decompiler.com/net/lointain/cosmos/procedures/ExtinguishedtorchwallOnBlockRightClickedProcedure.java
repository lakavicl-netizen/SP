package net.lointain.cosmos.procedures;

import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtinguishedtorchwallOnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
      if (entity != null) {
         boolean dimention_check = false;
         if (CosmosModVariables.WorldVariables.get(world).dimension_type.m_128441_(((Level)world).m_46472_().m_135782_().toString())) {
            if (((Level)world).m_46472_().m_135782_().toString().equals("space")) {
               dimention_check = true;
            } else {
               dimention_check = false;
            }
         } else {
            dimention_check = false;
         }

         if (!dimention_check) {
            ItemStack var10000;
            if (entity instanceof LivingEntity) {
               LivingEntity _livEnt = (LivingEntity)entity;
               var10000 = _livEnt.m_21205_();
            } else {
               var10000 = ItemStack.f_41583_;
            }

            if (var10000.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _livEnt = (LivingEntity)entity;
                  var10000 = _livEnt.m_21206_();
               } else {
                  var10000 = ItemStack.f_41583_;
               }

               if (var10000.m_41720_() != CosmosModItems.TITANIUMSPAKER.get()) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _livEnt = (LivingEntity)entity;
                     var10000 = _livEnt.m_21205_();
                  } else {
                     var10000 = ItemStack.f_41583_;
                  }

                  if (var10000.m_41720_() != Items.f_42409_) {
                     if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        var10000 = _livEnt.m_21206_();
                     } else {
                        var10000 = ItemStack.f_41583_;
                     }

                     if (var10000.m_41720_() != Items.f_42409_) {
                        return;
                     }
                  }
               }
            }

            world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50016_.m_49966_(), 3);
            if (((<undefinedtype>)(new Object() {
               public Direction getDirection(BlockState _bs) {
                  Property<?> _prop = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_prop instanceof DirectionProperty _dp) {
                     return (Direction)_bs.m_61143_(_dp);
                  } else {
                     _prop = _bs.m_60734_().m_49965_().m_61081_("axis");
                     Direction var10000;
                     if (_prop instanceof EnumProperty _ep) {
                        if (_ep.m_6908_().toArray()[0] instanceof Direction.Axis) {
                           var10000 = Direction.m_122387_((Direction.Axis)_bs.m_61143_(_ep), AxisDirection.POSITIVE);
                           return var10000;
                        }
                     }

                     var10000 = Direction.NORTH;
                     return var10000;
                  }
               }
            })).getDirection(blockstate) == Direction.WEST) {
               label101: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50082_.m_49966_(), 3);
                  Direction _dir = Direction.NORTH;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label101;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            } else if (((<undefinedtype>)(new Object() {
               public Direction getDirection(BlockState _bs) {
                  Property<?> _prop = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_prop instanceof DirectionProperty _dp) {
                     return (Direction)_bs.m_61143_(_dp);
                  } else {
                     _prop = _bs.m_60734_().m_49965_().m_61081_("axis");
                     Direction var10000;
                     if (_prop instanceof EnumProperty _ep) {
                        if (_ep.m_6908_().toArray()[0] instanceof Direction.Axis) {
                           var10000 = Direction.m_122387_((Direction.Axis)_bs.m_61143_(_ep), AxisDirection.POSITIVE);
                           return var10000;
                        }
                     }

                     var10000 = Direction.NORTH;
                     return var10000;
                  }
               }
            })).getDirection(blockstate) == Direction.EAST) {
               label106: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50082_.m_49966_(), 3);
                  Direction _dir = Direction.SOUTH;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label106;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            } else if (((<undefinedtype>)(new Object() {
               public Direction getDirection(BlockState _bs) {
                  Property<?> _prop = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_prop instanceof DirectionProperty _dp) {
                     return (Direction)_bs.m_61143_(_dp);
                  } else {
                     _prop = _bs.m_60734_().m_49965_().m_61081_("axis");
                     Direction var10000;
                     if (_prop instanceof EnumProperty _ep) {
                        if (_ep.m_6908_().toArray()[0] instanceof Direction.Axis) {
                           var10000 = Direction.m_122387_((Direction.Axis)_bs.m_61143_(_ep), AxisDirection.POSITIVE);
                           return var10000;
                        }
                     }

                     var10000 = Direction.NORTH;
                     return var10000;
                  }
               }
            })).getDirection(blockstate) == Direction.SOUTH) {
               label111: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50082_.m_49966_(), 3);
                  Direction _dir = Direction.WEST;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label111;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            } else if (((<undefinedtype>)(new Object() {
               public Direction getDirection(BlockState _bs) {
                  Property<?> _prop = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_prop instanceof DirectionProperty _dp) {
                     return (Direction)_bs.m_61143_(_dp);
                  } else {
                     _prop = _bs.m_60734_().m_49965_().m_61081_("axis");
                     Direction var10000;
                     if (_prop instanceof EnumProperty _ep) {
                        if (_ep.m_6908_().toArray()[0] instanceof Direction.Axis) {
                           var10000 = Direction.m_122387_((Direction.Axis)_bs.m_61143_(_ep), AxisDirection.POSITIVE);
                           return var10000;
                        }
                     }

                     var10000 = Direction.NORTH;
                     return var10000;
                  }
               }
            })).getDirection(blockstate) == Direction.NORTH) {
               label116: {
                  world.m_7731_(BlockPos.m_274561_(x, y, z), Blocks.f_50082_.m_49966_(), 3);
                  Direction _dir = Direction.EAST;
                  BlockPos _pos = BlockPos.m_274561_(x, y, z);
                  BlockState _bs = world.m_8055_(_pos);
                  Property<?> _property = _bs.m_60734_().m_49965_().m_61081_("facing");
                  if (_property instanceof DirectionProperty) {
                     DirectionProperty _dp = (DirectionProperty)_property;
                     if (_dp.m_6908_().contains(_dir)) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_dp, _dir), 3);
                        break label116;
                     }
                  }

                  _property = _bs.m_60734_().m_49965_().m_61081_("axis");
                  if (_property instanceof EnumProperty) {
                     EnumProperty _ap = (EnumProperty)_property;
                     if (_ap.m_6908_().contains(_dir.m_122434_())) {
                        world.m_7731_(_pos, (BlockState)_bs.m_61124_(_ap, _dir.m_122434_()), 3);
                     }
                  }
               }

               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.m_5776_()) {
                     _level.m_5594_((Player)null, BlockPos.m_274561_(x, y, z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F);
                  } else {
                     _level.m_7785_(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.firecharge.use")), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                  }
               }
            }
         }

      }
   }
}
