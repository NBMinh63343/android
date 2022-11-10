package com.example.mentor;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import api.UsersRepository;
import api.UsersService;


public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText txtEmail1, txtPassword1,txtComfirm;
    TextView txtAlready;
    UsersService usersService;
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup);


        btnSignUp = findViewById(R.id.btnSignUp);
        txtEmail1 = findViewById(R.id.txtEmail1);
        txtPassword1 = findViewById(R.id.txtPassword1);
        txtComfirm = findViewById(R.id.txtConfirm);
        txtAlready = findViewById(R.id.txtAlready);

        usersService = UsersRepository.getUsersService();

        txtAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail1.getText().toString();
                String password = txtPassword1.getText().toString();
                String comfirm = txtComfirm.getText().toString();
                System.out.println(password);
                System.out.println(comfirm+"confirm");
                if(email.length()<=0){
                    Toast.makeText(RegisterActivity.this, "Username not empty", Toast.LENGTH_SHORT).show();
                }else if(password.length()<=0){
                    Toast.makeText(RegisterActivity.this, "Password not empty", Toast.LENGTH_SHORT).show();
                }else if(!password.equalsIgnoreCase(comfirm)) {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }else{
                    register(email, password);
                }

            }
        });

    }

    private void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println(task.getException());
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Create Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Create Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}