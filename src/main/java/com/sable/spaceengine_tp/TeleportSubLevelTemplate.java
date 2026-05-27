package com.sable.spaceengine_tp;

import com.mojang.serialization.Codec;
import com.sable.spaceenginemod.mixin.LevelPlotAccessor;
import com.sable.spaceenginemod.mixin.ServerLevelPlotAccessor;
import dev.ryanhcode.sable.api.sublevel.ServerSubLevelContainer;
import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.mixinterface.plot.serialization.LevelChunkTicksExtension;
import dev.ryanhcode.sable.platform.SablePlotPlatform;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import dev.ryanhcode.sable.sublevel.plot.PlotChunkHolder;
import dev.ryanhcode.sable.sublevel.plot.ServerLevelPlot;
import dev.ryanhcode.sable.sublevel.system.SubLevelPhysicsSystem;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LayerLightEventListener;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.ticks.LevelChunkTicks;

import java.util.EnumSet;
import java.util.Map;

public record TeleportSubLevelTemplate(CompoundTag plotTag) {
    private static final int DATA_VERSION = 1;
    private static final Codec<PalettedContainer<BlockState>> BLOCK_STATE_CODEC = PalettedContainer.codecRW(
        Block.BLOCK_STATE_REGISTRY, BlockState.CODEC, PalettedContainer.Strategy.SECTION_STATES, Blocks.AIR.defaultBlockState()
    );

    public static CompoundTag save(ServerLevelPlot plot) {
        LevelPlotAccessor accessor = (LevelPlotAccessor) plot;
        ServerLevelPlotAccessor accessor1 = (ServerLevelPlotAccessor) plot;
        LevelLightEngine lightEngine = accessor1.spaceengine_tp$getLightEngine();
        SubLevelContainer container = accessor.spaceengine_tp$getContainer();

        final CompoundTag tag = new CompoundTag();
        tag.putInt("plot_x", plot.plotPos.x - container.getOrigin().x);
        tag.putInt("plot_z", plot.plotPos.z - container.getOrigin().y);
        tag.putInt("log_size", accessor.spaceengine_tp$getLogSize());
        tag.putString("biome", accessor.spaceengine_tp$getBiome().location().toString());
        tag.putInt("data_version", DATA_VERSION);
        tag.putUUID("SubLevelID", plot.getSubLevel().getUniqueId());

        final ServerLevel level = plot.getSubLevel().getLevel();

        int minY = level.dimensionType().minY();
        final BlockPos center = plot.getCenterBlock().offset(0, minY, 0);

        final CompoundTag chunks = new CompoundTag();
        for (final PlotChunkHolder chunkHolder : plot.getLoadedChunks()) {
            final ChunkPos global = chunkHolder.getPos();
            final ChunkPos local = plot.toLocal(global);
            final LevelChunk chunk = chunkHolder.getChunk();

            final CompoundTag chunkTag = new CompoundTag();
            final CompoundTag sectionsTag = new CompoundTag();

            for (int idx = 0; idx < chunk.getSectionsCount(); idx++) {
                final LevelChunkSection section = chunk.getSection(idx);
                if (section.hasOnlyAir()) continue;

                final CompoundTag sectionTag = new CompoundTag();
                sectionTag.put("block_states", BLOCK_STATE_CODEC.encodeStart(NbtOps.INSTANCE, section.getStates()).getOrThrow());

                final SectionPos sectionPos = SectionPos.of(global, level.getSectionYFromSectionIndex(idx));
                final DataLayer blockLight = lightEngine.getLayerListener(LightLayer.BLOCK).getDataLayerData(sectionPos);
                final DataLayer skyLight = lightEngine.getLayerListener(LightLayer.SKY).getDataLayerData(sectionPos);

                if (blockLight != null && !blockLight.isEmpty())
                    sectionTag.putByteArray("BlockLight", blockLight.getData());
                if (skyLight != null && !skyLight.isEmpty())
                    sectionTag.putByteArray("SkyLight", skyLight.getData());

                sectionsTag.put(String.valueOf(idx), sectionTag);
            }
            chunkTag.put("sections", sectionsTag);
            tag.putBoolean("isLightOn", chunk.isLightCorrect());

            final ListTag blockEntitiesTag = new ListTag();
            for (final BlockPos blockPos : chunk.getBlockEntitiesPos()) {
                final CompoundTag blockEntityNBT = chunk.getBlockEntityNbtForSaving(blockPos, level.registryAccess());
                if (blockEntityNBT != null) {
                    int x = blockEntityNBT.getInt("x");
                    int y = blockEntityNBT.getInt("y");
                    int z = blockEntityNBT.getInt("z");
                    blockEntityNBT.putInt("x", x - center.getX());
                    blockEntityNBT.putInt("y", y - center.getY());
                    blockEntityNBT.putInt("z", z - center.getZ());
                    blockEntitiesTag.add(blockEntityNBT);
                }
            }
            chunkTag.put("block_entities", blockEntitiesTag);

            final ChunkAccess.TicksToSave ticksToSave = chunk.getTicksForSerialization();
            final long gameTime = level.getGameTime();
            chunkTag.put("block_ticks", ticksToSave.blocks().save(gameTime, block -> BuiltInRegistries.BLOCK.getKey(block).toString()));
            chunkTag.put("fluid_ticks", ticksToSave.fluids().save(gameTime, fluid -> BuiltInRegistries.FLUID.getKey(fluid).toString()));

            final CompoundTag heightMapsTag = new CompoundTag();
            for (final Map.Entry<Heightmap.Types, Heightmap> entry : chunk.getHeightmaps()) {
                if (chunk.getPersistedStatus().heightmapsAfter().contains(entry.getKey()))
                    heightMapsTag.put(entry.getKey().getSerializationKey(), new LongArrayTag(entry.getValue().getRawData()));
            }
            chunkTag.put("heightmaps", heightMapsTag);

            SablePlotPlatform.INSTANCE.writeLightData(tag, level.registryAccess(), chunk);
            SablePlotPlatform.INSTANCE.writeChunkAttachments(tag, level.registryAccess(), chunk);

            chunks.put(String.valueOf(ChunkPos.asLong(local.x, local.z)), chunkTag);
        }
        tag.put("chunks", chunks);
        return tag;
    }

    public static void load(ServerLevelPlot destinationPlot, CompoundTag tag) {
        LevelPlotAccessor accessor = (LevelPlotAccessor) destinationPlot;
        ServerLevelPlotAccessor accessor1 = (ServerLevelPlotAccessor) destinationPlot;
        LevelLightEngine lightEngine = accessor1.spaceengine_tp$getLightEngine();
        SubLevelContainer container = accessor.spaceengine_tp$getContainer();

        int logSize = tag.getInt("log_size");
        if (logSize != accessor.spaceengine_tp$getLogSize())
            throw new IllegalArgumentException("Log size mismatch");

        int dataVersion = tag.contains("data_version") ? tag.getInt("data_version") : 0;
        if (dataVersion < 0 || dataVersion > DATA_VERSION)
            throw new IllegalArgumentException("Unsupported version: " + dataVersion);

        ServerSubLevel subLevel = destinationPlot.getSubLevel();
        ServerLevel level = subLevel.getLevel();

        int minY = level.dimensionType().minY();
        BlockPos center = destinationPlot.getCenterBlock().offset(0, minY, 0);

        if (tag.contains("biome")) {
            ResourceLocation location = ResourceLocation.tryParse(tag.getString("biome"));
            if (location != null)
                accessor.spaceengine_tp$setBiome(ResourceKey.create(Registries.BIOME, location));
        }

        CompoundTag chunks = tag.getCompound("chunks");
        for (String key : chunks.getAllKeys()) {
            long chunkPos = Long.parseLong(key);
            int x = ChunkPos.getX(chunkPos);
            int z = ChunkPos.getZ(chunkPos);
            ChunkPos local = new ChunkPos(x, z);
            ChunkPos global = destinationPlot.toGlobal(local);

            CompoundTag chunkTag = chunks.getCompound(key);
            CompoundTag sectionsTag = chunkTag.getCompound("sections");

            accessor1.spaceengine_tp$invokeNewNonLitChunk(global);
            LevelChunk chunk = destinationPlot.getChunk(local);

            boolean hasLit = false;
            for (String sectionKey : sectionsTag.getAllKeys()) {
                int yIndex = Integer.parseInt(sectionKey);
                LevelChunkSection[] sections = chunk.getSections();

                CompoundTag sectionTag = sectionsTag.getCompound(sectionKey);
                PalettedContainer<BlockState> palettedContainer = BLOCK_STATE_CODEC
                    .parse(NbtOps.INSTANCE, sectionTag.getCompound("block_states"))
                    .promotePartial(string -> ServerLevelPlotAccessor.spaceengine_tp$invokeLogLoadingErrors(new ChunkPos(chunkPos), chunk.getSectionYFromSectionIndex(yIndex), string))
                    .getOrThrow(ChunkSerializer.ChunkReadException::new);

                Registry<Biome> biomeRegistry = level.registryAccess().registryOrThrow(Registries.BIOME);
                PalettedContainer<Holder<Biome>> biomeContainer = new PalettedContainer<>(
                    biomeRegistry.asHolderIdMap(), biomeRegistry.getHolderOrThrow(accessor.spaceengine_tp$getBiome()), PalettedContainer.Strategy.SECTION_BIOMES
                );

                sections[yIndex] = new LevelChunkSection(palettedContainer, biomeContainer);

                SectionPos sectionPos = SectionPos.of(global, level.getSectionYFromSectionIndex(yIndex));
                boolean hasBlockLight = !(lightEngine.getLayerListener(LightLayer.BLOCK) instanceof LayerLightEventListener.DummyLightLayerEventListener) && sectionTag.contains("BlockLight", Tag.TAG_BYTE_ARRAY);
                boolean hasSkyLight = !(lightEngine.getLayerListener(LightLayer.SKY) instanceof LayerLightEventListener.DummyLightLayerEventListener) && level.dimensionType().hasSkyLight() && sectionTag.contains("SkyLight", Tag.TAG_BYTE_ARRAY);

                if (hasBlockLight || hasSkyLight) {
                    if (!hasLit) { lightEngine.retainData(global, true); hasLit = true; }
                    if (hasBlockLight) lightEngine.queueSectionData(LightLayer.BLOCK, sectionPos, new DataLayer(sectionTag.getByteArray("BlockLight")));
                    if (hasSkyLight) lightEngine.queueSectionData(LightLayer.SKY, sectionPos, new DataLayer(sectionTag.getByteArray("SkyLight")));
                }
            }

            if (dataVersion >= 0) {
                LevelChunkTicks<Block> blockTicks = LevelChunkTicks.load(
                    chunkTag.getList("block_ticks", Tag.TAG_COMPOUND), id -> BuiltInRegistries.BLOCK.getOptional(ResourceLocation.tryParse(id)), global
                );
                LevelChunkTicks<Fluid> fluidTicks = LevelChunkTicks.load(
                    chunkTag.getList("fluid_ticks", Tag.TAG_COMPOUND), id -> BuiltInRegistries.FLUID.getOptional(ResourceLocation.tryParse(id)), global
                );
                ((LevelChunkTicksExtension<Block>) chunk.getBlockTicks()).sable$copy(blockTicks);
                ((LevelChunkTicksExtension<Fluid>) chunk.getFluidTicks()).sable$copy(fluidTicks);

                CompoundTag heightMapsTag = chunkTag.getCompound("heightmaps");
                EnumSet<Heightmap.Types> enumset = EnumSet.noneOf(Heightmap.Types.class);
                for (Heightmap.Types heightMapType : chunk.getPersistedStatus().heightmapsAfter()) {
                    String heightMapKey = heightMapType.getSerializationKey();
                    if (heightMapsTag.contains(heightMapKey, Tag.TAG_LONG_ARRAY))
                        chunk.setHeightmap(heightMapType, heightMapsTag.getLongArray(heightMapKey));
                    else
                        enumset.add(heightMapType);
                }
                Heightmap.primeHeightmaps(chunk, enumset);
                SablePlotPlatform.INSTANCE.readLightData(chunkTag, level.registryAccess(), chunk);
                chunk.setLightCorrect(chunkTag.getBoolean("isLightOn"));
            }

            accessor1.spaceengine_tp$invokeLightChunk(chunk);
            SablePlotPlatform.INSTANCE.readChunkAttachments(chunkTag, level.registryAccess(), chunk);

            ListTag blockEntitiesTag = chunkTag.getList("block_entities", 10);
            for (int i = 0; i < blockEntitiesTag.size(); i++) {
                CompoundTag blockEntityTag = blockEntitiesTag.getCompound(i).copy();
                boolean keepBlockEntityPacked = blockEntityTag.getBoolean("keepPacked");
                BlockPos offset = BlockEntity.getPosFromTag(blockEntityTag);
                BlockPos pos = center.offset(offset);
                blockEntityTag.putInt("x", pos.getX());
                blockEntityTag.putInt("y", pos.getY());
                blockEntityTag.putInt("z", pos.getZ());

                if (keepBlockEntityPacked) {
                    chunk.setBlockEntityNbt(blockEntityTag);
                } else {
                    BlockPos blockPos = BlockEntity.getPosFromTag(blockEntityTag);
                    BlockEntity blockEntity = BlockEntity.loadStatic(blockPos, chunk.getBlockState(blockPos), blockEntityTag, level.registryAccess());
                    if (blockEntity != null)
                        chunk.setBlockEntity(blockEntity);
                }
            }

            chunk.registerAllBlockEntitiesAfterLevelLoad();
            level.startTickingChunk(chunk);
            SablePlotPlatform.INSTANCE.postLoad(chunkTag, chunk);
        }

        do { lightEngine.runLightUpdates(); } while (lightEngine.hasLightWork());

        SubLevelPhysicsSystem physicsSystem = ((ServerSubLevelContainer) container).physicsSystem();
        BlockPos.MutableBlockPos globalBlockPos = new BlockPos.MutableBlockPos();

        for (String key : chunks.getAllKeys()) {
            long chunkPos = Long.parseLong(key);
            int x = ChunkPos.getX(chunkPos);
            int z = ChunkPos.getZ(chunkPos);
            ChunkPos local = new ChunkPos(x, z);
            ChunkPos global = destinationPlot.toGlobal(local);
            PlotChunkHolder chunkHolder = destinationPlot.getChunkHolder(local);
            LevelChunk chunk = destinationPlot.getChunk(local);
            LevelChunkSection[] levelChunkSections = chunk.getSections();

            Iterable<ServerPlayer> players = container.getPlayersTracking(global);
            for (ServerPlayer player : players) {
                dev.ryanhcode.sable.sublevel.plot.SubLevelPlayerChunkSender.sendChunk(player.connection::send, lightEngine, chunk);
                dev.ryanhcode.sable.sublevel.plot.SubLevelPlayerChunkSender.sendChunkPoiData(level, chunk);
            }

            for (int i = 0; i < chunk.getSectionsCount(); i++) {
                LevelChunkSection section = levelChunkSections[i];
                if (!section.hasOnlyAir()) {
                    int sectionY = chunk.getSectionYFromSectionIndex(i);
                    int chunkMinX = global.getMinBlockX();
                    int chunkMinY = sectionY << 4;
                    int chunkMinZ = global.getMinBlockZ();

                    boolean expandPlotBackup = accessor.spaceengine_tp$getExpandPlotIfNecessary();
                    accessor.spaceengine_tp$setExpandPlotIfNecessary(false);

                    BlockState airState = Blocks.AIR.defaultBlockState();
                    for (int xOff = 0; xOff < 16; xOff++) {
                        for (int yOff = 0; yOff < 16; yOff++) {
                            for (int zOff = 0; zOff < 16; zOff++) {
                                BlockState state = section.getBlockState(xOff, yOff, zOff);
                                if (!state.isAir()) {
                                    globalBlockPos.set(xOff + chunkMinX, yOff + chunkMinY, zOff + chunkMinZ);
                                    BlockPos immutable = globalBlockPos.immutable();
                                    chunkHolder.handleBlockChange(xOff, chunkMinY + yOff, zOff, airState, state);
                                    subLevel.getHeatMapManager().onSolidAdded(immutable);
                                    subLevel.getFloatingBlockController().queueAddFloatingBlock(state, immutable);
                                    physicsSystem.updateMassDataFromBlockChange(subLevel, globalBlockPos, airState, state, false);
                                    destinationPlot.onBlockChange(immutable, state);
                                }
                            }
                        }
                    }
                    accessor.spaceengine_tp$setExpandPlotIfNecessary(expandPlotBackup);
                }
            }
        }

        destinationPlot.updateBoundingBox();
        subLevel.updateMergedMassData(1.0f);
        physicsSystem.getPipeline().onStatsChanged(subLevel);

        for (String key : chunks.getAllKeys()) {
            long chunkPos = Long.parseLong(key);
            int x = ChunkPos.getX(chunkPos);
            int z = ChunkPos.getZ(chunkPos);
            ChunkPos local = new ChunkPos(x, z);
            ChunkPos global = destinationPlot.toGlobal(local);
            LevelChunk chunk = destinationPlot.getChunk(local);
            LevelChunkSection[] levelChunkSections = chunk.getSections();

            for (int i = 0; i < chunk.getSectionsCount(); i++) {
                LevelChunkSection section = levelChunkSections[i];
                if (!section.hasOnlyAir()) {
                    int sectionY = chunk.getSectionYFromSectionIndex(i);
                    physicsSystem.getTicketManager().addTicketForSection(level, SectionPos.of(global.x, sectionY, global.z));
                    physicsSystem.getPipeline().handleChunkSectionAddition(section, global.x, sectionY, global.z, true);
                }
            }
        }

        subLevel.updateMergedMassData(1.0f);
        physicsSystem.getPipeline().onStatsChanged(subLevel);
    }
}
