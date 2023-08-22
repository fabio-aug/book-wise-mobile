package br.edu.ifmg.bookwise;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

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
        intent.putExtra(BookDetailsActivity.GENDERS, Gender.objectArrayToStringArray(book.genders));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBinding binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Book book = new Book(
                1,
                "O Segredo de Almoçar",
                "Luana Assis",
                "A princesa Almoçar sumiu misteriosamente! Como sua história não é muito conhecida pelas crianças, ela corre o risco de ser esquecida para sempre. Mas, para que isso não aconteça, todas as princesas encantadas se juntam para salvar a princesinha.",
                "https://engsoftmoderna.info/figs/capa/capa-3d-principal.jpg",
                2.5f,
                new Gender[]{new Gender(1, "Gênero 1"), new Gender(2, "Gênero 2")}
        );

        openBookDetails(book);
    }
}