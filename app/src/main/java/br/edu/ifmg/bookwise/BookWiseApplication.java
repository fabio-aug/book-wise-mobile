package br.edu.ifmg.bookwise;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import br.edu.ifmg.bookwise.apimodel.BookWiseRepo;
import retrofit2.Retrofit;
import br.edu.ifmg.bookwise.apimodel.BookWiseApi;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookWiseApplication extends Application {
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private BookWiseApi bookapi;
    private BookWiseRepo bookWiseRepo;

    public Executor getExecutor() {
        return executorService;
    }

    public BookWiseApi getApi() {
        //if (bookapi != null) return bookapi;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book-wise-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookapi = retrofit.create(BookWiseApi.class);

        return bookapi;
    }

    public BookWiseRepo getBookWiseRepo() {
        if (bookWiseRepo != null) return bookWiseRepo;

        bookWiseRepo = new BookWiseRepo(getApi());
        return bookWiseRepo;
    }
}
