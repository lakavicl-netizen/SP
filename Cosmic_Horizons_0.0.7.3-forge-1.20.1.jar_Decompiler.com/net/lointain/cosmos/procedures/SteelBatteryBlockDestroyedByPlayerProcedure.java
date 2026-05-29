package net.lointain.cosmos.procedures;

import java.text.DecimalFormat;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class SteelBatteryBlockDestroyedByPlayerProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String state = "";
         double PosX = (double)0.0F;
         double PosY = (double)0.0F;
         double PosZ = (double)0.0F;
         double en = (double)0.0F;
         double particleRadius = (double)0.0F;
         double particleAmount = (double)0.0F;
         state = (new DecimalFormat("##")).format(CosmosModVariables.MapVariables.get(world).nergy);
         if (CosmosModVariables.MapVariables.get(world).nergy == (double)0.0F) {
            if (((<undefinedtype>)(new Object() {
               public boolean checkGamemode(Entity _ent) {
                  if (_ent instanceof ServerPlayer _serverPlayer) {
                     return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
                  } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                     return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
                  } else {
                     return false;
                  }
               }
            })).checkGamemode(entity) && world instanceof ServerLevel) {
               ServerLevel _level = (ServerLevel)world;
               Commands var10000 = _level.m_7654_().m_129892_();
               CommandSourceStack var10001 = (new CommandSourceStack(CommandSource.f_80164_, new Vec3(x, y, z), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_();
               String var10002 = (new DecimalFormat("##.##")).format(x + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0));
               var10000.m_230957_(var10001, "/summon minecraft:item " + var10002 + " " + (new DecimalFormat("##.##")).format(y + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0)) + " " + (new DecimalFormat("##.##")).format(z + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0)) + " {Motion:[0.0,0.4,0.0],Item:{id:\"cosmos:steel_battery\",Count:1b,tag:{BlockEntityTag:{energyStorage: " + state + "}}}}");
            }
         } else if (CosmosModVariables.MapVariables.get(world).nergy > (double)0.0F && ((<undefinedtype>)(new Object() {
            public boolean checkGamemode(Entity _ent) {
               if (_ent instanceof ServerPlayer _serverPlayer) {
                  return _serverPlayer.f_8941_.m_9290_() == GameType.SURVIVAL;
               } else if (_ent.m_9236_().m_5776_() && _ent instanceof Player _player) {
                  return Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()) != null && Minecraft.m_91087_().m_91403_().m_104949_(_player.m_36316_().getId()).m_105325_() == GameType.SURVIVAL;
               } else {
                  return false;
               }
            }
         })).checkGamemode(entity) && world instanceof ServerLevel) {
            ServerLevel _level = (ServerLevel)world;
            Commands var24 = _level.m_7654_().m_129892_();
            CommandSourceStack var25 = (new CommandSourceStack(CommandSource.f_80164_, new Vec3(x, y, z), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_();
            String var26 = (new DecimalFormat("##.##")).format(x + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0));
            var24.m_230957_(var25, "/summon minecraft:item " + var26 + " " + (new DecimalFormat("##.##")).format(y + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0)) + " " + (new DecimalFormat("##.##")).format(z + (double)Mth.m_216271_(RandomSource.m_216327_(), 0, 0)) + " {Motion:[0.0,0.4,0.0],Item:{id:\"cosmos:steel_battery\",Count:1b,tag:{display:{Lore:[\"\\\"" + (new DecimalFormat("##")).format(CosmosModVariables.MapVariables.get(world).nergy / (double)64000.0F * (double)100.0F) + "% Charged\\\"\"]},BlockEntityTag:{energyStorage:" + state + "}}}}");
         }

      }
   }
}
