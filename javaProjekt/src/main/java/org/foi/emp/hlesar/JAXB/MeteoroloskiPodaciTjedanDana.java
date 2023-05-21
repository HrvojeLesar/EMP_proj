
package org.foi.emp.hlesar.JAXB;

import java.util.ArrayList;

import org.foi.emp.hlesar.IPrognoza;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AgroMeteoroloskiPodaci")
public class MeteoroloskiPodaciTjedanDana implements IPrognoza {
    @XmlElement(name = "Naslov")
    public String naslov;

    @XmlElementWrapper(name = "Podaci")
    @XmlElement(name = "Grad")
    public ArrayList<Grad> podaci;

    public String ispisPrognoze() {
        Grad vz = new Grad();
        for (Grad grad : this.podaci) {
            if (grad.gradIme.equals("Varaždin")) {
                vz = grad;
                break;
            }
        }
        return vz.toString();
    }
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

    @Override
    public String toString() {
        return String.format(
                "Grad: %s | Temperatura max: %s | Temperatura min: %s | Oborine: %smm | Vlaga max: %d | Vlaga min: %d",
                this.gradIme,
                this.tMax,
                this.tMin,
                this.obor,
                this.vlagaMax,
                this.vlagaMin);
    }
}
