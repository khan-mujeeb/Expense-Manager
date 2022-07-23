package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.expensetracker.databinding.ActivityUpdateDeleteBinding;

public class update_delete extends AppCompatActivity {
    ActivityUpdateDeleteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}