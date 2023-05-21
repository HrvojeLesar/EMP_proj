package org.foi.emp.hlesar;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.foi.emp.hlesar.DOM.DOMPrognoza;
import org.foi.emp.hlesar.JAXB.MeteoroloskiPodaciTjedanDana;
import org.foi.emp.hlesar.SAX.SAXPrognoza;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class App {
    public static void main(String[] args) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DOMPrognoza dom = new DOMPrognoza(DemoDatoteke.demoPrognozaXML(),
                    DemoDatoteke.demoPrognozaXSD());
            System.out.println(dom.ispisPrognoze());

            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();
            SAXPrognoza prognoza = new SAXPrognoza();
            saxParser.parse(DemoDatoteke.demoTrodnevnaPrognozaXML(), prognoza);

            // System.out.println(handler.ispisPrognoze());

            JAXBContext jaxbContext = JAXBContext.newInstance(MeteoroloskiPodaciTjedanDana.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            MeteoroloskiPodaciTjedanDana meteoroloskiPodaci = (MeteoroloskiPodaciTjedanDana) unmarshaller
                    .unmarshal(DemoDatoteke.demoSedmodnevniPodaciXML());

            // DocumentBuilder builder = factory.newDocumentBuilder();
            // File input = DemoFile.openDemoXMLFile();
            // Document doc = builder.parse(input);
            // Element root = doc.getDocumentElement();
            //
            // NodeList childNodes = root.getChildNodes();
            // int len = childNodes.getLength();
            // for (int i = 0; i < len; i++) {
            // Node node = childNodes.item(i);
            //
            // if (node.getNodeType() == Node.ELEMENT_NODE) {
            // Element e = (Element) node;
            // System.out.println(e.getNodeName() + ": " + e.getTextContent());
            // }
            // }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
