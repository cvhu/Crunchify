package org.cvhu;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Injector injector = Guice.createInjector(new CrunchModule());
        CrunchService crunchService = injector.getInstance(CrunchService.class);
        crunchService.parse();
    }
}
