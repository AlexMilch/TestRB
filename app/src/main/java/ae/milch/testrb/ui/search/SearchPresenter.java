package ae.milch.testrb.ui.search;

import java.util.List;

import ae.milch.testrb.domain.ApiService;
import ae.milch.testrb.domain.NetworkModule;
import ae.milch.testrb.models.BookModel;
import ae.milch.testrb.models.BooksModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

class SearchPresenter {

    private static final String SEARCH_QUERY_PARAM_MASK = "search+%s";

    private final SearchView view;
    private final ApiService apiService;

    SearchPresenter(SearchView view) {
        this.view = view;
        this.apiService = new NetworkModule().createService();
    }

    void searchBook(String text) {
        apiService.searchBook(String.format(SEARCH_QUERY_PARAM_MASK, text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BooksModel, List<BookModel>>() {
                    @Override
                    public List<BookModel> apply(BooksModel booksModel) {
                        return convert(booksModel);
                    }
                })
                .subscribe(new Consumer<List<BookModel>>() {
                               @Override
                               public void accept(List<BookModel> book) {
                                   view.outputListBooks(book);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
    }

    private List<BookModel> convert(BooksModel booksModel) {
        return booksModel.getItems();
    }
}
