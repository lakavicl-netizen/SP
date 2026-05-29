package net.lointain.cosmos;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class RenderCloudsEvent extends Event {
   private ClientLevel level;

   public RenderCloudsEvent(ClientLevel level) {
      this.level = level;
   }

   public ClientLevel getLevel() {
      return this.level;
   }
}
