package ae.milch.testrb.ui.favorites;

import android.support.annotation.NonNull;

import java.util.List;

import ae.milch.testrb.models.Book;
import io.realm.Realm;

class FavoritesPresenter {

    private final FavoritesView view;


    FavoritesPresenter(FavoritesView view) {
        this.view = view;
    }

    void changeFavorite(final boolean favorite, String id) {
        Realm realm = Realm.getDefaultInstance();
        final Book book = realm.where(Book.class)
                .equalTo("id", id)
                .findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                if (book != null) {
                    book.setFavorite(favorite);
                }
            }
        });
        realm.close();
        getFavoriteBooks();
    }

    void getFavoriteBooks() {
        Realm realm = Realm.getDefaultInstance();
        List<Book> books = realm.copyFromRealm(realm.where(Book.class)
                .equalTo("isFavorite", true)
                .findAll());
        realm.close();
        view.outputListBooks(books);
    }
}
