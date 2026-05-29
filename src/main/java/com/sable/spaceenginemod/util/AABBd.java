package com.sable.spaceenginemod.util;

import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * Tiny axis-aligned double-precision bounding box. Replaces the
 * {@code org.joml.primitives.AABBd}/{@code AABBdc} interfaces that Genesis used,
 * which live in a separate {@code joml-primitives} artifact we don't pull in.
 *
 * <p>Mutable; mirrors only the small subset of joml-primitives API that the
 * ported call sites actually used: ctor from min/max doubles, the per-axis
 * accessors, {@link #containsPoint(Vector3dc)}, and a copy ctor / no-op
 * {@link #set(AABBd)} helper.
 */
public final class AABBd {
    public double minX, minY, minZ, maxX, maxY, maxZ;

    public AABBd() {}

    public AABBd(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public AABBd(AABBd o) {
        this(o.minX, o.minY, o.minZ, o.maxX, o.maxY, o.maxZ);
    }

    public AABBd set(AABBd o) {
        this.minX = o.minX; this.minY = o.minY; this.minZ = o.minZ;
        this.maxX = o.maxX; this.maxY = o.maxY; this.maxZ = o.maxZ;
        return this;
    }

    public double minX() { return minX; }
    public double minY() { return minY; }
    public double minZ() { return minZ; }
    public double maxX() { return maxX; }
    public double maxY() { return maxY; }
    public double maxZ() { return maxZ; }

    public Vector3d center(Vector3d dest) {
        dest.x = (minX + maxX) * 0.5;
        dest.y = (minY + maxY) * 0.5;
        dest.z = (minZ + maxZ) * 0.5;
        return dest;
    }

    public Vector3d size(Vector3d dest) {
        dest.x = maxX - minX;
        dest.y = maxY - minY;
        dest.z = maxZ - minZ;
        return dest;
    }

    public Vector3d halfExtents(Vector3d dest) {
        dest.x = (maxX - minX) * 0.5;
        dest.y = (maxY - minY) * 0.5;
        dest.z = (maxZ - minZ) * 0.5;
        return dest;
    }

    /** Alias used by ported code that came from joml-primitives' {@code AABBd#extent}. */
    public Vector3d extent(Vector3d dest) {
        return halfExtents(dest);
    }

    /** Index-based min accessor (0=x, 1=y, 2=z). Mirrors joml-primitives. */
    public double getMin(int axis) {
        return switch (axis) {
            case 0 -> minX;
            case 1 -> minY;
            case 2 -> minZ;
            default -> throw new IllegalArgumentException("axis must be 0..2, got " + axis);
        };
    }

    /** Index-based max accessor (0=x, 1=y, 2=z). Mirrors joml-primitives. */
    public double getMax(int axis) {
        return switch (axis) {
            case 0 -> maxX;
            case 1 -> maxY;
            case 2 -> maxZ;
            default -> throw new IllegalArgumentException("axis must be 0..2, got " + axis);
        };
    }

    public boolean containsPoint(Vector3dc p) {
        return p.x() >= minX && p.x() <= maxX
            && p.y() >= minY && p.y() <= maxY
            && p.z() >= minZ && p.z() <= maxZ;
    }
}
