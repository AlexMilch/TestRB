package ae.milch.testrb.models;

import com.google.gson.annotations.SerializedName;

public class BookModel {
    @SerializedName("selfLink")
    private String selfLink;
    @SerializedName("id")
    private String id;
    @SerializedName("volumeInfo")
    private VolumeInfoBookModel volumeInfo;

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfoBookModel getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfoBookModel volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
