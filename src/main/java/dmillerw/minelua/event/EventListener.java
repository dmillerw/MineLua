package dmillerw.minelua.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmillerw.minelua.arg.event.EntityItemPickupArgument;
import dmillerw.minelua.arg.event.PlayerInteractArgument;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * @author dmillerw
 */
public class EventListener {

    @SubscribeEvent
    public void onEntityItemPickup(EntityItemPickupEvent event) {
        EventHandler.fireEvent(EntityItemPickupEvent.class, new EntityItemPickupArgument(event));
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        EventHandler.fireEvent(PlayerInteractEvent.class, new PlayerInteractArgument(event));
    }
}
