package net.lointain.cosmos.procedures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.phys.Vec3;

public class OcclusionDetectorProcedure {
   public static boolean execute(ListTag Object_List, Vec3 A, Vec3 B, Vec3 MainPos) {
      if (Object_List != null && A != null && B != null && MainPos != null) {
         double opaque_scale = (double)0.0F;
         boolean logic = false;
         Vec3 Position_point = Vec3.f_82478_;
         Vec3 Opaque_Rot = Vec3.f_82478_;
         Vec3 Opaque_Pos = Vec3.f_82478_;
         Vec3 Modified_vector = Vec3.f_82478_;
         Vec3 end_positioning_vector = Vec3.f_82478_;
         logic = false;
         Position_point = MainPos;
         end_positioning_vector = B;
         Modified_vector = A;

         for(Tag dataelementiterator : Object_List) {
            CompoundTag var10000;
            if (dataelementiterator instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag opaque_object_data = var10000;
            Vec3 var38 = new Vec3;
            Tag var17 = opaque_object_data.m_128423_("x");
            double var10002;
            if (var17 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var17;
               var10002 = _doubleTag.m_7061_();
            } else {
               var10002 = (double)0.0F;
            }

            var17 = opaque_object_data.m_128423_("y");
            double var10003;
            if (var17 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var17;
               var10003 = _doubleTag.m_7061_();
            } else {
               var10003 = (double)0.0F;
            }

            var17 = opaque_object_data.m_128423_("z");
            double var10004;
            if (var17 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var17;
               var10004 = _doubleTag.m_7061_();
            } else {
               var10004 = (double)0.0F;
            }

            var38.<init>(var10002, var10003, var10004);
            Opaque_Pos = var38;
            if (!Position_point.equals(Opaque_Pos)) {
               var38 = new Vec3;
               var17 = opaque_object_data.m_128423_("pitch");
               if (var17 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var17;
                  var10002 = _doubleTag.m_7061_();
               } else {
                  var10002 = (double)0.0F;
               }

               var17 = opaque_object_data.m_128423_("yaw");
               if (var17 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var17;
                  var10003 = _doubleTag.m_7061_();
               } else {
                  var10003 = (double)0.0F;
               }

               var17 = opaque_object_data.m_128423_("roll");
               if (var17 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var17;
                  var10004 = _doubleTag.m_7061_();
               } else {
                  var10004 = (double)0.0F;
               }

               var38.<init>(var10002, var10003, var10004);
               Opaque_Rot = var38;
               var17 = opaque_object_data.m_128423_("scale");
               double var40;
               if (var17 instanceof DoubleTag) {
                  DoubleTag _doubleTag = (DoubleTag)var17;
                  var40 = _doubleTag.m_7061_();
               } else {
                  var40 = (double)0.0F;
               }

               opaque_scale = var40;
               if (LineRawCollisionDetectorProcedure.execute(Opaque_Rot.m_7096_(), Opaque_Rot.m_7094_(), opaque_scale, Opaque_Rot.m_7098_(), Opaque_Pos, Modified_vector, end_positioning_vector)) {
                  logic = true;
                  break;
               }

               logic = false;
            }
         }

         return logic;
      } else {
         return false;
      }
   }
}
