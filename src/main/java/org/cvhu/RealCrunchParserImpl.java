package org.cvhu;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.inject.Inject;

public class RealCrunchParserImpl implements CrunchParser {
    private String url;
    
    @Inject
    public RealCrunchParserImpl(@CrunchUrl String url) {
        this.url = url;
    }

    @Override
    public List<CrunchPost> parse() {
        Connection conn = Jsoup.connect(url);
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
        return null;
    }

    @Override
    public void exportCsv() {
        // TODO Auto-generated method stub

    }

}
