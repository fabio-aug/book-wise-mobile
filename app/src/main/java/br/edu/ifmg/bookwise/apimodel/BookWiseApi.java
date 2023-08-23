package br.edu.ifmg.bookwise.apimodel;

import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookWiseApi {
        @POST("user/login")
        Call<UserLogin> login(@Body UserLogin user);
        @POST("user/create")
        Call<User> createUser(@Body User user);

        @GET("book/getRandomBook")
        Call<Book> loadBooks();
}
