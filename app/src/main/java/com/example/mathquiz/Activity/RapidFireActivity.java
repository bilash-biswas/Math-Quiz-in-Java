package com.example.mathquiz.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.mathquiz.Model.QuestionList;
import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.R;
import com.example.mathquiz.Utility.ScoreComment;
import com.example.mathquiz.databinding.ActivityRapidFireBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class RapidFireActivity extends AppCompatActivity {
    private ActivityRapidFireBinding binding;
    private QuestionModel model;
    private CountDownTimer countDownTimer;
    private QuestionList questionList;
    private int score = 0;
    private int timeLimit;
    private String level;

    private static final String PREFS_NAME = "RAPID_FIRE";
    private static final String KEY_TOP_SCORE = "rapid_fire_score";
    private static final String KEY_TOP_LEVEL = "rapid_fire_level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRapidFireBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        level = intent.getStringExtra("level");
        timeLimit = intent.getIntExtra("timeLimit", 15);
        binding.timer.setText(String.valueOf(timeLimit));
        questionList = new QuestionList("rapidFire");
        nextQuestion();
        setupOptionClickListeners();

    }

    private void setupOptionClickListeners() {
        binding.option1.setOnClickListener(view -> onOptionSelected(binding.option1.getText().toString()));
        binding.option2.setOnClickListener(view -> onOptionSelected(binding.option2.getText().toString()));
        binding.option3.setOnClickListener(view -> onOptionSelected(binding.option3.getText().toString()));
        binding.option4.setOnClickListener(view -> onOptionSelected(binding.option4.getText().toString()));
    }

    private void onOptionSelected(String selectedOption) {
        disableOptions();

        highlightCorrectAnswer();

        if (selectedOption.equals(String.valueOf(model.getAnswer()))) {
            score++;
            binding.totalScore.setText(String.valueOf(score));
        } else {
            highlightIncorrectAnswer(selectedOption);
            endQuiz();
            return;
        }

        new Handler().postDelayed(() -> {
            resetOptionColors();
            nextQuestion();
        }, 500);
    }

    private void endQuiz() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int rapidFireScore = sp.getInt(KEY_TOP_SCORE, 0);
        if (score > rapidFireScore) {
            sp.edit()
                    .putInt(KEY_TOP_SCORE, score)
                    .putString(KEY_TOP_LEVEL, level)
                    .apply();
        }

        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Your score: " + score + "\n\n" + ScoreComment.getComment(score))
                .setCancelable(false)
                .setPositiveButton("Play Again", (dialog, which) -> {
                    RapidFireActivity.this.finish();
                    startActivity(new Intent(RapidFireActivity.this, RapidFireActivity.class));
                })
                .setNegativeButton("Exit", (dialog, which) -> RapidFireActivity.this.finish())
                .show();
    }

    private void highlightCorrectAnswer() {
        if (binding.option1.getText().toString().equals(String.valueOf(model.getAnswer()))) {
            binding.option1.setBackground(ContextCompat.getDrawable(this, R.drawable.green_back));
        } else if (binding.option2.getText().toString().equals(String.valueOf(model.getAnswer()))) {
            binding.option2.setBackground(ContextCompat.getDrawable(this, R.drawable.green_back));
        } else if (binding.option3.getText().toString().equals(String.valueOf(model.getAnswer()))) {
            binding.option3.setBackground(ContextCompat.getDrawable(this, R.drawable.green_back));
        } else if (binding.option4.getText().toString().equals(String.valueOf(model.getAnswer()))) {
            binding.option4.setBackground(ContextCompat.getDrawable(this, R.drawable.green_back));
        }
    }

    private void highlightIncorrectAnswer(String selectedOption) {
        if (binding.option1.getText().toString().equals(selectedOption)) {
            binding.option1.setBackground(ContextCompat.getDrawable(this, R.drawable.red_back));
        } else if (binding.option2.getText().toString().equals(selectedOption)) {
            binding.option2.setBackground(ContextCompat.getDrawable(this, R.drawable.red_back));
        } else if (binding.option3.getText().toString().equals(selectedOption)) {
            binding.option3.setBackground(ContextCompat.getDrawable(this, R.drawable.red_back));
        } else if (binding.option4.getText().toString().equals(selectedOption)) {
            binding.option4.setBackground(ContextCompat.getDrawable(this, R.drawable.red_back));
        }
    }

    private void resetOptionColors() {
        binding.option1.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        binding.option2.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        binding.option3.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        binding.option4.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
    }

    private void nextQuestion() {
        model = questionList.generateRapidFireQuestions(level);
        binding.question.setText(model.getProblem());
        binding.option1.setText(model.getOption1());
        binding.option2.setText(model.getOption2());
        binding.option3.setText(model.getOption3());
        binding.option4.setText(model.getOption4());
        enableOptions();
        startTimer();
    }

    private void enableOptions() {
        binding.option1.setEnabled(true);
        binding.option2.setEnabled(true);
        binding.option3.setEnabled(true);
        binding.option4.setEnabled(true);
    }

    private void disableOptions() {
        binding.option1.setEnabled(false);
        binding.option2.setEnabled(false);
        binding.option3.setEnabled(false);
        binding.option4.setEnabled(false);
    }

    private void startTimer() {
        LinearProgressIndicator progressIndicator = binding.linearProgressIndicator;

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(timeLimit * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if ((millisUntilFinished / 1000) < 11) {
                    binding.timer.setTextColor(ContextCompat.getColor(RapidFireActivity.this, R.color.incorrect_answer));
                }
                binding.timer.setText(String.valueOf(millisUntilFinished / 1000));
                int progress = (int) ((timeLimit * 1000 - millisUntilFinished) / (timeLimit * 10));
                progressIndicator.setProgress(progress);
            }

            @Override
            public void onFinish() {
                binding.timer.setText("0");
                progressIndicator.setProgress(100);
                endQuiz();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit? Progress will be lost!")
                .setPositiveButton("Yes", (dialog, which) -> RapidFireActivity.this.finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
