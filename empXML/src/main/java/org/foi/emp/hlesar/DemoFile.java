package org.foi.emp.hlesar;

import java.io.File;

public class DemoFile {
    public static File openDemoXMLFile() {
        return new File("exampleData/prognoza.xml");
    }

    public static File openDemoXSDSchema() {
        return new File("exampleData/prognoza.xsd");
    }
}
