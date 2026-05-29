package net.lointain.cosmos.procedures;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class InitialiseRenderSequenceProcedure {
   public static void execute(LevelAccessor world) {
      Direction render_direction = Direction.NORTH;
      boolean logic = false;
      Vec3 objRot = Vec3.f_82478_;
      Vec3 objScale = Vec3.f_82478_;
      Vec3 vertex4 = Vec3.f_82478_;
      Vec3 vertex1 = Vec3.f_82478_;
      Vec3 direction_vector = Vec3.f_82478_;
      Vec3 vertex3 = Vec3.f_82478_;
      Vec3 vertex2 = Vec3.f_82478_;
      Vec3 objPos = Vec3.f_82478_;
      Vec3 vector3 = Vec3.f_82478_;
      Vec3 vector4 = Vec3.f_82478_;
      Vec3 vector1 = Vec3.f_82478_;
      Vec3 vector2 = Vec3.f_82478_;
      double counter = (double)0.0F;
      double ring_rot = (double)0.0F;
      double ring_scale = (double)0.0F;
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      JsonObject empty_light_map = new JsonObject();
      new JsonObject();
      JsonObject empty_alpha_map = new JsonObject();
      new JsonObject();
      JsonObject empty_i_alpha_map = new JsonObject();
      new JsonObject();
      JsonObject empty_absolete_light_map = new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      new JsonObject();
      JsonObject empty_ring1_light_data = new JsonObject();
      JsonObject empty_ring2_light_data = new JsonObject();
      JsonObject empty_ring3_light_data = new JsonObject();
      JsonObject empty_ring4_light_data = new JsonObject();
      CompoundTag render_map = new CompoundTag();
      CompoundTag position_map = new CompoundTag();
      CompoundTag skybox_map = new CompoundTag();
      CompoundTag sky_object_map = new CompoundTag();

      for(Tag dataelementiterator : CosmosModVariables.WorldVariables.get(world).datapack) {
         JsonObject var10000;
         if (dataelementiterator instanceof StringTag _stringTag) {
            var10000 = (JsonObject)(new Gson()).fromJson(_stringTag.m_7916_(), JsonObject.class);
         } else {
            var10000 = new JsonObject();
         }

         JsonObject data = var10000;
         if (data.has("planet_data")) {
            JsonObject planet_data = data.get("planet_data").getAsJsonObject();
            ListTag render_list = new ListTag();
            ListTag position_list = new ListTag();

            for(int iter = 0; iter < planet_data.size(); ++iter) {
               counter = (double)0.0F;
               JsonObject mint = planet_data.get((String)planet_data.keySet().stream().toList().get(iter)).getAsJsonObject();
               objPos = new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble());
               objScale = new Vec3(mint.get("scale").getAsDouble(), mint.get("scale").getAsDouble(), mint.get("scale").getAsDouble());
               objRot = new Vec3(mint.get("pitch").getAsDouble(), mint.get("yaw").getAsDouble(), mint.get("roll").getAsDouble());
               if (mint.has("glowing")) {
                  if (mint.get("glowing").getAsBoolean()) {
                     if (mint.has("core_color") && mint.has("glowing")) {
                        JsonObject core_color = mint.get("core_color").getAsJsonObject();
                        JsonObject bloom_color = mint.get("bloom_color").getAsJsonObject();
                        if (core_color.get("r").getAsDouble() == (double)0.0F && core_color.get("g").getAsDouble() == (double)0.0F && core_color.get("b").getAsDouble() == (double)0.0F) {
                           mint.addProperty("type", "blackhole");
                        } else {
                           mint.addProperty("type", "sun");
                        }
                     } else {
                        mint.addProperty("type", "planet");
                     }
                  } else if (mint.has("model_type")) {
                     if (mint.get("model_type").getAsString().equals("black_hole")) {
                        mint.addProperty("type", "blackhole");
                     } else {
                        mint.addProperty("type", "planet");
                     }
                  } else {
                     mint.addProperty("type", "planet");
                  }
               } else if (mint.has("model_type")) {
                  if (mint.get("model_type").getAsString().equals("black_hole")) {
                     mint.addProperty("type", "blackhole");
                  } else {
                     mint.addProperty("type", "planet");
                  }
               } else {
                  mint.addProperty("type", "planet");
               }

               mint.addProperty("function", "object");
               if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                  JsonObject light_data = empty_light_map;
                  JsonObject transparency_data = empty_alpha_map;
                  JsonObject i_alpha_data = empty_i_alpha_map;
                  if (CosmosModVariables.WorldVariables.get(world).light_source_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
                     Tag var69 = CosmosModVariables.WorldVariables.get(world).light_source_map.m_128423_(data.get("attached_dimention_id").getAsString());
                     ListTag var129;
                     if (var69 instanceof ListTag) {
                        ListTag _listTag = (ListTag)var69;
                        var129 = _listTag.m_6426_();
                     } else {
                        var129 = new ListTag();
                     }

                     ListTag light_source_list = var129;
                     var69 = CosmosModVariables.WorldVariables.get(world).opaque_object_map.m_128423_(data.get("attached_dimention_id").getAsString());
                     if (var69 instanceof ListTag) {
                        ListTag _listTag = (ListTag)var69;
                        var129 = _listTag.m_6426_();
                     } else {
                        var129 = new ListTag();
                     }

                     ListTag opaque_object_list = var129;

                     for(Direction directioniterator : Direction.values()) {
                        ArrayList<Vec3> texture_pos = LightCubeMapProcedure.execute(directioniterator);
                        direction_vector = new Vec3(directioniterator.m_253071_());
                        vertex1 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)-0.5F));
                        light_data.addProperty((new DecimalFormat("##.##")).format((double)0.0F + counter * (double)4.0F), BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "color", "none", direction_vector, objPos, objRot, objScale, vertex1));
                        transparency_data.addProperty((new DecimalFormat("##.##")).format((double)0.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "alpha", "none", direction_vector, objPos, objRot, objScale, vertex1) >>> 24);
                        i_alpha_data.addProperty((new DecimalFormat("##.##")).format((double)0.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "i_alpha", "none", direction_vector, objPos, objRot, objScale, vertex1) >>> 24);
                        vertex2 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)-0.5F, (double)0.5F, (double)0.5F));
                        light_data.addProperty((new DecimalFormat("##.##")).format((double)1.0F + counter * (double)4.0F), BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "color", "none", direction_vector, objPos, objRot, objScale, vertex2));
                        transparency_data.addProperty((new DecimalFormat("##.##")).format((double)1.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "alpha", "none", direction_vector, objPos, objRot, objScale, vertex2) >>> 24);
                        i_alpha_data.addProperty((new DecimalFormat("##.##")).format((double)1.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "i_alpha", "none", direction_vector, objPos, objRot, objScale, vertex2) >>> 24);
                        vertex3 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)0.5F));
                        light_data.addProperty((new DecimalFormat("##.##")).format((double)2.0F + counter * (double)4.0F), BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "color", "none", direction_vector, objPos, objRot, objScale, vertex3));
                        transparency_data.addProperty((new DecimalFormat("##.##")).format((double)2.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "alpha", "none", direction_vector, objPos, objRot, objScale, vertex3) >>> 24);
                        i_alpha_data.addProperty((new DecimalFormat("##.##")).format((double)2.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "i_alpha", "none", direction_vector, objPos, objRot, objScale, vertex3) >>> 24);
                        vertex4 = CubeVertexOrientorProcedure.execute(directioniterator, (double)0.0F, new Vec3((double)0.5F, (double)0.5F, (double)-0.5F));
                        light_data.addProperty((new DecimalFormat("##.##")).format((double)3.0F + counter * (double)4.0F), BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "color", "none", direction_vector, objPos, objRot, objScale, vertex4));
                        transparency_data.addProperty((new DecimalFormat("##.##")).format((double)3.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "alpha", "none", direction_vector, objPos, objRot, objScale, vertex4) >>> 24);
                        i_alpha_data.addProperty((new DecimalFormat("##.##")).format((double)3.0F + counter * (double)4.0F), (int)BrightnessProviderProcedure.execute(light_source_list, opaque_object_list, (double)-1.0F, "i_alpha", "none", direction_vector, objPos, objRot, objScale, vertex4) >>> 24);
                        counter = Mth.m_14008_(counter + (double)1.0F, (double)0.0F, (double)5.0F);
                     }
                  }

                  mint.add("light_data", light_data);
                  mint.add("alpha_data", transparency_data);
                  mint.add("i_alpha_data", i_alpha_data);
               }

               render_list.m_7614_(render_list.size(), JsontomapconverterProcedure.execute(mint));
               int var10001 = position_list.size();
               double var10002 = mint.get("x").getAsDouble();
               position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + mint.get("y").getAsDouble() + "|" + mint.get("z").getAsDouble() + "\\"));
               if (mint.has("ring_data")) {
                  Tag var122 = CosmosModVariables.WorldVariables.get(world).light_source_map.m_128423_(data.get("attached_dimention_id").getAsString());
                  ListTag var131;
                  if (var122 instanceof ListTag) {
                     ListTag _listTag = (ListTag)var122;
                     var131 = _listTag.m_6426_();
                  } else {
                     var131 = new ListTag();
                  }

                  ListTag light_source_list = var131;
                  JsonObject ring_data = mint.get("ring_data").getAsJsonObject();

                  for(int iterR = 0; iterR < ring_data.size(); ++iterR) {
                     JsonObject ring_mint = ring_data.get((String)ring_data.keySet().stream().toList().get(iterR)).getAsJsonObject();
                     ring_scale = ring_mint.get("scale_radius").getAsDouble() * (double)1.0F;
                     ring_mint.addProperty("x", mint.get("x").getAsDouble());
                     ring_mint.addProperty("y", mint.get("y").getAsDouble());
                     ring_mint.addProperty("z", mint.get("z").getAsDouble());
                     ring_mint.addProperty("yaw", mint.get("yaw").getAsDouble());
                     ring_mint.addProperty("pitch", mint.get("pitch").getAsDouble());
                     ring_mint.addProperty("roll", mint.get("roll").getAsDouble());
                     ring_mint.addProperty("function", "ring");
                     ring_mint.addProperty("type", "ring1");
                     if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                        JsonObject absolete_light_data = empty_absolete_light_map;

                        for(int rot = 0; rot < 4; ++rot) {
                           ring_rot = (double)(rot * 90);

                           for(int seq = 0; seq < 4; ++seq) {
                              vector1 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                              vector2 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                              vector3 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                              vector4 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                              absolete_light_data.addProperty((new DecimalFormat("##.##")).format((long)(0 + seq * 4 + rot * 16)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector1));
                              absolete_light_data.addProperty((new DecimalFormat("##.##")).format((long)(1 + seq * 4 + rot * 16)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector2));
                              absolete_light_data.addProperty((new DecimalFormat("##.##")).format((long)(2 + seq * 4 + rot * 16)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector3));
                              absolete_light_data.addProperty((new DecimalFormat("##.##")).format((long)(3 + seq * 4 + rot * 16)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector4));
                           }
                        }

                        ring_mint.add("o_light_data", absolete_light_data);
                        ring_rot = (double)0.0F;
                        JsonObject ring1_light_data = empty_ring1_light_data;

                        for(int seq = 0; seq < 4; ++seq) {
                           vector1 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector2 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector3 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector4 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           ring1_light_data.addProperty((new DecimalFormat("##.##")).format((long)(0 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector1));
                           ring1_light_data.addProperty((new DecimalFormat("##.##")).format((long)(1 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector2));
                           ring1_light_data.addProperty((new DecimalFormat("##.##")).format((long)(2 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector3));
                           ring1_light_data.addProperty((new DecimalFormat("##.##")).format((long)(3 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector4));
                        }

                        ring_mint.add("light_data", ring1_light_data);
                     }

                     var10001 = position_list.size();
                     var10002 = (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(-ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7096_();
                     position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(-ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7098_() + "|" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(-ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7094_() + "\\"));
                     render_list.m_7614_(render_list.size(), JsontomapconverterProcedure.execute(ring_mint));
                     ring_mint.addProperty("type", "ring2");
                     if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                        ring_rot = (double)180.0F;
                        JsonObject ring2_light_data = empty_ring2_light_data;

                        for(int seq = 0; seq < 4; ++seq) {
                           vector1 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector2 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector3 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector4 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           ring2_light_data.addProperty((new DecimalFormat("##.##")).format((long)(0 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector1));
                           ring2_light_data.addProperty((new DecimalFormat("##.##")).format((long)(1 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector2));
                           ring2_light_data.addProperty((new DecimalFormat("##.##")).format((long)(2 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector3));
                           ring2_light_data.addProperty((new DecimalFormat("##.##")).format((long)(3 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector4));
                        }

                        ring_mint.add("light_data", ring2_light_data);
                     }

                     var10001 = position_list.size();
                     var10002 = (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7096_();
                     position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7098_() + "|" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3(ring_mint.get("radius").getAsDouble(), (double)0.0F, (double)0.0F)).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7094_() + "\\"));
                     render_list.m_7614_(render_list.size(), JsontomapconverterProcedure.execute(ring_mint));
                     ring_mint.addProperty("type", "ring3");
                     if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                        ring_rot = (double)270.0F;
                        JsonObject ring3_light_data = empty_ring3_light_data;

                        for(int seq = 0; seq < 4; ++seq) {
                           vector1 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector2 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector3 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector4 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           ring3_light_data.addProperty((new DecimalFormat("##.##")).format((long)(0 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector1));
                           ring3_light_data.addProperty((new DecimalFormat("##.##")).format((long)(1 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector2));
                           ring3_light_data.addProperty((new DecimalFormat("##.##")).format((long)(2 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector3));
                           ring3_light_data.addProperty((new DecimalFormat("##.##")).format((long)(3 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector4));
                        }

                        ring_mint.add("light_data", ring3_light_data);
                     }

                     var10001 = position_list.size();
                     var10002 = (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, -ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7096_();
                     position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, -ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7098_() + "|" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, -ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7094_() + "\\"));
                     render_list.m_7614_(render_list.size(), JsontomapconverterProcedure.execute(ring_mint));
                     ring_mint.addProperty("type", "ring4");
                     if (mint.has("opaque") && mint.get("opaque").getAsBoolean()) {
                        ring_rot = (double)90.0F;
                        JsonObject ring4_light_data = empty_ring4_light_data;

                        for(int seq = 0; seq < 4; ++seq) {
                           vector1 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector2 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)-0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector3 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.5F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)-0.25F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           vector4 = seq == 1 ? objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 2 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : (seq == 3 ? objPos.m_82549_((new Vec3((double)-0.5F, (double)0.0F, (double)0.0F)).m_82496_(-(float)Math.PI).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_()))) : objPos.m_82549_((new Vec3((double)-0.25F, (double)0.0F, (double)0.0F)).m_82524_(((float)Math.PI / 180F) * (float)ring_rot).m_82490_(ring_scale).m_82535_(-0.017453292F * (float)(-objRot.m_7094_())).m_82496_(-0.017453292F * (float)objRot.m_7096_()).m_82524_(((float)Math.PI / 180F) * (float)(-objRot.m_7098_())))));
                           ring4_light_data.addProperty((new DecimalFormat("##.##")).format((long)(0 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector1));
                           ring4_light_data.addProperty((new DecimalFormat("##.##")).format((long)(1 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector2));
                           ring4_light_data.addProperty((new DecimalFormat("##.##")).format((long)(2 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector3));
                           ring4_light_data.addProperty((new DecimalFormat("##.##")).format((long)(3 + seq * 4)), SimpleOcclusionProviderProcedure.execute(light_source_list, objRot.m_7096_(), objRot.m_7094_(), objScale.m_7096_(), objRot.m_7098_(), objPos, vector4));
                        }

                        ring_mint.add("light_data", ring4_light_data);
                     }

                     var10001 = position_list.size();
                     var10002 = (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7096_();
                     position_list.m_7614_(var10001, StringTag.m_129297_("`" + var10002 + "~" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7098_() + "|" + (new Vec3(mint.get("x").getAsDouble(), mint.get("y").getAsDouble(), mint.get("z").getAsDouble())).m_82549_((new Vec3((double)0.0F, (double)0.0F, ring_mint.get("radius").getAsDouble())).m_82535_(-0.017453292F * (float)(-mint.get("roll").getAsDouble())).m_82496_(-0.017453292F * (float)mint.get("pitch").getAsDouble()).m_82524_(((float)Math.PI / 180F) * (float)(-mint.get("yaw").getAsDouble()))).m_7094_() + "\\"));
                     render_list.m_7614_(render_list.size(), JsontomapconverterProcedure.execute(ring_mint));
                  }
               }
            }

            if (render_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var117 = render_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var132;
               if (var117 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var117;
                  var132 = _listTag.m_6426_();
               } else {
                  var132 = new ListTag();
               }

               ListTag old_render_list = var132;
               render_map.m_128473_(data.get("attached_dimention_id").getAsString());
               render_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_render_list, render_list));
            } else {
               render_map.m_128365_(data.get("attached_dimention_id").getAsString(), render_list);
            }

            if (position_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var118 = position_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var133;
               if (var118 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var118;
                  var133 = _listTag.m_6426_();
               } else {
                  var133 = new ListTag();
               }

               ListTag old_position_list = var133;
               position_map.m_128473_(data.get("attached_dimention_id").getAsString());
               position_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_position_list, position_list));
            } else {
               position_map.m_128365_(data.get("attached_dimention_id").getAsString(), position_list);
            }
         }

         if (data.has("skybox_data")) {
            JsonObject skybox_data = data.get("skybox_data").getAsJsonObject();
            skybox_map.m_128365_(data.get("attached_dimention_id").getAsString(), JsontomapconverterProcedure.execute(skybox_data));
         }

         if (data.has("sky_data")) {
            JsonObject sky_data = data.get("sky_data").getAsJsonObject();
            ListTag sky_object_list = new ListTag();

            for(int iter = 0; iter < sky_data.size(); ++iter) {
               JsonObject sky_mint = sky_data.get((String)sky_data.keySet().stream().toList().get(iter)).getAsJsonObject();
               sky_object_list.m_7614_(sky_object_list.size(), JsontomapconverterProcedure.execute(sky_mint));
            }

            if (sky_object_map.m_128441_(data.get("attached_dimention_id").getAsString())) {
               Tag var119 = sky_object_map.m_128423_(data.get("attached_dimention_id").getAsString());
               ListTag var134;
               if (var119 instanceof ListTag) {
                  ListTag _listTag = (ListTag)var119;
                  var134 = _listTag.m_6426_();
               } else {
                  var134 = new ListTag();
               }

               ListTag old_sky_object_list = var134;
               sky_object_map.m_128473_(data.get("attached_dimention_id").getAsString());
               sky_object_map.m_128365_(data.get("attached_dimention_id").getAsString(), MergeListFunctionProcedure.execute(old_sky_object_list, sky_object_list));
            } else {
               sky_object_map.m_128365_(data.get("attached_dimention_id").getAsString(), sky_object_list);
            }
         }
      }

      CosmosModVariables.WorldVariables.get(world).render_data_map = render_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).global_position_map = position_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).skybox_data_map = skybox_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
      CosmosModVariables.WorldVariables.get(world).sky_object_data = sky_object_map;
      CosmosModVariables.WorldVariables.get(world).syncData(world);
   }
}
