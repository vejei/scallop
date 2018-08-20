package io.github.zeleven.scallop.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDu {
    private String content;
    private String cover;
    @SerializedName("created_at") private String createdAt;
    private boolean deleted;
    @SerializedName("published_at") private String publishedAt;
    private String raw;
    private XianDuSite site;
    private String title;
    private String uid;
    private String url;

    public String getContent() {
        return content;
    }

    public String getCover() {
        return cover;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getRaw() {
        return raw;
    }

    public XianDuSite getSite() {
        return site;
    }

    public String getTitle() {
        return title;
    }

    public String getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }
}
