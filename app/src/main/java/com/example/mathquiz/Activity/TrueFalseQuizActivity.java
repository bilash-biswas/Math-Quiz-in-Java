package com.example.mathquiz.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mathquiz.Model.QuestionList;
import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ActivityTrueFalseQuizBinding;

import java.util.ArrayList;
import java.util.Random;

public class TrueFalseQuizActivity extends AppCompatActivity {
    private static final int DELAY_MS = 500;
    private ActivityTrueFalseQuizBinding binding;
    private ArrayList<QuestionModel> questionLists;
    private int position = 0;
    private int score = 0;
    private ArrayList<QuestionModel> wrongQuestions = new ArrayList<>();
    private int initial = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrueFalseQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questionLists = new ArrayList<>(initial);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int number = intent.getIntExtra("number", 0);
        ArrayList<QuestionModel> wrong = (ArrayList<QuestionModel>) intent.getSerializableExtra("wrongData");
        if (wrong != null) {
            Log.d("@@@", "Wrong questions size: " + wrong.size());
            initial = wrong.size();
            questionLists.addAll(wrong);
        } else {
            questionLists = new QuestionList(type, number != 0 ? number : initial).getQuestionDataList();
        }

        updateQuestion();

        binding.correctAnswer.setOnClickListener(v -> onSelectedOption(true));
        binding.incorrectAnswer.setOnClickListener(v -> onSelectedOption(false));
    }

    private void updateQuestion() {
        if (position < questionLists.size()) {
            binding.totalQuestions.setText((position + 1) + "/" + initial);

            // Get random option to display for the question
            Random random = new Random();
            int randomIndex = random.nextInt(4);
            QuestionModel currentQuestion = questionLists.get(position);

            // Set question text with a randomly selected option
            switch (randomIndex) {
                case 0:
                    binding.question.setText(currentQuestion.getProblem() + currentQuestion.getOption1());
                    currentQuestion.setSelectedOption(currentQuestion.getOption1());
                    break;
                case 1:
                    binding.question.setText(currentQuestion.getProblem() + currentQuestion.getOption2());
                    currentQuestion.setSelectedOption(currentQuestion.getOption2());
                    break;
                case 2:
                    binding.question.setText(currentQuestion.getProblem() + currentQuestion.getOption3());
                    currentQuestion.setSelectedOption(currentQuestion.getOption3());
                    break;
                case 3:
                    binding.question.setText(currentQuestion.getProblem() + currentQuestion.getOption4());
                    currentQuestion.setSelectedOption(currentQuestion.getOption4());
                    break;
            }
        }
    }

    private void onSelectedOption(boolean isCorrect) {
        QuestionModel currentQuestion = questionLists.get(position);
        String correctAnswer = currentQuestion.getAnswer().toString();
        String selectedOption = currentQuestion.getSelectedOption();
        //currentQuestion.setProblem(binding.question.getText().toString());

        // Update score if the selected answer is correct
        if (selectedOption.equals(correctAnswer) == isCorrect) {
            score++;
            currentQuestion.setSelectedOption(correctAnswer);
        }else {
            wrongQuestions.add(currentQuestion);
        }

        // Update score display
        binding.rightTotal.setText(String.valueOf(score));
        binding.wrongTotal.setText(String.valueOf((position + 1) - score));

        // Move to the next question with a delay
        new Handler().postDelayed(this::nextQuestion, DELAY_MS);
    }

    private void nextQuestion() {
        position++;
        if (position < questionLists.size()) {
            updateQuestion();
        } else {
            // Finish quiz and move to FinishedActivity
            Intent intent = new Intent(TrueFalseQuizActivity.this, FinishedActivity.class);
            intent.putExtra("type", "trueFalse");
            intent.putExtra("score", score);
            intent.putExtra("total", initial);
            intent.putExtra("data", questionLists);
            intent.putExtra("wrongData", wrongQuestions);
            startActivity(intent);

            // Reset variables for the next quiz attempt
            resetQuiz();
        }
    }

    private void resetQuiz() {
        initial = 10;
        score = 0;
        questionLists.clear();
        wrongQuestions.clear();
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit? Progress will be lost!").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user confirms, finish the activity
                TrueFalseQuizActivity.this.finish();
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
