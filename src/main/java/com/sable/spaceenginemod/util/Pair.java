package com.sable.spaceenginemod.util;

/**
 * Tiny immutable pair record. Replaces {@code kotlin.Pair} from Genesis so we don't
 * have to drag the Kotlin runtime into the NeoForge port just for a 2-tuple.
 *
 * <p>Field accessors mirror Kotlin's {@code Pair}: {@link #getFirst()},
 * {@link #getSecond()}, {@link #component1()}, {@link #component2()}.
 */
public record Pair<A, B>(A first, B second) {
    public A getFirst() { return first; }
    public B getSecond() { return second; }
    public A component1() { return first; }
    public B component2() { return second; }
}
