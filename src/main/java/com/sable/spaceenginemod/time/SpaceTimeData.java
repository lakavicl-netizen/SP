package com.sable.spaceenginemod.time;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Per-server persistent storage for the global "frozen-time" offset.
 *
 * <p>Ported from {@code shipwrights.genesis.time.GenesisTimeData}. In
 * NeoForge 1.21.1 the {@link SavedData} API now takes a
 * {@link SavedData.Factory} (with optional {@code DataFixTypes}) and the
 * {@code load} hook receives a {@link HolderLookup.Provider}.
 */
public class SpaceTimeData extends SavedData {

    private static final String DATA_NAME = "genesis_time";
    private long timeOffset = 0;

    public static SpaceTimeData getOrCreate(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(SpaceTimeData::new, SpaceTimeData::load),
                DATA_NAME
        );
    }

    public static SpaceTimeData load(CompoundTag tag, HolderLookup.Provider registries) {
        SpaceTimeData data = new SpaceTimeData();
        data.timeOffset = tag.getLong("timeOffset");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putLong("timeOffset", timeOffset);
        return tag;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public void addOffset(long delta) {
        timeOffset += delta;
        setDirty();
    }
}
