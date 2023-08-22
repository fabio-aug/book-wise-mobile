package br.edu.ifmg.bookwise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import java.util.Arrays;
import java.util.Objects;

import com.squareup.picasso.Picasso;

import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.Gender;
import br.edu.ifmg.bookwise.classes.Review;
import br.edu.ifmg.bookwise.databinding.BookDetailsBinding;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String ID = "BookDetailsActivity_Book_Id";
    public static final String AUTHOR = "BookDetailsActivity_Book_Author";
    public static final String AVERAGE_REVIEW = "BookDetailsActivity_Book_Average_Review";
    public static final String TITLE = "BookDetailsActivity_Book_Title";
    public static final String SYNOPSIS = "BookDetailsActivity_Book_Synopsis";
    public static final String IMAGE = "BookDetailsActivity_Book_Image";
    public static final String GENDERS = "BookDetailsActivity_Book_Genders";
    private Book book;
    private BookWiseApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookDetailsBinding binding = BookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.app = (BookWiseApplication) getApplication();
        Intent intent = getIntent();

        this.book = new Book(
                intent.getIntExtra(ID, 0),
                intent.getStringExtra(TITLE),
                intent.getStringExtra(AUTHOR),
                intent.getStringExtra(SYNOPSIS),
                intent.getStringExtra(IMAGE),
                intent.getFloatExtra(AVERAGE_REVIEW, 0),
                Gender.stringArrayToObjectArray(intent.getStringExtra(GENDERS))
        );

        binding.author.setText(this.book.author);
        binding.booktitle.setText(this.book.title);
        binding.synopsis.setText(this.book.synopsis);
        Picasso.get().load(this.book.image).into(binding.image);
        binding.genders.setText(Gender.renderArrayObjects(this.book.genders));

        User currentUser = this.app.getUser();
        if (currentUser != null) {
            Review review = Arrays.stream(currentUser.reviews)
                    .filter(e -> Objects.equals(e.idBook, this.book.id))
                    .findAny()
                    .orElse(null);

            if (review != null) {
                binding.averageReview.setRating(review.stars);
            } else {
                binding.averageReview.setRating(0);
            }
        }

        binding.share.setOnClickListener(v -> shareBook(
                this.book.title,
                this.book.author,
                this.book.synopsis
        ));
    }

    public void shareBook(String bookTitle, String author, String synopsis) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "*Olá! Aqui está uma indicação de livro que você pode se interessar:* \n" +
                "O nome do livro é *" + bookTitle +
                "*\nDe *" + author +
                "*\nE em resumo a estória é :" + synopsis);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}
