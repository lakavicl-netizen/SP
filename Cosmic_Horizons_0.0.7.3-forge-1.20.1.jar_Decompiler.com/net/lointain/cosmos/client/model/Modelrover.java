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

public class Modelrover<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cosmos", "modelrover"), "main");
   public final ModelPart group;
   public final ModelPart wheel1;
   public final ModelPart wheel2;
   public final ModelPart wheel3;
   public final ModelPart wheel4;
   public final ModelPart wheel5;
   public final ModelPart wheel6;
   public final ModelPart bone;
   public final ModelPart bb_main;

   public Modelrover(ModelPart root) {
      this.group = root.m_171324_("group");
      this.wheel1 = this.group.m_171324_("wheel1");
      this.wheel2 = this.group.m_171324_("wheel2");
      this.wheel3 = this.group.m_171324_("wheel3");
      this.wheel4 = this.group.m_171324_("wheel4");
      this.wheel5 = this.group.m_171324_("wheel5");
      this.wheel6 = this.group.m_171324_("wheel6");
      this.bone = root.m_171324_("bone");
      this.bb_main = root.m_171324_("bb_main");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition group = partdefinition.m_171599_("group", CubeListBuilder.m_171558_().m_171514_(49, 48).m_171488_(-18.0F, -10.0F, -21.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(49, 48).m_171488_(-2.0F, -10.0F, -21.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(49, 48).m_171488_(-2.0F, -10.0F, -5.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(49, 48).m_171488_(-2.0F, -10.0F, 11.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(49, 48).m_171488_(-18.0F, -10.0F, 11.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(49, 48).m_171488_(-18.0F, -10.0F, -5.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(69, 45).m_171480_().m_171488_(-11.0F, -10.0F, 11.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).m_171555_(false).m_171514_(0, 0).m_171488_(-14.0F, -15.0F, -18.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(0, 0).m_171488_(-3.0F, -15.0F, -18.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)).m_171514_(76, 57).m_171488_(0.5F, -25.5F, 3.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).m_171514_(76, 57).m_171488_(0.5F, -25.5F, 10.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).m_171514_(93, 48).m_171488_(-3.5F, -29.5F, 4.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).m_171514_(93, 48).m_171488_(-3.5F, -29.5F, 11.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).m_171514_(81, 12).m_171488_(-18.5F, -31.5F, 5.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(0, 84).m_171488_(-12.5F, -29.5F, -3.5F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).m_171514_(54, 11).m_171488_(-17.5F, -22.5F, -10.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(71, 16).m_171488_(-12.5F, -28.5F, -5.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(71, 16).m_171488_(-6.5F, -28.5F, -5.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(0, 0).m_171488_(-16.5F, -28.5F, -2.5F, 17.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)).m_171514_(0, 37).m_171488_(-14.5F, -11.5F, -7.5F, 13.0F, 3.0F, 23.0F, new CubeDeformation(0.0F)).m_171514_(49, 37).m_171480_().m_171488_(-18.5F, -23.5F, 15.5F, 21.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).m_171555_(false).m_171514_(0, 37).m_171488_(-17.5F, -23.5F, -8.5F, 2.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).m_171514_(0, 63).m_171488_(-11.5F, -30.5F, 1.5F, 7.0F, 4.0F, 17.0F, new CubeDeformation(0.0F)).m_171514_(54, 0).m_171480_().m_171488_(-11.0F, -9.0F, -5.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).m_171555_(false).m_171514_(27, 63).m_171488_(-14.0F, -9.0F, 12.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).m_171514_(27, 63).m_171488_(-14.0F, -9.0F, -4.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(8.0F, 24.0F, 2.0F));
      group.m_171599_("cube_r1", CubeListBuilder.m_171558_().m_171514_(0, 37).m_171488_(-3.5F, -10.5F, -3.5F, 2.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-2.0F, -13.0F, -5.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r2", CubeListBuilder.m_171558_().m_171514_(54, 11).m_171488_(-3.5F, -9.5F, 1.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(3.0F, -13.0F, -7.0F, 0.0F, -1.5708F, 0.0F));
      group.m_171599_("cube_r3", CubeListBuilder.m_171558_().m_171514_(81, 12).m_171488_(2.5F, -8.5F, 10.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(5.0F, -23.0F, 17.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r4", CubeListBuilder.m_171558_().m_171514_(93, 48).m_171488_(-0.5F, -8.5F, 9.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-13.0F, -21.0F, 17.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r5", CubeListBuilder.m_171558_().m_171514_(93, 48).m_171488_(-0.5F, -8.5F, 9.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-13.0F, -21.0F, 24.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r6", CubeListBuilder.m_171558_().m_171514_(76, 57).m_171488_(1.5F, -12.5F, 8.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-15.0F, -13.0F, 17.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r7", CubeListBuilder.m_171558_().m_171514_(76, 57).m_171488_(1.5F, -12.5F, 8.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-15.0F, -13.0F, 24.0F, 0.0F, 3.1416F, 0.0F));
      group.m_171599_("cube_r8", CubeListBuilder.m_171558_().m_171514_(74, 7).m_171488_(-6.5F, -13.0F, -5.0F, 15.0F, 15.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-9.0F, -12.0F, -7.0F, 0.3927F, 0.0F, 0.0F));
      PartDefinition wheel1 = group.m_171599_("wheel1", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171419_(-18.0F, -5.0F, -16.0F));
      wheel1.m_171599_("cube_r9", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r10", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r11", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r12", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r13", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r14", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel1.m_171599_("cube_r15", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition wheel2 = group.m_171599_("wheel2", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171419_(-18.0F, -5.0F, 0.0F));
      wheel2.m_171599_("cube_r16", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r17", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r18", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r19", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r20", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r21", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel2.m_171599_("cube_r22", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition wheel3 = group.m_171599_("wheel3", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171419_(-18.0F, -5.0F, 16.0F));
      wheel3.m_171599_("cube_r23", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r24", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r25", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r26", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r27", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r28", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel3.m_171599_("cube_r29", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition wheel4 = group.m_171599_("wheel4", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(2.0F, -5.0F, 12.0F, 0.0F, 3.1416F, 0.0F));
      wheel4.m_171599_("cube_r30", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r31", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r32", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r33", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r34", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r35", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel4.m_171599_("cube_r36", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition wheel5 = group.m_171599_("wheel5", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(2.0F, -5.0F, -4.0F, 0.0F, 3.1416F, 0.0F));
      wheel5.m_171599_("cube_r37", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r38", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r39", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r40", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r41", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r42", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel5.m_171599_("cube_r43", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition wheel6 = group.m_171599_("wheel6", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -9.0F, -5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(52, 63).m_171488_(-4.0F, -6.0F, -6.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(2.0F, -5.0F, -20.0F, 0.0F, 3.1416F, 0.0F));
      wheel6.m_171599_("cube_r44", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)).m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -0.7854F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r45", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 0.7854F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r46", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 1.5708F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r47", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 2.3562F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r48", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, 3.1416F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r49", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -2.3562F, 0.0F, 0.0F));
      wheel6.m_171599_("cube_r50", CubeListBuilder.m_171558_().m_171514_(93, 39).m_171488_(-6.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.m_171423_(0.0F, -2.0F, -2.0F, -1.5708F, 0.0F, 0.0F));
      PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_().m_171514_(120, 116).m_171488_(-5.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(96, 124).m_171488_(-3.0F, -6.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(120, 108).m_171488_(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).m_171514_(112, 108).m_171488_(3.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(0.0F, 13.0F, -4.0F, 0.7854F, 0.0F, 0.0F));
      bone.m_171599_("cube_r51", CubeListBuilder.m_171558_().m_171514_(112, 120).m_171488_(-5.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-3.0F, 3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
      bone.m_171599_("cube_r52", CubeListBuilder.m_171558_().m_171514_(104, 116).m_171488_(-5.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.m_171423_(-3.0F, -5.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
      partdefinition.m_171599_("bb_main", CubeListBuilder.m_171558_().m_171514_(0, 106).m_171488_(-6.0F, -9.0F, -9.0F, 12.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 128, 128);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.group.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.bone.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.bb_main.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
