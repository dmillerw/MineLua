package me.dmillerw.minelua.event;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event;
import me.dmillerw.minelua.lib.EventArgument;
import org.luaj.vm2.LuaFunction;

import java.util.Map;
import java.util.Set;

/**
 * @author dmillerw
 */
public class EventHandler {

    private static HashBiMap<String, Class> eventClassMap = HashBiMap.create();
    public static Map<String, Set<LuaFunction>> eventHandlers = Maps.newHashMap();

    private static String addClass(Class cls) {
        String name = cls.getName().substring(cls.getName().lastIndexOf(".") + 1);
        name = name.replace("Event", "");
        name = name.replace("event", "");

        StringBuilder tag = new StringBuilder();

        boolean lastLower = false;
        for (char c : name.toCharArray()) {
            if (c == '$') {
                c = '#';
                lastLower = false;
                tag.append(c);
            } else if (isSymbol(c)) {
                lastLower = false;
                tag.append(c);
            }  else if (Character.isUpperCase(c)) {
                if (lastLower) {
                    lastLower = false;
                    tag.append("_");
                }
                tag.append(Character.toLowerCase(c));
            } else {
                lastLower = true;
                tag.append(c);
            }
        }

        String key = tag.toString();
        eventClassMap.put(key, cls);
        return key;
    }

    private static boolean isSymbol(char c) {
        return c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' ||
               c == '(' || c == ')' || c == '_' || c == '-' || c == '+' || c == '=';
    }

    public static void registerCallback(String event, LuaFunction function) {
        if (function.narg() != 1) {
            //TODO better handling
            System.out.println("Failed to register callback for '" + event + "'. Function has more or less than one argument!");
            return;
        }

        Set<LuaFunction> set = eventHandlers.get(event);
        if (set == null)
            set = Sets.newHashSet();

        set.add(function);

        eventHandlers.put(event, set);
    }

    public static void fireEvent(Class<? extends Event> event, EventArgument<?> argument) {
        String key = eventClassMap.inverse().get(event);
        if (key == null || key.isEmpty()) {
            key = addClass(event);
        }
        fireEvent(key, argument);
    }

    public static void fireEvent(String event, EventArgument<?> argument) {
        Set<LuaFunction> set = eventHandlers.get(event);
        if (set != null) {
            for (LuaFunction function : set) {
                function.invoke(argument);
            }
        }
    }
}
