package org.cvhu;

import com.google.inject.Inject;

public class CrunchService {
    private CrunchParser parser;
    
    @Inject
    public CrunchService(CrunchParser parser) {
        this.parser = parser;
    }
    
    public void parse() {
        parser.parse();
    }
}
