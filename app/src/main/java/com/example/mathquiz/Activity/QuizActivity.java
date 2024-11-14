package com.example.mathquiz.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathquiz.Model.QuestionList;
import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ActivityQuizBinding;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ActivityQuizBinding binding;
    private ArrayList<QuestionModel> questionLists ;
    private int position = 0;
    private int score = 0;
    private ArrayList<QuestionModel> wrongQuestions = new ArrayList<>();
    private int initial = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questionLists = new ArrayList<>(initial);


        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int number = intent.getIntExtra("number",0);
        ArrayList<QuestionModel> wrong = (ArrayList<QuestionModel>) intent.getSerializableExtra("wrongData");
        if (wrong != null) {
            initial = wrong.size();
            questionLists.addAll(wrong);
        } else {
            questionLists = new QuestionList(type, number != 0 ? number : initial).getQuestionDataList();
        }

        updateQuestionAndOptions();


        binding.option1.setOnClickListener(view -> {
            onSelectedOption(binding.option1.getText().toString());
            binding.option2.setEnabled(false);
            binding.option3.setEnabled(false);
            binding.option4.setEnabled(false);
        });
        binding.option2.setOnClickListener(view -> {
            onSelectedOption(binding.option2.getText().toString());
            binding.option1.setEnabled(false);
            binding.option3.setEnabled(false);
            binding.option4.setEnabled(false);
        });
        binding.option3.setOnClickListener(view -> {
            onSelectedOption(binding.option3.getText().toString());
            binding.option2.setEnabled(false);
            binding.option1.setEnabled(false);
            binding.option4.setEnabled(false);
        });
        binding.option4.setOnClickListener(view -> {
            onSelectedOption(binding.option4.getText().toString());
            binding.option2.setEnabled(false);
            binding.option3.setEnabled(false);
            binding.option1.setEnabled(false);
        });


    }
    private void updateQuestionAndOptions(){
        if (position < questionLists.size()){
            binding.question.setText(questionLists.get(position).getProblem());
            binding.option1.setText(questionLists.get(position).getOption1());
            binding.option2.setText(questionLists.get(position).getOption2());
            binding.option3.setText(questionLists.get(position).getOption3());
            binding.option4.setText(questionLists.get(position).getOption4());
            binding.totalQuestions.setText((position+1)+"/"+ initial);
        }

    }
    private void onSelectedOption(String selectedOption){
        String correctAnswer = questionLists.get(position).getAnswer().toString();
        if (!selectedOption.equals(correctAnswer)){
            wrongQuestions.add(questionLists.get(position));
        }

        // Highlight the correct answer
        if (binding.option1.getText().toString().equals(correctAnswer)) {
            binding.option1.setBackground(getResources().getDrawable(R.drawable.green_back));
        } else if (binding.option2.getText().toString().equals(correctAnswer)) {
            binding.option2.setBackground(getResources().getDrawable(R.drawable.green_back));
        } else if (binding.option3.getText().toString().equals(correctAnswer)) {
            binding.option3.setBackground(getResources().getDrawable(R.drawable.green_back));
        } else if (binding.option4.getText().toString().equals(correctAnswer)) {
            binding.option4.setBackground(getResources().getDrawable(R.drawable.green_back));
        }

        // Mark the selected option as incorrect if it's wrong
        if (!selectedOption.equals(correctAnswer)) {
            if (binding.option1.getText().toString().equals(selectedOption)) {
                binding.option1.setBackground(getResources().getDrawable(R.drawable.red_back));
            } else if (binding.option2.getText().toString().equals(selectedOption)) {
                binding.option2.setBackground(getResources().getDrawable(R.drawable.red_back));
            } else if (binding.option3.getText().toString().equals(selectedOption)) {
                binding.option3.setBackground(getResources().getDrawable(R.drawable.red_back));
            } else if (binding.option4.getText().toString().equals(selectedOption)) {
                binding.option4.setBackground(getResources().getDrawable(R.drawable.red_back));
            }
        }

        // Update score if the selected answer is correct
        if (selectedOption.equals(correctAnswer)) {
            score++;
        }
        questionLists.get(position).setSelectedOption(selectedOption);
        binding.rightTotal.setText(String.valueOf(score));
        binding.wrongTotal.setText(String.valueOf((position + 1) - score));

        // Delay before moving to the next question
        new Handler().postDelayed(() -> {
            resetOptionColors(); // Reset colors for next question
            nextQuestion();
        }, 500);
    }

    private void nextQuestion(){
        position++;
        if (position<questionLists.size()){
            binding.option1.setEnabled(true);
            binding.option2.setEnabled(true);
            binding.option3.setEnabled(true);
            binding.option4.setEnabled(true);
            updateQuestionAndOptions();
        }else {

            Intent intent = new Intent(QuizActivity.this, FinishedActivity.class);
            intent.putExtra("score",score);
            intent.putExtra("total",initial);
            intent.putExtra("data",questionLists);
            intent.putExtra("wrongData",wrongQuestions);
            startActivity(intent);
            initial = 10;
            score = 0;
            questionLists.clear();
            wrongQuestions.clear();
            finish();
        }
    }

    private void resetOptionColors() {
        binding.option1.setBackground(getResources().getDrawable(R.drawable.button_background));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.button_background));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.button_background));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.button_background));
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit? Progress will be lost!").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user confirms, finish the activity
                QuizActivity.this.finish();
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