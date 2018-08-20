package io.github.zeleven.scallop.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuSubCategory {
    @SerializedName("created_at") private String createdAt;
    @SerializedName("icon") private String iconUrl;
    private String id;
    private String title;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
