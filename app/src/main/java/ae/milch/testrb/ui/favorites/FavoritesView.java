package ae.milch.testrb.ui.favorites;

import java.util.List;

import ae.milch.testrb.models.Book;

interface FavoritesView {

    void outputListBooks(List<Book> books);

    void onFavoriteClick(boolean favorite, String id);

}
