package dmillerw.minelua.arg.event;

import dmillerw.minelua.arg.object.ItemStackArgument;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

/**
 * @author dmillerw
 */
public class EntityItemPickupArgument extends EventArgument<EntityItemPickupEvent> {

    public EntityItemPickupArgument(EntityItemPickupEvent backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("item", new ItemStackArgument(backingObject.item.getEntityItem()));
    }
}
