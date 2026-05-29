package net.lointain.cosmos.block;

import net.lointain.cosmos.init.CosmosModItems;
import net.lointain.cosmos.procedures.ExtinguishedLanternOnBlockRightClickedProcedure;
import net.lointain.cosmos.procedures.ExtinguishedLanternOnTickUpdateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
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

public class ExtinguishedLanternHangingBlock extends Block {
   public static final DirectionProperty FACING;

   public ExtinguishedLanternHangingBlock() {
      super(Properties.m_284310_().m_280658_(NoteBlockInstrument.BASEDRUM).m_60918_(SoundType.f_56762_).m_60913_(1.0F, 10.0F).m_60955_().m_60924_((bs, br, bp) -> false));
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
         case NORTH -> var10000 = Shapes.m_83110_(m_49796_((double)5.0F, (double)1.0F, (double)5.0F, (double)11.0F, (double)8.0F, (double)11.0F), m_49796_((double)6.0F, (double)8.0F, (double)6.0F, (double)10.0F, (double)10.0F, (double)10.0F));
         case EAST -> var10000 = Shapes.m_83110_(m_49796_((double)5.0F, (double)1.0F, (double)5.0F, (double)11.0F, (double)8.0F, (double)11.0F), m_49796_((double)6.0F, (double)8.0F, (double)6.0F, (double)10.0F, (double)10.0F, (double)10.0F));
         case WEST -> var10000 = Shapes.m_83110_(m_49796_((double)5.0F, (double)1.0F, (double)5.0F, (double)11.0F, (double)8.0F, (double)11.0F), m_49796_((double)6.0F, (double)8.0F, (double)6.0F, (double)10.0F, (double)10.0F, (double)10.0F));
         default -> var10000 = Shapes.m_83110_(m_49796_((double)5.0F, (double)1.0F, (double)5.0F, (double)11.0F, (double)8.0F, (double)11.0F), m_49796_((double)6.0F, (double)8.0F, (double)6.0F, (double)10.0F, (double)10.0F, (double)10.0F));
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
      return new ItemStack((ItemLike)CosmosModItems.LANTERNEXTINGUISHED.get());
   }

   public void m_6861_(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
      super.m_6861_(blockstate, world, pos, neighborBlock, fromPos, moving);
      ExtinguishedLanternOnTickUpdateProcedure.execute(world, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_());
   }

   public InteractionResult m_6227_(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
      super.m_6227_(blockstate, world, pos, entity, hand, hit);
      int x = pos.m_123341_();
      int y = pos.m_123342_();
      int z = pos.m_123343_();
      double hitX = hit.m_82450_().f_82479_;
      double hitY = hit.m_82450_().f_82480_;
      double hitZ = hit.m_82450_().f_82481_;
      Direction direction = hit.m_82434_();
      ExtinguishedLanternOnBlockRightClickedProcedure.execute(world, (double)x, (double)y, (double)z, entity);
      return InteractionResult.SUCCESS;
   }

   static {
      FACING = HorizontalDirectionalBlock.f_54117_;
   }
}
