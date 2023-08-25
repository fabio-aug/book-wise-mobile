package br.edu.ifmg.bookwise;

import android.os.Bundle;
import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.Gender;
import br.edu.ifmg.bookwise.classes.Review;
import br.edu.ifmg.bookwise.databinding.HomeBinding;

public class HomeActivity extends AppCompatActivity {
    private BookWiseApplication app;

    private void openBookDetails() {
        User user = new User("Admin - Fábio", "fabio@admin.com", "admin");
        user.id = 1;
        user.reviews = new Review[]{new Review(1, 1, 2, 3f)};
        app.setUser(user);

        Book book = new Book(
                2, "Engenharia de Software Moderna", "Marco Tulio Valente",
                "Portal e livro para ensino moderno de Engenharia de Software",
                "https://engsoftmoderna.info/figs/capa/capa-3d-principal.jpg",
                3f, new Gender[]{new Gender(11, "Didático")}
        );

        Intent intent = new Intent(this, BookDetailsActivity.class);

        intent.putExtra(BookDetailsActivity.ID, book.id);
        intent.putExtra(BookDetailsActivity.IMAGE, book.image);
        intent.putExtra(BookDetailsActivity.TITLE, book.title);
        intent.putExtra(BookDetailsActivity.AUTHOR, book.author);
        intent.putExtra(BookDetailsActivity.SYNOPSIS, book.synopsis);
        intent.putExtra(BookDetailsActivity.AVERAGE_REVIEW, book.averageReview);
        intent.putExtra(BookDetailsActivity.GENDERS, Gender.objectArrayToStringArray(book.genders));

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBinding binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.app = (BookWiseApplication) getApplication();

        openBookDetails();

        // BookViewModel vm = new ViewModelProvider(this).get(BookViewModel.class);
        // BookAdapter adapter = new BookAdapter(vm.getBooks());
        // vm.getLoading().observe(this, binding.progressBar::setVisibility);

    }
}