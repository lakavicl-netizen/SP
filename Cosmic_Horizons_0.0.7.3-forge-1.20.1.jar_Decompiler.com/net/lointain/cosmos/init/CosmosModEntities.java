package net.lointain.cosmos.init;

import net.lointain.cosmos.entity.EntityAshedTNTEntity;
import net.lointain.cosmos.entity.EntitycorrodedsulphertntEntity;
import net.lointain.cosmos.entity.NickelRoverEntity;
import net.lointain.cosmos.entity.RocketSeatEntity;
import net.lointain.cosmos.entity.SteelRoverEntity;
import net.lointain.cosmos.entity.SulphuricShardsEntity;
import net.lointain.cosmos.entity.TitaniumRoverEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class CosmosModEntities {
   public static final DeferredRegister<EntityType<?>> REGISTRY;
   public static final RegistryObject<EntityType<EntitycorrodedsulphertntEntity>> ENTITYCORRODEDSULPHERTNT;
   public static final RegistryObject<EntityType<EntityAshedTNTEntity>> ENTITY_ASHED_TNT;
   public static final RegistryObject<EntityType<SulphuricShardsEntity>> SULPHURIC_SHARDS;
   public static final RegistryObject<EntityType<RocketSeatEntity>> ROCKET_SEAT;
   public static final RegistryObject<EntityType<SteelRoverEntity>> STEEL_ROVER;
   public static final RegistryObject<EntityType<TitaniumRoverEntity>> TITANIUM_ROVER;
   public static final RegistryObject<EntityType<NickelRoverEntity>> NICKEL_ROVER;

   private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
      return REGISTRY.register(registryname, () -> entityTypeBuilder.m_20712_(registryname));
   }

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> {
         EntitycorrodedsulphertntEntity.init();
         EntityAshedTNTEntity.init();
         RocketSeatEntity.init();
         SteelRoverEntity.init();
         TitaniumRoverEntity.init();
         NickelRoverEntity.init();
      });
   }

   @SubscribeEvent
   public static void registerAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)ENTITYCORRODEDSULPHERTNT.get(), EntitycorrodedsulphertntEntity.createAttributes().m_22265_());
      event.put((EntityType)ENTITY_ASHED_TNT.get(), EntityAshedTNTEntity.createAttributes().m_22265_());
      event.put((EntityType)ROCKET_SEAT.get(), RocketSeatEntity.createAttributes().m_22265_());
      event.put((EntityType)STEEL_ROVER.get(), SteelRoverEntity.createAttributes().m_22265_());
      event.put((EntityType)TITANIUM_ROVER.get(), TitaniumRoverEntity.createAttributes().m_22265_());
      event.put((EntityType)NICKEL_ROVER.get(), NickelRoverEntity.createAttributes().m_22265_());
   }

   static {
      REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "cosmos");
      ENTITYCORRODEDSULPHERTNT = register("entitycorrodedsulphertnt", Builder.m_20704_(EntitycorrodedsulphertntEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1).setUpdateInterval(3).setCustomClientFactory(EntitycorrodedsulphertntEntity::new).m_20719_().m_20699_(0.6F, 1.8F));
      ENTITY_ASHED_TNT = register("entity_ashed_tnt", Builder.m_20704_(EntityAshedTNTEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1).setUpdateInterval(3).setCustomClientFactory(EntityAshedTNTEntity::new).m_20719_().m_20699_(0.6F, 1.8F));
      SULPHURIC_SHARDS = register("sulphuric_shards", Builder.m_20704_(SulphuricShardsEntity::new, MobCategory.MISC).setCustomClientFactory(SulphuricShardsEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).m_20699_(0.5F, 0.5F));
      ROCKET_SEAT = register("rocket_seat", Builder.m_20704_(RocketSeatEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RocketSeatEntity::new).m_20719_().m_20699_(0.6F, 1.0F));
      STEEL_ROVER = register("steel_rover", Builder.m_20704_(SteelRoverEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SteelRoverEntity::new).m_20719_().m_20699_(0.6F, 1.8F));
      TITANIUM_ROVER = register("titanium_rover", Builder.m_20704_(TitaniumRoverEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TitaniumRoverEntity::new).m_20719_().m_20699_(0.6F, 1.8F));
      NICKEL_ROVER = register("nickel_rover", Builder.m_20704_(NickelRoverEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NickelRoverEntity::new).m_20719_().m_20699_(0.6F, 1.8F));
   }
}
