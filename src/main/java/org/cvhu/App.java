package org.cvhu;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Connection conn = Jsoup.connect("http://techcrunch.com");
        try {
            Document doc = conn.get();
            System.out.println("Start");
//            System.out.println(doc.html());
            Elements titles = doc.getElementsByClass("post-title");
            System.out.println(titles.size());
            for (Element title : titles) {
                System.out.println(title.text());
            }
            System.out.println("Finished");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
