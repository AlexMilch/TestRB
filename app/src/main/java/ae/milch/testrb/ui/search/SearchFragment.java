package ae.milch.testrb.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.BookModel;

public class SearchFragment extends Fragment implements SearchView {

    private EditText etSearch;
    private ImageView btnSearch;
    private RecyclerView rvSearch;

    private SearchAdapter searchAdapter;

    private SearchPresenter presenter = new SearchPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = view.findViewById(R.id.et_search);
        btnSearch = view.findViewById(R.id.btn_search);
        rvSearch = view.findViewById(R.id.rv_search);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        searchAdapter = new SearchAdapter(this, presenter);
        rvSearch.setAdapter(searchAdapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    searchBook(etSearch.getText().toString());
            }
        });
    }

    public void searchBook(String text) {
        if (!text.isEmpty()) {
            presenter.searchBook(text);
        }
    }

    @Override
    public void outputListBooks(List<BookModel> books) {
        searchAdapter.initBooks(books);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFavoriteClick(BookModel bookModel) {
        presenter.changeFavorite(bookModel);
        searchAdapter.notifyDataSetChanged();
    }

    public void updateList() {
        searchAdapter.notifyDataSetChanged();
    }
}
