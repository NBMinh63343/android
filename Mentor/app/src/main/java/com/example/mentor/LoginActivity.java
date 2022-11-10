package com.example.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import api.UsersRepository;
import api.UsersService;


public class LoginActivity extends AppCompatActivity {

//    private SignInClient oneTapClient;
//    private BeginSignInRequest signInRequest;
//
//    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
//    private boolean showOneTapUI = true;

    Button btnLogin, btnLogin2;
    EditText txtEmail, txtPassword;
    TextView txtCreate;
    UsersService usersService;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_signin);

        btnLogin = findViewById(R.id.btnLogin);
//        btnLogin2 = findViewById(R.id.btnLogin2);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtCreate = findViewById(R.id.txtCreate);
        usersService = UsersRepository.getUsersService();

//        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                        .setSupported(true)
//                        .build())
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                // Automatically sign in when exactly one credential is retrieved.
//                .setAutoSelectEnabled(true)
//                .build();





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if(email.length()<=0){
                    Toast.makeText(LoginActivity.this, "Username not empty", Toast.LENGTH_SHORT).show();
                }else if(password.length()<=0){
                    Toast.makeText(LoginActivity.this, "Password not empty", Toast.LENGTH_SHORT).show();
                }else{
                    login(email, password);
                }

            }
        });

//        btnLogin2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onActivityResult();
//            }
//        });




        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, SlotCreateActivity.class);
                    intent.putExtra("mentor", txtEmail.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_ONE_TAP:
//                try {
//                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
//                    String idToken = credential.getGoogleIdToken();
//                    if (idToken !=  null) {
//                        // Got an ID token from Google. Use it to authenticate
//                        // with Firebase.
//                        Log.d(idToken, "Got ID token.");
//                    }
//                } catch (ApiException e) {
//                    // ...
//                }
//                break;
//        }
//    }
}

