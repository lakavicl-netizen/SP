package net.lointain.cosmos.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlumeParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;
   static ParticleRenderType PARTICLE_SHEET_ADDITIVE = new ParticleRenderType() {
      public void m_6505_(BufferBuilder p_107455, TextureManager p107456) {
         RenderSystem.depthMask(true);
         RenderSystem.setShaderTexture(0, TextureAtlas.f_118260_);
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(770, 1);
         RenderSystem.blendEquation(32774);
         p_107455.m_166779_(Mode.QUADS, DefaultVertexFormat.f_85813_);
      }

      public void m_6294_(Tesselator p107458) {
         p107458.m_85914_();
      }

      public String toString() {
         return "PARTICLE_SHEET_ADDITIVE";
      }
   };

   public static PlumeParticleProvider provider(SpriteSet spriteSet) {
      return new PlumeParticleProvider(spriteSet);
   }

   protected PlumeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.m_107250_(30.0F, 30.0F);
      this.f_107225_ = 20;
      this.f_107226_ = 1.5F;
      this.f_107219_ = true;
      this.f_107215_ = vx * (double)0.5F;
      this.f_107216_ = vy * (double)0.5F;
      this.f_107217_ = vz * (double)0.5F;
      this.m_108335_(spriteSet);
   }

   public ParticleRenderType m_7556_() {
      return PARTICLE_SHEET_ADDITIVE;
   }

   public void m_5989_() {
      super.m_5989_();
      System.out.println("Particle age: " + this.f_107224_);
      this.f_107663_ = 0.9F * (1.0F - (float)this.f_107224_ / (float)(this.f_107225_ * 2));
      this.f_107230_ = 1.0F - (float)this.f_107224_ / (float)this.f_107225_;
      float progress = (float)this.f_107224_ / (float)this.f_107225_;
      if (progress <= 0.5F) {
         this.f_107227_ = 1.0F;
         this.f_107228_ = progress * 2.0F;
         this.f_107229_ = 0.0F;
      } else {
         this.f_107227_ = 1.0F;
         this.f_107228_ = 1.0F;
         this.f_107229_ = 0.0F;
      }

   }

   public static class PlumeParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public PlumeParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         PlumeParticle particle = new PlumeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
         return particle;
      }
   }
}
