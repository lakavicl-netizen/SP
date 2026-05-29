package net.lointain.cosmos.entity;

import javax.annotation.Nullable;
import net.lointain.cosmos.init.CosmosModEntities;
import net.lointain.cosmos.procedures.NoduleRightClickEventsProcedure;
import net.lointain.cosmos.procedures.RocketSeatOnEntityTickUpdateProcedure;
import net.lointain.cosmos.procedures.RocketSeatOnInitialEntitySpawnProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class RocketSeatEntity extends PathfinderMob {
   public static final EntityDataAccessor<Integer> DATA_yaw;
   public static final EntityDataAccessor<Integer> DATA_pitch;
   public static final EntityDataAccessor<Integer> DATA_roll;
   public static final EntityDataAccessor<Integer> DATA_type;
   public static final EntityDataAccessor<Integer> DATA_engine;

   public RocketSeatEntity(PlayMessages.SpawnEntity packet, Level world) {
      this((EntityType)CosmosModEntities.ROCKET_SEAT.get(), world);
   }

   public RocketSeatEntity(EntityType<RocketSeatEntity> type, Level world) {
      super(type, world);
      this.m_274367_(0.6F);
      this.f_21364_ = 0;
      this.m_21557_(false);
      this.m_21530_();
      this.m_21441_(BlockPathTypes.WATER, 0.0F);
      this.f_21342_ = new MoveControl(this) {
         public void m_8126_() {
            if (RocketSeatEntity.this.m_20069_()) {
               RocketSeatEntity.this.m_20256_(RocketSeatEntity.this.m_20184_().m_82520_((double)0.0F, 0.005, (double)0.0F));
            }

            if (this.f_24981_ == Operation.MOVE_TO && !RocketSeatEntity.this.m_21573_().m_26571_()) {
               double dx = this.f_24975_ - RocketSeatEntity.this.m_20185_();
               double dy = this.f_24976_ - RocketSeatEntity.this.m_20186_();
               double dz = this.f_24977_ - RocketSeatEntity.this.m_20189_();
               float f = (float)(Mth.m_14136_(dz, dx) * (180D / Math.PI)) - 90.0F;
               float f1 = (float)(this.f_24978_ * RocketSeatEntity.this.m_21051_(Attributes.f_22279_).m_22135_());
               RocketSeatEntity.this.m_146922_(this.m_24991_(RocketSeatEntity.this.m_146908_(), f, 10.0F));
               RocketSeatEntity.this.f_20883_ = RocketSeatEntity.this.m_146908_();
               RocketSeatEntity.this.f_20885_ = RocketSeatEntity.this.m_146908_();
               if (RocketSeatEntity.this.m_20069_()) {
                  RocketSeatEntity.this.m_7910_((float)RocketSeatEntity.this.m_21051_(Attributes.f_22279_).m_22135_());
                  float f2 = -((float)(Mth.m_14136_(dy, (double)((float)Math.sqrt(dx * dx + dz * dz))) * (180D / Math.PI)));
                  f2 = Mth.m_14036_(Mth.m_14177_(f2), -85.0F, 85.0F);
                  RocketSeatEntity.this.m_146926_(this.m_24991_(RocketSeatEntity.this.m_146909_(), f2, 5.0F));
                  float f3 = Mth.m_14089_(RocketSeatEntity.this.m_146909_() * ((float)Math.PI / 180F));
                  RocketSeatEntity.this.m_21564_(f3 * f1);
                  RocketSeatEntity.this.m_21567_((float)((double)f1 * dy));
               } else {
                  RocketSeatEntity.this.m_7910_(f1 * 0.05F);
               }
            } else {
               RocketSeatEntity.this.m_7910_(0.0F);
               RocketSeatEntity.this.m_21567_(0.0F);
               RocketSeatEntity.this.m_21564_(0.0F);
            }

         }
      };
      this.m_6210_();
   }

   public Packet<ClientGamePacketListener> m_5654_() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(DATA_yaw, 0);
      this.f_19804_.m_135372_(DATA_pitch, 0);
      this.f_19804_.m_135372_(DATA_roll, 0);
      this.f_19804_.m_135372_(DATA_type, 0);
      this.f_19804_.m_135372_(DATA_engine, 0);
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
      return super.m_6048_() + -1.2;
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

   public SpawnGroupData m_6518_(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
      SpawnGroupData retval = super.m_6518_(world, difficulty, reason, livingdata, tag);
      RocketSeatOnInitialEntitySpawnProcedure.execute(this);
      return retval;
   }

   public void m_7380_(CompoundTag compound) {
      super.m_7380_(compound);
      compound.m_128405_("Datayaw", (Integer)this.f_19804_.m_135370_(DATA_yaw));
      compound.m_128405_("Datapitch", (Integer)this.f_19804_.m_135370_(DATA_pitch));
      compound.m_128405_("Dataroll", (Integer)this.f_19804_.m_135370_(DATA_roll));
      compound.m_128405_("Datatype", (Integer)this.f_19804_.m_135370_(DATA_type));
      compound.m_128405_("Dataengine", (Integer)this.f_19804_.m_135370_(DATA_engine));
   }

   public void m_7378_(CompoundTag compound) {
      super.m_7378_(compound);
      if (compound.m_128441_("Datayaw")) {
         this.f_19804_.m_135381_(DATA_yaw, compound.m_128451_("Datayaw"));
      }

      if (compound.m_128441_("Datapitch")) {
         this.f_19804_.m_135381_(DATA_pitch, compound.m_128451_("Datapitch"));
      }

      if (compound.m_128441_("Dataroll")) {
         this.f_19804_.m_135381_(DATA_roll, compound.m_128451_("Dataroll"));
      }

      if (compound.m_128441_("Datatype")) {
         this.f_19804_.m_135381_(DATA_type, compound.m_128451_("Datatype"));
      }

      if (compound.m_128441_("Dataengine")) {
         this.f_19804_.m_135381_(DATA_engine, compound.m_128451_("Dataengine"));
      }

   }

   public InteractionResult m_6071_(Player sourceentity, InteractionHand hand) {
      sourceentity.m_21120_(hand);
      InteractionResult retval = InteractionResult.m_19078_(this.m_9236_().m_5776_());
      super.m_6071_(sourceentity, hand);
      sourceentity.m_20329_(this);
      double x = this.m_20185_();
      double y = this.m_20186_();
      double z = this.m_20189_();
      Level world = this.m_9236_();
      NoduleRightClickEventsProcedure.execute(this, sourceentity);
      return retval;
   }

   public void m_6075_() {
      super.m_6075_();
      RocketSeatOnEntityTickUpdateProcedure.execute(this.m_9236_(), this);
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

   public EntityDimensions m_6972_(Pose pose) {
      return super.m_6972_(pose).m_20388_(2.0F);
   }

   public static void init() {
   }

   public static AttributeSupplier.Builder createAttributes() {
      AttributeSupplier.Builder builder = Mob.m_21552_();
      builder = builder.m_22268_(Attributes.f_22279_, 0.3);
      builder = builder.m_22268_(Attributes.f_22276_, (double)10.0F);
      builder = builder.m_22268_(Attributes.f_22284_, (double)0.0F);
      builder = builder.m_22268_(Attributes.f_22281_, (double)3.0F);
      builder = builder.m_22268_(Attributes.f_22277_, (double)16.0F);
      builder = builder.m_22268_((Attribute)ForgeMod.SWIM_SPEED.get(), 0.3);
      return builder;
   }

   static {
      DATA_yaw = SynchedEntityData.m_135353_(RocketSeatEntity.class, EntityDataSerializers.f_135028_);
      DATA_pitch = SynchedEntityData.m_135353_(RocketSeatEntity.class, EntityDataSerializers.f_135028_);
      DATA_roll = SynchedEntityData.m_135353_(RocketSeatEntity.class, EntityDataSerializers.f_135028_);
      DATA_type = SynchedEntityData.m_135353_(RocketSeatEntity.class, EntityDataSerializers.f_135028_);
      DATA_engine = SynchedEntityData.m_135353_(RocketSeatEntity.class, EntityDataSerializers.f_135028_);
   }
}
