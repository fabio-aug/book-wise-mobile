package br.edu.ifmg.bookwise.api;

import java.io.IOException;

import br.edu.ifmg.bookwise.classes.CreateReviewInput;
import br.edu.ifmg.bookwise.classes.Review;
import br.edu.ifmg.bookwise.classes.ReviewOutput;
import br.edu.ifmg.bookwise.classes.UpdateReviewInput;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;
import br.edu.ifmg.bookwise.classes.UserOutput;

public class BookWiseRepo {

    private BookWiseApi api;

    public BookWiseRepo(BookWiseApi api) {
        this.api = api;
    }

    public User register(String name, String email, String password) throws IOException {
        User newUser = new User(name, email, password);
        return api.createUser(newUser).execute().body();
    }

    public ReviewOutput createReview(CreateReviewInput newValue) throws IOException {
        return api.createReview(newValue).execute().body();
    }

    public ReviewOutput updateReview(UpdateReviewInput newValue) throws IOException {
        return api.updateReview(newValue).execute().body();
    }
}
