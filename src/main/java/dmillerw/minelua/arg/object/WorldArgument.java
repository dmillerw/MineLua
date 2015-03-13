package dmillerw.minelua.arg.object;

import cpw.mods.fml.common.registry.GameData;
import dmillerw.minelua.arg.Argument;
import dmillerw.minelua.lua.FiveArgFunction;
import net.minecraft.world.World;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;

/**
 * @author dmillerw
 */
public class WorldArgument extends Argument<World> {

    public WorldArgument(World backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        super.fillTable();

        set("get_block", new FunctionGetBlock());
        set("set_block", new FunctionSetBlock());
    }

    public class FunctionGetBlock extends ThreeArgFunction {
        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue z) {
            return new BlockArgument(backingObject.getBlock(x.toint(), y.toint(), z.toint()));
        }
    }

    public class FunctionSetBlock extends FiveArgFunction {

        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue z, LuaValue block) {
            return call(x, y, z, block, LuaValue.valueOf(0));
        }

        @Override
        public LuaValue call(LuaValue x, LuaValue y, LuaValue z, LuaValue block, LuaValue meta) {
            backingObject.setBlock(x.toint(), y.toint(), z.toint(), GameData.getBlockRegistry().getObject(block.tojstring()), meta.toint(), 3);
            return null;
        }
    }
}
