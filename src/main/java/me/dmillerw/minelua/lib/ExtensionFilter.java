package me.dmillerw.minelua.lib;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dmillerw
 */
public class ExtensionFilter implements FilenameFilter {

    private String filter;

    public ExtensionFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(filter);
    }
}
