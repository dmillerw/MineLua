package dmillerw.minelua.arg.event;

import dmillerw.minelua.arg.object.PlayerArgument;
import dmillerw.minelua.arg.object.WorldArgument;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * @author dmillerw
 */
public class PlayerInteractArgument extends EventArgument<PlayerInteractEvent> {

    public PlayerInteractArgument(PlayerInteractEvent backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("x", backingObject.x);
        set("y", backingObject.y);
        set("z", backingObject.z);
        set("face", backingObject.face);
        set("world", new WorldArgument(backingObject.world));
        set("action", backingObject.action.toString().toLowerCase());
        set("player", new PlayerArgument(backingObject.entityPlayer));
    }
}
