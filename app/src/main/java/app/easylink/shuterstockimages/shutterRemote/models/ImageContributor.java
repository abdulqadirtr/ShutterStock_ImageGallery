package app.easylink.shuterstockimages.shutterRemote.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageContributor implements Serializable {
    @SerializedName("id") private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
