package com.example.mathquiz.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ActivityRapidFireLevelBinding;

public class RapidFireLevel extends AppCompatActivity {
    private ActivityRapidFireLevelBinding binding;
    private static final String PREFS_NAME = "RAPID_FIRE";
    private static final String KEY_TOP_SCORE = "rapid_fire_score";
    private static final String KEY_TOP_LEVEL = "rapid_fire_level";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRapidFireLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.easy.setOnClickListener(v -> startQuiz("easy",15));
        binding.medium.setOnClickListener(v -> startQuiz("medium", 25));
        binding.hard.setOnClickListener(v -> startQuiz("hard", 40));
        binding.advance.setOnClickListener(v -> startQuiz("advance", 60));

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int topScore = sp.getInt(KEY_TOP_SCORE, 0);
        String topLevel = sp.getString(KEY_TOP_LEVEL, "");

        if (topScore == 0) {
            binding.topScore.setText("No high scores yet");
        } else {
            binding.topScore.setText("Top Score : " + topScore + "\nLevel : " + topLevel);
        }



    }
    private void startQuiz(String level, int timeLimit) {
        Intent intent = new Intent(this, RapidFireActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("timeLimit", timeLimit);
        startActivity(intent);
    }
}