package org.foi.emp.hlesar;

import java.io.IOException;

import javax.xml.parsers.*;

import org.foi.emp.hlesar.DOM.DOMPrognoza;
import org.foi.emp.hlesar.JAXB.MeteoroloskiPodaciTjedanDana;
import org.foi.emp.hlesar.SAX.SAXPrognoza;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class App {
    public static void main(String[] args) {
        try {
            App.validacijaUlaznihDatoteka();

            DOMPrognoza dom = new DOMPrognoza(DemoDatoteke.demoPrognozaXML(),
                    DemoDatoteke.demoPrognozaXSD());
            System.out.println("Jednodnevna prognoza za Varaždin:");
            System.out.println(dom.ispisPrognoze());
            System.out.println("\n");

            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();
            SAXPrognoza prognoza = new SAXPrognoza();
            saxParser.parse(DemoDatoteke.demoTrodnevnaPrognozaXML(), prognoza);
            System.out.println("Trodnevno trosatna prognoza za Varaždin:");
            System.out.println(prognoza.ispisPrognoze());
            System.out.println("\n");

            JAXBContext jaxbContext = JAXBContext.newInstance(MeteoroloskiPodaciTjedanDana.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            MeteoroloskiPodaciTjedanDana meteoroloskiPodaci = (MeteoroloskiPodaciTjedanDana) unmarshaller
                    .unmarshal(DemoDatoteke.demoSedmodnevniPodaciXML());
            System.out.println("Meteorološki podaci za Varaždin za razdoblje 14.05.2023 do 21.05.2023 u 8 h:");
            System.out.println(meteoroloskiPodaci.ispisPrognoze());
            System.out.println("\n");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void validacijaUlaznihDatoteka() throws SAXException, IOException {
        XSDValidator.validiraj(DemoDatoteke.demoPrognozaXML(), DemoDatoteke.demoPrognozaXSD());
        XSDValidator.validiraj(DemoDatoteke.demoSedmodnevniPodaciXML(), DemoDatoteke.demoSedmodnevniPodaciXSD());
        XSDValidator.validiraj(DemoDatoteke.demoTrodnevnaPrognozaXML(), DemoDatoteke.demoTrodnevnaPrognozaXSD());
    }
}
