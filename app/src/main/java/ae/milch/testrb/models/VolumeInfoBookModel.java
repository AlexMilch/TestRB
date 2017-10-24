package ae.milch.testrb.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VolumeInfoBookModel {

    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private ArrayList<String> authors;
    @SerializedName("imageLinks")
    private ImageLinksBookModel imageLinks;
    @SerializedName("description")
    private String description;
    @SerializedName("previewLink")
    private String previewLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ImageLinksBookModel getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinksBookModel imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }
}
