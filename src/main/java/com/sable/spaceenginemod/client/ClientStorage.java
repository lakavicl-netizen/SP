package com.sable.spaceenginemod.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Rather sussy class to store global client variables, mostly used in mixins
 */
@OnlyIn(Dist.CLIENT)
public class ClientStorage {
    /**
     * True for a couple of ticks while the player is entering or leaving the wormhole dimension.
     * Used for {@link com.sable.spaceenginemod.mixin.MinecraftMixin}.
     * <br>
     * Set to true by the server when the player begins a wormhole trip
     * (see the void-engine block-entity tick, dropped during the 1.21.1 port).
     * <br>
     * Set to false by {@link WarpLoadingMenu#onClose()}
     */
    public static boolean goingToFromWormhole = false;
}
