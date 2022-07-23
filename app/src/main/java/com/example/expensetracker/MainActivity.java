package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    try {
                        startActivity(new Intent(MainActivity.this,dashBoard.class));
                        finish();
                    }catch (Exception e) {

                    }
                }
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.email.getText().toString();
                String pass = binding.password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(mail,pass);

                if(mail.trim().length()<=0 || mail.trim().length()<=0)
                    return ;

                firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainActivity.this,dashBoard.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}