package net.lointain.cosmos.procedures;

import java.util.ArrayList;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class LightCubeMapProcedure {
   public static ArrayList<Vec3> execute(Direction direction) {
      if (direction == null) {
         return new ArrayList();
      } else {
         double Order_num = (double)0.0F;
         Direction Directions = Direction.NORTH;
         ArrayList<Vec3> final_list = new ArrayList();
         if (direction == Direction.UP) {
            ArrayList<Vec3> top = new ArrayList();
            top.add(new Vec3((double)0.125F, (double)0.0F, (double)0.0F));
            top.add(new Vec3((double)0.125F, (double)0.0F, (double)0.25F));
            top.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F));
            top.add(new Vec3((double)0.25F, (double)0.0F, (double)0.0F));
            final_list = top;
         } else if (direction == Direction.DOWN) {
            ArrayList<Vec3> bottom = new ArrayList();
            bottom.add(new Vec3((double)0.375F, (double)0.0F, (double)0.0F));
            bottom.add(new Vec3((double)0.375F, (double)0.0F, (double)0.25F));
            bottom.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F));
            bottom.add(new Vec3((double)0.25F, (double)0.0F, (double)0.0F));
            final_list = bottom;
         } else if (direction == Direction.NORTH) {
            ArrayList<Vec3> front = new ArrayList();
            front.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F));
            front.add(new Vec3((double)0.25F, (double)0.0F, (double)0.5F));
            front.add(new Vec3((double)0.375F, (double)0.0F, (double)0.5F));
            front.add(new Vec3((double)0.375F, (double)0.0F, (double)0.25F));
            final_list = front;
         } else if (direction == Direction.SOUTH) {
            ArrayList<Vec3> back = new ArrayList();
            back.add(new Vec3((double)0.0F, (double)0.0F, (double)0.25F));
            back.add(new Vec3((double)0.0F, (double)0.0F, (double)0.5F));
            back.add(new Vec3((double)0.125F, (double)0.0F, (double)0.5F));
            back.add(new Vec3((double)0.125F, (double)0.0F, (double)0.25F));
            final_list = back;
         } else if (direction == Direction.WEST) {
            ArrayList<Vec3> left = new ArrayList();
            left.add(new Vec3((double)0.375F, (double)0.0F, (double)0.25F));
            left.add(new Vec3((double)0.375F, (double)0.0F, (double)0.5F));
            left.add(new Vec3((double)0.5F, (double)0.0F, (double)0.5F));
            left.add(new Vec3((double)0.5F, (double)0.0F, (double)0.25F));
            final_list = left;
         } else if (direction == Direction.EAST) {
            ArrayList<Vec3> right = new ArrayList();
            right.add(new Vec3((double)0.125F, (double)0.0F, (double)0.25F));
            right.add(new Vec3((double)0.125F, (double)0.0F, (double)0.5F));
            right.add(new Vec3((double)0.25F, (double)0.0F, (double)0.5F));
            right.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F));
            final_list = right;
         }

         return final_list;
      }
   }
}
