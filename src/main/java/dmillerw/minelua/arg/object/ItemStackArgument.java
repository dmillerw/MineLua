package dmillerw.minelua.arg.object;

import cpw.mods.fml.common.registry.GameData;
import dmillerw.minelua.arg.Argument;
import net.minecraft.item.ItemStack;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * @author dmillerw
 */
public class ItemStackArgument extends Argument<ItemStack> {

    public ItemStackArgument(ItemStack backing) {
        super(backing);
    }

    @Override
    public void fillTable() {
        set("name", GameData.getItemRegistry().getNameForObject(backingObject.getItem()));
        set("item", new ItemArgument(backingObject.getItem()));
        set("damage", backingObject.getItemDamage());
        set("stack_size", backingObject.stackSize);

        set("set_item", new FunctionSetItem());
        set("set_damage", new FunctionSetDamage());
        set("set_stack_size", new FunctionSetStackSize());
    }

    public class FunctionSetItem extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            backingObject.func_150996_a(GameData.getItemRegistry().getObject(luaValue.tojstring()));
            return NIL;
        }
    }

    public class FunctionSetDamage extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            backingObject.setItemDamage(luaValue.toint());
            return NIL;
        }
    }

    public class FunctionSetStackSize extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            backingObject.stackSize = luaValue.toint();
            return NIL;
        }
    }
}
