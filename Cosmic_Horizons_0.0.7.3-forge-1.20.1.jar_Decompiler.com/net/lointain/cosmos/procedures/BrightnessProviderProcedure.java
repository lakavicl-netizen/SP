package net.lointain.cosmos.procedures;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class BrightnessProviderProcedure {
   public static double execute(ListTag Lights, ListTag Objects, double Color, String Mode, String OBJMode, Vec3 Normal, Vec3 Position, Vec3 Rotation, Vec3 Scale, Vec3 Vertex) {
      if (Lights != null && Objects != null && Mode != null && OBJMode != null && Normal != null && Position != null && Rotation != null && Scale != null && Vertex != null) {
         List<Object> Light_Sources_List = new ArrayList();
         Vec3 Normal_vector = Vec3.f_82478_;
         Vec3 Vertex_position = Vec3.f_82478_;
         Vec3 Vertex_normal = Vec3.f_82478_;
         Vec3 Modified_vector = Vec3.f_82478_;
         Vec3 Position_point = Vec3.f_82478_;
         Vec3 Normal_scale = Vec3.f_82478_;
         Vec3 Rotations = Vec3.f_82478_;
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
         double counter = (double)0.0F;
         double multiple = (double)0.0F;
         String Moding = "";
         String object_mode = "";
         object_mode = OBJMode;
         ArrayList<Vec3> vertex_positions = new ArrayList();
         ArrayList<Vec3> main_position = new ArrayList();
         vertex_positions.add(new Vec3((double)-0.5F, (double)-0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)-0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)-0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)-0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
         vertex_positions.add(new Vec3((double)0.5F, (double)0.5F, (double)0.5F));
         ArrayList<Vec3> Light_Sources = new ArrayList();
         Moding = Mode;
         Vertex_normal = Vertex.m_82541_();
         Modified_vector = Vertex_normal.m_82535_(-0.017453292F * (float)(-Rotation.m_7094_())).m_82496_(-0.017453292F * (float)Rotation.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-Rotation.m_7098_()));
         Position_point = Position;
         ListTag opaque_object_list = Objects;

         for(Tag dataelementiterator : Lights) {
            CompoundTag var10000;
            if (dataelementiterator instanceof CompoundTag) {
               CompoundTag _compoundTag = (CompoundTag)dataelementiterator;
               var10000 = _compoundTag.m_6426_();
            } else {
               var10000 = new CompoundTag();
            }

            CompoundTag light_data = var10000;
            Vec3 var90 = new Vec3;
            Tag var50 = light_data.m_128423_("pitch");
            double var10002;
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10002 = _doubleTag.m_7061_();
            } else {
               var10002 = (double)0.0F;
            }

            var50 = light_data.m_128423_("yaw");
            double var10003;
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10003 = _doubleTag.m_7061_();
            } else {
               var10003 = (double)0.0F;
            }

            var50 = light_data.m_128423_("roll");
            double var10004;
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10004 = _doubleTag.m_7061_();
            } else {
               var10004 = (double)0.0F;
            }

            var90.<init>(var10002, var10003, var10004);
            Light_Rot = var90;
            var90 = new Vec3;
            var50 = light_data.m_128423_("x");
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10002 = _doubleTag.m_7061_();
            } else {
               var10002 = (double)0.0F;
            }

            var50 = light_data.m_128423_("y");
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10003 = _doubleTag.m_7061_();
            } else {
               var10003 = (double)0.0F;
            }

            var50 = light_data.m_128423_("z");
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var10004 = _doubleTag.m_7061_();
            } else {
               var10004 = (double)0.0F;
            }

            var90.<init>(var10002, var10003, var10004);
            Light_Pos = var90;
            var50 = light_data.m_128423_("scale");
            double var92;
            if (var50 instanceof DoubleTag) {
               DoubleTag _doubleTag = (DoubleTag)var50;
               var92 = _doubleTag.m_7061_();
            } else {
               var92 = (double)0.0F;
            }

            scale = var92;

            for(Vec3 vectoriterator : vertex_positions) {
               end_positioning_vector = Light_Pos.m_82549_(vectoriterator.m_82535_(-0.017453292F * (float)(-Light_Rot.m_7094_())).m_82496_(-0.017453292F * (float)Light_Rot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-Light_Rot.m_7098_())).m_82559_(new Vec3(scale, scale, scale)));
               if (object_mode.equals("cull")) {
                  logic = OcclusionDetectorProcedure.execute(opaque_object_list, Modified_vector, end_positioning_vector, Position_point);
               }

               if (!logic) {
                  Light_Sources.add(end_positioning_vector);
               }
            }

            main_position.add(Light_Pos);
         }

         if (Mode.equals("color")) {
            multiple = (double)2.0F;
         } else if (Mode.equals("i_alpha")) {
            multiple = (double)2.0F;
         } else {
            multiple = (double)1.0F;
         }

         for(Vec3 vectoriterator : Light_Sources) {
            Light_Sources_List.add(Mth.m_14008_((Moding.equals("i_alpha") ? Position_point.m_82546_(vectoriterator) : vectoriterator.m_82546_(Position_point)).m_82541_().m_82526_(Modified_vector) * multiple, (double)0.0F, (double)1.0F));
         }

         try {
            Light_Sources_List = Light_Sources_List.stream().sorted().toList();
         } catch (Exception e) {
            e.printStackTrace();
         }

         if (!Light_Sources_List.isEmpty()) {
            if (Moding.equals("color")) {
               double var93 = (double)((int)Color >> 16 & 255);
               Object _doubleValue = Light_Sources_List.get(Mth.m_14045_(Light_Sources_List.size() - 1, 0, Light_Sources_List.size()));
               double var10001;
               if (_doubleValue instanceof Number) {
                  Number _doubleValue = (Number)_doubleValue;
                  var10001 = _doubleValue.doubleValue();
               } else {
                  var10001 = (double)0.0F;
               }

               red = var93 * var10001;
               var93 = (double)((int)Color >> 8 & 255);
               _doubleValue = Light_Sources_List.get(Mth.m_14045_(Light_Sources_List.size() - 1, 0, Light_Sources_List.size()));
               if (_doubleValue instanceof Number) {
                  Number _doubleValue = (Number)_doubleValue;
                  var10001 = _doubleValue.doubleValue();
               } else {
                  var10001 = (double)0.0F;
               }

               green = var93 * var10001;
               var93 = (double)((int)Color & 255);
               _doubleValue = Light_Sources_List.get(Mth.m_14045_(Light_Sources_List.size() - 1, 0, Light_Sources_List.size()));
               if (_doubleValue instanceof Number) {
                  Number _doubleValue = (Number)_doubleValue;
                  var10001 = _doubleValue.doubleValue();
               } else {
                  var10001 = (double)0.0F;
               }

               blue = var93 * var10001;
               alpha = (double)((int)Color >>> 24);
            } else {
               red = (double)((int)Color >> 16 & 255);
               green = (double)((int)Color >> 8 & 255);
               blue = (double)((int)Color & 255);
               alpha = (double)((int)Color >>> 24);
               Object var81 = Light_Sources_List.get(Mth.m_14045_(Light_Sources_List.size() - 1, 0, Light_Sources_List.size()));
               double var98;
               if (var81 instanceof Number) {
                  Number _doubleValue = (Number)var81;
                  var98 = _doubleValue.doubleValue();
               } else {
                  var98 = (double)0.0F;
               }

               if (var98 < (double)0.25F) {
                  var98 = (double)0.0F;
               } else {
                  var81 = Light_Sources_List.get(Mth.m_14045_(Light_Sources_List.size() - 1, 0, Light_Sources_List.size()));
                  if (var81 instanceof Number) {
                     Number _doubleValue = (Number)var81;
                     var98 = _doubleValue.doubleValue();
                  } else {
                     var98 = (double)0.0F;
                  }

                  var98 /= (double)2.0F;
               }

               alpha *= var98;
            }
         }

         return (double)((int)alpha << 24 | (int)red << 16 | (int)green << 8 | (int)blue);
      } else {
         return (double)0.0F;
      }
   }
}
