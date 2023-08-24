package br.edu.ifmg.bookwise;

import static androidx.lifecycle.ViewModelProvider.Factory.from;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.edu.ifmg.bookwise.apimodel.BookAdapter;
import br.edu.ifmg.bookwise.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BookViewModel vm = new ViewModelProvider(this,from(BookViewModel.initializer)).get(BookViewModel.class);
        BookAdapter adapter = new BookAdapter(vm.getBooks()); //mudar vm.getBooks()

        vm.getLoading().observe(this, (newV) -> binding.progressBar.setVisibility(newV));

        //binding.books.setLayoutManager(new LinearLayoutManager(this));
        //binding.books.setAdapter(adapter);

        //vm.loadBook();

    }
}