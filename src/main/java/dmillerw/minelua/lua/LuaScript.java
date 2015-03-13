package dmillerw.minelua.lua;

import dmillerw.minelua.lib.CoreLogger;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author dmillerw
 */
public class LuaScript {

    public final Globals mGlobals;
    private LuaValue script;

    public LuaScript(final File file) {
        mGlobals = MineGlobals.getGlobals();

        // Override print function to add custom output
        mGlobals.set("print", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue) {
                CoreLogger.info(file.getName(), luaValue.tojstring());
                return NIL;
            }
        });

        try {
            script = mGlobals.load(new FileReader(file), "script");
        } catch (IOException ex) {
            script = null;
        }
    }

    public LuaScript(String script) {
        mGlobals = MineGlobals.getGlobals();

        // Override print function to add custom output
        mGlobals.set("print", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue) {
                CoreLogger.info("script", luaValue.tojstring());
                return NIL;
            }
        });

        this.script = mGlobals.load(script, "script");
    }

    public void call() {
        if (script != null)
            script.call();
    }
}
