package me.dmillerw.minelua.arg;

import com.google.common.collect.Maps;
import me.dmillerw.minelua.lib.Argument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author dmillerw
 */
public class ArgumentHandler {

    public static final Map<Class<?>, Class<? extends Argument<?>>> arguments = Maps.newHashMap();

    public static void loadArguments(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        Element element = doc.getDocumentElement();
        NodeList nodes = element.getElementsByTagName("argument");

        for (int i=0; i<nodes.getLength(); i++) {
            Node argument = nodes.item(i);
        }
    }
}
