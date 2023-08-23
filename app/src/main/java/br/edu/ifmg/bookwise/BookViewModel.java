package br.edu.ifmg.bookwise;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.util.ArrayList;

import br.edu.ifmg.bookwise.classes.Book;

public class BookViewModel extends ViewModel {

    private BookWiseApplication app;
    private MutableLiveData<Integer> loading;
    private ArrayList<Book> books;
    private MutableLiveData<String> title;
    private MutableLiveData<String> author;
    private MutableLiveData<String> image;


    public static ViewModelInitializer<BookViewModel> initializer = new ViewModelInitializer<>(
            BookViewModel.class,
            createExtras -> new BookViewModel((BookWiseApplication) createExtras.get(APPLICATION_KEY)));

    public BookViewModel(BookWiseApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        books = new ArrayList<>();
        title = new MutableLiveData<>();
        author = new MutableLiveData<>();
        image = new MutableLiveData<>();
    }

    public void loadBook(Book book){
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {
            try {
                Book b = app.getBookWiseRepo().LoadBooks();
                title.postValue(b.title);
                title.postValue(b.author);
                image.postValue(b.image);

            } catch (IOException e) {
                throw new RuntimeException(e);
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

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getAuthor() {
        return author;
    }

    public MutableLiveData<String> getImage() {
        return image;
    }
}
