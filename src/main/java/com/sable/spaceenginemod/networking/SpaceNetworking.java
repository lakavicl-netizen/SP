package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * NeoForge 1.21.1 payload-API networking. Replaces the Forge 1.20.1
 * {@code SimpleChannel} setup from genesis.
 *
 * <p>Each payload class registers its own {@link net.minecraft.network.protocol.common.custom.CustomPacketPayload}
 * type and {@link net.minecraft.network.codec.StreamCodec}. We just wire them
 * to the registrar here.
 */
public final class SpaceNetworking {

    private static final String PROTOCOL_VERSION = "1";

    private SpaceNetworking() {}

    /** Listener fired by NeoForge on {@link RegisterPayloadHandlersEvent}. */
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(SpaceengineS.MODID).versioned(PROTOCOL_VERSION);

        // Server -> Client only
        registrar.playToClient(
                WormholeTravelSoundPacket.TYPE,
                WormholeTravelSoundPacket.STREAM_CODEC,
                WormholeTravelSoundPacket::handle);

        registrar.playToClient(
                VoidEngineSoundPacket.TYPE,
                VoidEngineSoundPacket.STREAM_CODEC,
                VoidEngineSoundPacket::handle);

        registrar.playToClient(
                StopVoidEngineStartSoundPacket.TYPE,
                StopVoidEngineStartSoundPacket.STREAM_CODEC,
                StopVoidEngineStartSoundPacket::handle);

        registrar.playToClient(
                EnteringWarpPacket.TYPE,
                EnteringWarpPacket.STREAM_CODEC,
                EnteringWarpPacket::handle);

        registrar.playToClient(
                SyncTimeOffsetPacket.TYPE,
                SyncTimeOffsetPacket.STREAM_CODEC,
                SyncTimeOffsetPacket::handle);
    }

    // --- Helpers used by the rest of the codebase ---------------------------------------------

    public static <T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> void sendToAll(T payload) {
        PacketDistributor.sendToAllPlayers(payload);
    }

    public static <T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> void sendToPlayer(ServerPlayer player, T payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    public static <T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> void sendToTracking(
            net.minecraft.server.level.ServerLevel level,
            net.minecraft.core.BlockPos pos,
            T payload) {
        PacketDistributor.sendToPlayersTrackingChunk(level, new net.minecraft.world.level.ChunkPos(pos), payload);
    }

    /** Convenience: build a {@link ResourceLocation} in our mod namespace. */
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(SpaceengineS.MODID, path);
    }
}
