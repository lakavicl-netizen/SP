package net.lointain.cosmos.block;

import io.netty.buffer.Unpooled;
import net.lointain.cosmos.block.entity.ProjectorTableBlockEntity;
import net.lointain.cosmos.procedures.ProjectorTableBlockAddedProcedure;
import net.lointain.cosmos.world.inventory.ProjectionSelectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class ProjectorTableBlock extends Block implements EntityBlock {
   public static final DirectionProperty FACING;

   public ProjectorTableBlock() {
      super(Properties.m_284310_().m_280658_(NoteBlockInstrument.BASEDRUM).m_60918_(SoundType.f_154663_).m_60913_(15.0F, 20.0F).m_60953_((s) -> 10).m_60955_().m_60924_((bs, br, bp) -> false));
      this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_(FACING, Direction.NORTH));
   }

   public boolean m_7420_(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   public VoxelShape m_5909_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.m_83040_();
   }

   public VoxelShape m_5940_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      VoxelShape var10000;
      switch ((Direction)state.m_61143_(FACING)) {
         case NORTH -> var10000 = Shapes.m_83110_(m_49796_((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)13.0F, (double)16.0F), m_49796_((double)-8.0F, (double)13.0F, (double)-8.0F, (double)24.0F, (double)16.0F, (double)24.0F));
         case EAST -> var10000 = Shapes.m_83110_(m_49796_((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)13.0F, (double)16.0F), m_49796_((double)-8.0F, (double)13.0F, (double)-8.0F, (double)24.0F, (double)16.0F, (double)24.0F));
         case WEST -> var10000 = Shapes.m_83110_(m_49796_((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)13.0F, (double)16.0F), m_49796_((double)-8.0F, (double)13.0F, (double)-8.0F, (double)24.0F, (double)16.0F, (double)24.0F));
         default -> var10000 = Shapes.m_83110_(m_49796_((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)13.0F, (double)16.0F), m_49796_((double)-8.0F, (double)13.0F, (double)-8.0F, (double)24.0F, (double)16.0F, (double)24.0F));
      }

      return var10000;
   }

   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
      super.m_7926_(builder);
      builder.m_61104_(new Property[]{FACING});
   }

   public BlockState m_5573_(BlockPlaceContext context) {
      return (BlockState)super.m_5573_(context).m_61124_(FACING, context.m_8125_().m_122424_());
   }

   public BlockState m_6843_(BlockState state, Rotation rot) {
      return (BlockState)state.m_61124_(FACING, rot.m_55954_((Direction)state.m_61143_(FACING)));
   }

   public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
      return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_(FACING)));
   }

   public void m_6807_(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
      super.m_6807_(blockstate, world, pos, oldState, moving);
      ProjectorTableBlockAddedProcedure.execute(world, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_());
   }

   public InteractionResult m_6227_(BlockState blockstate, Level world, final BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
      super.m_6227_(blockstate, world, pos, entity, hand, hit);
      if (entity instanceof ServerPlayer player) {
         NetworkHooks.openScreen(player, new MenuProvider() {
            public Component m_5446_() {
               return Component.m_237113_("Projector Table");
            }

            public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
               return new ProjectionSelectorMenu(id, inventory, (new FriendlyByteBuf(Unpooled.buffer())).m_130064_(pos));
            }
         }, pos);
      }

      return InteractionResult.SUCCESS;
   }

   public MenuProvider m_7246_(BlockState state, Level worldIn, BlockPos pos) {
      BlockEntity tileEntity = worldIn.m_7702_(pos);
      MenuProvider var10000;
      if (tileEntity instanceof MenuProvider menuProvider) {
         var10000 = menuProvider;
      } else {
         var10000 = null;
      }

      return var10000;
   }

   public BlockEntity m_142194_(BlockPos pos, BlockState state) {
      return new ProjectorTableBlockEntity(pos, state);
   }

   public boolean m_8133_(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
      super.m_8133_(state, world, pos, eventID, eventParam);
      BlockEntity blockEntity = world.m_7702_(pos);
      return blockEntity == null ? false : blockEntity.m_7531_(eventID, eventParam);
   }

   static {
      FACING = HorizontalDirectionalBlock.f_54117_;
   }
}
