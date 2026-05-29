package net.lointain.cosmos.procedures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.DimensionSpecialEffectsManager;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class PlayJellyProcedure {
   private static int ticks = 0;
   private static float partialTick = 0.0F;
   private static ParticleOptions genericParticle;
   private static ParticleOptions specialParticle;
   private static SoundEvent genericSound;
   private static SoundEvent specialSound;
   private static final Predicate<Object[]> PREDICATE;

   public static void setParticles(ParticleOptions genericParticle, ParticleOptions specialParticle) {
      PlayJellyProcedure.genericParticle = genericParticle;
      PlayJellyProcedure.specialParticle = specialParticle;
   }

   public static void setSounds(SoundEvent genericSound, SoundEvent specialSound) {
      PlayJellyProcedure.genericSound = genericSound;
      PlayJellyProcedure.specialSound = specialSound;
   }

   public static void addEffects(int target, float power, int range, boolean particles, boolean sounds, boolean constant) {
      if (particles || sounds) {
         Minecraft minecraft = Minecraft.m_91087_();
         ClientLevel level = minecraft.f_91073_;
         float factor = (constant ? 1.0F : level.m_46722_(1.0F)) / (Minecraft.m_91405_() ? 1.0F : 2.0F);
         Camera camera = minecraft.f_91063_.m_109153_();
         RandomSource randomSource = RandomSource.m_216335_((long)ticks * 312987231L);
         BlockPos iPos = BlockPos.m_274446_(camera.m_90583_());
         boolean effects = false;
         ParticleStatus particleStatus = (ParticleStatus)minecraft.f_91066_.m_231929_().m_231551_();
         boolean visible = particleStatus != ParticleStatus.MINIMAL;
         int amount = (int)(100.0F * factor * factor) / (particleStatus == ParticleStatus.DECREASED ? 2 : 1);
         amount = (int)((float)amount * power);

         for(int i = 0; i < amount; ++i) {
            int ix = randomSource.m_188503_((range << 1) + 1) - range;
            int iz = randomSource.m_188503_((range << 1) + 1) - range;
            BlockPos surface = level.m_5452_(Types.MOTION_BLOCKING, iPos.m_7918_(ix, 0, iz));
            if (surface.m_123342_() > level.m_141937_() && surface.m_123342_() <= iPos.m_123342_() + range && surface.m_123342_() >= iPos.m_123342_() - range) {
               Biome.Precipitation precipitation = ((Biome)level.m_204166_(surface).m_203334_()).m_264600_(surface);
               switch (target) {
                  case 1 -> effects = precipitation == Precipitation.RAIN;
                  case 2 -> effects = precipitation == Precipitation.SNOW;
                  case 4 -> effects = precipitation == Precipitation.RAIN || precipitation == Precipitation.SNOW;
                  case 8 -> effects = precipitation == Precipitation.NONE;
                  case 16 -> effects = true;
               }

               if (effects) {
                  BlockPos ground = surface.m_7495_();
                  if (particles && visible) {
                     double dx = randomSource.m_188500_();
                     double dz = randomSource.m_188500_();
                     BlockState blockState = level.m_8055_(ground);
                     FluidState fluidState = level.m_6425_(ground);
                     VoxelShape voxelShape = blockState.m_60812_(level, ground);
                     double maxHeight = Math.max(voxelShape.m_83290_(Axis.Y, dx, dz), (double)fluidState.m_76155_(level, ground));
                     ParticleOptions particleOptions = !fluidState.m_205070_(FluidTags.f_13132_) && !blockState.m_60713_(Blocks.f_50450_) && !CampfireBlock.m_51319_(blockState) ? genericParticle : specialParticle;
                     level.m_7106_(particleOptions, (double)ground.m_123341_() + dx, (double)ground.m_123342_() + maxHeight, (double)ground.m_123343_() + dz, (double)0.0F, (double)0.0F, (double)0.0F);
                  }

                  if (sounds && i == 0 && ground != null && (ticks & 3) > randomSource.m_188503_(3)) {
                     if (ground.m_123342_() > iPos.m_123342_() + 1 && level.m_5452_(Types.MOTION_BLOCKING, iPos).m_123342_() > Mth.m_14143_((float)iPos.m_123342_())) {
                        level.m_245747_(ground, specialSound, SoundSource.WEATHER, 0.1F, 0.5F, false);
                     } else {
                        level.m_245747_(ground, genericSound, SoundSource.WEATHER, 0.2F, 1.0F, false);
                     }
                  }
               }
            }
         }

      }
   }

   @SubscribeEvent
   public static void effectsSetup(FMLClientSetupEvent event) {
      try {
         Field field = DimensionSpecialEffectsManager.class.getDeclaredField("EFFECTS");
         field.setAccessible(true);
         UnmodifiableIterator var2 = ((ImmutableMap)field.get((Object)null)).values().iterator();

         while(var2.hasNext()) {
            DimensionSpecialEffects dimensionSpecialEffects = (DimensionSpecialEffects)var2.next();
            if (dimensionSpecialEffects instanceof SetupProcedure.CosmosModDimensionSpecialEffects cosmosSpecialEffects) {
               Class<?> effects = dimensionSpecialEffects.getClass();
               ((Set)effects.getField("CUSTOM_EFFECTS").get((Object)null)).add(PREDICATE);
            }
         }
      } catch (Exception var6) {
      }

   }

   public static boolean execute(LevelAccessor world, Entity entity) {
      return execute((Event)null, world, entity);
   }

   private static boolean execute(@Nullable Event event, final LevelAccessor world, Entity entity) {
      if (entity == null) {
         return false;
      } else {
         boolean logic = false;
         String generic = "";
         String special = "";
         String condition = "";
         if (CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var10 = CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (var10 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var10;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag dimensional_data = var10000;
            if (dimensional_data.m_128441_("weather_data")) {
               var10 = dimensional_data.m_128423_("weather_data");
               if (var10 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var10;
                  var10000 = _compoundTag.m_6426_();
               } else {
                  var10000 = new CompoundTag();
               }

               CompoundTag weather_data = var10000;
               if (world instanceof ClientLevel) {
                  BlockPos _renderCenter = Minecraft.m_91087_().f_91063_.m_109153_().m_90588_();
                  int _renderLength = Minecraft.m_91405_() ? 10 : 5;
                  int _renderRange = Minecraft.m_91405_() ? 10 : 5;
                  _renderLength = _renderRange > _renderLength ? _renderRange : _renderLength;

                  for(int _renderZ = -_renderRange; _renderZ <= _renderRange; ++_renderZ) {
                     for(int _renderX = -_renderRange; _renderX <= _renderRange; ++_renderX) {
                        int positionx = _renderCenter.m_123341_() + _renderX;
                        int positionz = _renderCenter.m_123343_() + _renderZ;
                        int positiony = world.m_6924_(Types.MOTION_BLOCKING, positionx, positionz);
                        if (positiony <= _renderCenter.m_123342_() + _renderLength) {
                           positiony = positiony < _renderCenter.m_123342_() - _renderLength ? _renderCenter.m_123342_() - _renderLength : positiony;
                           Tag var18 = weather_data.m_128423_("condition");
                           String var42;
                           if (var18 instanceof StringTag) {
                              StringTag _stringTag = (StringTag)var18;
                              var42 = _stringTag.m_7916_();
                           } else {
                              var42 = "";
                           }

                           condition = var42;
                           if (condition.equals("rain")) {
                              if (((<undefinedtype>)(new Object() {
                                 public boolean is(LevelAccessor levelAccessor, BlockPos blockPos, Biome.Precipitation precipitation) {
                                    return ((Biome)world.m_204166_(blockPos).m_203334_()).m_264600_(blockPos) == precipitation;
                                 }
                              })).is(world, new BlockPos(positionx, positiony, positionz), Precipitation.RAIN)) {
                                 logic = true;
                              } else {
                                 logic = false;
                              }
                           } else if (condition.equals("snow")) {
                              if (((<undefinedtype>)(new Object() {
                                 public boolean is(LevelAccessor levelAccessor, BlockPos blockPos, Biome.Precipitation precipitation) {
                                    return ((Biome)world.m_204166_(blockPos).m_203334_()).m_264600_(blockPos) == precipitation;
                                 }
                              })).is(world, new BlockPos(positionx, positiony, positionz), Precipitation.SNOW)) {
                                 logic = true;
                              } else {
                                 logic = false;
                              }
                           } else if (condition.equals("none")) {
                              if (((<undefinedtype>)(new Object() {
                                 public boolean is(LevelAccessor levelAccessor, BlockPos blockPos, Biome.Precipitation precipitation) {
                                    return ((Biome)world.m_204166_(blockPos).m_203334_()).m_264600_(blockPos) == precipitation;
                                 }
                              })).is(world, new BlockPos(positionx, positiony, positionz), Precipitation.NONE)) {
                                 logic = true;
                              } else {
                                 logic = false;
                              }
                           }

                           logic = false;
                           if (logic) {
                              var18 = weather_data.m_128423_("sound_generic");
                              if (var18 instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)var18;
                                 var42 = _stringTag.m_7916_();
                              } else {
                                 var42 = "";
                              }

                              generic = var42;
                              var18 = weather_data.m_128423_("sound_special");
                              if (var18 instanceof StringTag) {
                                 StringTag _stringTag = (StringTag)var18;
                                 var42 = _stringTag.m_7916_();
                              } else {
                                 var42 = "";
                              }

                              special = var42;
                              setSounds((SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(generic)), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(special)));
                              var18 = weather_data.m_128423_("power");
                              double var10001;
                              if (var18 instanceof DoubleTag) {
                                 DoubleTag _doubleTag = (DoubleTag)var18;
                                 var10001 = _doubleTag.m_7061_();
                              } else {
                                 var10001 = (double)0.0F;
                              }

                              addEffects(16, (float)var10001, Minecraft.m_91405_() ? 10 : 5, false, true, true);
                           }
                        }
                     }
                  }
               }
            } else {
               addEffects(1, 1.5F, Minecraft.m_91405_() ? 10 : 5, true, true, false);
            }
         }

         return true;
      }
   }

   static {
      genericParticle = ParticleTypes.f_123761_;
      specialParticle = ParticleTypes.f_123762_;
      genericSound = SoundEvents.f_12541_;
      specialSound = SoundEvents.f_12542_;
      PREDICATE = (params) -> {
         Minecraft minecraft = Minecraft.m_91087_();
         Entity entity = minecraft.f_91063_.m_109153_().m_90592_();
         partialTick = minecraft.getPartialTick();
         ticks = (Integer)params[1];
         if (entity != null) {
            ClientLevel level = minecraft.f_91073_;
            Vec3 pos = entity.m_20318_(partialTick);
            return execute((Event)null, level, entity);
         } else {
            return false;
         }
      };
   }
}
