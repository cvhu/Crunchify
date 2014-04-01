package org.cvhu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The main class to run the application.
 */
public class Main {
    public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException {
        Injector injector = Guice.createInjector(new CrunchModule());
        CrunchParser crunchParser = injector.getInstance(RealCrunchParserImpl.class);
        crunchParser.exportCsv(new PrintWriter(args[0] + ".csv", "UTF-8"));
    }
}
