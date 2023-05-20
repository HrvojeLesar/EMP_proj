package org.foi.emp.hlesar.DOM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import org.foi.emp.hlesar.IPrognoza;
import org.foi.emp.hlesar.XSDValidator;

public class DOMPrognoza implements IPrognoza {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Document xmlDocument;

    public String ispisPrognoze() {
        Element korijenskiElement = this.xmlDocument.getDocumentElement();

        Element prognozaDatum = (Element) korijenskiElement.getElementsByTagName("DatumTermin").item(0);
        String datum = prognozaDatum.getElementsByTagName("Datum").item(0).getTextContent();

        Element grad;
        try {
            grad = pronadiGrad("Varaždin", korijenskiElement);
        } catch (Exception e) {
            System.err.println(e);
            return "";
        }

        String imeGrada = grad.getElementsByTagName("GradIme").item(0).getTextContent();
        Element podatciElement = (Element) grad.getElementsByTagName("Podatci").item(0);
        double temperatura = Double.parseDouble(podatciElement.getElementsByTagName("Temp").item(0).getTextContent());
        String vlaga = podatciElement.getElementsByTagName("Vlaga").item(0).getTextContent();
        String tlak = podatciElement.getElementsByTagName("Tlak").item(0).getTextContent();
        String vjetarSmejr = podatciElement.getElementsByTagName("VjetarSmjer").item(0).getTextContent();
        String vjetarBrzina = podatciElement.getElementsByTagName("VjetarBrzina").item(0).getTextContent();
        String vrijeme = podatciElement.getElementsByTagName("Vrijeme").item(0).getTextContent();

        return String.format(
                "Prognoza za grad [%s] na datum [%s].\nProsječna temperatura: %.1f°C\nVlaga: %s\nTlak: %s\nSmjer vjetra: %s | Brzina vjetra: %s\nVrijeme: %s",
                imeGrada,
                datum,
                temperatura,
                vlaga,
                tlak,
                vjetarSmejr,
                vjetarBrzina,
                vrijeme);
    }

    Element pronadiGrad(String cityName, Element root) throws Exception {
        NodeList cityNodes = root.getElementsByTagName("Grad");
        int len = cityNodes.getLength();
        for (int i = 0; i < len; i++) {
            Node cityNode = cityNodes.item(i);
            if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                Element cityElement = (Element) cityNode;
                if (cityElement.getElementsByTagName("GradIme").item(0).getTextContent().equals(cityName)) {
                    return cityElement;
                }
            }
        }
        throw new Exception("Grad nije bio pronađen");
    }

    public DOMPrognoza(File xmlFile, File xsdFile)
            throws SAXException, IOException, ParserConfigurationException {
        this(xmlFile);
        XSDValidator.validate(xmlFile, xsdFile);
    }

    DOMPrognoza(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = this.docFactory.newDocumentBuilder();
        this.xmlDocument = this.docBuilder.parse(xmlFile);
    }
}
