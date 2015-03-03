package edu.unm.jeevan.xml_list;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

public class SitesXmlPullParser {

    static final String KEY_ITEM = "Item";
    static final String KEY_TITLE = "Title";
    static final String KEY_Deal_URL = "DealURL";
    static final String KEY_PRICE_NOW = "ConvertedCurrentPrice";
    static final String KEY_IMAGE_URL = "SmallPictureURL";
    static final String KEY_SAVINGS = "SavingsRate";

    public static List<EbayData> getEbayDataFromFile(Context ctx) {

        // List of EbayData that we will return
        List<EbayData> EbayData;
        EbayData = new ArrayList<EbayData>();

        /* temp holder for current EbayData while parsing */
        EbayData curEbayData = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("EbayData.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equals(KEY_ITEM)) {
                            // If we are starting a new <site> block we need
                            //a new EbayData object to represent it
                            curEbayData = new EbayData();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equals(KEY_ITEM)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            EbayData.add(curEbayData);
                        } else if (tagname.equals(KEY_TITLE)) {
                            // if </name> use setName() on curSite
                            curEbayData.setTitle(curText);
                        } else if (tagname.equals(KEY_IMAGE_URL)) {
                            // if </link> use setLink() on curSite
                            curEbayData.setImgUrl(curText);
                        } else if (tagname.equals(KEY_Deal_URL)) {
                            // if </about> use setAbout() on curSite
                            curEbayData.setDealurl(curText);
                        } else if (tagname.equals(KEY_PRICE_NOW)) {
                            // if </image> use setImgUrl() on curSite
                            curEbayData.setPricenow(curText);
                        } else if (tagname.equals(KEY_SAVINGS)) {
                            // if </image> use setImgUrl() on curSite
                            curEbayData.setSavings(curText);
                        }

                        break;
                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return the populated list.
        return EbayData;
    }
}
