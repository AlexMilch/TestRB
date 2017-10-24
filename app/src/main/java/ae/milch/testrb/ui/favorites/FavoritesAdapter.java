package ae.milch.testrb.ui.favorites;

import android.net.Uri;
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

class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<Book> books;
    private FavoritesView view;

    FavoritesAdapter(FavoritesView view) {
        setHasStableIds(true);
        this.view = view;
        this.books = new ArrayList<>();
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_view, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoritesViewHolder holder, int position) {
        final Book book = books.get(position);

        holder.ivBook.setImageURI(Uri.parse(book.getSmallThumbnail()));
        holder.tvTitle.setText(book.getTitle());
        holder.authors.setText(book.getAuthors());
        holder.loadFragment.setText(book.getPreviewLink());
        holder.onFavorites.setImageResource(book.isFavorite()
                ? R.drawable.ic_star_black_24dp
                : R.drawable.ic_star_border_black_24dp);
        holder.onFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.onFavoriteClick(!book.isFavorite(), book.getId());
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return books.get(position).getId().hashCode();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    void initBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView ivBook;
        private TextView tvTitle;
        private TextView authors;
        private TextView loadFragment;
        private ImageView onFavorites;

        FavoritesViewHolder(View itemView) {
            super(itemView);
            ivBook = itemView.findViewById(R.id.iv_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            authors = itemView.findViewById(R.id.tv_authors);
            loadFragment = itemView.findViewById(R.id.tv_load_fragment);
            onFavorites = itemView.findViewById(R.id.btn_on_favorites);
        }
    }
}
