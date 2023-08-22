package br.edu.ifmg.bookwise;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import br.edu.ifmg.bookwise.classes.Book;

public class BookViewModel extends ViewModel {

    private BookWiseApplication app;
    private MutableLiveData<Integer> loading;
    private ArrayList<Book> books;


    public static ViewModelInitializer<BookViewModel> initializer = new ViewModelInitializer<>(
            BookViewModel.class,
            createExtras -> new BookViewModel((BookWiseApplication) createExtras.get(APPLICATION_KEY)));


    public BookViewModel(BookWiseApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        books = new ArrayList<>();
    }

    public void loadBook(Book book){
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {
            try {

            }finally {
                loading.postValue(View.GONE);
            }
        });
    }

    public LiveData<Integer> getLoading() {
        return loading;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
