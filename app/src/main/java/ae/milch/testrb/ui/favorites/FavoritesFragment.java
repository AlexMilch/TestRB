package ae.milch.testrb.ui.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.Book;

public class FavoritesFragment extends Fragment implements FavoritesView {

    private RecyclerView rvFavorites;
    private FavoritesAdapter favoritesAdapter;
    private FavoritesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvFavorites = view.findViewById(R.id.rv_favorites);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        favoritesAdapter = new FavoritesAdapter(this);
        rvFavorites.setAdapter(favoritesAdapter);

        presenter = new FavoritesPresenter(this);
        presenter.getFavoriteBooks();
    }

    @Override
    public void outputListBooks(List<Book> books) {
        favoritesAdapter.initBooks(books);
    }

    @Override
    public void onFavoriteClick(boolean favorite, String id) {
        presenter.changeFavorite(favorite, id);
    }

    public void updateList() {
        presenter.getFavoriteBooks();
    }
}
