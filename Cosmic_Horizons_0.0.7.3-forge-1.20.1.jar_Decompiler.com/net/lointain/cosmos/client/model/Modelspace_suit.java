package net.lointain.cosmos.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Modelspace_suit<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cosmos", "modelspace_suit"), "main");
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;
   public final ModelPart Head;
   public final ModelPart RightLeg;
   public final ModelPart LeftLeg;
   public final ModelPart LeftFoot;
   public final ModelPart RightFoot;

   public Modelspace_suit(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.RightArm = root.m_171324_("RightArm");
      this.LeftArm = root.m_171324_("LeftArm");
      this.Head = root.m_171324_("Head");
      this.RightLeg = root.m_171324_("RightLeg");
      this.LeftLeg = root.m_171324_("LeftLeg");
      this.LeftFoot = root.m_171324_("LeftFoot");
      this.RightFoot = root.m_171324_("RightFoot");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      partdefinition.m_171599_("Body", CubeListBuilder.m_171558_().m_171514_(30, 34).m_171488_(-5.225F, -1.25F, -3.0F, 10.0F, 14.0F, 6.0F, new CubeDeformation(-0.5F)).m_171514_(27, 22).m_171488_(-6.85F, 13.0F, 3.5F, 13.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(60, 68).m_171488_(3.15F, -1.0F, 9.5F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).m_171514_(20, 40).m_171488_(-6.85F, -1.0F, 9.5F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).m_171514_(0, 0).m_171488_(-8.35F, -2.0F, 2.5F, 16.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(0.25F, 0.0F, -1.0F));
      partdefinition.m_171599_("RightArm", CubeListBuilder.m_171558_().m_171514_(60, 48).m_171488_(-4.6F, -3.0F, -3.3F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.1F)).m_171514_(46, 2).m_171488_(-3.6F, -2.0F, -2.75F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.1F)).m_171514_(62, 35).m_171488_(-4.1F, 6.0F, -3.3F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.m_171419_(-5.0F, 2.0F, 0.0F));
      partdefinition.m_171599_("LeftArm", CubeListBuilder.m_171558_().m_171514_(59, 23).m_171488_(-1.6F, -3.0F, -3.3F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.1F)).m_171514_(0, 65).m_171488_(-1.1F, 6.0F, -3.3F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)).m_171514_(0, 42).m_171488_(-1.6F, -2.0F, -2.75F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.m_171419_(5.0F, 2.0F, 0.0F));
      partdefinition.m_171599_("Head", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171488_(-4.6F, -8.3F, -4.6F, 9.0F, 9.0F, 9.0F, new CubeDeformation(-0.25F)), PartPose.m_171419_(0.0F, 0.0F, 0.0F));
      partdefinition.m_171599_("RightLeg", CubeListBuilder.m_171558_().m_171514_(20, 54).m_171488_(-2.6F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(-0.24F)), PartPose.m_171419_(-2.0F, 12.0F, 0.0F));
      partdefinition.m_171599_("LeftLeg", CubeListBuilder.m_171558_().m_171514_(40, 54).m_171488_(-2.3F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(-0.24F)), PartPose.m_171419_(2.0F, 12.0F, 0.0F));
      partdefinition.m_171599_("LeftFoot", CubeListBuilder.m_171558_().m_171514_(67, 13).m_171488_(-2.3F, 7.6F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.m_171419_(2.0F, 12.0F, 0.0F));
      partdefinition.m_171599_("RightFoot", CubeListBuilder.m_171558_().m_171514_(70, 60).m_171488_(-2.75F, 7.6F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.m_171419_(-2.0F, 12.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 128, 128);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.RightArm.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.LeftArm.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.Head.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.RightLeg.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.LeftLeg.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.LeftFoot.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.RightFoot.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
