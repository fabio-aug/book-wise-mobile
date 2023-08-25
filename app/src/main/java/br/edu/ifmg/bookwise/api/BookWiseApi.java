package br.edu.ifmg.bookwise.api;

import java.util.Map;

import br.edu.ifmg.bookwise.classes.BookOutput;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import br.edu.ifmg.bookwise.classes.UserOutput;
import br.edu.ifmg.bookwise.classes.ReviewOutput;
import br.edu.ifmg.bookwise.classes.UpdateReviewInput;
import br.edu.ifmg.bookwise.classes.CreateReviewInput;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;

public interface BookWiseApi {
    @POST("user/login")
    Call<UserOutput> loginUser(@Body UserLogin user);

    @POST("user/create")
    Call<UserOutput> createUser(@Body User user);

    @POST("/review/create")
    Call<ReviewOutput> createReview(@Body CreateReviewInput newReview);

    @PUT("/review/update")
    Call<ReviewOutput> updateReview(@Body UpdateReviewInput newReview);

    @GET("/book/search")
    Call<BookOutput> searchBook(@QueryMap Map<String, String> params);
}
