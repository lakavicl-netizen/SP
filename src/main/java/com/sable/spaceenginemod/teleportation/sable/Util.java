package com.sable.spaceenginemod.teleportation.sable;

/**
 * Helpers for the dimension-transition teleporters. Ported from
 * {@code shipwrights.genesis.teleportation.integration.Util}.
 *
 * <p>The Forge/VS original exposed a single method, {@code getSortedShips},
 * that walked Valkyrien Skies' {@code ShipObjectWorld} and returned every
 * loaded ship in the given level sorted by AABB volume. The Sable port does
 * not relocate ships (Sable owns its own sublevel transfer via
 * {@code dev.egg.SubLevelWarper}), so the entire helper surface is gone.
 *
 * <p>The class is retained as a stub so anything that historically imported
 * {@code teleportation.integration.Util} can be flipped to this package
 * without further surgery, and so future helpers shared between
 * {@link PlanetToSpaceTeleporter} and {@link SpaceToPlanetTeleporter} have a
 * sensible home.
 */
public final class Util {
    private Util() {}
}
