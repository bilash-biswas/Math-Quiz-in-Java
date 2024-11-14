package com.example.mathquiz.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mathquiz.Adapter.CustomAdapter;
import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.databinding.ActivitySeeAnswerBinding;

import java.util.ArrayList;
import java.util.Objects;

public class SeeAnswerActivity extends AppCompatActivity {
    private ActivitySeeAnswerBinding binding;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeAnswerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        ArrayList<QuestionModel> questions = (ArrayList<QuestionModel>) intent.getSerializableExtra("data");
        ArrayList<QuestionModel> wrongQuestions = (ArrayList<QuestionModel>) intent.getSerializableExtra("wrongData");
        adapter = new CustomAdapter(this, questions);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeeAnswerActivity.this, MainActivity.class));
                finish();
            }
        });
        if (wrongQuestions.isEmpty()){
            binding.mistakeButton.setVisibility(View.GONE);
        }

        binding.mistakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(intent.getStringExtra("type"), "trueFalse")){
                    //Log.d("@@@", intent.getStringExtra("type"));
                    Intent intent1 = new Intent(SeeAnswerActivity.this, TrueFalseQuizActivity.class);
                    intent1.putExtra("wrongData", wrongQuestions);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(SeeAnswerActivity.this, QuizActivity.class);
                    intent1.putExtra("wrongData", wrongQuestions);
                    //Log.d("@@@", intent.getStringExtra("type"));
                    startActivity(intent1);
                }

            }
        });
    }
}