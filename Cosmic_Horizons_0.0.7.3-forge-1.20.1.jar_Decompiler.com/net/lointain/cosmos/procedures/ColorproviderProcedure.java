package net.lointain.cosmos.procedures;

import net.minecraft.world.level.material.MapColor;

public class ColorproviderProcedure {
   public static double execute(String color) {
      if (color == null) {
         return (double)0.0F;
      } else {
         double color_num = (double)0.0F;
         if (color.equals("black")) {
            color_num = (double)(MapColor.f_283927_.f_283871_ | -16777216);
         } else if (color.equals("blue")) {
            color_num = (double)-1.6777012E7F;
         } else if (color.equals("brown")) {
            color_num = (double)-6730752.0F;
         } else if (color.equals("cyan")) {
            color_num = (double)-1.6744193E7F;
         } else if (color.equals("gray")) {
            color_num = (double)-4013374.0F;
         } else if (color.equals("green")) {
            color_num = (double)-1.6751104E7F;
         } else if (color.equals("light_blue")) {
            color_num = (double)-1.67509E7F;
         } else if (color.equals("light_gray")) {
            color_num = (double)-2039584.0F;
         } else if (color.equals("lime")) {
            color_num = (double)-1.6738048E7F;
         } else if (color.equals("magenta")) {
            color_num = (double)-1.0092442E7F;
         } else if (color.equals("orange")) {
            color_num = (double)-3381760.0F;
         } else if (color.equals("pink")) {
            color_num = (double)-3407770.0F;
         } else if (color.equals("purple")) {
            color_num = (double)-1.1796327E7F;
         } else if (color.equals("red")) {
            color_num = (double)-1700852.0F;
         } else if (color.equals("dark_red")) {
            color_num = (double)-5701632.0F;
         } else if (color.equals("yellow")) {
            color_num = (double)-8704.0F;
         } else if (color.equals("white")) {
            color_num = (double)-1.0F;
         }

         return color_num;
      }
   }
}
