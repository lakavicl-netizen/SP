package com.sable.spaceenginemod;

import com.sable.spaceengine_tp.client.PlanetShaderRegistration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = SpaceengineS.MODID, dist = Dist.CLIENT)
public class SpaceengineSClient {
    public SpaceengineSClient(IEventBus modEventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(PlanetShaderRegistration::onRegisterShaders);
        SpaceengineS.LOGGER.info("Space Engine-S Client loaded!");
    }
}
