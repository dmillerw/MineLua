package dmillerw.minelua.arg.object;

import dmillerw.minelua.arg.Argument;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author dmillerw
 */
public class PlayerArgument extends Argument<EntityPlayer> {

    public PlayerArgument(EntityPlayer backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("held_item", new ItemStackArgument(backingObject.getHeldItem()));
    }
}
