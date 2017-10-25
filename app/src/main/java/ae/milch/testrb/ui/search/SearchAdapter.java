package ae.milch.testrb.ui.search;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.BookModel;
import ae.milch.testrb.models.ImageLinksBookModel;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.BooksViewHolder> {

    private List<BookModel> books;
    private SearchView view;
    private SearchPresenter presenter;

    SearchAdapter(SearchView view, SearchPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
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
        ImageLinksBookModel imageLinks = bookModel
                .getVolumeInfo()
                .getImageLinks();
        if (imageLinks != null && imageLinks.getSmallThumbnail() != null) {
            holder.ivBook.setImageURI(Uri.parse(imageLinks
                    .getSmallThumbnail()));
        } else {
            holder.ivBook.setImageResource(R.drawable.ic_broken_book);
        }
        holder.tvTitle.setText(bookModel.getVolumeInfo().getTitle());
        holder.authors.setText(getAllAuthors(bookModel));
        String linkFragment = bookModel.getVolumeInfo().getPreviewLink();
        holder.loadFragment.setText(Html.fromHtml("<a href=" + linkFragment + "><font color=#3366BB>Ссылка на загрузку фрагмента книги</font></a>"));
        holder.loadFragment.setMovementMethod(LinkMovementMethod.getInstance());
        holder.onFavorites.setImageResource(checkFavorite(bookModel.getId()));
        holder.onFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onFavoriteClick(bookModel);
            }
        });
    }

    private String getAllAuthors(BookModel book) {
        if (book.getVolumeInfo().getAuthors() == null) {
            return "нет автора";
        }
        int sizeAuthors = book.getVolumeInfo().getAuthors().size();

        String listAuthors = "";
        for (int i = 0; i < sizeAuthors; i++) {
            listAuthors = listAuthors + book.getVolumeInfo().getAuthors().get(i);
        }
        return listAuthors;
    }

    private int checkFavorite(String id) {
        return presenter.checkFavorite(id)
                ? R.drawable.ic_star
                : R.drawable.ic_star_border;
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
