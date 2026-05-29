package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.client.WormholeAmbianceHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Server -> Client: signals the client to play the void-engine start sound at the given engine pos. */
public record VoidEngineSoundPacket(BlockPos enginePos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<VoidEngineSoundPacket> TYPE =
            new CustomPacketPayload.Type<>(SpaceNetworking.id("void_engine_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, VoidEngineSoundPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, VoidEngineSoundPacket::enginePos,
                    VoidEngineSoundPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(VoidEngineSoundPacket packet, IPayloadContext ctx) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ctx.enqueueWork(() -> ClientHandler.handle(packet));
        }
    }

    private static final class ClientHandler {
        static void handle(VoidEngineSoundPacket packet) {
            WormholeAmbianceHandler.voidEngineStartPos = packet.enginePos;
            WormholeAmbianceHandler.playVoidEngineStart();
        }
    }
}
