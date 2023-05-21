package org.foi.emp.hlesar.SAX;

import java.util.ArrayList;

import org.foi.emp.hlesar.IPrognoza;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPrognoza extends DefaultHandler implements IPrognoza {

    Izmjena izmjena;
    ArrayList<Grad> gradovi = new ArrayList<Grad>();

    String trenutnaVrijednost;
    Grad trenutniGrad;
    Dan trenutniDan;

    @Override
    public String ispisPrognoze() {
        Grad vz = new Grad();
        for (Grad grad : this.gradovi) {
            if (grad.attrIme.equals("Varazdin")) {
                vz = grad;
                break;
            }
        }
        return vz.toString();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "izmjena": {
                this.izmjena = new Izmjena();
                this.izmjena.attrRun = Integer.parseInt(attributes.getValue("run"));
                break;
            }
            case "grad": {
                this.trenutniGrad = new Grad();
                this.trenutniGrad.attrIme = attributes.getValue("ime");
                this.trenutniGrad.attrCode = attributes.getValue("code");
                break;
            }
            case "dan": {
                this.trenutniDan = new Dan();
                this.trenutniDan.attrDatum = attributes.getValue("datum");
                this.trenutniDan.attrDtj = attributes.getValue("dtj");
                this.trenutniDan.attrSat = Integer.parseInt(attributes.getValue("sat"));
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int lenght) {
        this.trenutnaVrijednost = new String(ch, start, lenght);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "izmjena": {
                this.izmjena.vrijednost = this.trenutnaVrijednost;
                break;
            }
            case "t_2m": {
                this.trenutniDan.t2m = Integer.parseInt(this.trenutnaVrijednost);
                break;
            }
            case "simbol": {
                this.trenutniDan.simbol = this.trenutnaVrijednost;
                break;
            }
            case "vjetar": {
                this.trenutniDan.vjetar = this.trenutnaVrijednost;
                break;
            }
            case "oborina": {
                this.trenutniDan.oborina = Double.parseDouble(this.trenutnaVrijednost);
                break;
            }
            case "dan": {
                this.trenutniGrad.dan.add(this.trenutniDan);
                break;
            }
            case "grad": {
                gradovi.add(this.trenutniGrad);
                break;
            }
        }
    }
}

class Izmjena {
    int attrRun;
    String vrijednost;

    @Override
    public String toString() {
        return String.format("Izmjena | Run: %d | Vrijednost: %s", this.attrRun, this.vrijednost);
    }
}

class Dan {
    String attrDatum;
    String attrDtj;
    int attrSat;
    int t2m;
    String simbol;
    String vjetar;
    double oborina;

    @Override
    public String toString() {
        return String.format("Dan | Datum: %s | Dtj: %s | Sat: %d | T_2m: %d | Simbol: %s | Vjetar: %s | Oborina: %f",
                this.attrDatum,
                this.attrDtj,
                this.attrSat,
                this.t2m,
                this.simbol,
                this.vjetar,
                this.oborina);
    }
}

class Grad {
    String attrIme;
    String attrCode;
    ArrayList<Dan> dan = new ArrayList<Dan>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Dan d : this.dan) {
            builder.append(d.toString() + "\n");
        }
        return String.format("Grad | Ime: %s | Code: %s | Dani: %s", this.attrIme, this.attrCode, builder.toString());
    }
}
