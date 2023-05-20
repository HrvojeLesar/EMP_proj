package org.foi.emp.hlesar;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class App {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File input = DemoFile.openDemoXMLFile();
            Document doc = builder.parse(input);
            Element root = doc.getDocumentElement();

            NodeList childNodes = root.getChildNodes();
            int len = childNodes.getLength();
            for (int i = 0; i < len; i++) {
                Node node = childNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    System.out.println(e.getNodeName() + ": " + e.getTextContent());
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class DemoFile {
    public static File openDemoXMLFile() {
        File demoFile = new File("racun.xml");
        return demoFile;
    }

    public static File openDemoXSDSchema() {
        File demoFile = new File("racun_shema.xsd");
        return demoFile;
    }
}

class DOMDemo {
    public DOMDemo() throws ParserConfigurationException, IOException, SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(DemoFile.openDemoXSDSchema());
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(DemoFile.openDemoXMLFile()));
    }
}
