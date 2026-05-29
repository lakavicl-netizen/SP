package net.lointain.cosmos.procedures;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexBuffer.Usage;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import javax.annotation.Nullable;
import net.minecraftforge.eventbus.api.Event;

public class BlackholeProcedure {
   private static boolean texture = false;
   private static BufferBuilder bufferBuilder = null;
   private static VertexBuffer vertexBuffer = null;

   private static void add(double x, double y, double z, float u, float v, int color) {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         if (texture) {
            bufferBuilder.m_5483_(x, y, z).m_7421_(u, v).m_193479_(color).m_5752_();
         } else {
            bufferBuilder.m_5483_(x, y, z).m_193479_(color).m_5752_();
         }

      }
   }

   private static void add(double size, int color) {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         size /= (double)2.0F;
         if (texture) {
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_7421_(0.0F, 0.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_7421_(0.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_7421_(1.0F, 1.0F).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_7421_(1.0F, 0.0F).m_193479_(color).m_5752_();
         } else {
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(-size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, -size, -size).m_193479_(color).m_5752_();
            bufferBuilder.m_5483_(size, size, -size).m_193479_(color).m_5752_();
         }

      }
   }

   private static boolean begin(VertexFormat.Mode mode, boolean texture, boolean update) {
      if (bufferBuilder == null || !bufferBuilder.m_85732_()) {
         if (update) {
            clear();
         }

         if (vertexBuffer == null) {
            BlackholeProcedure.texture = texture;
            bufferBuilder = Tesselator.m_85913_().m_85915_();
            if (texture) {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85819_);
            } else {
               bufferBuilder.m_166779_(mode, DefaultVertexFormat.f_85815_);
            }

            return true;
         }
      }

      return false;
   }

   private static void clear() {
      if (vertexBuffer != null) {
         vertexBuffer.close();
         vertexBuffer = null;
      }

   }

   private static void end() {
      if (bufferBuilder != null && bufferBuilder.m_85732_()) {
         if (vertexBuffer != null) {
            vertexBuffer.close();
         }

         vertexBuffer = new VertexBuffer(Usage.STATIC);
         vertexBuffer.m_85921_();
         vertexBuffer.m_231221_(bufferBuilder.m_231175_());
         VertexBuffer.m_85931_();
      }
   }

   private static VertexBuffer shape() {
      return vertexBuffer;
   }

   public static VertexBuffer execute() {
      return execute((Event)null);
   }

   private static VertexBuffer execute(@Nullable Event event) {
      double color = (double)0.0F;
      double camY = (double)0.0F;
      double deltaZ = (double)0.0F;
      double distance = (double)0.0F;
      double camZ = (double)0.0F;
      double deltaX = (double)0.0F;
      double camX = (double)0.0F;
      double deltaY = (double)0.0F;
      double i = (double)0.0F;
      double j = (double)0.0F;
      double k = (double)0.0F;
      double l = (double)0.0F;
      double layer = (double)0.0F;
      double objZ = (double)0.0F;
      double objY = (double)0.0F;
      double objX = (double)0.0F;
      double objScale = (double)0.0F;
      double renderScale = (double)0.0F;
      double renderZ = (double)0.0F;
      double renderX = (double)0.0F;
      double renderY = (double)0.0F;
      if (begin(Mode.QUADS, false, true)) {
         layer = (double)16.0F;

         for(int index0 = 0; index0 < (int)(layer + (double)1.0F); ++index0) {
            l = 0.1 / layer;
            j = i == layer ? 0.4 : (double)0.5F - l * i;
            k = i == layer ? -0.4 : (double)-0.5F + l * i;
            color = i == layer ? (double)-1.6777216E7F : 2.81594111E8;
            add(j, k, j, 0.0F, 0.0F, (int)color);
            add(j, k, k, 0.0F, 1.0F, (int)color);
            add(k, k, k, 1.0F, 1.0F, (int)color);
            add(k, k, j, 1.0F, 0.0F, (int)color);
            add(k, j, j, 0.0F, 0.0F, (int)color);
            add(k, j, k, 0.0F, 1.0F, (int)color);
            add(j, j, k, 1.0F, 1.0F, (int)color);
            add(j, j, j, 1.0F, 0.0F, (int)color);
            add(j, k, k, 0.0F, 0.0F, (int)color);
            add(j, j, k, 0.0F, 1.0F, (int)color);
            add(k, j, k, 1.0F, 1.0F, (int)color);
            add(k, k, k, 1.0F, 0.0F, (int)color);
            add(k, k, k, 0.0F, 0.0F, (int)color);
            add(k, j, k, 0.0F, 1.0F, (int)color);
            add(k, j, j, 1.0F, 1.0F, (int)color);
            add(k, k, j, 1.0F, 0.0F, (int)color);
            add(k, k, j, 0.0F, 0.0F, (int)color);
            add(k, j, j, 0.0F, 1.0F, (int)color);
            add(j, j, j, 1.0F, 1.0F, (int)color);
            add(j, k, j, 1.0F, 0.0F, (int)color);
            add(j, k, j, 0.0F, 0.0F, (int)color);
            add(j, j, j, 0.0F, 1.0F, (int)color);
            add(j, j, k, 1.0F, 1.0F, (int)color);
            add(j, k, k, 1.0F, 0.0F, (int)color);
            ++i;
         }

         end();
      }

      return shape();
   }
}
