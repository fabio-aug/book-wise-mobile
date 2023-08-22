package br.edu.ifmg.bookwise;

import android.util.Log;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifmg.bookwise.databinding.RegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegisterBinding binding = RegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(view -> Log.d("RegisterActivity", "RA"));
    }
}
