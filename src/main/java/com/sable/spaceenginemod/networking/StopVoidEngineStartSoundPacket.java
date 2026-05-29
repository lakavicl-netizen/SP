package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.client.WormholeAmbianceHandler;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Server -> Client: cancels the in-flight "engine starting" sound on the client. */
public record StopVoidEngineStartSoundPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<StopVoidEngineStartSoundPacket> TYPE =
            new CustomPacketPayload.Type<>(SpaceNetworking.id("stop_void_engine_start_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StopVoidEngineStartSoundPacket> STREAM_CODEC =
            StreamCodec.unit(new StopVoidEngineStartSoundPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(StopVoidEngineStartSoundPacket packet, IPayloadContext ctx) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ctx.enqueueWork(ClientHandler::handle);
        }
    }

    private static final class ClientHandler {
        static void handle() {
            WormholeAmbianceHandler.stopVoidEngineStart();
        }
    }
}
