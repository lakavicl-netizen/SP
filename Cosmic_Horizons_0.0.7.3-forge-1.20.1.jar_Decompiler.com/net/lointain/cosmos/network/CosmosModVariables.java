package net.lointain.cosmos.network;

import java.util.Objects;
import java.util.function.Supplier;
import net.lointain.cosmos.CosmosMod;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class CosmosModVariables {
   public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
   });

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      CosmosMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
      CosmosMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
   }

   @SubscribeEvent
   public static void init(RegisterCapabilitiesEvent event) {
      event.register(PlayerVariables.class);
   }

   @EventBusSubscriber
   public static class EventBusVariableHandlers {
      @SubscribeEvent
      public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
         if (!event.getEntity().m_9236_().m_5776_()) {
            ((PlayerVariables)event.getEntity().getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
         }

      }

      @SubscribeEvent
      public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
         if (!event.getEntity().m_9236_().m_5776_()) {
            ((PlayerVariables)event.getEntity().getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
         }

      }

      @SubscribeEvent
      public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
         if (!event.getEntity().m_9236_().m_5776_()) {
            ((PlayerVariables)event.getEntity().getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
         }

      }

      @SubscribeEvent
      public static void clonePlayer(PlayerEvent.Clone event) {
         event.getOriginal().revive();
         PlayerVariables original = (PlayerVariables)event.getOriginal().getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables());
         PlayerVariables clone = (PlayerVariables)event.getEntity().getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables());
         clone.traveled_dimentions = original.traveled_dimentions;
         clone.page_iter = original.page_iter;
         clone.planet_page_iter = original.planet_page_iter;
         clone.page_sel_index = original.page_sel_index;
         clone.planet_page_sel_index = original.planet_page_sel_index;
         clone.ticks = original.ticks;
         clone.check_collision = original.check_collision;
         if (!event.isWasDeath()) {
            clone.pitch_i = original.pitch_i;
            clone.pitch_d = original.pitch_d;
            clone.roll_i = original.roll_i;
            clone.roll_d = original.roll_d;
            clone.thrust = original.thrust;
            clone.pitch = original.pitch;
            clone.roll = original.roll;
            clone.thrust_drop = original.thrust_drop;
            clone.thrust_catch = original.thrust_catch;
            clone.vehicle = original.vehicle;
            clone.compass_X = original.compass_X;
            clone.compass_Y = original.compass_Y;
            clone.compass_Z = original.compass_Z;
            clone.background = original.background;
            clone.current_tick = original.current_tick;
            clone.landing_coords = original.landing_coords;
            clone.entry_velocity = original.entry_velocity;
            clone.entry_world = original.entry_world;
            clone.search_bar = original.search_bar;
            clone.initial_vector = original.initial_vector;
            clone.ctrl = original.ctrl;
            clone.shift = original.shift;
            clone.open_gui = original.open_gui;
            clone.gui_open = original.gui_open;
         }

      }

      @SubscribeEvent
      public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
         if (!event.getEntity().m_9236_().m_5776_()) {
            SavedData mapdata = CosmosModVariables.MapVariables.get(event.getEntity().m_9236_());
            SavedData worlddata = CosmosModVariables.WorldVariables.get(event.getEntity().m_9236_());
            if (mapdata != null) {
               CosmosMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SavedDataSyncMessage(0, mapdata));
            }

            if (worlddata != null) {
               CosmosMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SavedDataSyncMessage(1, worlddata));
            }
         }

      }

      @SubscribeEvent
      public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
         if (!event.getEntity().m_9236_().m_5776_()) {
            SavedData worlddata = CosmosModVariables.WorldVariables.get(event.getEntity().m_9236_());
            if (worlddata != null) {
               CosmosMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SavedDataSyncMessage(1, worlddata));
            }
         }

      }
   }

   public static class WorldVariables extends SavedData {
      public static final String DATA_NAME = "cosmos_worldvars";
      public ListTag planet_sel_data = new ListTag();
      public CompoundTag gravity_data = new CompoundTag();
      public CompoundTag friction_data = new CompoundTag();
      public ListTag projection_list = new ListTag();
      public ListTag datapack = new ListTag();
      public CompoundTag render_data_map = new CompoundTag();
      public CompoundTag light_source_map = new CompoundTag();
      public CompoundTag global_position_map = new CompoundTag();
      public CompoundTag skybox_data_map = new CompoundTag();
      public CompoundTag collision_data_map = new CompoundTag();
      public CompoundTag global_collision_position_map = new CompoundTag();
      public CompoundTag atmospheric_collision_data_map = new CompoundTag();
      public CompoundTag dimensional_data = new CompoundTag();
      public CompoundTag sky_object_data = new CompoundTag();
      public CompoundTag dimension_type = new CompoundTag();
      public CompoundTag opaque_object_map = new CompoundTag();
      static WorldVariables clientSide = new WorldVariables();

      public static WorldVariables load(CompoundTag tag) {
         WorldVariables data = new WorldVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
         Tag var3 = nbt.m_128423_("planet_sel_data");
         ListTag var10001;
         if (var3 instanceof ListTag planet_sel_data) {
            var10001 = planet_sel_data;
         } else {
            var10001 = new ListTag();
         }

         this.planet_sel_data = var10001;
         var3 = nbt.m_128423_("gravity_data");
         CompoundTag var34;
         if (var3 instanceof CompoundTag gravity_data) {
            var34 = gravity_data;
         } else {
            var34 = new CompoundTag();
         }

         this.gravity_data = var34;
         var3 = nbt.m_128423_("friction_data");
         if (var3 instanceof CompoundTag friction_data) {
            var34 = friction_data;
         } else {
            var34 = new CompoundTag();
         }

         this.friction_data = var34;
         var3 = nbt.m_128423_("projection_list");
         ListTag var36;
         if (var3 instanceof ListTag projection_list) {
            var36 = projection_list;
         } else {
            var36 = new ListTag();
         }

         this.projection_list = var36;
         var3 = nbt.m_128423_("datapack");
         if (var3 instanceof ListTag datapack) {
            var36 = datapack;
         } else {
            var36 = new ListTag();
         }

         this.datapack = var36;
         var3 = nbt.m_128423_("render_data_map");
         CompoundTag var38;
         if (var3 instanceof CompoundTag render_data_map) {
            var38 = render_data_map;
         } else {
            var38 = new CompoundTag();
         }

         this.render_data_map = var38;
         var3 = nbt.m_128423_("light_source_map");
         if (var3 instanceof CompoundTag light_source_map) {
            var38 = light_source_map;
         } else {
            var38 = new CompoundTag();
         }

         this.light_source_map = var38;
         var3 = nbt.m_128423_("global_position_map");
         if (var3 instanceof CompoundTag global_position_map) {
            var38 = global_position_map;
         } else {
            var38 = new CompoundTag();
         }

         this.global_position_map = var38;
         var3 = nbt.m_128423_("skybox_data_map");
         if (var3 instanceof CompoundTag skybox_data_map) {
            var38 = skybox_data_map;
         } else {
            var38 = new CompoundTag();
         }

         this.skybox_data_map = var38;
         var3 = nbt.m_128423_("collision_data_map");
         if (var3 instanceof CompoundTag collision_data_map) {
            var38 = collision_data_map;
         } else {
            var38 = new CompoundTag();
         }

         this.collision_data_map = var38;
         var3 = nbt.m_128423_("global_collision_position_map");
         if (var3 instanceof CompoundTag global_collision_position_map) {
            var38 = global_collision_position_map;
         } else {
            var38 = new CompoundTag();
         }

         this.global_collision_position_map = var38;
         var3 = nbt.m_128423_("atmospheric_collision_data_map");
         if (var3 instanceof CompoundTag atmospheric_collision_data_map) {
            var38 = atmospheric_collision_data_map;
         } else {
            var38 = new CompoundTag();
         }

         this.atmospheric_collision_data_map = var38;
         var3 = nbt.m_128423_("dimensional_data");
         if (var3 instanceof CompoundTag dimensional_data) {
            var38 = dimensional_data;
         } else {
            var38 = new CompoundTag();
         }

         this.dimensional_data = var38;
         var3 = nbt.m_128423_("sky_object_data");
         if (var3 instanceof CompoundTag sky_object_data) {
            var38 = sky_object_data;
         } else {
            var38 = new CompoundTag();
         }

         this.sky_object_data = var38;
         var3 = nbt.m_128423_("dimension_type");
         if (var3 instanceof CompoundTag dimension_type) {
            var38 = dimension_type;
         } else {
            var38 = new CompoundTag();
         }

         this.dimension_type = var38;
         var3 = nbt.m_128423_("opaque_object_map");
         if (var3 instanceof CompoundTag opaque_object_map) {
            var38 = opaque_object_map;
         } else {
            var38 = new CompoundTag();
         }

         this.opaque_object_map = var38;
      }

      public CompoundTag m_7176_(CompoundTag nbt) {
         nbt.m_128365_("planet_sel_data", this.planet_sel_data);
         nbt.m_128365_("gravity_data", this.gravity_data);
         nbt.m_128365_("friction_data", this.friction_data);
         nbt.m_128365_("projection_list", this.projection_list);
         nbt.m_128365_("datapack", this.datapack);
         nbt.m_128365_("render_data_map", this.render_data_map);
         nbt.m_128365_("light_source_map", this.light_source_map);
         nbt.m_128365_("global_position_map", this.global_position_map);
         nbt.m_128365_("skybox_data_map", this.skybox_data_map);
         nbt.m_128365_("collision_data_map", this.collision_data_map);
         nbt.m_128365_("global_collision_position_map", this.global_collision_position_map);
         nbt.m_128365_("atmospheric_collision_data_map", this.atmospheric_collision_data_map);
         nbt.m_128365_("dimensional_data", this.dimensional_data);
         nbt.m_128365_("sky_object_data", this.sky_object_data);
         nbt.m_128365_("dimension_type", this.dimension_type);
         nbt.m_128365_("opaque_object_map", this.opaque_object_map);
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.m_77762_();
         if (world instanceof Level level) {
            if (!level.m_5776_()) {
               SimpleChannel var10000 = CosmosMod.PACKET_HANDLER;
               PacketDistributor var10001 = PacketDistributor.DIMENSION;
               Objects.requireNonNull(level);
               var10000.send(var10001.with(level::m_46472_), new SavedDataSyncMessage(1, this));
            }
         }

      }

      public static WorldVariables get(LevelAccessor world) {
         if (world instanceof ServerLevel level) {
            return (WorldVariables)level.m_8895_().m_164861_((e) -> load(e), WorldVariables::new, "cosmos_worldvars");
         } else {
            return clientSide;
         }
      }
   }

   public static class MapVariables extends SavedData {
      public static final String DATA_NAME = "cosmos_mapvars";
      public double nergy = (double)0.0F;
      public CompoundTag antena_locations = new CompoundTag();
      static MapVariables clientSide = new MapVariables();

      public static MapVariables load(CompoundTag tag) {
         MapVariables data = new MapVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
         this.nergy = nbt.m_128459_("nergy");
         Tag var3 = nbt.m_128423_("antena_locations");
         CompoundTag var10001;
         if (var3 instanceof CompoundTag antena_locations) {
            var10001 = antena_locations;
         } else {
            var10001 = new CompoundTag();
         }

         this.antena_locations = var10001;
      }

      public CompoundTag m_7176_(CompoundTag nbt) {
         nbt.m_128347_("nergy", this.nergy);
         nbt.m_128365_("antena_locations", this.antena_locations);
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.m_77762_();
         if (world instanceof Level && !world.m_5776_()) {
            CosmosMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
         }

      }

      public static MapVariables get(LevelAccessor world) {
         if (world instanceof ServerLevelAccessor serverLevelAcc) {
            return (MapVariables)serverLevelAcc.m_6018_().m_7654_().m_129880_(Level.f_46428_).m_8895_().m_164861_((e) -> load(e), MapVariables::new, "cosmos_mapvars");
         } else {
            return clientSide;
         }
      }
   }

   public static class SavedDataSyncMessage {
      private final int type;
      private SavedData data;

      public SavedDataSyncMessage(FriendlyByteBuf buffer) {
         this.type = buffer.readInt();
         CompoundTag nbt = buffer.m_130260_();
         if (nbt != null) {
            this.data = (SavedData)(this.type == 0 ? new MapVariables() : new WorldVariables());
            SavedData var5 = this.data;
            if (var5 instanceof MapVariables) {
               MapVariables mapVariables = (MapVariables)var5;
               mapVariables.read(nbt);
            } else {
               var5 = this.data;
               if (var5 instanceof WorldVariables) {
                  WorldVariables worldVariables = (WorldVariables)var5;
                  worldVariables.read(nbt);
               }
            }
         }

      }

      public SavedDataSyncMessage(int type, SavedData data) {
         this.type = type;
         this.data = data;
      }

      public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
         buffer.writeInt(message.type);
         if (message.data != null) {
            buffer.m_130079_(message.data.m_7176_(new CompoundTag()));
         }

      }

      public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
         NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
         context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
               if (message.type == 0) {
                  CosmosModVariables.MapVariables.clientSide = (MapVariables)message.data;
               } else {
                  CosmosModVariables.WorldVariables.clientSide = (WorldVariables)message.data;
               }
            }

         });
         context.setPacketHandled(true);
      }
   }

   @EventBusSubscriber
   private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
      private final PlayerVariables playerVariables = new PlayerVariables();
      private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> this.playerVariables);

      @SubscribeEvent
      public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
         if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(new ResourceLocation("cosmos", "player_variables"), new PlayerVariablesProvider());
         }

      }

      public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
         return cap == CosmosModVariables.PLAYER_VARIABLES_CAPABILITY ? this.instance.cast() : LazyOptional.empty();
      }

      public Tag serializeNBT() {
         return this.playerVariables.writeNBT();
      }

      public void deserializeNBT(Tag nbt) {
         this.playerVariables.readNBT(nbt);
      }
   }

   public static class PlayerVariables {
      public boolean pitch_i = false;
      public boolean pitch_d = false;
      public boolean roll_i = false;
      public boolean roll_d = false;
      public boolean thrust = false;
      public double pitch = (double)0.0F;
      public double roll = (double)0.0F;
      public boolean thrust_drop = false;
      public boolean thrust_catch = false;
      public String vehicle = "\"\"";
      public double compass_X = (double)0.0F;
      public double compass_Y = (double)0.0F;
      public double compass_Z = (double)0.0F;
      public String background = "\"\"";
      public double current_tick = (double)0.0F;
      public ListTag traveled_dimentions = new ListTag();
      public double page_iter = (double)0.0F;
      public double planet_page_iter = (double)0.0F;
      public double page_sel_index = (double)0.0F;
      public double planet_page_sel_index = (double)0.0F;
      public double ticks = (double)0.0F;
      public String landing_coords = "\"^\"";
      public Vec3 entry_velocity;
      public ListTag entry_world;
      public String search_bar;
      public Vec3 initial_vector;
      public boolean ctrl;
      public boolean shift;
      public boolean open_gui;
      public boolean gui_open;
      public boolean check_collision;

      public PlayerVariables() {
         this.entry_velocity = Vec3.f_82478_;
         this.entry_world = new ListTag();
         this.search_bar = "\"\"";
         this.initial_vector = Vec3.f_82478_;
         this.ctrl = false;
         this.shift = false;
         this.open_gui = false;
         this.gui_open = false;
         this.check_collision = true;
      }

      public void syncPlayerVariables(Entity entity) {
         if (entity instanceof ServerPlayer serverPlayer) {
            CosmosMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
         }

      }

      public Tag writeNBT() {
         CompoundTag nbt = new CompoundTag();
         nbt.m_128379_("pitch_i", this.pitch_i);
         nbt.m_128379_("pitch_d", this.pitch_d);
         nbt.m_128379_("roll_i", this.roll_i);
         nbt.m_128379_("roll_d", this.roll_d);
         nbt.m_128379_("thrust", this.thrust);
         nbt.m_128347_("pitch", this.pitch);
         nbt.m_128347_("roll", this.roll);
         nbt.m_128379_("thrust_drop", this.thrust_drop);
         nbt.m_128379_("thrust_catch", this.thrust_catch);
         nbt.m_128359_("vehicle", this.vehicle);
         nbt.m_128347_("compass_X", this.compass_X);
         nbt.m_128347_("compass_Y", this.compass_Y);
         nbt.m_128347_("compass_Z", this.compass_Z);
         nbt.m_128359_("background", this.background);
         nbt.m_128347_("current_tick", this.current_tick);
         nbt.m_128365_("traveled_dimentions", this.traveled_dimentions);
         nbt.m_128347_("page_iter", this.page_iter);
         nbt.m_128347_("planet_page_iter", this.planet_page_iter);
         nbt.m_128347_("page_sel_index", this.page_sel_index);
         nbt.m_128347_("planet_page_sel_index", this.planet_page_sel_index);
         nbt.m_128347_("ticks", this.ticks);
         nbt.m_128359_("landing_coords", this.landing_coords);
         this.entry_velocity = this.entry_velocity == null ? Vec3.f_82478_ : this.entry_velocity;
         ListTag listTag = new ListTag();
         listTag.m_7614_(0, DoubleTag.m_128500_(this.entry_velocity.m_7096_()));
         listTag.m_7614_(1, DoubleTag.m_128500_(this.entry_velocity.m_7098_()));
         listTag.m_7614_(2, DoubleTag.m_128500_(this.entry_velocity.m_7094_()));
         nbt.m_128365_("entry_velocity", listTag);
         nbt.m_128365_("entry_world", this.entry_world);
         nbt.m_128359_("search_bar", this.search_bar);
         this.initial_vector = this.initial_vector == null ? Vec3.f_82478_ : this.initial_vector;
         listTag = new ListTag();
         listTag.m_7614_(0, DoubleTag.m_128500_(this.initial_vector.m_7096_()));
         listTag.m_7614_(1, DoubleTag.m_128500_(this.initial_vector.m_7098_()));
         listTag.m_7614_(2, DoubleTag.m_128500_(this.initial_vector.m_7094_()));
         nbt.m_128365_("initial_vector", listTag);
         nbt.m_128379_("ctrl", this.ctrl);
         nbt.m_128379_("shift", this.shift);
         nbt.m_128379_("open_gui", this.open_gui);
         nbt.m_128379_("gui_open", this.gui_open);
         nbt.m_128379_("check_collision", this.check_collision);
         return nbt;
      }

      public void readNBT(Tag tag) {
         CompoundTag nbt = (CompoundTag)tag;
         this.pitch_i = nbt.m_128471_("pitch_i");
         this.pitch_d = nbt.m_128471_("pitch_d");
         this.roll_i = nbt.m_128471_("roll_i");
         this.roll_d = nbt.m_128471_("roll_d");
         this.thrust = nbt.m_128471_("thrust");
         this.pitch = nbt.m_128459_("pitch");
         this.roll = nbt.m_128459_("roll");
         this.thrust_drop = nbt.m_128471_("thrust_drop");
         this.thrust_catch = nbt.m_128471_("thrust_catch");
         this.vehicle = nbt.m_128461_("vehicle");
         this.compass_X = nbt.m_128459_("compass_X");
         this.compass_Y = nbt.m_128459_("compass_Y");
         this.compass_Z = nbt.m_128459_("compass_Z");
         this.background = nbt.m_128461_("background");
         this.current_tick = nbt.m_128459_("current_tick");
         Tag var4 = nbt.m_128423_("traveled_dimentions");
         ListTag var10001;
         if (var4 instanceof ListTag traveled_dimentions) {
            var10001 = traveled_dimentions;
         } else {
            var10001 = new ListTag();
         }

         this.traveled_dimentions = var10001;
         this.page_iter = nbt.m_128459_("page_iter");
         this.planet_page_iter = nbt.m_128459_("planet_page_iter");
         this.page_sel_index = nbt.m_128459_("page_sel_index");
         this.planet_page_sel_index = nbt.m_128459_("planet_page_sel_index");
         this.ticks = nbt.m_128459_("ticks");
         this.landing_coords = nbt.m_128461_("landing_coords");
         this.entry_velocity = new Vec3(listTag.m_128772_(0), listTag.m_128772_(1), listTag.m_128772_(2));
         var4 = nbt.m_128423_("entry_world");
         if (var4 instanceof ListTag listTag) {
            var10001 = listTag;
         } else {
            var10001 = new ListTag();
         }

         this.entry_world = var10001;
         this.search_bar = nbt.m_128461_("search_bar");
         listTag = nbt.m_128437_("initial_vector", 6);
         this.initial_vector = new Vec3(listTag.m_128772_(0), listTag.m_128772_(1), listTag.m_128772_(2));
         this.ctrl = nbt.m_128471_("ctrl");
         this.shift = nbt.m_128471_("shift");
         this.open_gui = nbt.m_128471_("open_gui");
         this.gui_open = nbt.m_128471_("gui_open");
         this.check_collision = nbt.m_128471_("check_collision");
      }
   }

   public static class PlayerVariablesSyncMessage {
      private final PlayerVariables data;

      public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
         this.data = new PlayerVariables();
         this.data.readNBT(buffer.m_130260_());
      }

      public PlayerVariablesSyncMessage(PlayerVariables data) {
         this.data = data;
      }

      public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
         buffer.m_130079_((CompoundTag)message.data.writeNBT());
      }

      public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
         NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
         context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer()) {
               PlayerVariables variables = (PlayerVariables)Minecraft.m_91087_().f_91074_.getCapability(CosmosModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction)null).orElse(new PlayerVariables());
               variables.pitch_i = message.data.pitch_i;
               variables.pitch_d = message.data.pitch_d;
               variables.roll_i = message.data.roll_i;
               variables.roll_d = message.data.roll_d;
               variables.thrust = message.data.thrust;
               variables.pitch = message.data.pitch;
               variables.roll = message.data.roll;
               variables.thrust_drop = message.data.thrust_drop;
               variables.thrust_catch = message.data.thrust_catch;
               variables.vehicle = message.data.vehicle;
               variables.compass_X = message.data.compass_X;
               variables.compass_Y = message.data.compass_Y;
               variables.compass_Z = message.data.compass_Z;
               variables.background = message.data.background;
               variables.current_tick = message.data.current_tick;
               variables.traveled_dimentions = message.data.traveled_dimentions;
               variables.page_iter = message.data.page_iter;
               variables.planet_page_iter = message.data.planet_page_iter;
               variables.page_sel_index = message.data.page_sel_index;
               variables.planet_page_sel_index = message.data.planet_page_sel_index;
               variables.ticks = message.data.ticks;
               variables.landing_coords = message.data.landing_coords;
               variables.entry_velocity = message.data.entry_velocity;
               variables.entry_world = message.data.entry_world;
               variables.search_bar = message.data.search_bar;
               variables.initial_vector = message.data.initial_vector;
               variables.ctrl = message.data.ctrl;
               variables.shift = message.data.shift;
               variables.open_gui = message.data.open_gui;
               variables.gui_open = message.data.gui_open;
               variables.check_collision = message.data.check_collision;
            }

         });
         context.setPacketHandled(true);
      }
   }
}
