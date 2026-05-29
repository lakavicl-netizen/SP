package net.lointain.cosmos.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.lointain.cosmos.procedures.TravelunlockProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class UnlocktravelCommand {
   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher().register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("unlock").requires((s) -> s.m_6761_(2))).executes((arguments) -> {
         Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
         double x = ((CommandSourceStack)arguments.getSource()).m_81371_().m_7096_();
         double y = ((CommandSourceStack)arguments.getSource()).m_81371_().m_7098_();
         double z = ((CommandSourceStack)arguments.getSource()).m_81371_().m_7094_();
         Entity entity = ((CommandSourceStack)arguments.getSource()).m_81373_();
         if (entity == null && world instanceof ServerLevel _servLevel) {
            entity = FakePlayerFactory.getMinecraft(_servLevel);
         }

         Direction direction = Direction.DOWN;
         if (entity != null) {
            direction = entity.m_6350_();
         }

         TravelunlockProcedure.execute(world, entity);
         return 0;
      }));
   }
}
