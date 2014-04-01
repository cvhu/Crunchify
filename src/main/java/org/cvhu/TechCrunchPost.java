package org.cvhu;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TechCrunchPost extends AbstractPost{

    /**
     * A flag used to avoid redundant parsing.
     */
    private boolean isParsed;
    
    public TechCrunchPost(String title, String url) {
        super(title, url);
    }
    
    /**
     * Find the subject company and website using the given document.
     */
    @Override
    public void parse(Document document) {
        if (isParsed) {
            return;
        }
        try {
            // Find the CrunchBase card div.
            Element crunchbaseTag = document.getElementsByClass("crunchbase-accordion").first();
            parseCompanyName(crunchbaseTag);
            parseCompanyWebsite(crunchbaseTag);
        } catch (NullPointerException e) {
            // Relevant fields can't be found. Do nothing.
        } finally {
            isParsed = true;
        }
    }
    
    /**
     * Find and set the company name.
     * 
     * @param crunchbaseTag The HTML tag containing the CrunchBase card.
     */
    public void parseCompanyName(Element crunchbaseTag) {
        // Find the company name in the CrunchBase card.
        companyName = crunchbaseTag.getElementsByClass("card-title").first().text();
    }
    
    /**
     * Find and set the company website.
     * 
     * @param crunchbaseTag The HTML tag containing the CrunchBase card.
     */
    public void parseCompanyWebsite(Element crunchbaseTag) {
        // Find the company website if exists. We have to go through all tags with class='full' because there's no useful selector. 
        Elements lis = crunchbaseTag.getElementsByClass("full");
        for (Element li : lis) {
            if (li.getElementsByClass("key").first().text().equals("Website")) {
                // Find a matching 'key' tag, so store its value as the company website. 
                companyWebsite = li.getElementsByClass("value").first().text();
                break;
            }
        }
    }
}
