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
            Elements titles = doc.getElementsByClass("post-title");
            for (Element title : titles) {
                CrunchPost post = new CrunchPost(title.text(), title.getElementsByTag("a").get(0).attr("href"));
                System.out.print(post.getCsvString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
