package ae.milch.testrb.ui.search;

import java.util.List;

import ae.milch.testrb.models.BookModel;

interface SearchView {

    void outputListBooks(List<BookModel> bookModel);
}
