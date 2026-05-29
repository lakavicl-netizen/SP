package net.lointain.cosmos.procedures;

import java.text.DecimalFormat;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class AtmosphericCollisionDetectorProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         double posX = (double)0.0F;
         double posY = (double)0.0F;
         double posZ = (double)0.0F;
         String dimension = "";
         Vec3 position = Vec3.f_82478_;
         if (entity.m_20202_() instanceof RocketSeatEntity && CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var12 = CosmosModVariables.WorldVariables.get(world).atmospheric_collision_data_map.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (var12 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var12;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag atmospheric_data = var10000;
            double var30 = entity.m_20186_();
            var12 = atmospheric_data.m_128423_("atmosphere_y");
            double var10001;
            if (var12 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var12;
               var10001 = _doubleTag.m_7061_();
            } else {
               var10001 = (double)0.0F;
            }

            if (var30 > var10001) {
               String _setval = entity.m_20202_().m_20149_();
               entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).ifPresent((capability) -> {
                  capability.vehicle = _setval;
                  capability.syncPlayerVariables(entity);
               });
               Tag var13 = atmospheric_data.m_128423_("origin_x");
               if (var13 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var13;
                  var30 = _doubleTag.m_7061_();
               } else {
                  var30 = (double)0.0F;
               }

               posX = var30 + (double)Mth.m_216271_(RandomSource.m_216327_(), -10, 10);
               var13 = atmospheric_data.m_128423_("origin_y");
               if (var13 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var13;
                  var30 = _doubleTag.m_7061_();
               } else {
                  var30 = (double)0.0F;
               }

               posY = var30 + (double)Mth.m_216271_(RandomSource.m_216327_(), -5, 5);
               var13 = atmospheric_data.m_128423_("origin_z");
               if (var13 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var13;
                  var30 = _doubleTag.m_7061_();
               } else {
                  var30 = (double)0.0F;
               }

               posZ = var30 + (double)Mth.m_216271_(RandomSource.m_216327_(), -10, 10);
               var13 = atmospheric_data.m_128423_("travel_to");
               String var34;
               if (var13 instanceof StringTag) {
                  StringTag _stringTag = (StringTag)var13;
                  var34 = _stringTag.m_7916_();
               } else {
                  var34 = "";
               }

               dimension = var34;
               if (world instanceof ServerLevel) {
                  ServerLevel _level = (ServerLevel)world;
                  _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20202_().m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
               }

               if (world instanceof ServerLevel) {
                  ServerLevel _level = (ServerLevel)world;
                  _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_()), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "execute in " + dimension + " run tp " + entity.m_20149_() + " " + (new DecimalFormat("##")).format(posX) + " " + (new DecimalFormat("##")).format(posY) + " " + (new DecimalFormat("##")).format(posZ));
               }

               CosmosMod.queueServerWork(25, () -> {
                  for(int index0 = 0; index0 < 75; ++index0) {
                     if (!entity.m_20159_() && !(entity.m_20202_() instanceof RocketSeatEntity) && !entity.m_9236_().m_5776_() && entity.m_20194_() != null) {
                        Commands var10000 = entity.m_20194_().m_129892_();
                        CommandSourceStack var10001 = new CommandSourceStack(CommandSource.f_80164_, entity.m_20182_(), entity.m_20155_(), entity.m_9236_() instanceof ServerLevel ? (ServerLevel)entity.m_9236_() : null, 4, entity.m_7755_().getString(), entity.m_5446_(), entity.m_9236_().m_7654_(), entity);
                        String var10002 = entity.m_20149_();
                        var10000.m_230957_(var10001, "ride " + var10002 + " mount " + ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).vehicle);
                     }
                  }

               });
            }
         }

      }
   }
}
