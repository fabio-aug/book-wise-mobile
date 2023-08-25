package br.edu.ifmg.bookwise;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;

import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;
import br.edu.ifmg.bookwise.classes.UserOutput;
import br.edu.ifmg.bookwise.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    private BookWiseApplication app;
    private SignInClient oneTapClient;
    private BeginSignInRequest signUpRequest;
    private LoginBinding binding;

    public void openHome(User user) {
        app.setUser(user);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.app = (BookWiseApplication) getApplication();

        this.binding.btnRegister.setOnClickListener(v -> manualLogin());

        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        try {
                            SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                            String idToken = credential.getGoogleIdToken();
                            if (idToken != null) {
                                String email = credential.getId();
                                String name = credential.getDisplayName();

                                this.app.getExecutor().execute(() -> {
                                    try {
                                        UserOutput uo1 = app.getBookWiseRepo().loginUser(new UserLogin(email, email));
                                        if (uo1 != null && uo1.data != null) {
                                            openHome(uo1.data);
                                        } else {
                                            UserOutput uo2 = app.getBookWiseRepo().createUser(new User(name, email, email));
                                            if (uo2.data != null) {
                                                openHome(uo2.data);
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                });

        this.binding.googleSignIn.setOnClickListener(view -> oneTapClient.beginSignIn(signUpRequest)
                .addOnSuccessListener(LoginActivity.this, result -> {
                    IntentSenderRequest intentSenderRequest =
                            new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                    activityResultLauncher.launch(intentSenderRequest);
                })
                .addOnFailureListener(LoginActivity.this, e -> Log.d("TAG", e.getLocalizedMessage())));
    }

    public void manualLogin() {
        String email = String.valueOf(this.binding.editTextEmail.getText());
        String password = String.valueOf(this.binding.editTextPassword.getText());

        this.app.getExecutor().execute(() -> {
            try {
                UserOutput uo1 = this.app.getBookWiseRepo().loginUser(new UserLogin(email, password));
                if (uo1 != null && uo1.data != null) {
                    openHome(uo1.data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}