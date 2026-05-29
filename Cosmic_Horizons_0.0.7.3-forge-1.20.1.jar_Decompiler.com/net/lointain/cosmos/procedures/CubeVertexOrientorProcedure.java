package net.lointain.cosmos.procedures;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class CubeVertexOrientorProcedure {
   public static Vec3 execute(Direction direction, double plane_angle, Vec3 vertex) {
      if (direction != null && vertex != null) {
         Vec3 direction_vector = Vec3.f_82478_;
         Vec3 final_vector = Vec3.f_82478_;
         Vec3 peliminary_vector = Vec3.f_82478_;
         Vec3 rotations = Vec3.f_82478_;
         if (direction == Direction.UP) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)0.0F));
            final_vector = peliminary_vector;
         } else if (direction == Direction.DOWN) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)180.0F)).m_82496_(-(float)Math.PI);
            final_vector = peliminary_vector;
         } else if (direction == Direction.NORTH) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)0.0F)).m_82496_((-(float)Math.PI / 2F)).m_82524_(((float)Math.PI / 2F));
            final_vector = peliminary_vector;
         } else if (direction == Direction.SOUTH) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)0.0F)).m_82496_((-(float)Math.PI / 2F)).m_82524_(((float)Math.PI * 1.5F));
            final_vector = peliminary_vector;
         } else if (direction == Direction.EAST) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)0.0F)).m_82496_((-(float)Math.PI / 2F)).m_82524_(0.0F);
            final_vector = peliminary_vector;
         } else if (direction == Direction.WEST) {
            peliminary_vector = vertex.m_82524_(((float)Math.PI / 180F) * (float)(plane_angle + (double)0.0F)).m_82496_((-(float)Math.PI / 2F)).m_82524_((float)Math.PI);
            final_vector = peliminary_vector;
         }

         return final_vector;
      } else {
         return Vec3.f_82478_;
      }
   }
}
