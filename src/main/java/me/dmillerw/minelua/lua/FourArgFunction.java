package me.dmillerw.minelua.lua;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.LibFunction;

public abstract class FourArgFunction extends LibFunction {

    public abstract LuaValue call(LuaValue var1, LuaValue var2, LuaValue var3, LuaValue var4);

    public FourArgFunction() {
    }

    public final LuaValue call() {
        return this.call(NIL, NIL, NIL);
    }

    public final LuaValue call(LuaValue var1) {
        return this.call(var1, NIL, NIL);
    }

    public LuaValue call(LuaValue var1, LuaValue var2) {
        return this.call(var1, var2, NIL);
    }

    public LuaValue call(LuaValue var1, LuaValue var2, LuaValue var3) {
        return this.call(var1, var2, var3, NIL);
    }

    public Varargs invoke(Varargs var1) {
        return this.call(var1.arg1(), var1.arg(2), var1.arg(3), var1.arg(4));
    }
}