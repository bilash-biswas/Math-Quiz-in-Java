package com.example.mathquiz.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.Utility.ScoreComment;
import com.example.mathquiz.databinding.ActivityFinishedBinding;

import java.util.ArrayList;
import java.util.Objects;

public class FinishedActivity extends AppCompatActivity {
    private ActivityFinishedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinishedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int total = intent.getIntExtra("total", 10);
        ArrayList<QuestionModel> questions = (ArrayList<QuestionModel>) intent.getSerializableExtra("data");
        ArrayList<QuestionModel> wrongQuestions = (ArrayList<QuestionModel>) intent.getSerializableExtra("wrongData");

        binding.correctAnswer.setText(String.valueOf(score));
        binding.incorrectAnswer.setText(String.valueOf(total - score));
        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        binding.accuracy.setText("Accuracy : \n"+String.valueOf(score * 100 / total) + "%");

        binding.seeAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), SeeAnswerActivity.class);
                intent1.putExtra("data", questions);
                intent1.putExtra("wrongData", wrongQuestions);
                intent1.putExtra("type", intent.getStringExtra("type"));
                startActivity(intent1);
                finish();
            }
        });

        binding.comment.setText(ScoreComment.getComment(score));

    }
}