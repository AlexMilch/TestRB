package ae.milch.testrb.ui;

import java.util.List;

import ae.milch.testrb.domain.ApiService;
import ae.milch.testrb.domain.NetworkModule;
import ae.milch.testrb.models.BookModel;
import ae.milch.testrb.models.BooksModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter {

    private final MainActivityView view;

    public MainActivityPresenter(MainActivityView view) {
        this.view = view;
    }

    public void searchBook(String text) {
        ApiService apiService = new NetworkModule().createService();
        apiService.searchBook("search+" + text)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BooksModel, List<BookModel>>() {
                    @Override
                    public List<BookModel> apply(BooksModel booksModel) throws Exception {
                        return MainActivityPresenter.this.convert(booksModel);
                    }
                })
                .subscribe(new Consumer<List<BookModel>>() {
                               @Override
                               public void accept(List<BookModel> book) throws Exception {
                                   view.outputListBooks(book);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
    }

    private List<BookModel> convert(BooksModel booksModel) {
        return booksModel.getItems();
    }
}
