package org.cvhu;

import junit.framework.TestCase;

public class CrunchPostTest extends TestCase {
    
    public void testEscapedCsvString() {
        CrunchPost post = new CrunchPost("\"company name\"", "http://companyname.com");
        assertEquals("\"n/a\",", post.getCsvString());
    }
}
