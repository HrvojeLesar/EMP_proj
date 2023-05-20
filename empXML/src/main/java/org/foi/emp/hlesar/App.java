package org.foi.emp.hlesar;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.foi.emp.hlesar.DOM.DOMCurrentWeather;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class App {
    public static void main(String[] args) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DOMCurrentWeather dom = new DOMCurrentWeather(DemoFile.openDemoXMLFile(), DemoFile.openDemoXSDSchema());
            System.out.println(dom.getForecast());

            // DocumentBuilder builder = factory.newDocumentBuilder();
            // File input = DemoFile.openDemoXMLFile();
            // Document doc = builder.parse(input);
            // Element root = doc.getDocumentElement();
            //
            // NodeList childNodes = root.getChildNodes();
            // int len = childNodes.getLength();
            // for (int i = 0; i < len; i++) {
            //     Node node = childNodes.item(i);
            //
            //     if (node.getNodeType() == Node.ELEMENT_NODE) {
            //         Element e = (Element) node;
            //         System.out.println(e.getNodeName() + ": " + e.getTextContent());
            //     }
            // }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
