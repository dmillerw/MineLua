package me.dmillerw.minelua.lib;

import org.luaj.vm2.LuaTable;

/**
 * @author dmillerw
 */
public abstract class Argument<B> extends LuaTable {

    protected B backingObject;

    public Argument(B backing) {
        super();
        this.backingObject = backing;
        fillTable();
    }

    public void fillTable() {

    }
}
