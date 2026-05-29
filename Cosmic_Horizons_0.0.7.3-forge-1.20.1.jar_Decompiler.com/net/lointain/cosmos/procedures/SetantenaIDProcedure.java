package net.lointain.cosmos.procedures;

import java.util.HashMap;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SetantenaIDProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         String id = "";
         String dim = "";
         boolean logic = false;
         boolean kogic = false;
         id = guistate.containsKey("text:intel") ? ((EditBox)guistate.get("text:intel")).m_94155_() : "";
         dim = ((Level)world).m_46472_().m_135782_().toString();
         if (!id.equals("ID Already Exists") && !id.equals("Same ID") && !id.equals("Invalid Coords") && !id.equals("Invalid ID")) {
            if (!id.equals(((<undefinedtype>)(new Object() {
               public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.m_7702_(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
               }
            })).getValue(world, BlockPos.m_274561_(x, y, z), "Antena_ID"))) {
               if (!id.equals("")) {
                  CompoundTag new_map = new CompoundTag();
                  Tag var18 = CosmosModVariables.MapVariables.get(world).antena_locations.m_128423_(dim);
                  ListTag var10000;
                  if (var18 instanceof ListTag) {
                     ListTag _listTag = (ListTag)var18;
                     var10000 = _listTag.m_6426_();
                  } else {
                     var10000 = new ListTag();
                  }

                  ListTag check_list = var10000;
                  if (((<undefinedtype>)(new Object() {
                     public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.m_7702_(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                     }
                  })).getValue(world, BlockPos.m_274561_(x, y, z), "Antena_ID").equals("")) {
                     logic = false;

                     for(Tag dataelementiterator : check_list) {
                        CompoundTag var55;
                        if (dataelementiterator instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
                           var55 = _compoundTag.m_6426_();
                        } else {
                           var55 = new CompoundTag();
                        }

                        CompoundTag iter_map = var55;
                        if (iter_map.m_128441_(id)) {
                           logic = false;
                           break;
                        }

                        logic = true;
                     }

                     if (!check_list.isEmpty()) {
                        for(Tag dataelementiterator : check_list) {
                           CompoundTag var56;
                           if (dataelementiterator instanceof CompoundTag) {
                              CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
                              var56 = _compoundTag.m_6426_();
                           } else {
                              var56 = new CompoundTag();
                           }

                           CompoundTag iter_map = var56;
                           if (!iter_map.m_128456_()) {
                              for(String keyiterator : iter_map.m_128431_()) {
                                 Tag var22 = iter_map.m_128423_(keyiterator);
                                 String var57;
                                 if (var22 instanceof StringTag) {
                                    StringTag _stringTag = (StringTag)var22;
                                    var57 = _stringTag.m_7916_();
                                 } else {
                                    var57 = "";
                                 }

                                 if (var57.equals("*" + x + "|" + z + "~")) {
                                    kogic = false;
                                    break;
                                 }

                                 kogic = true;
                              }
                           } else {
                              kogic = true;
                           }
                        }
                     }

                     if (check_list.isEmpty() || logic && kogic) {
                        new_map.m_128365_(id, StringTag.m_129297_("*" + x + "|" + z + "~"));
                        check_list.m_7614_(check_list.size(), new_map);
                        CosmosModVariables.MapVariables.get(world).antena_locations.m_128473_(dim);
                        CosmosModVariables.MapVariables.get(world).antena_locations.m_128365_(dim, check_list);
                        if (!world.m_5776_()) {
                           BlockPos _bp = BlockPos.m_274561_(x, y, z);
                           BlockEntity _blockEntity = world.m_7702_(_bp);
                           BlockState _bs = world.m_8055_(_bp);
                           if (_blockEntity != null) {
                              _blockEntity.getPersistentData().m_128359_("Antena_ID", id);
                           }

                           if (world instanceof Level) {
                              Level _level = (Level)world;
                              _level.m_7260_(_bp, _bs, _bs, 3);
                           }
                        }

                        if (entity instanceof Player) {
                           Player _player = (Player)entity;
                           _player.m_6915_();
                        }
                     } else if (!logic) {
                        Object _compoundTag = guistate.get("text:intel");
                        if (_compoundTag instanceof EditBox) {
                           EditBox _tf = (EditBox)_compoundTag;
                           _tf.m_94144_("ID Already Exists");
                        }
                     } else if (!kogic) {
                        Object _bp = guistate.get("text:intel");
                        if (_bp instanceof EditBox) {
                           EditBox _tf = (EditBox)_bp;
                           _tf.m_94144_("Invalid Coords");
                        }
                     }
                  } else {
                     CompoundTag rechange_map = new CompoundTag();

                     for(int l = 0; l < check_list.size(); ++l) {
                        Tag var50 = check_list.get(l);
                        CompoundTag var58;
                        if (var50 instanceof CompoundTag) {
                           CompoundTag _compoundTag = (CompoundTag)var50;
                           var58 = _compoundTag.m_6426_();
                        } else {
                           var58 = new CompoundTag();
                        }

                        CompoundTag iter_map = var58;
                        if (iter_map.m_128441_(((<undefinedtype>)(new Object() {
                           public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                              BlockEntity blockEntity = world.m_7702_(pos);
                              return blockEntity != null ? blockEntity.getPersistentData().m_128461_(tag) : "";
                           }
                        })).getValue(world, BlockPos.m_274561_(x, y, z), "Antena_ID"))) {
                           rechange_map.m_128365_(id, StringTag.m_129297_("*" + x + "|" + z + "~"));
                           check_list.m_7615_(l, rechange_map);
                           CosmosModVariables.MapVariables.get(world).antena_locations.m_128473_(dim);
                           CosmosModVariables.MapVariables.get(world).antena_locations.m_128365_(dim, check_list);
                           if (!world.m_5776_()) {
                              BlockPos _bp = BlockPos.m_274561_(x, y, z);
                              BlockEntity _blockEntity = world.m_7702_(_bp);
                              BlockState _bs = world.m_8055_(_bp);
                              if (_blockEntity != null) {
                                 _blockEntity.getPersistentData().m_128359_("Antena_ID", id);
                              }

                              if (world instanceof Level) {
                                 Level _level = (Level)world;
                                 _level.m_7260_(_bp, _bs, _bs, 3);
                              }
                           }

                           if (entity instanceof Player) {
                              Player _player = (Player)entity;
                              _player.m_6915_();
                           }
                        }
                     }
                  }
               } else {
                  Object var45 = guistate.get("text:intel");
                  if (var45 instanceof EditBox) {
                     EditBox _tf = (EditBox)var45;
                     _tf.m_94144_("Invalid ID");
                  }
               }
            } else {
               Object var46 = guistate.get("text:intel");
               if (var46 instanceof EditBox) {
                  EditBox _tf = (EditBox)var46;
                  _tf.m_94144_("Same ID");
               }
            }
         }

      }
   }
}
