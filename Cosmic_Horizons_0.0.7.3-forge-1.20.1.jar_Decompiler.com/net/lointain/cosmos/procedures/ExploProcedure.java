package net.lointain.cosmos.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class ExploProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, BlockState block, double falling_block_amount, double motionx, double motiony, double motionz, double power_of_explosion) {
      if (world instanceof Level _level) {
         if (!_level.m_5776_()) {
            _level.m_254849_((Entity)null, x, y, z, (float)power_of_explosion, ExplosionInteraction.MOB);
         }
      }

      for(int index0 = 0; index0 < (int)falling_block_amount; ++index0) {
         if (world instanceof ServerLevel _level) {
            _level.m_7654_().m_129892_().m_230957_((new CommandSourceStack(CommandSource.f_80164_, new Vec3(x, y, z), Vec2.f_82462_, _level, 4, "", Component.m_237113_(""), _level.m_7654_(), (Entity)null)).m_81324_(), "/summon falling_block ~ ~ ~ {BlockState:{Name:\"" + ForgeRegistries.BLOCKS.getKey(block.m_60734_()).toString() + "\"},Time:1,Motion:[" + motionx + "d," + motiony + "d," + motionz + "d]}");
         }
      }

   }
}
