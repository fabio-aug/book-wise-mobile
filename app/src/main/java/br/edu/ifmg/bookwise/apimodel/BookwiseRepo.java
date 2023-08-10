package br.edu.ifmg.bookwise.apimodel;

import java.io.IOException;

import br.edu.ifmg.bookwise.classes.User;

public class BookwiseRepo {

    private BookwiseApi api;

    public BookwiseRepo(BookwiseApi api) {
        this.api = api;
    }

    public User register(String name, String email, String password) throws IOException {
        User newUser = new User(name, email, password);
        User u = api.createUser(newUser).execute().body();
        return u;
    }
}
