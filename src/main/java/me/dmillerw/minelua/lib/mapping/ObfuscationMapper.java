package me.dmillerw.minelua.lib.mapping;

import com.google.common.base.Charsets;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.io.CharSource;
import cpw.mods.fml.common.asm.transformers.deobf.LZMAInputSupplier;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author dmillerw
 */
public class ObfuscationMapper {

    private static final String CLASS = "CL";
    private static final String FIELD = "FD";
    private static final String METHOD = "MD";

    private static HashBiMap<String, String> classes = HashBiMap.create();
    private static HashBiMap<FieldMapping, FieldMapping> fields = HashBiMap.create();
    private static HashBiMap<MethodMapping, MethodMapping> methods = HashBiMap.create();

    public static void initialize(File mcLocation, File coremodLocation, String deobfuscationFileName, boolean liveEnv) {
        Map<FieldMapping, FieldMapping> tempFields = Maps.newHashMap();
        Map<MethodMapping, MethodMapping> tempMethods = Maps.newHashMap();

        try {
            // We parse the SRG file first, as that contains the actual OBF to SRG mappings
            InputStream classData = ObfuscationMapper.class.getResourceAsStream(deobfuscationFileName);
            LZMAInputSupplier zis = new LZMAInputSupplier(classData);
            CharSource srgSource = zis.asCharSource(Charsets.UTF_8);
            List<String> srgList = srgSource.readLines();

            for (String line : srgList) {
                String type = line.substring(0, line.indexOf(":"));
                if (CLASS.equals(type)) {
                    String[] parts = line.substring(line.indexOf(":") + 2).split(" ");
                    classes.put(parts[1], parts[0]); // Deobf is key, obf is value
                } else if (FIELD.equals(type)) {
                    String[] parts = line.substring(line.indexOf(":") + 2).split(" ");
                    String obf = parts[0];
                    String deobf = parts[1];
                    String[] obfParts = new String[2];
                    String[] deobfParts = new String[2];

                    obfParts[0] = obf.substring(0, obf.lastIndexOf("/")); // type
                    obfParts[1] = obf.substring(obf.lastIndexOf("/") + 1); // field
                    deobfParts[0] = deobf.substring(0, deobf.lastIndexOf("/")); // type
                    deobfParts[1] = deobf.substring(deobf.lastIndexOf("/") + 1); // field

                    FieldMapping obfMapping = new FieldMapping(obfParts[0], obfParts[1]);
                    FieldMapping deobfMapping = new FieldMapping(deobfParts[0], deobfParts[1]);

                    tempFields.put(deobfMapping, obfMapping);
                } else if (METHOD.equals(type)) {
                    String[] parts = line.substring(line.indexOf(":") + 2).split(" ");

                    String obf = parts[0];
                    String obfDesc = parts[1];
                    String deobf = parts[2];
                    String deobfDesc = parts[3];

                    String obfOwner = obf.substring(0, obf.lastIndexOf("/"));
                    String obfName = obf.substring(obf.lastIndexOf("/") + 1);
                    String deobfOwner = deobf.substring(0, deobf.lastIndexOf("/"));
                    String deobfName = deobf.substring(deobf.lastIndexOf("/") + 1);

                    MethodMapping obfMapping = new MethodMapping(obfOwner, obfName, obfDesc);
                    MethodMapping deobfMapping = new MethodMapping(deobfOwner, deobfName, deobfDesc);

                    tempMethods.put(deobfMapping, obfMapping);
                }
            }

            // From there, we go and parse through the included .csv files to get the DEOBF mappings
            InputStream fieldsInputStream = ObfuscationMapper.class.getResourceAsStream("fields.csv");
            List<String> lines = IOUtils.readLines(fieldsInputStream);

            for (String line : lines) {
                if (line.startsWith("field")) {
                    String[] parts = line.split(",");
                    String srg = parts[0];
                    String deobf = parts[1];

                    for (Map.Entry<FieldMapping, FieldMapping> entry : tempFields.entrySet()) {
                        if (entry.getKey().name.equals(srg)) {
                            fields.put(new FieldMapping(entry.getKey().owner, deobf), entry.getValue());
                        }
                    }
                }
            }

            InputStream methodsInputStream = ObfuscationMapper.class.getResourceAsStream("methods.csv");
            lines = IOUtils.readLines(methodsInputStream);

            for (String line : lines) {
                if (line.startsWith("func")) {
                    String[] parts = line.split(",");
                    String srg = parts[0];
                    String deobf = parts[1];

                    for (Map.Entry<MethodMapping, MethodMapping> entry : tempMethods.entrySet()) {
                        if (entry.getKey().name.equals(srg)) {
                            methods.put(new MethodMapping(entry.getKey().owner, deobf, entry.getKey().desc), entry.getValue());
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String mapType(String type) {
        String result = classes.get(type);
        return result != null ? result : type;
    }

    public static String mapField(String owner, String field) {
        FieldMapping fieldMapping = fields.get(new FieldMapping(owner, field));
        return fieldMapping != null ? fieldMapping.name : field;
    }

    public static String mapMethod(String owner, String method, String desc) {
        MethodMapping methodMapping = methods.get(new MethodMapping(owner, method, desc));
        return methodMapping != null ? methodMapping.name : method;
    }

    public static String unmapType(String type) {
        String result = classes.inverse().get(type);
        return result != null ? result : type;
    }

    public static String unmapField(String owner, String field) {
        FieldMapping fieldMapping = fields.inverse().get(new FieldMapping(owner, field));
        return fieldMapping != null ? fieldMapping.name : field;
    }

    public static String unmapMethod(String owner, String method, String desc) {
        MethodMapping methodMapping = methods.inverse().get(new MethodMapping(owner, method, desc));
        return methodMapping != null ? methodMapping.name : method;
    }
}
