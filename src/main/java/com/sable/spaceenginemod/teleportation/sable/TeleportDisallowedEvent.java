package com.sable.spaceenginemod.teleportation.sable;

import com.sable.spaceenginemod.space.Celestial;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * Fired on the {@code NeoForge.EVENT_BUS} when a player is about to be moved
 * between a planet and space by the altitude-based dimension teleporter, but
 * an addon or datapack wants to deny the transfer.
 *
 * <p>Ported from {@code shipwrights.genesis.teleportation.integration
 * .TeleportDisallowedEvent}. The Forge 1.20.1 original referenced Valkyrien
 * Skies' {@code Ship} type; here the subject is a {@link ServerPlayer}, since
 * the Sable port triggers on player altitude rather than on ships.
 *
 * <p>The event is {@linkplain ICancellableEvent cancellable} — listeners that
 * cancel it block the teleport for the current tick.
 */
public final class TeleportDisallowedEvent extends Event implements ICancellableEvent {

    private final ServerPlayer player;
    private final Celestial celestial;

    public TeleportDisallowedEvent(ServerPlayer player, Celestial celestial) {
        this.player = player;
        this.celestial = celestial;
    }

    /** The player the teleporter wanted to move. */
    public ServerPlayer player() {
        return player;
    }

    /** The celestial the player was about to be sent to / from. May be {@code null}. */
    public Celestial celestial() {
        return celestial;
    }
}
