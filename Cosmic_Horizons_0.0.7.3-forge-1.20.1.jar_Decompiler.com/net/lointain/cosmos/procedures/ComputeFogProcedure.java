package net.lointain.cosmos.procedures;

import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class ComputeFogProcedure {
   public static ViewportEvent.ComputeFogColor provider = null;

   public static void setColor(float level, int color) {
      if (!(level <= 0.0F)) {
         if (level >= 1.0F) {
            provider.setRed((float)(color >> 16 & 255) / 255.0F);
            provider.setGreen((float)(color >> 8 & 255) / 255.0F);
            provider.setBlue((float)(color & 255) / 255.0F);
         } else {
            level = Mth.m_14036_(level, 0.0F, 1.0F);
            provider.setRed(Mth.m_14036_(Mth.m_14179_(level, Mth.m_14036_(provider.getRed(), 0.0F, 1.0F), (float)(color >> 16 & 255) / 255.0F), 0.0F, 1.0F));
            provider.setGreen(Mth.m_14036_(Mth.m_14179_(level, Mth.m_14036_(provider.getGreen(), 0.0F, 1.0F), (float)(color >> 8 & 255) / 255.0F), 0.0F, 1.0F));
            provider.setBlue(Mth.m_14036_(Mth.m_14179_(level, Mth.m_14036_(provider.getBlue(), 0.0F, 1.0F), (float)(color & 255) / 255.0F), 0.0F, 1.0F));
         }

      }
   }

   @SubscribeEvent
   public static void computeFogColor(ViewportEvent.ComputeFogColor event) {
      provider = event;
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      Entity entity = provider.getCamera().m_90592_();
      if (level != null && entity != null) {
         Vec3 entPos = entity.m_20318_((float)provider.getPartialTick());
         execute(provider, level, entity);
      }

   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag var7 = CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (var7 instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)var7;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag dimensional_data = var10000;
            if (dimensional_data.m_128441_("fog_data")) {
               var7 = dimensional_data.m_128423_("fog_data");
               if (var7 instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)var7;
                  var10000 = _compoundTag.m_6426_();
               } else {
                  var10000 = new CompoundTag();
               }

               CompoundTag fog_data = var10000;
               if (fog_data.m_128441_("color")) {
                  var7 = fog_data.m_128423_("color");
                  if (var7 instanceof CompoundTag) {
                     CompoundTag _compoundTag = (CompoundTag)var7;
                     var10000 = _compoundTag.m_6426_();
                  } else {
                     var10000 = new CompoundTag();
                  }

                  CompoundTag color = var10000;
                  var7 = color.m_128423_("level");
                  double var22;
                  if (var7 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var7;
                     var22 = _doubleTag.m_7061_();
                  } else {
                     var22 = (double)0.0F;
                  }

                  float var23 = (float)var22;
                  Tag var9 = color.m_128423_("r");
                  double var10002;
                  if (var9 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var9;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  int var10001 = -16777216 | (int)var10002 << 16;
                  var9 = color.m_128423_("g");
                  if (var9 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var9;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  var10001 |= (int)var10002 << 8;
                  var9 = color.m_128423_("b");
                  if (var9 instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)var9;
                     var10002 = _doubleTag.m_7061_();
                  } else {
                     var10002 = (double)0.0F;
                  }

                  setColor(var23, var10001 | (int)var10002);
               }
            }
         }

      }
   }
}
