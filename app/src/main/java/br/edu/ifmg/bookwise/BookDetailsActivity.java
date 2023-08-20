package br.edu.ifmg.bookwise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import br.edu.ifmg.bookwise.databinding.BookDetailsBinding;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String ID = "BookDetailsActivity_Book_Id";
    public static final String AUTHOR = "BookDetailsActivity_Book_Author";
    public static final String AVERAGE_REVIEW = "BookDetailsActivity_Book_Average_Review";
    public static final String TITLE = "BookDetailsActivity_Book_Title";
    public static final String SYNOPSIS = "BookDetailsActivity_Book_Synopsis";
    public static final String IMAGE = "BookDetailsActivity_Book_Image";
    public static final String SHARING = "BookDetailsActivity_Book_Sharing";
    public static final String GENDERS = "BookDetailsActivity_Book_Genders";
    public static final String REVIEWS = "BookDetailsActivity_Book_Reviews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookDetailsBinding binding = BookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        binding.author.setText(intent.getStringExtra(AUTHOR));
        // binding.share.setText(intent.getStringExtra(SHARING));
        binding.booktitle.setText(intent.getStringExtra(TITLE));
        binding.genders.setText(intent.getStringExtra(GENDERS));
        binding.synopsis.setText(intent.getStringExtra(SYNOPSIS));
        Picasso.get().load(intent.getStringExtra(IMAGE)).into(binding.image);
        binding.averageReview.setNumStars(5);
        binding.averageReview.setRating(intent.getFloatExtra(AVERAGE_REVIEW, 0));
    }
}
