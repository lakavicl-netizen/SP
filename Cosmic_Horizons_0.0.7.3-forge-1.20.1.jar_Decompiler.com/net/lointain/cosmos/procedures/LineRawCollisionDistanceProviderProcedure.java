package net.lointain.cosmos.procedures;

import net.minecraft.world.phys.Vec3;

public class LineRawCollisionDistanceProviderProcedure {
   public static double execute(double Pitch, double Roll, double Size, double Yaw, Vec3 CubePos, Vec3 PointA, Vec3 PointB) {
      if (CubePos != null && PointA != null && PointB != null) {
         boolean isCollided = false;
         Vec3 rotatedZAxis = Vec3.f_82478_;
         Vec3 toPlayer = Vec3.f_82478_;
         Vec3 rotatedXAxis = Vec3.f_82478_;
         Vec3 dir_vect = Vec3.f_82478_;
         Vec3 rotatedYAxis = Vec3.f_82478_;
         Vec3 cubePos = Vec3.f_82478_;
         Vec3 pointA = Vec3.f_82478_;
         Vec3 pointB = Vec3.f_82478_;
         Vec3 rotated = Vec3.f_82478_;
         Vec3 vector = Vec3.f_82478_;
         double roll = (double)0.0F;
         double range = (double)0.0F;
         double Scale = (double)0.0F;
         double distanceSqrZ = (double)0.0F;
         double distanceSqrY = (double)0.0F;
         double yaw = (double)0.0F;
         double distanceSqrX = (double)0.0F;
         double default_color = (double)0.0F;
         double step = (double)0.0F;
         double pitch = (double)0.0F;
         double distance = (double)0.0F;
         Scale = Size;
         default_color = (double)-1.6777216E7F;
         cubePos = CubePos;
         pointA = PointA;
         dir_vect = PointB.m_82546_(PointA);
         rotatedXAxis = (new Vec3((double)1.0F, (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-Roll)).m_82524_(((float)Math.PI / 180F) * (float)(-Yaw));
         rotatedYAxis = (new Vec3((double)0.0F, (double)1.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-Roll)).m_82496_(-0.017453292F * (float)Pitch);
         rotatedZAxis = (new Vec3((double)0.0F, (double)0.0F, (double)1.0F)).m_82496_(-0.017453292F * (float)Pitch).m_82524_(((float)Math.PI / 180F) * (float)(-Yaw));
         step = (double)0.25F;
         isCollided = false;

         for(double t = (double)0.0F; t < dir_vect.m_82553_(); t += step) {
            toPlayer = pointA.m_82549_(dir_vect.m_82541_().m_82490_(t)).m_82546_(cubePos);
            distanceSqrX = rotatedXAxis.m_82490_(toPlayer.m_82526_(rotatedXAxis)).m_82556_();
            distanceSqrY = rotatedYAxis.m_82490_(toPlayer.m_82526_(rotatedYAxis)).m_82556_();
            distanceSqrZ = rotatedZAxis.m_82490_(toPlayer.m_82526_(rotatedZAxis)).m_82556_();
            range = Scale * Scale / (double)4.0F;
            isCollided = distanceSqrX <= range && distanceSqrY <= range && distanceSqrZ <= range;
            if (isCollided) {
               distance = t;
               break;
            }
         }

         return distance;
      } else {
         return (double)0.0F;
      }
   }
}
