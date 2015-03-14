package me.dmillerw.minelua.lib;

import cpw.mods.fml.common.eventhandler.Event;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * @author dmillerw
 */
public class EventArgument<T extends Event> extends Argument<T> {

    public EventArgument(T backing) {
        super(backing);
    }

    public void fillTable() {
        set("set_canceled", new FunctionSetCanceled());
    }

    public boolean isCanceled() {
        return backingObject.isCanceled();
    }

    private class FunctionSetCanceled extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            backingObject.setCanceled(luaValue.toboolean());
            return NIL;
        }
    }
}
