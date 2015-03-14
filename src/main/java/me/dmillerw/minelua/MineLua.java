package me.dmillerw.minelua;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.dmillerw.minelua.event.EventListener;
import me.dmillerw.minelua.lib.ExtensionFilter;
import me.dmillerw.minelua.lib.mapping.ObfuscationMapper;
import me.dmillerw.minelua.lua.LuaScript;

import java.io.File;
import java.util.List;

/**
 * @author dmillerw
 */
@Mod(modid = "MineLua", name = "MineLua", version = "%MOD_VERSION%")
public class MineLua {

    public static final ExtensionFilter LUA = new ExtensionFilter("lua");

    private static List<LuaScript> loadedScripts = Lists.newArrayList();
    private static File mScripts;
    private static EventListener mEventListener;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(ObfuscationMapper.mapMethod("net/minecraft/block/Block", "getBlockHardness", "(Lnet/minecraft/world/World;III)F"));

        /*
        mScripts = new File(event.getModConfigurationDirectory(), "MineLua/scripts");
        if (!mScripts.exists()) {
            mScripts.mkdirs();
        }

        mEventListener = new EventListener();
        MinecraftForge.EVENT_BUS.register(mEventListener);

        File[] files = mScripts.listFiles(LUA);

        if (files != null && files.length > 0) {
            for (File file : files) {
                CoreLogger.info("Loading Lua script: " + file.getName());

                LuaScript luaScript = new LuaScript(file);
                luaScript.call();

                loadedScripts.add(luaScript);
            }
        }

        */
    }
}
