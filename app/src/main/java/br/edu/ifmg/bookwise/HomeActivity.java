package br.edu.ifmg.bookwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.databinding.HomeBinding;

public class HomeActivity extends AppCompatActivity {

    private void openBookDetails(Book book) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra(BookDetailsActivity.ID, book.id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBinding binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Book book = new Book();
        book.id = 1;

        openBookDetails(book);
    }
}