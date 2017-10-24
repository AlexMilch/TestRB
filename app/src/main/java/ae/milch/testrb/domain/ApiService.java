package ae.milch.testrb.domain;

import ae.milch.testrb.models.BooksModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/books/v1/volumes")
    Observable<BooksModel> searchBook(@Query("q") String text);

}
