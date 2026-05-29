package net.lointain.cosmos.entity;

import net.lointain.cosmos.init.CosmosModEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.MoveControl.Operation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class SteelRoverEntity extends PathfinderMob {
   public SteelRoverEntity(PlayMessages.SpawnEntity packet, Level world) {
      this((EntityType)CosmosModEntities.STEEL_ROVER.get(), world);
   }

   public SteelRoverEntity(EntityType<SteelRoverEntity> type, Level world) {
      super(type, world);
      this.m_274367_(1.0F);
      this.f_21364_ = 0;
      this.m_21557_(false);
      this.m_21530_();
      this.m_21441_(BlockPathTypes.WATER, 0.0F);
      this.f_21342_ = new MoveControl(this) {
         public void m_8126_() {
            if (SteelRoverEntity.this.m_20069_()) {
               SteelRoverEntity.this.m_20256_(SteelRoverEntity.this.m_20184_().m_82520_((double)0.0F, 0.005, (double)0.0F));
            }

            if (this.f_24981_ == Operation.MOVE_TO && !SteelRoverEntity.this.m_21573_().m_26571_()) {
               double dx = this.f_24975_ - SteelRoverEntity.this.m_20185_();
               double dy = this.f_24976_ - SteelRoverEntity.this.m_20186_();
               double dz = this.f_24977_ - SteelRoverEntity.this.m_20189_();
               float f = (float)(Mth.m_14136_(dz, dx) * (180D / Math.PI)) - 90.0F;
               float f1 = (float)(this.f_24978_ * SteelRoverEntity.this.m_21051_(Attributes.f_22279_).m_22135_());
               SteelRoverEntity.this.m_146922_(this.m_24991_(SteelRoverEntity.this.m_146908_(), f, 10.0F));
               SteelRoverEntity.this.f_20883_ = SteelRoverEntity.this.m_146908_();
               SteelRoverEntity.this.f_20885_ = SteelRoverEntity.this.m_146908_();
               if (SteelRoverEntity.this.m_20069_()) {
                  SteelRoverEntity.this.m_7910_((float)SteelRoverEntity.this.m_21051_(Attributes.f_22279_).m_22135_());
                  float f2 = -((float)(Mth.m_14136_(dy, (double)((float)Math.sqrt(dx * dx + dz * dz))) * (180D / Math.PI)));
                  f2 = Mth.m_14036_(Mth.m_14177_(f2), -85.0F, 85.0F);
                  SteelRoverEntity.this.m_146926_(this.m_24991_(SteelRoverEntity.this.m_146909_(), f2, 5.0F));
                  float f3 = Mth.m_14089_(SteelRoverEntity.this.m_146909_() * ((float)Math.PI / 180F));
                  SteelRoverEntity.this.m_21564_(f3 * f1);
                  SteelRoverEntity.this.m_21567_((float)((double)f1 * dy));
               } else {
                  SteelRoverEntity.this.m_7910_(f1 * 0.05F);
               }
            } else {
               SteelRoverEntity.this.m_7910_(0.0F);
               SteelRoverEntity.this.m_21567_(0.0F);
               SteelRoverEntity.this.m_21564_(0.0F);
            }

         }
      };
   }

   public Packet<ClientGamePacketListener> m_5654_() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   protected PathNavigation m_6037_(Level world) {
      return new WaterBoundPathNavigation(this, world);
   }

   protected void m_8099_() {
      super.m_8099_();
   }

   public MobType m_6336_() {
      return MobType.f_21640_;
   }

   public boolean m_6785_(double distanceToClosestPlayer) {
      return false;
   }

   public double m_6048_() {
      return super.m_6048_() + -1.18;
   }

   public SoundEvent m_7975_(DamageSource ds) {
      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
   }

   public SoundEvent m_5592_() {
      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
   }

   public boolean m_6469_(DamageSource damagesource, float amount) {
      if (damagesource.m_276093_(DamageTypes.f_268631_)) {
         return false;
      } else if (damagesource.m_7640_() instanceof AbstractArrow) {
         return false;
      } else if (damagesource.m_7640_() instanceof Player) {
         return false;
      } else if (!(damagesource.m_7640_() instanceof ThrownPotion) && !(damagesource.m_7640_() instanceof AreaEffectCloud)) {
         if (damagesource.m_276093_(DamageTypes.f_268671_)) {
            return false;
         } else if (damagesource.m_276093_(DamageTypes.f_268585_)) {
            return false;
         } else if (damagesource.m_276093_(DamageTypes.f_268722_)) {
            return false;
         } else if (damagesource.m_276093_(DamageTypes.f_268450_)) {
            return false;
         } else if (!damagesource.m_276093_(DamageTypes.f_268565_) && !damagesource.m_276093_(DamageTypes.f_268448_)) {
            if (damagesource.m_276093_(DamageTypes.f_268714_)) {
               return false;
            } else if (damagesource.m_276093_(DamageTypes.f_268526_)) {
               return false;
            } else if (damagesource.m_276093_(DamageTypes.f_268482_)) {
               return false;
            } else {
               return !damagesource.m_276093_(DamageTypes.f_268493_) && !damagesource.m_276093_(DamageTypes.f_268641_) ? super.m_6469_(damagesource, amount) : false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean m_6128_() {
      return true;
   }

   public boolean m_5825_() {
      return true;
   }

   public InteractionResult m_6071_(Player sourceentity, InteractionHand hand) {
      sourceentity.m_21120_(hand);
      InteractionResult retval = InteractionResult.m_19078_(this.m_9236_().m_5776_());
      super.m_6071_(sourceentity, hand);
      sourceentity.m_20329_(this);
      return retval;
   }

   public boolean m_6914_(LevelReader world) {
      return world.m_45784_(this);
   }

   public boolean m_6040_() {
      double x = this.m_20185_();
      double y = this.m_20186_();
      double z = this.m_20189_();
      Level world = this.m_9236_();
      return true;
   }

   public boolean m_6063_() {
      double x = this.m_20185_();
      double y = this.m_20186_();
      double z = this.m_20189_();
      Level world = this.m_9236_();
      return false;
   }

   public boolean m_6094_() {
      return false;
   }

   protected void m_7324_(Entity entityIn) {
   }

   protected void m_6138_() {
   }

   public void m_7023_(Vec3 dir) {
      Entity entity = this.m_20197_().isEmpty() ? null : (Entity)this.m_20197_().get(0);
      if (this.m_20160_()) {
         this.m_146922_(entity.m_146908_());
         this.f_19859_ = this.m_146908_();
         this.m_146926_(entity.m_146909_() * 0.5F);
         this.m_19915_(this.m_146908_(), this.m_146909_());
         this.f_20883_ = entity.m_146908_();
         this.f_20885_ = entity.m_146908_();
         if (entity instanceof LivingEntity) {
            LivingEntity passenger = (LivingEntity)entity;
            this.m_7910_((float)this.m_21133_(Attributes.f_22279_));
            float forward = passenger.f_20902_;
            float strafe = 0.0F;
            super.m_7023_(new Vec3((double)strafe, (double)0.0F, (double)forward));
         }

         double d1 = this.m_20185_() - this.f_19854_;
         double d0 = this.m_20189_() - this.f_19856_;
         float f1 = (float)Math.sqrt(d1 * d1 + d0 * d0) * 4.0F;
         if (f1 > 1.0F) {
            f1 = 1.0F;
         }

         this.f_267362_.m_267771_(this.f_267362_.m_267731_() + (f1 - this.f_267362_.m_267731_()) * 0.4F);
         this.f_267362_.m_267590_(this.f_267362_.m_267756_() + this.f_267362_.m_267731_());
         this.m_267651_(true);
      } else {
         super.m_7023_(dir);
      }
   }

   public static void init() {
   }

   public static AttributeSupplier.Builder createAttributes() {
      AttributeSupplier.Builder builder = Mob.m_21552_();
      builder = builder.m_22268_(Attributes.f_22279_, 0.15);
      builder = builder.m_22268_(Attributes.f_22276_, (double)10.0F);
      builder = builder.m_22268_(Attributes.f_22284_, (double)0.0F);
      builder = builder.m_22268_(Attributes.f_22281_, (double)3.0F);
      builder = builder.m_22268_(Attributes.f_22277_, (double)16.0F);
      builder = builder.m_22268_((Attribute)ForgeMod.SWIM_SPEED.get(), 0.15);
      return builder;
   }
}
