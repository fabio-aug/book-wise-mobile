package br.edu.ifmg.bookwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

public class BookDetailsActivity extends AppCompatActivity {

    private MutableLiveData<String> bookName = new MutableLiveData<>();
    private MutableLiveData<String> bookAuthor = new MutableLiveData<>();
    private MutableLiveData<String> bookSynopsis = new MutableLiveData<>();
    private MutableLiveData<String> bookGenders = new MutableLiveData<>();
    private MutableLiveData<Integer> bookAvgReview = new MutableLiveData<>();
    private MutableLiveData<String> bookImage = new MutableLiveData<>();
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
        image = findViewById(R.id.image);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}