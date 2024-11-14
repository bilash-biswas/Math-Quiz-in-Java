package com.example.mathquiz.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathquiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.additionButton.setOnClickListener(v -> startQuiz("addition"));
        binding.subtractionButton.setOnClickListener(v -> startQuiz("subtraction"));
        binding.multiplicationButton.setOnClickListener(v -> startQuiz("multiplication"));
        binding.divisionButton.setOnClickListener(v -> startQuiz("division"));
        binding.modulusButton.setOnClickListener(v -> startQuiz("modulus"));
        binding.rapidFire.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RapidFireLevel.class)));
        binding.table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TableActivity.class));
            }
        });

        binding.trueFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TrueFalseQuizActivity.class).putExtra("type", "trueFalse"));
            }
        });


    }

    private void startQuiz(String type) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Create an AlertDialog to confirm the exit action
        new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user confirms, finish the activity
                MainActivity.this.finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user cancels, dismiss the dialog
                dialog.dismiss();
            }
        }).show();
    }
}