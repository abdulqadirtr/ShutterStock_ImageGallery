package app.easylink.shuterstockimages.shutterRemote.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import app.easylink.shuterstockimages.shutterRemote.models.ImageAssets;
import app.easylink.shuterstockimages.shutterRemote.models.ImageContributor;

public class MyShutterImage implements Serializable {

    @SerializedName("id") private String id;
    @SerializedName("aspect") private Double aspect;
    @SerializedName("assets") private ImageAssets assets;
    @SerializedName("contributor") private ImageContributor imageContributor;
    @SerializedName("description") private String description;
    @SerializedName("image_type") private String imageType;
    @SerializedName("media_type") private String mediaType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAspect() {
        return aspect;
    }

    public void setAspect(Double aspect) {
        this.aspect = aspect;
    }

    public ImageAssets getAssets() {
        return assets;
    }

    public void setAssets(ImageAssets assets) {
        this.assets = assets;
    }

    public ImageContributor getContributor() {
        return imageContributor;
    }

    public void setContributor(ImageContributor contributor) {
        this.imageContributor = contributor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

}
