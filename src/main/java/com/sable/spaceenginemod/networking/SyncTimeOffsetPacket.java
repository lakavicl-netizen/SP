package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.SpaceengineS;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Server -> Client: syncs the global per-server time offset to the client. */
public record SyncTimeOffsetPacket(long timeOffset) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncTimeOffsetPacket> TYPE =
            new CustomPacketPayload.Type<>(SpaceNetworking.id("sync_time_offset"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncTimeOffsetPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_LONG, SyncTimeOffsetPacket::timeOffset,
                    SyncTimeOffsetPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncTimeOffsetPacket packet, IPayloadContext ctx) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ctx.enqueueWork(() -> SpaceengineS.clientTimeOffset = packet.timeOffset);
        }
    }
}
