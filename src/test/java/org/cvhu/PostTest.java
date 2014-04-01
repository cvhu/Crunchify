package org.cvhu;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Iterator;

import junit.framework.TestCase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mockito.Mockito;

public class PostTest extends TestCase {
    
    private static String articleTitle;
    private static String articleUrl;
    private static String mockedName;
    private static String mockedWebsite;
    private static String expected;
    private static Document mockedDoc;
    private static Document realDoc;
    private static TechCrunchPost post;
    
    public void setUp() {
        articleTitle = "OneSpot Launches Platform For Turning Articles Into Ads, Raises $1.5M";
        articleUrl = "http://techcrunch.com/2012/12/03/onespot-content-marketing-relaunch/";
        mockedName = "OneSpot";
        mockedWebsite = "http://www.onespot.com";
        expected = String.format("\"%s\",\"%s\",\"%s\",\"%s\"", mockedName, mockedWebsite, articleTitle, articleUrl);
        post = new TechCrunchPost(articleTitle, articleUrl);
        
        Element mockedLi = mock(Element.class, Mockito.RETURNS_DEEP_STUBS);
        when(mockedLi.getElementsByClass("key").first().text()).thenReturn("Website");
        when(mockedLi.getElementsByClass("value").first().text()).thenReturn(mockedWebsite);
        
        Iterator<Element> mockedLiIterator = mock(Iterator.class);
        when(mockedLiIterator.hasNext()).thenReturn(true, false);
        when(mockedLiIterator.next()).thenReturn(mockedLi);
        
        Elements mockedLis = mock(Elements.class);
        when(mockedLis.iterator()).thenReturn(mockedLiIterator);
        
        Element mockedCrunchbase = mock(Element.class, Mockito.RETURNS_DEEP_STUBS);
        when(mockedCrunchbase.getElementsByClass("card-title").first().text()).thenReturn(mockedName);
        when(mockedCrunchbase.getElementsByClass("full")).thenReturn(mockedLis);
        
        mockedDoc = mock(Document.class, Mockito.RETURNS_DEEP_STUBS);
        when(mockedDoc.getElementsByClass("crunchbase-accordion").first()).thenReturn(mockedCrunchbase);
        
        try {
            realDoc = Jsoup.connect(articleUrl).get();
        } catch (IOException e) {
            // Error connecting to article url. Do nothing.
        }
    }
    
    public void tearDown() {
        post.clear();
    }
    
    public void testMockedParse() {
        post.parse(mockedDoc);
        assertEquals(expected, post.getCsvString());
    }
    
    public void testRealParse() {
        post.parse(realDoc);
        assertEquals(expected, post.getCsvString());
    }
    
    public void testClear() {
        post.clear();
        expected = String.format("\"n/a\",\"n/a\",\"%s\",\"%s\"", articleTitle, articleUrl);
        assertEquals(expected, post.getCsvString());
    }
    
    public void testEscapedCsvString() {
        assertEquals("\"n/a\"",AbstractPost.getEscapedCsvString(null));
        assertEquals("\"Company, Inc.\"",AbstractPost.getEscapedCsvString("Company, Inc."));
    }
}
