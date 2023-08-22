package br.edu.ifmg.bookwise;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import retrofit2.Retrofit;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.apimodel.BookWiseApi;
import br.edu.ifmg.bookwise.apimodel.BookWiseRepo;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookWiseApplication extends Application {
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private BookWiseApi bookApi;
    private BookWiseRepo bookWiseRepo;
    private User user;

    public Executor getExecutor() {
        return executorService;
    }

    public BookWiseApi getApi() {
        if (this.bookApi != null) return this.bookApi;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book-wise-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.bookApi = retrofit.create(BookWiseApi.class);

        return this.bookApi;
    }

    public BookWiseRepo getBookWiseRepo() {
        if (this.bookWiseRepo != null) return this.bookWiseRepo;

        this.bookWiseRepo = new BookWiseRepo(getApi());
        return this.bookWiseRepo;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
