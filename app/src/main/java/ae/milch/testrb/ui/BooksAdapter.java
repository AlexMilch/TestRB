package ae.milch.testrb.ui;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.BookModel;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private List<BookModel> books;

    public BooksAdapter() {
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
        BookModel book = getItem(position);
        if (book == null) {
            return;
        }
        holder.ivBook.setImageURI(Uri.parse(book.getVolumeInfo().getImageLinks().getSmallThumbnail()));
        holder.tvTitle.setText(book.getVolumeInfo().getTitle());
        holder.authors.setText(getAllAuthors(book));
        holder.loadFragment.setText(book.getVolumeInfo().getPreviewLink());
        //TODO сделать добавление в избранное
//        holder.onFavorites.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private String getAllAuthors(BookModel book) {
        String listAuthors = "";
        if (book.getVolumeInfo().getAuthors() == null){
            return listAuthors = "нет автора";
        }
        int sizeAuthors = book.getVolumeInfo().getAuthors().size();

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
        Button onFavorites;

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
