package ae.milch.testrb.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import ae.milch.testrb.R;
import ae.milch.testrb.models.BookModel;

public class SearchFragment extends Fragment implements MainActivityView{

    private EditText etSearch;
    private Button btnSearch;

    private MainActivityPresenter presenter = new MainActivityPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = view.findViewById(R.id.et_search);
        btnSearch = view.findViewById(R.id.btn_search);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBook(etSearch.getText().toString());
            }
        });
    }

    public void searchBook(String text) {
        presenter.searchBook(text);
    }

    @Override
    public void outputListBooks(List<BookModel> bookModel) {
        etSearch.setText(bookModel.get(1).getTitle());
    }
}
