package ae.milch.testrb.ui.search;

import android.support.annotation.NonNull;

import java.util.List;

import ae.milch.testrb.domain.ApiService;
import ae.milch.testrb.domain.NetworkModule;
import ae.milch.testrb.models.Book;
import ae.milch.testrb.models.BookModel;
import ae.milch.testrb.models.BooksModel;
import ae.milch.testrb.models.ImageLinksBookModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

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

    public void changeFavorite(final BookModel bookModel) {
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
                    bookNew.setAuthors(getAllAuthors(bookModel));
                    ImageLinksBookModel imageLinks = bookModel.getVolumeInfo().getImageLinks();
                    if (imageLinks != null) {
                        bookNew.setSmallThumbnail(imageLinks.getSmallThumbnail());
                    }
                    bookNew.setTitle(bookModel.getVolumeInfo().getTitle());
                    bookNew.setPreviewLink(bookModel.getVolumeInfo().getPreviewLink());
                    bookNew.setId(bookModel.getId());
                    realm.copyToRealm(bookNew);
                }
            }
        });
        realm.close();
    }

    public boolean checkFavorite(String id) {
        Realm realm = Realm.getDefaultInstance();
        Book book = realm.where(Book.class)
                .equalTo("id", id)
                .findFirst();
        boolean isFavorite = book != null && book.isFavorite();
        realm.close();
        return isFavorite;
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
}
