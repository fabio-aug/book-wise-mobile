package br.edu.ifmg.bookwise;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.edu.ifmg.bookwise.apimodel.BookwiseApi;
import br.edu.ifmg.bookwise.apimodel.BookwiseRepo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookwiseApplication extends Application {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private BookwiseApi bookapi;
    private BookwiseRepo bookwiseRepo;

    public Executor getExecutor() {
        return executorService;
    }

    public BookwiseApi getApi() {
        //if (bookapi != null) return bookapi;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book-wise-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookapi = retrofit.create(BookwiseApi.class);

        return bookapi;
    }

    public BookwiseRepo getBookwiseRepo() {
        if (bookwiseRepo != null) return bookwiseRepo;

        bookwiseRepo = new BookwiseRepo(getApi());
        return bookwiseRepo;
    }
}
