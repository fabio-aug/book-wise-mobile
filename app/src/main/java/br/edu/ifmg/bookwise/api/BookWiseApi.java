package br.edu.ifmg.bookwise.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;

public interface BookWiseApi {
    @POST("user/login")
    Call<UserLogin> login(@Body UserLogin user);

    @POST("user/create")
    Call<User> createUser(@Body User user);
}
