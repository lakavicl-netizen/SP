package net.lointain.cosmos.procedures;

import java.util.ArrayList;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class AnimatedLightCubeMapProcedure {
   public static ArrayList<Vec3> execute(Direction direction) {
      if (direction == null) {
         return new ArrayList();
      } else {
         Direction Directions = Direction.NORTH;
         double iter = (double)0.0F;
         double frame_length = (double)0.0F;
         ArrayList<Vec3> final_list = new ArrayList();
         if (direction == Direction.UP) {
            ArrayList<Vec3> top = new ArrayList();
            top.add(new Vec3((double)0.25F, (double)0.0F, (double)0.0F + iter * frame_length));
            top.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F + iter * frame_length));
            top.add(new Vec3((double)0.5F, (double)0.0F, (double)0.25F + iter * frame_length));
            top.add(new Vec3((double)0.5F, (double)0.0F, (double)0.0F + iter * frame_length));
            final_list = top;
         } else if (direction == Direction.DOWN) {
            ArrayList<Vec3> bottom = new ArrayList();
            bottom.add(new Vec3((double)0.75F, (double)0.0F, (double)0.0F + iter * frame_length));
            bottom.add(new Vec3((double)0.75F, (double)0.0F, (double)0.25F + iter * frame_length));
            bottom.add(new Vec3((double)0.5F, (double)0.0F, (double)0.25F + iter * frame_length));
            bottom.add(new Vec3((double)0.5F, (double)0.0F, (double)0.0F + iter * frame_length));
            final_list = bottom;
         } else if (direction == Direction.NORTH) {
            ArrayList<Vec3> front = new ArrayList();
            front.add(new Vec3((double)0.5F, (double)0.0F, (double)0.25F + iter * frame_length));
            front.add(new Vec3((double)0.5F, (double)0.0F, (double)0.5F + iter * frame_length));
            front.add(new Vec3((double)0.75F, (double)0.0F, (double)0.5F + iter * frame_length));
            front.add(new Vec3((double)0.75F, (double)0.0F, (double)0.25F + iter * frame_length));
            final_list = front;
         } else if (direction == Direction.SOUTH) {
            ArrayList<Vec3> back = new ArrayList();
            back.add(new Vec3((double)0.0F, (double)0.0F, (double)0.25F + iter * frame_length));
            back.add(new Vec3((double)0.0F, (double)0.0F, (double)0.5F + iter * frame_length));
            back.add(new Vec3((double)0.25F, (double)0.0F, (double)0.5F + iter * frame_length));
            back.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F + iter * frame_length));
            final_list = back;
         } else if (direction == Direction.WEST) {
            ArrayList<Vec3> left = new ArrayList();
            left.add(new Vec3((double)0.75F, (double)0.0F, (double)0.25F + iter * frame_length));
            left.add(new Vec3((double)0.75F, (double)0.0F, (double)0.5F + iter * frame_length));
            left.add(new Vec3((double)1.0F, (double)0.0F, (double)0.5F + iter * frame_length));
            left.add(new Vec3((double)1.0F, (double)0.0F, (double)0.25F + iter * frame_length));
            final_list = left;
         } else if (direction == Direction.EAST) {
            ArrayList<Vec3> right = new ArrayList();
            right.add(new Vec3((double)0.25F, (double)0.0F, (double)0.25F + iter * frame_length));
            right.add(new Vec3((double)0.25F, (double)0.0F, (double)0.5F + iter * frame_length));
            right.add(new Vec3((double)0.5F, (double)0.0F, (double)0.5F + iter * frame_length));
            right.add(new Vec3((double)0.5F, (double)0.0F, (double)0.25F + iter * frame_length));
            final_list = right;
         }

         return final_list;
      }
   }
}
