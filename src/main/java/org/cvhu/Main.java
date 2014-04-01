package org.cvhu;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The main class to run the application.
 */
public class Main {
    public static void main( String[] args ) {
        Injector injector = Guice.createInjector(new CrunchModule());
        CrunchParser crunchParser = injector.getInstance(RealCrunchParserImpl.class);
        crunchParser.exportCsv();
    }
}
