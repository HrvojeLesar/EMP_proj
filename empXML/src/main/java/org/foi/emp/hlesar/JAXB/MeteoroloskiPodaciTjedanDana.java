
package org.foi.emp.hlesar.JAXB;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AgroMeteoroloskiPodaci")
public class MeteoroloskiPodaciTjedanDana {
    @XmlElement(name = "Naslov")
    public String naslov;

    @XmlElementWrapper(name = "Podaci")
    @XmlElement(name = "Grad")
    public ArrayList<Grad> podaci;
}

class Grad {
    @XmlElement(name = "GradIme")
    String gradIme;

    @XmlElement(name = "Tmax")
    String tMax;

    @XmlElement(name = "Tmin")
    String tMin;

    @XmlElement(name = "Tmin5")
    String tMin5;

    @XmlElement(name = "Obor")
    String obor;

    @XmlElement(name = "Snijeg")
    String snijeg;

    @XmlElement(name = "VlagaMax")
    int vlagaMax;

    @XmlElement(name = "VlagaMin")
    int vlagaMin;

    @XmlElement(name = "Sunce")
    String sunce;

    @XmlElement(name = "Tna5Max")
    String tna5Max;

    @XmlElement(name = "Tna5Min")
    String tna5Min;

    @XmlElement(name = "Tna20Max")
    String tna20Max;

    @XmlElement(name = "Tna20Min")
    String tna20Min;
}
