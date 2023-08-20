package br.edu.ifmg.bookwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.Gender;
import br.edu.ifmg.bookwise.databinding.HomeBinding;

public class HomeActivity extends AppCompatActivity {

    private void openBookDetails(Book book) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra(BookDetailsActivity.ID, book.id);
        intent.putExtra(BookDetailsActivity.AUTHOR, book.author);
        intent.putExtra(BookDetailsActivity.AVERAGE_REVIEW, book.averageReview);
        intent.putExtra(BookDetailsActivity.TITLE, book.title);
        intent.putExtra(BookDetailsActivity.SYNOPSIS, book.synopsis);
        intent.putExtra(BookDetailsActivity.IMAGE, book.image);
        intent.putExtra(BookDetailsActivity.SHARING, book.sharing);
        intent.putExtra(BookDetailsActivity.GENDERS, book.genders);
        intent.putExtra(BookDetailsActivity.REVIEWS, book.reviews);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBinding binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Book book = new Book();
        book.id = 1;
        book.author = "Luana Assis";
        book.averageReview = 2.5f;
        book.title = "O Segredo de Feiurinha";
        book.synopsis = "A princesa Feiurinha sumiu misteriosamente! Como sua história não é muito conhecida pelas crianças, ela corre o risco de ser esquecida para sempre. Mas, para que isso não aconteça, todas as princesas encantadas se juntam para salvar a princesinha.";
        book.image = "https://engsoftmoderna.info/figs/capa/capa-3d-principal.jpg";
        book.genders = null;
        book.sharing = 1;
        book.reviews = null;

        openBookDetails(book);
    }
}