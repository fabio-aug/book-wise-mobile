package br.edu.ifmg.bookwise;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import br.edu.ifmg.bookwise.apimodel.BookWiseApi;
import br.edu.ifmg.bookwise.classes.User;
import br.edu.ifmg.bookwise.classes.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import br.edu.ifmg.bookwise.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    SignInClient oneTapClient;
    BeginSignInRequest signUpRequest;

    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private BookWiseApplication app;

    public void openHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginBinding binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        app = new BookWiseApplication();

        boolean x = true;

        if (x) {
            openHome();
        } else {
            oneTapClient = Identity.getSignInClient(this);
            signUpRequest = BeginSignInRequest.builder()
                    .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                            .setSupported(true)
                            // Your server's client ID, not your Android client ID.
                            .setServerClientId(getString(R.string.web_client_id))
                            // Show all accounts on the device.
                            .setFilterByAuthorizedAccounts(false)
                            .build())
                    .build();

            ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                    registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                    String idToken = credential.getGoogleIdToken();
                                    if (idToken != null) {
                                        // Got an ID token from Google. Use it to authenticate
                                        // with your backend.
                                        Log.d("TAG", "Got ID token.");
                                        String email = credential.getId();
                                        String name = credential.getDisplayName();


                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl("https://book-wise-api.onrender.com/")
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();
                                        BookWiseApi bookapi = retrofit.create(BookWiseApi.class);

                                        User user = new User(name, email, email);
                                        UserLogin userLogin = new UserLogin(email, email);
                                        Call<UserLogin> callLogin = bookapi.login(userLogin);
                                        callLogin.enqueue(new Callback<UserLogin>() {
                                            @Override
                                            public void onResponse(Call<UserLogin> callLogin, Response<UserLogin> response) {
                                                // this method is called when we get response from our api.
                                                Toast.makeText(LoginActivity.this, "Data received into API", Toast.LENGTH_SHORT).show();

                                                // we are getting response from our body
                                                // and passing it to our modal class.
                                                UserLogin responseFromAPI = response.body();

                                                // on below line we are getting our data from modal class and adding it to our string.
                                                String responseString = "Response Code : " + response.code();
                                                Log.d("resposta api 1", response.toString());
                                                if (response.code() == 500) {
                                                    // calling a method to create a post and passing our modal class.
                                                    Call<User> call = bookapi.createUser(user);
                                                    Log.d("user", user.getName());
                                                    // on below line we are executing our method.
                                                    call.enqueue(new Callback<User>() {
                                                        @Override
                                                        public void onResponse(Call<User> call, Response<User> response) {
                                                            // this method is called when we get response from our api.
                                                            Toast.makeText(LoginActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                                                            // we are getting response from our body
                                                            // and passing it to our modal class.
                                                            User responseFromAPI = response.body();

                                                            // on below line we are getting our data from modal class and adding it to our string.
                                                            String responseString = "Response Code : " + response.code();
                                                            Log.d("resposta api 1", response.toString());
                                                        }

                                                        @Override
                                                        public void onFailure(Call<User> call, Throwable t) {
                                                            // setting text to our text view when
                                                            // we get error response from API.
                                                            Log.d("resposta api 2", t.getMessage());

                                                        }
                                                    });
                                                } else {
                                                    Log.d("deveria logar", String.valueOf(response.code()));
                                                    openHome();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<UserLogin> call, Throwable t) {
                                                // setting text to our text view when
                                                // we get error response from API.
                                                Log.d("resposta api 2", t.getMessage());

                                            }
                                        });

                                    }
                                } catch (ApiException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
            binding.googleSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneTapClient.beginSignIn(signUpRequest)
                            .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                                @Override
                                public void onSuccess(BeginSignInResult result) {
                                    IntentSenderRequest intentSenderRequest =
                                            new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                    activityResultLauncher.launch(intentSenderRequest);
                                }
                            })
                            .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // No saved credentials found. Launch the One Tap sign-up flow, or
                                    // do nothing and continue presenting the signed-out UI.
                                    Log.d("TAG", e.getLocalizedMessage());
                                }
                            });
                }
            });
        }
    }
}