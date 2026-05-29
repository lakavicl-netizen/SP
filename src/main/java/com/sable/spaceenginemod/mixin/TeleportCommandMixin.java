package com.sable.spaceenginemod.mixin;

// TODO(port): Re-enable the `/teleport <celestial>` and `/teleport <targets> <celestial>`
// command branches once `com.sable.spaceenginemod.teleportation.impl.EntityTeleporter`
// has been ported from genesis 1.20.1. Until then this file holds no @Mixin so the
// mixin processor ignores it, and the config does not list it.
//
// The original genesis injector signature is preserved in comments at the bottom of
// this file for reference.
//
// Genesis source: shipwrights/genesis/mixin/TeleportCommandMixin.java

class TeleportCommandMixin {
    private TeleportCommandMixin() {}
}
