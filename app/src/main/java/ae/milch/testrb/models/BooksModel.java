package ae.milch.testrb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksModel {
    @SerializedName("totalItems")
    private int totalItems;
    @SerializedName("items")
    private List<BookModel> items;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<BookModel> getItems() {
        return items;
    }

    public void setItems(List<BookModel> items) {
        this.items = items;
    }
}
