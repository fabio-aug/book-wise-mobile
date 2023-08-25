package br.edu.ifmg.bookwise;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import retrofit2.Retrofit;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.api.BookWiseApi;
import br.edu.ifmg.bookwise.api.BookWiseRepo;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookWiseApplication extends Application {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private BookWiseRepo bookWiseRepo;
    private User user;

    public Executor getExecutor() {
        return executorService;
    }

    public BookWiseRepo getBookWiseRepo() {
        if (this.bookWiseRepo != null) return this.bookWiseRepo;

        BookWiseApi api = new Retrofit.Builder()
                .baseUrl("https://book-wise-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookWiseApi.class);

        this.bookWiseRepo = new BookWiseRepo(api);
        return this.bookWiseRepo;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
