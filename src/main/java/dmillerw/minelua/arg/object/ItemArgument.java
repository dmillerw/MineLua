package dmillerw.minelua.arg.object;

import cpw.mods.fml.common.registry.GameData;
import dmillerw.minelua.arg.Argument;
import net.minecraft.item.Item;

/**
 * @author dmillerw
 */
public class ItemArgument extends Argument<Item> {

    public ItemArgument(Item backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("name", GameData.getItemRegistry().getNameForObject(backingObject));
    }
}
