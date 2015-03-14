package me.dmillerw.minelua.lib.coremod;

import cpw.mods.fml.relauncher.IFMLCallHook;
import me.dmillerw.minelua.lib.mapping.ObfuscationMapper;

import java.io.File;
import java.util.Map;

/**
 * @author dmillerw
 */
public class CallHook implements IFMLCallHook {

    @Override
    public void injectData(Map<String, Object> data) {
        File mcLocation = (File)data.get("mcLocation");
        File coremodLocation = (File)data.get("coremodLocation");
        String deobfuscationFileName = (String)data.get("deobfuscationFileName");
        boolean liveEnv = (Boolean)data.get("runtimeDeobfuscationEnabled");
        ObfuscationMapper.initialize(mcLocation, coremodLocation, deobfuscationFileName, liveEnv);
    }

    @Override
    public Void call() throws Exception {
        return null;
    }
}
