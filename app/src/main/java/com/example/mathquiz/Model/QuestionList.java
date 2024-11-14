package com.example.mathquiz.Model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionList {
    private String questionType;
    private int number;
    private ArrayList<Pair<String, Integer>> questionList = new ArrayList<>(10);
    private ArrayList<QuestionModel> questionDataList = new ArrayList<>(10);

    public QuestionList(String questionType) {
        this.questionType = questionType;
    }

    public QuestionList(String questionType, int number) {
        this.questionType = questionType;
        this.number = number;
    }

    public QuestionModel generateRapidFireQuestions(String level) {
        Pair<String, Integer> question = null;
        if (level.equals("easy")){
            question = Questions.getEasyLevelQuestions();
        }else if (level.equals("medium")){
            question = Questions.getMediumLevelQuestions();
        }else if (level.equals("hard")){
            question = Questions.getHardLevelQuestions(12);
        }else if (level.equals("advance")){
            question = Questions.getAdvancedLevelQuestions(12);
        }
        ArrayList<String> optionList = new ArrayList<>();
        int answer = question.second;
        Random random = new Random();
        optionList.add(String.valueOf(answer));
        optionList.add(String.valueOf(answer + 1 + random.nextInt(3)));
        optionList.add(String.valueOf(answer + 4 + random.nextInt(3)));
        optionList.add(String.valueOf(answer - 4 + random.nextInt(3)));

        Collections.shuffle(optionList);

        return new QuestionModel(
                question.first,
                answer,
                optionList.get(0),
                optionList.get(1),
                optionList.get(2),
                optionList.get(3),
                "none"
        );
    }

    public void generateQuestions() {

        if (questionType.equals("addition")) {
            for (int i = 0; i < 10; i++) {
                Pair<String, Integer> questions = Questions.getAdditionQuestions(100);
                questionList.add(questions);
            }
        } else if (questionType.equals("subtraction")) {
            for (int i = 0; i < 10; i++) {
                Pair<String, Integer> questions = Questions.getSubtractionQuestions(100);
                questionList.add(questions);
            }

        } else if (questionType.equals("tableMultiplication")) {
            for (int i = 1; i <= 10; i++) {
                Pair<String, Integer> questions = Questions.getTableMultiplicationQuestions(number, i);
                questionList.add(questions);
            }
            Collections.shuffle(questionList);
        } else if (questionType.equals("tableDivision")) {
            for (int i = 1; i <= 10; i++) {
                Pair<String, Integer> questions = Questions.getTableDivisionQuestions(number * i, number);
                questionList.add(questions);
            }
            Collections.shuffle(questionList);
        } else if (questionType.equals("trueFalse")) {
            for (int i = 1; i <= 10; i++) {
                Pair<String, Integer> questions = Questions.getTrueFalseQuestions();
                questionList.add(questions);
            }
            Collections.shuffle(questionList);
        } else if (questionType.equals("multiplication")) {
            for (int i = 0; i < 10; i++) {
                Pair<String, Integer> questions = Questions.getMultiplicationQuestions(50);
                questionList.add(questions);
            }
        } else if (questionType.equals("division")) {
            for (int i = 0; i < 10; i++) {
                Pair<String, Integer> questions = Questions.getDivisionQuestions();
                questionList.add(questions);
            }
        } else if (questionType.equals("modulus")) {
            for (int i = 0; i < 10; i++) {
                Pair<String, Integer> questions = Questions.getModulusQuestions();
                questionList.add(questions);
            }
        }


    }

    private List<String> getOptions(int position) {
        ArrayList<String> optionList = new ArrayList<>();
        int answer = questionList.get(position).second;
        Random random = new Random();
        optionList.add(String.valueOf(answer));
        optionList.add(String.valueOf(answer + 1 + random.nextInt(3)));
        optionList.add(String.valueOf(answer + 4 + random.nextInt(3)));
        optionList.add(String.valueOf(answer - 4 + random.nextInt(3)));

        Collections.shuffle(optionList);
        return optionList;
    }

    public ArrayList<QuestionModel> getQuestionDataList() {
        generateQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            List<String> optionList = getOptions(i);
            questionDataList.add(new QuestionModel(
                    questionList.get(i).first,
                    questionList.get(i).second,
                    optionList.get(0),
                    optionList.get(1),
                    optionList.get(2),
                    optionList.get(3),
                    "none"
            ));
        }
        return questionDataList;
    }
}
