package org.cvhu;

import com.google.inject.AbstractModule;


public class CrunchModule extends AbstractModule {

    @Override
    protected void configure() {
        // TODO Auto-generated method stub
        bind(CrunchParser.class).to(RealCrunchParserImpl.class);
        
        bind(String.class).annotatedWith(CrunchUrl.class).toInstance("http://techcrunch.com");
    }

}
