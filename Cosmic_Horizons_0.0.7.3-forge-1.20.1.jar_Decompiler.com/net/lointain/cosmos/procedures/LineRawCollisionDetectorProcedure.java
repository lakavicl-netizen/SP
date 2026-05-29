package net.lointain.cosmos.procedures;

import net.minecraft.world.phys.Vec3;

public class LineRawCollisionDetectorProcedure {
   public static boolean execute(double Pitch, double Roll, double Size, double Yaw, Vec3 CubePos, Vec3 PointA, Vec3 PointB) {
      if (CubePos != null && PointA != null && PointB != null) {
         Vec3 dir_vect = PointB.m_82546_(PointA);
         Vec3 toPointA = PointA.m_82546_(CubePos);
         Vec3 toPointB = PointB.m_82546_(CubePos);
         Vec3 rotatedXAxis = (new Vec3((double)1.0F, (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-Roll)).m_82524_(((float)Math.PI / 180F) * (float)(-Yaw));
         Vec3 rotatedYAxis = (new Vec3((double)0.0F, (double)1.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-Roll)).m_82496_(-0.017453292F * (float)Pitch);
         Vec3 rotatedZAxis = (new Vec3((double)0.0F, (double)0.0F, (double)1.0F)).m_82496_(-0.017453292F * (float)Pitch).m_82524_(((float)Math.PI / 180F) * (float)(-Yaw));
         return checkLineSegmentIntersection(toPointA, toPointB, rotatedXAxis, rotatedYAxis, rotatedZAxis, Size);
      } else {
         return false;
      }
   }

   private static boolean checkLineSegmentIntersection(Vec3 pointA, Vec3 pointB, Vec3 rotatedXAxis, Vec3 rotatedYAxis, Vec3 rotatedZAxis, double size) {
      double tmin = (double)0.0F;
      double tmax = (double)1.0F;
      Vec3 dir_vect = pointB.m_82546_(pointA);

      for(int i = 0; i < 3; ++i) {
         Vec3 axis = i == 0 ? rotatedXAxis : (i == 1 ? rotatedYAxis : rotatedZAxis);
         double axisLength = size * (double)0.5F;
         double invDir = (double)1.0F / dir_vect.m_82526_(axis);
         double t1 = (-axisLength - pointA.m_82526_(axis)) * invDir;
         double t2 = (axisLength - pointA.m_82526_(axis)) * invDir;
         tmin = Math.max(tmin, Math.min(t1, t2));
         tmax = Math.min(tmax, Math.max(t1, t2));
         if (tmin > tmax) {
            return false;
         }
      }

      return true;
   }
}
