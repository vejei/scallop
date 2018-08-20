package io.github.zeleven.scallop.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuSite {
    @SerializedName("cat_cn") private String catCN;
    @SerializedName("cat_en") private String catEN;
    private String desc;
    @SerializedName("feed_id") private String feedId;
    private String icon;
    private String id;
    private String name;
    private int subscribers;
    private String type;
    private String url;

    public String getCatCN() {
        return catCN;
    }

    public String getCatEN() {
        return catEN;
    }

    public String getDesc() {
        return desc;
    }

    public String getFeedId() {
        return feedId;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
