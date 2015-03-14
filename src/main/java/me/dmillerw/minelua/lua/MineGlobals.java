package me.dmillerw.minelua.lua;

import me.dmillerw.minelua.event.Functions;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.jse.JsePlatform;

import static org.luaj.vm2.LuaValue.NIL;

/**
 * @author dmillerw
 */
public class MineGlobals {

    public static Globals getGlobals() {
        Globals globals = JsePlatform.standardGlobals();

        globals.set("collectgarbage", NIL);
        globals.set("dofile", NIL);
        globals.set("load", NIL);
        globals.set("loadfile", NIL);
        globals.set("module", NIL);
        globals.set("require", NIL);
        globals.set("package", NIL);
        globals.set("io", NIL);
        globals.set("os", NIL);
        globals.set("luajava", NIL);
        globals.set("debug", NIL);
        globals.set("newproxy", NIL);

        globals.set("print", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue) {
                System.out.println(luaValue.tojstring());
                return NIL;
            }
        });

        globals.set("register_callback", new Functions.RegisterCallback());

        return globals;
    }

}
