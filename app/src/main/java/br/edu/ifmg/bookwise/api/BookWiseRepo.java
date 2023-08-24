package br.edu.ifmg.bookwise.api;

import java.io.IOException;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;
import br.edu.ifmg.bookwise.classes.UserOutput;
import br.edu.ifmg.bookwise.classes.ReviewOutput;
import br.edu.ifmg.bookwise.classes.CreateReviewInput;
import br.edu.ifmg.bookwise.classes.UpdateReviewInput;

public class BookWiseRepo {
    private final BookWiseApi api;

    public BookWiseRepo(BookWiseApi api) {
        this.api = api;
    }

    public UserOutput createUser(User user) throws IOException {
        return api.createUser(user).execute().body();
    }

    public UserOutput loginUser(UserLogin userLogin) throws IOException {
        return api.loginUser(userLogin).execute().body();
    }

    public ReviewOutput createReview(CreateReviewInput newValue) throws IOException {
        return api.createReview(newValue).execute().body();
    }

    public ReviewOutput updateReview(UpdateReviewInput newValue) throws IOException {
        return api.updateReview(newValue).execute().body();
    }
}
