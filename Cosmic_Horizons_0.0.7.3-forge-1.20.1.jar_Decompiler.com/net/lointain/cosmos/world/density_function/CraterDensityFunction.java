package net.lointain.cosmos.world.density_function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;

public class CraterDensityFunction implements DensityFunction {
   public static final MapCodec<CraterDensityFunction> DATA_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(DensityFunction.f_208218_.fieldOf("data").forGetter(CraterDensityFunction::getInput), Codec.intRange(0, Integer.MAX_VALUE).fieldOf("approx_diameter").forGetter(CraterDensityFunction::getRange), Codec.floatRange(0.0F, 1.0F).fieldOf("verge").forGetter(CraterDensityFunction::getThreshold)).apply(instance, CraterDensityFunction::new));
   public static final KeyDispatchDataCodec<CraterDensityFunction> CODEC;
   private static final int INCREMENT = 4;
   private final DensityFunction data;
   private final int approx_diameter;
   private final double sqRange;
   private final float verge;

   public CraterDensityFunction(DensityFunction data, int approx_diameter, float verge) {
      this.data = data;
      this.approx_diameter = approx_diameter;
      this.verge = verge;
      this.sqRange = (double)(approx_diameter * approx_diameter);
   }

   public double m_207386_(DensityFunction.FunctionContext pos) {
      int closestSqDist = Integer.MAX_VALUE;
      int randA = ThreadLocalRandom.current().nextInt(-4, 8);
      int randB = ThreadLocalRandom.current().nextInt(-4, 8);
      int blockX = pos.m_207115_();
      int blockY = pos.m_207114_();
      int blockZ = pos.m_207113_();

      for(int x = -this.approx_diameter; x <= this.approx_diameter; ++x) {
         for(int z = -this.approx_diameter; z <= this.approx_diameter; ++z) {
            int sqDist = x * x + z * z;
            if ((double)sqDist <= this.sqRange) {
               DensityFunction.SinglePointContext samplePos = new DensityFunction.SinglePointContext(blockX + x * 4, blockY, blockZ + z * 4);
               double sampledValue = this.data.m_207386_(samplePos);
               if (sampledValue >= (double)this.verge) {
                  closestSqDist = Math.min(closestSqDist, sqDist);
               }
            }
         }
      }

      if (closestSqDist == Integer.MAX_VALUE) {
         return (double)0.0F;
      } else {
         return (double)1.0F - (double)closestSqDist / this.sqRange;
      }
   }

   public void m_207362_(double[] densities, DensityFunction.ContextProvider applier) {
      applier.m_207207_(densities, this);
   }

   public DensityFunction m_207456_(DensityFunction.Visitor visitor) {
      return new CraterDensityFunction(this.data.m_207456_(visitor), this.approx_diameter, this.verge);
   }

   public double m_207402_() {
      return (double)0.0F;
   }

   public double m_207401_() {
      return (double)1.0F;
   }

   public DensityFunction getInput() {
      return this.data;
   }

   public int getRange() {
      return this.approx_diameter;
   }

   public float getThreshold() {
      return this.verge;
   }

   public KeyDispatchDataCodec<? extends DensityFunction> m_214023_() {
      return CODEC;
   }

   static {
      CODEC = KeyDispatchDataCodec.m_216238_(DATA_CODEC);
   }
}
