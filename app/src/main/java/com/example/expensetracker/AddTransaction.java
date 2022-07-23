package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.databinding.ActivityAddTransactionBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class AddTransaction extends AppCompatActivity {
    String type = "";
    ActivityAddTransactionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        **************Binding********************
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore fstore;
        FirebaseAuth firebaseauth;
        FirebaseUser firebaseuser;

        fstore = FirebaseFirestore.getInstance();
        firebaseauth = FirebaseAuth.getInstance();
        firebaseuser = firebaseauth.getCurrentUser();

//        ******************** Check Box ********************
        binding.expenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Expense";
                binding.expenseBtn.setChecked(true);
                binding.incomeBtn.setChecked(false);
            }
        });

        binding.incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Income";
                binding.expenseBtn.setChecked(false);
                binding.incomeBtn.setChecked(true);
            }
        });

//        ************************ ADD Button ***********************
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = binding.addAmt.getText().toString().trim();
                String note = binding.addNote.getText().toString().trim();

                if(amount.length()<=0)
                    return;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm", Locale.getDefault());
                String cdt = sdf.format(new Date());
                String id = UUID.randomUUID().toString();
                Map<String,Object> transaction = new HashMap<>();
                transaction.put("amount",amount);
                transaction.put("note",note);
                transaction.put("type",type);
                transaction.put("id",id);
                transaction.put("date",cdt);

                fstore.collection("Expense").document(firebaseauth.getUid()).collection("Notes").document(id)
                        .set(transaction)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddTransaction.this, "Added", Toast.LENGTH_SHORT).show();
                                binding.addNote.setText("");
                                binding.addAmt.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddTransaction.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                try {
                    startActivity(new Intent(AddTransaction.this,dashBoard.class));
                }catch (Exception e){

                }

            }
        });


    }
}