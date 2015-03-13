package dmillerw.minelua.arg.object;

import cpw.mods.fml.common.registry.GameData;
import dmillerw.minelua.arg.Argument;
import net.minecraft.block.Block;

/**
 * @author dmillerw
 */
public class BlockArgument extends Argument<Block> {

    public BlockArgument(Block backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("name", GameData.getBlockRegistry().getNameForObject(backingObject));
    }
}
