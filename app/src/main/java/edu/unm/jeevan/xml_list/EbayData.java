package edu.unm.jeevan.xml_list;

import java.lang.Override;import java.lang.String; /**
 * Created by Jeevan on 2/27/2015.
 */
public class EbayData {

    private String id;
    private String title;
    private String dealurl;
    private String pricenow;
    private String imgUrl;
    private String savings;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDealurl() {
        return dealurl;
    }
    public void setDealurl(String dealurl) {
        this.dealurl = dealurl;
    }
    public String getPricenow() {
        return pricenow;
    }
    public void setPricenow(String pricenow) {
        this.pricenow = pricenow;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Override
    public String toString() {
        return "EbayData [title=" + title + ", dealurl=" + dealurl + ", pricenow="
                + pricenow + ", imgUrl=" + imgUrl + ",savings=" + savings + "]";
    }

}
