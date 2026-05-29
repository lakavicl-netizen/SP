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

public class RingsProcedure {
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
            RingsProcedure.texture = texture;
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
      if (begin(Mode.QUADS, true, true)) {
         add((double)-0.5F, (double)0.0F, (double)-0.5F, 1.0F, 0.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)-0.5F, 1.0F, 0.0F, -1);
         add((double)0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
         add((double)0.5F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
         add((double)0.5F, (double)0.0F, (double)0.5F, 1.0F, 0.0F, -1);
         add((double)0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
         add((double)0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
         add((double)0.5F, (double)0.0F, (double)0.5F, 1.0F, 0.0F, -1);
         add((double)0.5F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
         add((double)0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
         add((double)0.5F, (double)0.0F, (double)-0.5F, 1.0F, 0.0F, -1);
         add((double)0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)-0.25F, 0.25F, 1.0F, -1);
         add((double)0.25F, (double)0.0F, (double)-0.25F, 0.75F, 1.0F, -1);
         add((double)0.5F, (double)0.0F, (double)-0.5F, 1.0F, 0.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)-0.5F, 0.0F, 0.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
         add((double)0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
         add((double)0.5F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)0.5F, 1.0F, 0.0F, -1);
         add((double)-0.5F, (double)0.0F, (double)0.5F, 1.0F, 0.0F, -1);
         add((double)0.5F, (double)0.0F, (double)0.5F, 0.0F, 0.0F, -1);
         add((double)0.25F, (double)0.0F, (double)0.25F, 0.25F, 1.0F, -1);
         add((double)-0.25F, (double)0.0F, (double)0.25F, 0.75F, 1.0F, -1);
         end();
      }

      return shape();
   }
}
