package me.dmillerw.minelua.event;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * @author dmillerw
 */
public class Functions {

    public static class RegisterCallback extends TwoArgFunction {

        @Override
        public LuaValue call(LuaValue type, LuaValue function) {
            if (!type.typename().equals("string"))
                argerror(1, "expected string, got " + type.typename());

            if (!function.typename().equals("function"))
                argerror(2, "expected function, got " + function.typename());

            EventHandler.registerCallback(type.tojstring(), (LuaFunction) function);

            return NIL;
        }
    }
}
