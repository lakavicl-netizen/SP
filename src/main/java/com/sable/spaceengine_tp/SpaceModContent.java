package com.sable.spaceengine_tp;

import com.sable.spaceengine_tp.entity.PlanetEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpaceModContent {
    public static final String MODID = "space_engine_s";

    public static final ResourceKey<Level> SPACE_LEVEL_KEY = ResourceKey.create(
        Registries.DIMENSION,
        ResourceLocation.fromNamespaceAndPath(MODID, "space")
    );

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
        Registries.ENTITY_TYPE, MODID
    );

    public static final Supplier<EntityType<PlanetEntity>> PLANET_ENTITY = ENTITIES.register("planet", () ->
        EntityType.Builder.<PlanetEntity>of(PlanetEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .clientTrackingRange(0)
            .updateInterval(20)
            .build("planet")
    );
}
