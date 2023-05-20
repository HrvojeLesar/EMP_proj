package org.foi.emp.hlesar;

import java.io.File;

public class DemoFile {
    public static File getDemoCurrentForecastXML() {
        return new File("exampleData/prognoza.xml");
    }

    public static File getDemoCurrentForecastXSD() {
        return new File("exampleData/prognoza.xsd");
    }

    public static File getDemoForecastXML() {
        return new File("exampleData/trodnevna_trosatna_prognoza.xml");
    }

    public static File getDemoForecastXSD() {
        return new File("exampleData/trodnevna_trosatna_prognoza.xsd");
    }
}
