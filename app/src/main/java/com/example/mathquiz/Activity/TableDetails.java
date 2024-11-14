package com.example.mathquiz.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ActivityTableDetailsBinding;

public class TableDetails extends AppCompatActivity {
    private ActivityTableDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int tableNumber = intent.getIntExtra("number", 0);

        for (int i = 1; i <= 10; i++) {
            if (i == 10){
                binding.tableCount.append(tableNumber + " x " + i + " = " + (tableNumber * i));
            }else {
                binding.tableCount.append(tableNumber + " x " + i + " = " + (tableNumber * i)+"\n");
            }
        }

        binding.multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableDetails.this, QuizActivity.class).putExtra("type", "tableMultiplication").putExtra("number", tableNumber));
            }
        });

        binding.division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableDetails.this, QuizActivity.class).putExtra("type", "tableDivision").putExtra("number", tableNumber));
            }
        });

    }
}