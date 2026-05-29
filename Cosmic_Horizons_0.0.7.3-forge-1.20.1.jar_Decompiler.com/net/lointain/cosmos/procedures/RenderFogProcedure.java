package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.shaders.FogShape;
import javax.annotation.Nullable;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class RenderFogProcedure {
   public static ViewportEvent.RenderFog provider = null;

   public static void setDistance(float start, float end) {
      provider.setNearPlaneDistance(start);
      provider.setFarPlaneDistance(end);
      if (!provider.isCanceled()) {
         provider.setCanceled(true);
      }

   }

   public static void setShape(FogShape shape) {
      provider.setFogShape(shape);
      if (!provider.isCanceled()) {
         provider.setCanceled(true);
      }

   }

   @SubscribeEvent
   public static void renderFog(ViewportEvent.RenderFog event) {
      provider = event;
      if (provider.getMode() == FogMode.FOG_TERRAIN) {
         ClientLevel level = Minecraft.m_91087_().f_91073_;
         Entity entity = provider.getCamera().m_90592_();
         if (level != null && entity != null) {
            Vec3 pos = entity.m_20318_((float)provider.getPartialTick());
            execute(provider, level, entity);
         }
      }

   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute((Event)null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128441_(entity.m_9236_().m_46472_().m_135782_().toString())) {
            Tag _stringTag = CosmosModVariables.WorldVariables.get(world).dimensional_data.m_128423_(entity.m_9236_().m_46472_().m_135782_().toString());
            CompoundTag var10000;
            if (_stringTag instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)_stringTag;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag dimensional_data = var10000;
            if (dimensional_data.m_128441_("fog_data")) {
               _stringTag = dimensional_data.m_128423_("fog_data");
               if (_stringTag instanceof CompoundTag) {
                  CompoundTag _compoundTag = (CompoundTag)_stringTag;
                  var10000 = _compoundTag.m_6426_();
               } else {
                  var10000 = new CompoundTag();
               }

               CompoundTag fog_data = var10000;
               if (fog_data.m_128441_("start") && fog_data.m_128441_("end")) {
                  Tag var7 = fog_data.m_128423_("shape");
                  String var18;
                  if (var7 instanceof StringTag) {
                     StringTag _stringTag = (StringTag)var7;
                     var18 = _stringTag.m_7916_();
                  } else {
                     var18 = "";
                  }

                  if (var18.equals("sphere")) {
                     setShape(FogShape.SPHERE);
                  } else {
                     var7 = fog_data.m_128423_("shape");
                     if (var7 instanceof StringTag) {
                        StringTag _stringTag = (StringTag)var7;
                        var18 = _stringTag.m_7916_();
                     } else {
                        var18 = "";
                     }

                     if (var18.equals("cylinder")) {
                        setShape(FogShape.CYLINDER);
                     }
                  }

                  _stringTag = fog_data.m_128423_("start");
                  double var20;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var20 = _doubleTag.m_7061_();
                  } else {
                     var20 = (double)0.0F;
                  }

                  float var21 = (float)var20;
                  _stringTag = fog_data.m_128423_("end");
                  double var10001;
                  if (_stringTag instanceof DoubleTag) {
                     DoubleTag _doubleTag = (DoubleTag)_stringTag;
                     var10001 = _doubleTag.m_7061_();
                  } else {
                     var10001 = (double)0.0F;
                  }

                  setDistance(var21, (float)var10001);
               }
            }
         }

      }
   }
}
