package org.foi.emp.hlesar.DOM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import org.foi.emp.hlesar.Forecast;
import org.foi.emp.hlesar.XSDValidator;

public class DOMCurrentWeather implements Forecast {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Document xmlDocument;

    public String getForecast() {
        Element root = this.xmlDocument.getDocumentElement();

        Element forecastDate = (Element) root.getElementsByTagName("DatumTermin").item(0);
        String date = forecastDate.getElementsByTagName("Datum").item(0).getTextContent();

        Element city;
        try {
            city = tryFindCity("Varaždin", root);
        } catch (Exception e) {
            System.err.println(e);
            return "";
        }

        String cityName = city.getElementsByTagName("GradIme").item(0).getTextContent();
        Element forecastData = (Element) city.getElementsByTagName("Podatci").item(0);
        double tempreture = Double.parseDouble(forecastData.getElementsByTagName("Temp").item(0).getTextContent());
        String humidity = forecastData.getElementsByTagName("Vlaga").item(0).getTextContent();
        String pressure = forecastData.getElementsByTagName("Tlak").item(0).getTextContent();
        String windDirection = forecastData.getElementsByTagName("VjetarSmjer").item(0).getTextContent();
        String windSpeed = forecastData.getElementsByTagName("VjetarBrzina").item(0).getTextContent();
        String forecast = forecastData.getElementsByTagName("Vrijeme").item(0).getTextContent();

        return String.format(
                "Prognoza za grad [%s] na datum [%s].\nProsječna temperatura: %.1f°C\nVlaga: %s\nTlak: %s\nSmjer vjetra: %s | Brzina vjetra: %s\nVrijeme: %s",
                cityName,
                date,
                tempreture,
                humidity,
                pressure,
                windDirection,
                windSpeed,
                forecast);
    }

    Element tryFindCity(String cityName, Element root) throws Exception {
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
        throw new Exception("City not found");
    }

    public DOMCurrentWeather(File xmlFile, File xsdFile)
            throws SAXException, IOException, ParserConfigurationException {
        this(xmlFile);
        XSDValidator.validate(xmlFile, xsdFile);
    }

    DOMCurrentWeather(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docBuilder = this.docFactory.newDocumentBuilder();
        this.xmlDocument = this.docBuilder.parse(xmlFile);
    }
}
