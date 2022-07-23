package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.databinding.ActivityMainBinding;
import com.example.expensetracker.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.email.getText().toString();
                String pass = binding.password.getText().toString();
                if(mail.trim().length()<=0 || mail.trim().length()<=0)
                    return ;

                firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(signup.this, "user created", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}