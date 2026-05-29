package net.lointain.cosmos.block;

import net.lointain.cosmos.procedures.BrightFireEntityCollidesInTheBlockProcedure;
import net.lointain.cosmos.procedures.BrightFireOnTickUpdateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;

public class BrightFireBlock extends Block implements SimpleWaterloggedBlock {
   public static final BooleanProperty WATERLOGGED;

   public BrightFireBlock() {
      super(Properties.m_284310_().m_284180_(MapColor.f_283816_).m_60918_(new ForgeSoundType(1.0F, 1.0F, () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.ambient")), () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.campfire.crackle")), () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), () -> (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.campfire.crackle")))).m_60966_().m_60910_().m_60955_().m_60977_().m_60924_((bs, br, bp) -> false));
      this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_(WATERLOGGED, false));
   }

   public boolean m_7420_(BlockState state, BlockGetter reader, BlockPos pos) {
      return state.m_60819_().m_76178_();
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   public VoxelShape m_5909_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.m_83040_();
   }

   public VoxelShape m_5940_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return m_49796_((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)1.0F, (double)16.0F);
   }

   protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
      super.m_7926_(builder);
      builder.m_61104_(new Property[]{WATERLOGGED});
   }

   public BlockState m_5573_(BlockPlaceContext context) {
      boolean flag = context.m_43725_().m_6425_(context.m_8083_()).m_76152_() == Fluids.f_76193_;
      return (BlockState)super.m_5573_(context).m_61124_(WATERLOGGED, flag);
   }

   public FluidState m_5888_(BlockState state) {
      return (Boolean)state.m_61143_(WATERLOGGED) ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
   }

   public BlockState m_7417_(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)state.m_61143_(WATERLOGGED)) {
         world.m_186469_(currentPos, Fluids.f_76193_, Fluids.f_76193_.m_6718_(world));
      }

      return super.m_7417_(state, facing, facingState, world, currentPos, facingPos);
   }

   public boolean m_6864_(BlockState state, BlockPlaceContext context) {
      return context.m_43722_().m_41720_() != this.m_5456_();
   }

   public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
      return 2;
   }

   public void m_213897_(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
      super.m_213897_(blockstate, world, pos, random);
      int x = pos.m_123341_();
      int y = pos.m_123342_();
      int z = pos.m_123343_();
      BrightFireOnTickUpdateProcedure.execute(world, (double)x, (double)y, (double)z);
   }

   public void m_7892_(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.m_7892_(blockstate, world, pos, entity);
      BrightFireEntityCollidesInTheBlockProcedure.execute(world, entity);
   }

   static {
      WATERLOGGED = BlockStateProperties.f_61362_;
   }
}
