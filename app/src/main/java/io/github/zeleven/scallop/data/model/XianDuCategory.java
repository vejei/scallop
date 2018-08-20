package io.github.zeleven.scallop.data.model;

import com.google.gson.annotations.SerializedName;

public class XianDuCategory {
    @SerializedName("_id") private String id;
    @SerializedName("en_name") private String enName;
    private String name;
    private int rank;

    public String getId() {
        return id;
    }

    public String getEnName() {
        return enName;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
