package org.cvhu;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class AbstractPost {
    
    /**
     * Title of the given article.
     */
    protected String articleTitle;
    
    /**
     * Url of the given article.
     */
    protected String articleUrl;
    
    /**
     * Name of the subject matter company, set by parsing the article url.
     */
    protected String companyName;
    
    /**
     * Website url of the subject matter company, set by parsing the article url.
     */
    protected String companyWebsite;
    
    public AbstractPost(String title, String url) {
        setArticle(title, url);
    }

    public void setArticle(String title, String url) {
        this.articleTitle = title;
        this.articleUrl = url;
    }
    
    public void clear() {
        this.companyName = null;
        this.companyWebsite = null;
    }
    
    /**
     * Find the subject company and website using the default url.
     */
    public void parse() {
        Connection conn = Jsoup.connect(articleUrl);
        try {
            parse(conn.get());
        } catch (IOException e) {
            // Error receiving the HTTP response. Do nothing.
        }
    }
    
    /**
     * Implemented by the subclass to find out the subject matter company name and website.
     */
    abstract void parse(Document document);
    
    /**
     * Compile a CSV row and return it.
     * 
     * @return a string consists of comma-separated values of the instance properties.
     */
    public String getCsvString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getEscapedCsvString(companyName));
        sb.append(",");
        sb.append(getEscapedCsvString(companyWebsite));
        sb.append(",");
        sb.append(getEscapedCsvString(articleTitle));
        sb.append(",");
        sb.append(getEscapedCsvString(articleUrl));
        return sb.toString();
    }
    
    /**
     * Give the corresponding header of the CSV string.
     * 
     * @return CSV header.
     */
    public static String getCsvHeader() {
        return "Company Name, Company Website, Article Title, Article Url";
    }
    
    /**
     * Wrapper to return valid CSV string to escape special characters.
     * 
     * @param input A instance property.
     * @return A string that escapes '"', augments quotes to preserve commas, and returns 'n/a' if null.
     */
    public static String getEscapedCsvString(String input) {
        if (input == null) {
            input = "n/a";
        }
        return '"' + input.replace('"', '\'') + '"';
    }
}
