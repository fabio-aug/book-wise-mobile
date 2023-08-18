package br.edu.ifmg.bookwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {
    TextView bookTitle, author, synopsis, genders, averageReview;
    Button share;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        bookTitle = findViewById(R.id.booktitle);
        author = findViewById(R.id.author);
        synopsis = findViewById(R.id.synopsis);
        genders = findViewById(R.id.genders);
        averageReview = findViewById(R.id.genders);
        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
