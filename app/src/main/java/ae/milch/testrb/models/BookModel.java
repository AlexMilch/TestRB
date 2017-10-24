package ae.milch.testrb.models;

import com.google.gson.annotations.SerializedName;

public class BookModel {
    @SerializedName("selfLink")
    String selfLink;
    @SerializedName("volumeInfo")
    private VolumeInfoBookModel volumeInfo;

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfoBookModel getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfoBookModel volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
