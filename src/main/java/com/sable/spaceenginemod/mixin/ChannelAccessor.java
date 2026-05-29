package com.sable.spaceenginemod.mixin;

import com.mojang.blaze3d.audio.Channel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Exposes {@link Channel#source} so {@link SoundEngineMixin} can attach
 * OpenAL filter handles to individual playing channels.
 *
 * <p>Ported from genesis 1.20.1; the {@code source} field is unchanged in 1.21.1.
 */
@Mixin(Channel.class)
public interface ChannelAccessor {
    @Accessor("source") int getSourceId();
}
