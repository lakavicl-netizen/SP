package com.sable.spaceenginemod;

import com.mojang.logging.LogUtils;

import com.sable.spaceenginemod.commands.SpaceCommandArguments;
import com.sable.spaceenginemod.config.ClientConfig;
import com.sable.spaceenginemod.config.CommonConfig;
import com.sable.spaceenginemod.content.particle.SpaceParticles;
import com.sable.spaceenginemod.content.sound.SpaceSounds;
import com.sable.spaceenginemod.networking.SpaceNetworking;
import com.sable.spaceenginemod.space.Celestial;
import com.sable.spaceenginemod.space.transformProvider.BuiltinTransformProviders;
import com.sable.spaceenginemod.space.type.BuiltinCelestialTypes;
import com.sable.spaceenginemod.teleportation.sable.PlanetToSpaceTeleporter;
import com.sable.spaceenginemod.teleportation.sable.SpaceToPlanetTeleporter;
import com.sable.spaceenginemod.time.SpaceTimeData;
import com.sable.spaceenginemod.time.TimeTracker;
import com.sable.spaceenginemod.worldgen.WorldGenRegistry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.slf4j.Logger;

/**
 * Main mod entry point. Ported from {@code shipwrights.genesis.GenesisMod} to
 * NeoForge 1.21.1. Blocks, items, the player-shrinking ("mini-scale") system,
 * and Valkyrien Skies ship physics are intentionally NOT carried over.
 *
 * <p>Constants and helper methods that the rest of the codebase imports were
 * preserved verbatim wherever possible (renamed only at the package level)
 * so the port surface stays small.
 */
@Mod(SpaceengineS.MODID)
public final class SpaceengineS {

    public static final String MODID = "space_engine_s";
    /** Legacy alias used by ported call sites that still reference {@code MOD_ID}. */
    public static final String MOD_ID = MODID;

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Vector3dc UP = new Vector3d(0.0, 1.0, 0.0);
    public static final Vector3dc EAST = new Vector3d(1.0, 0.0, 0.0);

    public static long clientTimeOffset = 0;

    public static final ResourceLocation SPACE_DIM = ResourceLocation.fromNamespaceAndPath(MODID, "great_unknown");
    public static final ResourceLocation WORMHOLE_DIM = ResourceLocation.fromNamespaceAndPath(MODID, "subspace");
    public static final ResourceLocation ASTEROID_RULE_ID = ResourceLocation.fromNamespaceAndPath(MODID, "asteroid_block_surface_rule");
    public static ResourceLocation GENERIC_PLANET_ID = ResourceLocation.fromNamespaceAndPath(MODID, "planet");

    public static final ResourceKey<Registry<Celestial>> CELESTIALS_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "celestials"));

    public SpaceengineS(IEventBus modEventBus, ModContainer modContainer) {
        // --- Configs ---
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG_SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG_SPEC);

        // --- Datapack registry for Celestials ---
        modEventBus.addListener(SpaceengineS::registerDataPackRegistries);

        // --- Networking (NeoForge 1.21.1 payload API) ---
        modEventBus.addListener(SpaceNetworking::register);

        // --- Celestial types & transform providers ---
        BuiltinCelestialTypes.register();
        BuiltinTransformProviders.register();

        // --- Command arguments ---
        SpaceCommandArguments.register(modEventBus);

        // --- Deferred registers for the surviving content (particles, sounds) ---
        SpaceParticles.PARTICLE_TYPES.register(modEventBus);
        SpaceSounds.SOUND_EVENTS.register(modEventBus);
        WorldGenRegistry.init(modEventBus);

        // --- Event subscribers ---
        boolean isGameTest = System.getProperty("neoforge.enabledGameTestNamespaces") != null;
        NeoForge.EVENT_BUS.register(new PlanetToSpaceTeleporter(isGameTest));
        NeoForge.EVENT_BUS.register(new SpaceToPlanetTeleporter(isGameTest));
        NeoForge.EVENT_BUS.register(TimeTracker.class);
    }

    private static void registerDataPackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(CELESTIALS_KEY, Celestial.CODEC, Celestial.CODEC);
    }

    public static Registry<Celestial> getCelestialRegistry(Level level) {
        return level.registryAccess().registryOrThrow(CELESTIALS_KEY);
    }

    @Nullable
    public static Celestial getCelestialForLevel(Level level) {
        return getCelestialRegistry(level).get(level.dimension().location());
    }

    @SuppressWarnings("ConstantConditions")
    public static long getTicks(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            MinecraftServer server = serverLevel.getServer();
            if (server == null || server.overworld() == null) return level.getGameTime();
            return level.getGameTime() + SpaceTimeData.getOrCreate(server).getTimeOffset();
        }
        return level.getGameTime() + clientTimeOffset;
    }

    public static float getPartialTick(Level level, RenderLevelStageEvent event) {
        if (!level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) return 0f;
        return event.getPartialTick().getGameTimeDeltaTicks();
    }

    /**
     * The original "mini-scale" detection. We kept the helper because several
     * downstream classes still poke at "is this a special-rendered dim?"; the
     * actual player-shrink behavior is intentionally NOT ported.
     */
    @Deprecated
    public static boolean isMiniScale(ResourceLocation dimensionLocation) {
        return dimensionLocation.equals(SPACE_DIM) || dimensionLocation.equals(WORMHOLE_DIM);
    }

    @Deprecated
    public static boolean isMiniScale(Level level) {
        return isMiniScale(level.dimension().location());
    }

    /**
     * Returns 1.0 unconditionally now that player-shrinking is removed. Kept
     * because several ported call sites still divide / multiply by it.
     */
    public static double getDimensionScale(Level level) {
        return 1.0;
    }

    public static boolean shouldCancelVoidDamage(ResourceLocation dimensionLocation) {
        return dimensionLocation.equals(SPACE_DIM) || dimensionLocation.equals(WORMHOLE_DIM);
    }

    public static boolean shouldCancelVoidDamage(Level level) {
        return shouldCancelVoidDamage(level.dimension().location());
    }

    public static boolean isSpaceDimension(ResourceLocation dimensionLocation) {
        return dimensionLocation.equals(SPACE_DIM);
    }

    public static boolean isSpaceDimension(Level level) {
        return isSpaceDimension(level.dimension().location());
    }

    public static boolean isSubSpaceDimension(Level level) {
        return level.dimension().location().equals(WORMHOLE_DIM);
    }

    public static double getApparentSunAngle(double starUpDot, double starEastDot) {
        boolean sign = Math.signum(starUpDot) < 0;
        double starDot0To1 = (1 - starEastDot) / 4;
        return sign ? 1 - starDot0To1 : starDot0To1;
    }

    /**
     * The Pehkui-based player scaling logic from Genesis was intentionally
     * removed (per the porting brief: drop "обратное масштабирование(уменьшения
     * игрока в космосе)"). This is now a no-op kept only to keep API call
     * sites compiling until we sweep them.
     */
    @ApiStatus.Internal
    public static void refreshEntityScaling(net.minecraft.world.entity.Entity entity, Level level) {
        // Intentionally empty — player shrinking dropped.
    }
}
