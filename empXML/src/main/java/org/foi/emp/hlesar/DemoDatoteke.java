package org.foi.emp.hlesar;

import java.io.File;

public class DemoDatoteke {
    public static File demoPrognozaXML() {
        return new File("demoPodatci/prognoza.xml");
    }

    public static File demoPrognozaXSD() {
        return new File("demoPodatci/prognoza.xsd");
    }

    public static File demoTrodnevnaPrognozaXML() {
        return new File("demoPodatci/trodnevna_trosatna_prognoza.xml");
    }

    public static File demoTrodnevnaPrognozaXSD() {
        return new File("demoPodatci/trodnevna_trosatna_prognoza.xsd");
    }
}
