package ae.milch.testrb.ui.search;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.Book;
import ae.milch.testrb.models.BookModel;
import io.realm.Realm;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.BooksViewHolder> {

    private List<BookModel> books;

    SearchAdapter() {
        this.books = new ArrayList<>();
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_view, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {
        final BookModel bookModel = getItem(position);
        if (bookModel == null) {
            return;
        }
        final String smallThumbnail = bookModel.getVolumeInfo().getImageLinks().getSmallThumbnail();
        holder.ivBook.setImageURI(Uri.parse(smallThumbnail));
        final String title = bookModel.getVolumeInfo().getTitle();
        holder.tvTitle.setText(title);
        final String allAuthors = getAllAuthors(bookModel);
        holder.authors.setText(allAuthors);
        final String previewLink = bookModel.getVolumeInfo().getPreviewLink();
        holder.loadFragment.setText(previewLink);
        holder.onFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                final Book book = realm.where(Book.class)
                        .equalTo("id", bookModel.getId())
                        .findFirst();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        if (book != null) {
                            book.setFavorite(!book.isFavorite());
                        } else {
                            Book bookNew = new Book();
                            bookNew.setFavorite(true);
                            bookNew.setAuthors(allAuthors);
                            bookNew.setSmallThumbnail(smallThumbnail);
                            bookNew.setTitle(title);
                            bookNew.setPreviewLink(previewLink);
                            bookNew.setId(bookModel.getId());
                            realm.copyToRealm(bookNew);
                        }
                    }
                });
                realm.close();
            }
        });
    }

    private String getAllAuthors(BookModel book) {
        if (book.getVolumeInfo().getAuthors() == null){
            return "нет автора";
        }
        int sizeAuthors = book.getVolumeInfo().getAuthors().size();

        String listAuthors = "";
        for (int i = 0; i < sizeAuthors; i++) {
            listAuthors = listAuthors + book.getVolumeInfo().getAuthors().get(i);
        }
        return listAuthors;
    }

    @Override
    public int getItemCount() {
        return (books == null) ? 0 : books.size();
    }

    private BookModel getItem(int position) {
        return books == null ? null : books.get(position);
    }

    void initBooks(List<BookModel> books) {
        this.books = books;
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView ivBook;
        TextView tvTitle;
        TextView authors;
        TextView loadFragment;
        ImageView onFavorites;

        BooksViewHolder(View itemView) {
            super(itemView);
            ivBook = itemView.findViewById(R.id.iv_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            authors = itemView.findViewById(R.id.tv_authors);
            loadFragment = itemView.findViewById(R.id.tv_load_fragment);
            onFavorites = itemView.findViewById(R.id.btn_on_favorites);
        }
    }
}
