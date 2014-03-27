package org.cvhu;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrunchPost {
    private String articleTitle;
    private String articleContent;
    private String articleUrl;
    private String companyName;
    private String companyWebsite;
    
    public CrunchPost(String title,String url) {
        this.articleTitle = title;
        this.articleUrl = url;
        parse();
    }
    
    public void parse() {
        Connection conn = Jsoup.connect(articleUrl);
        try {
            Document doc = conn.get();
            Element crunchbaseTag = doc.getElementsByClass("crunchbase-accordion").first();
            companyName = crunchbaseTag.getElementsByClass("card-title").first().text();
            Elements lis = crunchbaseTag.getElementsByClass("full");
            for (Element li : lis) {
                if (li.getElementsByClass("key").first().text().equals("Website")) {
                    companyWebsite = li.getElementsByClass("value").first().text();
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
    }
    
    public String getCsvString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getEscapedCsvString(companyName));
        sb.append(",");
        sb.append(getEscapedCsvString(companyWebsite));
        sb.append(",");
        sb.append(getEscapedCsvString(articleTitle));
        sb.append(",");
        sb.append(getEscapedCsvString(articleUrl));
        sb.append("\n");
        return sb.toString();
    }
    
    public String getEscapedCsvString(String input) {
        if (input == null) {
            input = "n/a";
        }
        return '"' + input.replace('"', '\'') + '"';
    }
}
