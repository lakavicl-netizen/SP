package net.lointain.cosmos.procedures;

import io.netty.buffer.Unpooled;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.network.CosmosModVariables;
import net.lointain.cosmos.world.inventory.PlanetSelectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.NetworkHooks;

public class TempplanetselopenProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (entity.m_20202_() instanceof RocketSeatEntity && ((CosmosModVariables.PlayerVariables)entity.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new CosmosModVariables.PlayerVariables())).open_gui) {
            if (entity instanceof Player) {
               Player _plr2 = (Player)entity;
               if (_plr2.f_36096_ instanceof PlanetSelectorMenu) {
                  return;
               }
            }

            if (entity instanceof ServerPlayer) {
               ServerPlayer _ent = (ServerPlayer)entity;
               final BlockPos _bpos = BlockPos.m_274561_(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
               NetworkHooks.openScreen(_ent, new MenuProvider() {
                  public Component m_5446_() {
                     return Component.m_237113_("PlanetSelector");
                  }

                  public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
                     return new PlanetSelectorMenu(id, inventory, (new FriendlyByteBuf(Unpooled.buffer())).m_130064_(_bpos));
                  }
               }, _bpos);
            }
         }

      }
   }
}
