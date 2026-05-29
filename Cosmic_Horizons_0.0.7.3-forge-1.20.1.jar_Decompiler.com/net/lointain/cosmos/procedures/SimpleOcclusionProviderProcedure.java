package net.lointain.cosmos.procedures;

import java.util.ArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.phys.Vec3;

public class SimpleOcclusionProviderProcedure {
   public static double execute(ListTag Lights, double Pitch, double Roll, double Size, double Yaw, Vec3 CubePos, Vec3 Vertex) {
      if (Lights != null && CubePos != null && Vertex != null) {
         Vec3 Light_Rot = Vec3.f_82478_;
         Vec3 Light_Pos = Vec3.f_82478_;
         Vec3 end_positioning_vector = Vec3.f_82478_;
         boolean logic = false;
         double Default_color = (double)0.0F;
         double red = (double)0.0F;
         double green = (double)0.0F;
         double blue = (double)0.0F;
         double alpha = (double)0.0F;
         double scale = (double)0.0F;
         ArrayList<Vec3> vertex_positions = new ArrayList();
         vertex_positions.add(new Vec3((double)-0.5F, (double)-0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)-0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)-0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)-0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)0.5F, (double)0.5F));

         for(Tag dataelementiterator : Lights) {
            CompoundTag var10000;
            if (dataelementiterator instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag light_data = var10000;
            Vec3 var53 = new Vec3;
            Tag var33 = light_data.m_128423_("pitch");
            double var10002;
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10002 = _doubleTag.m_7061_();
            } else {
               var10002 = (double)0.0F;
            }

            var33 = light_data.m_128423_("yaw");
            double var10003;
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10003 = _doubleTag.m_7061_();
            } else {
               var10003 = (double)0.0F;
            }

            var33 = light_data.m_128423_("roll");
            double var10004;
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10004 = _doubleTag.m_7061_();
            } else {
               var10004 = (double)0.0F;
            }

            var53.<init>(var10002, var10003, var10004);
            Light_Rot = var53;
            var53 = new Vec3;
            var33 = light_data.m_128423_("x");
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10002 = _doubleTag.m_7061_();
            } else {
               var10002 = (double)0.0F;
            }

            var33 = light_data.m_128423_("y");
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10003 = _doubleTag.m_7061_();
            } else {
               var10003 = (double)0.0F;
            }

            var33 = light_data.m_128423_("z");
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var10004 = _doubleTag.m_7061_();
            } else {
               var10004 = (double)0.0F;
            }

            var53.<init>(var10002, var10003, var10004);
            Light_Pos = var53;
            var33 = light_data.m_128423_("scale");
            double var55;
            if (var33 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var33;
               var55 = _doubleTag.m_7061_();
            } else {
               var55 = (double)0.0F;
            }

            scale = var55;

            for(Vec3 vectoriterator : vertex_positions) {
               end_positioning_vector = Light_Pos.m_82549_(vectoriterator.m_82535_(-0.017453292F * (float)(-Light_Rot.m_7094_())).m_82496_(-0.017453292F * (float)Light_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-Light_Rot.m_7098_())).m_82559_(new Vec3(scale, scale, scale)));
               logic = LineRawCollisionDetectorProcedure.execute(Pitch, Roll, Size, Yaw, CubePos, Vertex, end_positioning_vector);
               if (!logic) {
                  red = (double)180.0F;
                  green = (double)180.0F;
                  blue = (double)180.0F;
                  break;
               }

               red = (double)0.0F;
               green = (double)0.0F;
               blue = (double)0.0F;
            }

            if (!logic) {
               break;
            }
         }

         return (double)(-16777216 | (int)red << 16 | (int)green << 8 | (int)blue);
      } else {
         return (double)0.0F;
      }
   }
}
