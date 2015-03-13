package dmillerw.minelua.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author dmillerw
 */
public class CoreLogger {

    private static final Logger LOGGER = LogManager.getLogger("MineLua");

    public static void info(String prefix, String msg) {
        LOGGER.info("[" + prefix + "] " + msg);
    }

    public static void warn(String prefix, String msg) {
        LOGGER.warn("[" + prefix + "] " + msg);
    }

    public static void debug(String prefix, String msg) {
        LOGGER.debug("[" + prefix + "] " + msg);
    }

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }

    public static void debug(String msg) {
        LOGGER.debug(msg);
    }
}
