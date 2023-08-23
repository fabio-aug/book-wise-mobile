package br.edu.ifmg.bookwise.apimodel;

import java.io.IOException;

import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.User;

public class BookWiseRepo {

    private BookWiseApi api;

    public BookWiseRepo(BookWiseApi api) {
        this.api = api;
    }

    public User register(String name, String email, String password) throws IOException {
        User newUser = new User(name, email, password);
        User u = api.createUser(newUser).execute().body();
        return u;
    }

    public Book LoadBooks() throws IOException {
        Book b = api.loadBooks().execute().body();
        return b;
    }

}
