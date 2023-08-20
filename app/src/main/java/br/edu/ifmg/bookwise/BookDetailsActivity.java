package br.edu.ifmg.bookwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import br.edu.ifmg.bookwise.databinding.BookDetailsBinding;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String ID = "BookDetailsActivity_Book_Id";
    TextView bookTitle, author, synopsis, genders, averageReview;
    Button share;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookDetailsBinding binding = BookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bookTitle = findViewById(R.id.booktitle);
        author = findViewById(R.id.author);
        synopsis = findViewById(R.id.synopsis);
        genders = findViewById(R.id.genders);
        averageReview = findViewById(R.id.genders);
        share = findViewById(R.id.share);
        image = findViewById(R.id.image);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
