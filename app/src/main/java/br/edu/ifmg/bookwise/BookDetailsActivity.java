package br.edu.ifmg.bookwise;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Objects;

import android.util.Log;
import android.os.Bundle;
import android.content.Intent;

import com.squareup.picasso.Picasso;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.Book;
import br.edu.ifmg.bookwise.classes.Gender;
import br.edu.ifmg.bookwise.classes.Review;
import br.edu.ifmg.bookwise.classes.ReviewOutput;
import br.edu.ifmg.bookwise.classes.CreateReviewInput;
import br.edu.ifmg.bookwise.classes.UpdateReviewInput;
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
    private Review review;
    private User currentUser;
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
        binding.averageReview.setText(this.book.averageReview + "⭐");

        this.currentUser = this.app.getUser();
        if (currentUser != null) {
            this.review = Arrays.stream(currentUser.reviews)
                    .filter(e -> Objects.equals(e.idBook, this.book.id))
                    .findAny()
                    .orElse(null);

            if (this.review != null) {
                binding.stars.setRating(this.review.stars);
            } else {
                binding.stars.setRating(0);
            }
        }

        binding.stars.setOnRatingBarChangeListener((a, b, c) -> onChangeStars(b, c));

        binding.share.setOnClickListener(v -> shareBook(
                this.book.title,
                this.book.author,
                this.book.synopsis
        ));
    }

    public void onChangeStars(float newValue, boolean isUser) {
        if (isUser) {
            if (this.review != null) {
                this.app.getExecutor().execute(() -> {
                    try {
                        ReviewOutput ro = app.getBookWiseRepo().updateReview(
                                new UpdateReviewInput(this.review.id, newValue)
                        );
                        if (ro != null && ro.data != null) {
                            Review[] aux = new Review[this.currentUser.reviews.length];
                            for (int i = 0; i < aux.length; i++) {
                                aux[i] = this.currentUser.reviews[i];
                                if (Objects.equals(this.review.id, aux[i].id)) {
                                    aux[i].stars = newValue;
                                }
                            }

                            this.review = ro.data;
                            this.currentUser.reviews = aux;
                            this.app.setUser(this.currentUser);
                        }
                    } catch (Exception e) {
                        Log.d("onChangeStars", e.getMessage());
                        Log.d("onChangeStars", "onChangeStars-update-error");
                    }
                });
            } else {
                this.app.getExecutor().execute(() -> {
                    try {
                        ReviewOutput ro = app.getBookWiseRepo().createReview(
                                new CreateReviewInput(newValue, this.book.id, this.app.getUser().id)
                        );
                        if (ro != null && ro.data != null) {
                            Review[] aux = new Review[this.currentUser.reviews.length + 1];
                            for (int i = 0; i < this.currentUser.reviews.length; i++) {
                                aux[i] = this.currentUser.reviews[i];
                            }
                            aux[aux.length - 1] = ro.data;

                            this.review = ro.data;
                            this.currentUser.reviews = aux;
                            this.app.setUser(this.currentUser);
                        }
                    } catch (Exception e) {
                        Log.d("onChangeStars", e.getMessage());
                        Log.d("onChangeStars", "onChangeStars-create-error");
                    }
                });
            }
        }
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
