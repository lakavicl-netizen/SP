package com.sable.spaceenginemod.networking;

import com.sable.spaceenginemod.client.ClientStorage;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Server -> Client: notifies the client it is entering / leaving the wormhole dimension. */
public record EnteringWarpPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EnteringWarpPacket> TYPE =
            new CustomPacketPayload.Type<>(SpaceNetworking.id("entering_warp"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnteringWarpPacket> STREAM_CODEC =
            StreamCodec.unit(new EnteringWarpPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EnteringWarpPacket packet, IPayloadContext ctx) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ctx.enqueueWork(() -> ClientStorage.goingToFromWormhole = true);
        }
    }
}
