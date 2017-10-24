package ae.milch.testrb.models;

import com.google.gson.annotations.SerializedName;

public class BookModel {
    @SerializedName("selfLink")
    String selfLink;
    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private String authors;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("publishedDate")
    private int publishedDate;
    @SerializedName("description")
    private String description;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(int publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
