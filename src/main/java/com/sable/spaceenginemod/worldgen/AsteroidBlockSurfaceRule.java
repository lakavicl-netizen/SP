package com.sable.spaceenginemod.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AsteroidBlockSurfaceRule implements SurfaceRules.RuleSource {
    public static final KeyDispatchDataCodec<AsteroidBlockSurfaceRule> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new AsteroidBlockSurfaceRule()));

    private static List<BlockState> states;

    AsteroidBlockSurfaceRule() {}

    @Override
    public @NotNull KeyDispatchDataCodec<? extends SurfaceRules.RuleSource> codec() {
        return CODEC;
    }

    @Override
    public SurfaceRules.SurfaceRule apply(SurfaceRules.Context arg) {
        return (i, j, k) -> {
            int index = (int) (hash3(i, j, k) % getStates().size());
            return getStates().get(index);
        };
    }

    private long hash3(int x, int y, int z) {
        long h = x * 73428767L ^ y * 91278311L ^ z * 37855139L;
        h = (h ^ (h >>> 13)) * 1274126177L;
        h = h ^ (h >>> 16);
        return Math.abs(h);
    }

    private static List<BlockState> getStates() {
        if (states == null) {
            states = buildStates();
        }
        return states;
    }

    private static List<BlockState> buildStates() {
        // TODO: vanilla block placeholders. The original Genesis port used the
        // modded AsteroidBlock with VARIANT * PALETTE blockstate combinations
        // (see shipwrights.genesis.content.block.AsteroidBlock). Blocks/items
        // were intentionally dropped from this port, so we substitute a mixed
        // palette of stone-like vanilla blocks to keep the surface rule
        // structurally identical and the codec valid.
        List<BlockState> result = new ArrayList<>();
        result.add(Blocks.STONE.defaultBlockState());
        result.add(Blocks.COBBLESTONE.defaultBlockState());
        result.add(Blocks.DEEPSLATE.defaultBlockState());
        result.add(Blocks.COBBLED_DEEPSLATE.defaultBlockState());
        result.add(Blocks.ANDESITE.defaultBlockState());
        result.add(Blocks.DIORITE.defaultBlockState());
        result.add(Blocks.GRANITE.defaultBlockState());
        result.add(Blocks.TUFF.defaultBlockState());
        result.add(Blocks.GRAVEL.defaultBlockState());
        result.add(Blocks.SAND.defaultBlockState());
        return result;
    }
}
