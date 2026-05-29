package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.SpaceengineS;
import com.sable.spaceenginemod.client.WormholeAmbianceHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Server -> Client: signals the client to start wormhole-travel ambiance at the given engine pos. */
public record WormholeTravelSoundPacket(BlockPos enginePos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<WormholeTravelSoundPacket> TYPE =
            new CustomPacketPayload.Type<>(SpaceNetworking.id("wormhole_travel_sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WormholeTravelSoundPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, WormholeTravelSoundPacket::enginePos,
                    WormholeTravelSoundPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(WormholeTravelSoundPacket packet, IPayloadContext ctx) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ctx.enqueueWork(() -> ClientHandler.handle(packet));
        }
    }

    private static final class ClientHandler {
        static void handle(WormholeTravelSoundPacket packet) {
            WormholeAmbianceHandler.wormholeTravelPos = packet.enginePos;
        }
    }
}
