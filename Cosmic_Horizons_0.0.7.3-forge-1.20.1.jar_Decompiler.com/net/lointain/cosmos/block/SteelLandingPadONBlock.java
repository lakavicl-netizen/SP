package net.lointain.cosmos.block;

import io.netty.buffer.Unpooled;
import net.lointain.cosmos.block.entity.SteelLandingPadONBlockEntity;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.lointain.cosmos.procedures.SteelLandingPadBlockAddedProcedure;
import net.lointain.cosmos.procedures.SteelLandingPadOnTickUpdateProcedure;
import net.lointain.cosmos.world.inventory.AntenaNamerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class SteelLandingPadONBlock extends Block implements EntityBlock {
   public static final DirectionProperty FACING;

   public SteelLandingPadONBlock() {
      super(Properties.m_284310_().m_280658_(NoteBlockInstrument.BASEDRUM).m_60918_(SoundType.f_154663_).m_60913_(5.0F, 8000.0F).m_60999_().m_60955_().m_60982_((bs, br, bp) -> true).m_60991_((bs, br, bp) -> true).m_60924_((bs, br, bp) -> false));
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
         case NORTH -> var10000 = Shapes.m_83124_(m_49796_((double)-16.0F, (double)0.0F, (double)-16.0F, (double)32.0F, (double)3.0F, (double)32.0F), new VoxelShape[]{m_49796_((double)20.0F, 1.60058, -12.07401, (double)24.0F, 6.60058, -9.07401), m_49796_((double)13.5F, (double)4.0F, (double)-15.0F, (double)30.5F, (double)5.0F, (double)-6.0F), m_49796_((double)12.5F, (double)4.0F, (double)-6.0F, (double)31.5F, (double)6.0F, (double)-5.0F), m_49796_((double)12.5F, (double)4.0F, (double)-16.0F, (double)31.5F, (double)6.0F, (double)-15.0F), m_49796_((double)12.5F, (double)4.0F, (double)-15.0F, (double)13.5F, (double)6.0F, (double)-5.0F), m_49796_((double)30.5F, (double)4.0F, (double)-15.0F, (double)31.5F, (double)6.0F, (double)-6.0F)});
         case EAST -> var10000 = Shapes.m_83124_(m_49796_((double)-16.0F, (double)0.0F, (double)-16.0F, (double)32.0F, (double)3.0F, (double)32.0F), new VoxelShape[]{m_49796_(25.07401, 1.60058, (double)20.0F, 28.07401, 6.60058, (double)24.0F), m_49796_((double)22.0F, (double)4.0F, (double)13.5F, (double)31.0F, (double)5.0F, (double)30.5F), m_49796_((double)21.0F, (double)4.0F, (double)12.5F, (double)22.0F, (double)6.0F, (double)31.5F), m_49796_((double)31.0F, (double)4.0F, (double)12.5F, (double)32.0F, (double)6.0F, (double)31.5F), m_49796_((double)21.0F, (double)4.0F, (double)12.5F, (double)31.0F, (double)6.0F, (double)13.5F), m_49796_((double)22.0F, (double)4.0F, (double)30.5F, (double)31.0F, (double)6.0F, (double)31.5F)});
         case WEST -> var10000 = Shapes.m_83124_(m_49796_((double)-16.0F, (double)0.0F, (double)-16.0F, (double)32.0F, (double)3.0F, (double)32.0F), new VoxelShape[]{m_49796_(-12.07401, 1.60058, (double)-8.0F, -9.07401, 6.60058, (double)-4.0F), m_49796_((double)-15.0F, (double)4.0F, (double)-14.5F, (double)-6.0F, (double)5.0F, (double)2.5F), m_49796_((double)-6.0F, (double)4.0F, (double)-15.5F, (double)-5.0F, (double)6.0F, (double)3.5F), m_49796_((double)-16.0F, (double)4.0F, (double)-15.5F, (double)-15.0F, (double)6.0F, (double)3.5F), m_49796_((double)-15.0F, (double)4.0F, (double)2.5F, (double)-5.0F, (double)6.0F, (double)3.5F), m_49796_((double)-15.0F, (double)4.0F, (double)-15.5F, (double)-6.0F, (double)6.0F, (double)-14.5F)});
         default -> var10000 = Shapes.m_83124_(m_49796_((double)-16.0F, (double)0.0F, (double)-16.0F, (double)32.0F, (double)3.0F, (double)32.0F), new VoxelShape[]{m_49796_((double)-8.0F, 1.60058, 25.07401, (double)-4.0F, 6.60058, 28.07401), m_49796_((double)-14.5F, (double)4.0F, (double)22.0F, (double)2.5F, (double)5.0F, (double)31.0F), m_49796_((double)-15.5F, (double)4.0F, (double)21.0F, (double)3.5F, (double)6.0F, (double)22.0F), m_49796_((double)-15.5F, (double)4.0F, (double)31.0F, (double)3.5F, (double)6.0F, (double)32.0F), m_49796_((double)2.5F, (double)4.0F, (double)21.0F, (double)3.5F, (double)6.0F, (double)31.0F), m_49796_((double)-15.5F, (double)4.0F, (double)22.0F, (double)-14.5F, (double)6.0F, (double)31.0F)});
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

   public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
      return new ItemStack((ItemLike)CosmosModBlocks.STEEL_RESTONE_RECIEVER_OFF.get());
   }

   public void m_6807_(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
      super.m_6807_(blockstate, world, pos, oldState, moving);
      world.m_186460_(pos, this, 2);
      SteelLandingPadBlockAddedProcedure.execute(world, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_());
   }

   public void m_213897_(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
      super.m_213897_(blockstate, world, pos, random);
      int x = pos.m_123341_();
      int y = pos.m_123342_();
      int z = pos.m_123343_();
      SteelLandingPadOnTickUpdateProcedure.execute(world, (double)x, (double)y, (double)z);
      world.m_186460_(pos, this, 2);
   }

   public InteractionResult m_6227_(BlockState blockstate, Level world, final BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
      super.m_6227_(blockstate, world, pos, entity, hand, hit);
      if (entity instanceof ServerPlayer player) {
         NetworkHooks.openScreen(player, new MenuProvider() {
            public Component m_5446_() {
               return Component.m_237113_("Steel Landing Pad");
            }

            public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
               return new AntenaNamerMenu(id, inventory, (new FriendlyByteBuf(Unpooled.buffer())).m_130064_(pos));
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
      return new SteelLandingPadONBlockEntity(pos, state);
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
