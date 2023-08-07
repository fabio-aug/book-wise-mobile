package br.edu.ifmg.bookwise.apimodel;

import br.edu.ifmg.bookwise.classes.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookwiseApi {
        @POST("user/create")
        Call<User> createPost(@Body User user);
}
