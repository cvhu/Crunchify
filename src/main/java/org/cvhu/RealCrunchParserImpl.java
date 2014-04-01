package org.cvhu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    public List<TechCrunchPost> run() {
        Connection conn = Jsoup.connect(url);
        ArrayList<TechCrunchPost> posts = new ArrayList<TechCrunchPost>();
        try {
            Document doc = conn.get();
            Elements titles = doc.getElementsByClass("post-title");
            for (Element title : titles) {
                TechCrunchPost post = new TechCrunchPost(title.text(), title.getElementsByTag("a").get(0).attr("href"));
                posts.add(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public void exportCsv(PrintWriter writer) {
        for (TechCrunchPost post : run()) {
            post.parse();
            writer.println(post.getCsvString());
        }
    }

}
