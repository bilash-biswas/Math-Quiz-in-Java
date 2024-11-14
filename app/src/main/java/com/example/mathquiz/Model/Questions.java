package com.example.mathquiz.Model;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Questions {

    public static Pair<String, Integer> getAdditionQuestions(int number) {
        int answer = 0;
        Random random = new Random();
        int num1 = random.nextInt(number);
        int num2 = random.nextInt(number);


        return AdditionOperation(num1, num2);
    }

    private static Pair<String, Integer> AdditionOperation(int num1, int num2) {
        int answer;
        String question = "";
        Random random = new Random();
        int operation = random.nextInt(3);
        answer = num1 + num2;
        switch (operation) {
            case 0:
                question = num1 + " + " + num2 + " = ?";
                break;
            case 1:
                question = "?" + " + " + num2 + " = " + answer;
                answer = num1;
                break;
            case 2:
                question = num1 + " + " + "?" + " = " + answer;
                answer = num2;
                break;
        }

        return Pair.create(question, answer);

    }

    public static Pair<String, Integer> getSubtractionQuestions(int number) {
        int answer;
        Random random = new Random();
        int num1 = random.nextInt(number);
        int num2 = random.nextInt(number);
        String question = "";

        int operation = random.nextInt(3);
        answer = num1 - num2;
        switch (operation) {
            case 0:
                question = num1 + " - " + num2 + " = ?";
                break;
            case 1:
                question = "?" + " - " + num2 + " = " + answer;
                answer = num1;
                break;
            case 2:
                question = num1 + " - " + "?" + " = " + answer;
                answer = num2;
                break;
        }


        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getMultiplicationQuestions(int number) {
        int answer;
        Random random = new Random();
        int num1 = random.nextInt(number);
        int num2 = random.nextInt(number);

        String question = "";

        int operation = random.nextInt(3);
        answer = num1 * num2;
        switch (operation) {
            case 0:
                question = num1 + " * " + num2 + " = ?";
                break;
            case 1:
                question = "?" + " * " + num2 + " = " + answer;
                answer = num1;
                break;
            case 2:
                question = num1 + " * " + "?" + " = " + answer;
                answer = num2;
                break;
        }

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getTableMultiplicationQuestions(int num1, int num2) {
        int answer;
        String question = num1 + " * " + num2 + " = ?";
        answer = num1 * num2;

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getTableDivisionQuestions(int num1, int num2) {
        int answer;
        String question = num1 + " / " + num2 + " = ?";
        answer = num1 / num2;

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getTrueFalseQuestions() {
        List<Character> operators = Arrays.asList('+', '-', '*');
        Random random = new Random();

        int num1 = random.nextInt(20) + 1;
        int num2 = random.nextInt(20) + 1;
        char operator = operators.get(random.nextInt(operators.size()));

        int answer = 0;
        String question;

        switch (operator) {
            case '+':
                answer = num1 + num2;
                break;
            case '-':
                answer = num1 - num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
        }

        question = num1 + " " + operator + " " + num2 + " = ";

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getDivisionQuestions() {
        int answer;
        Random random = new Random();
        int num1 = random.nextInt(20) + 1;
        int num2 = random.nextInt(20) + 2;

        int result = num1 * num2;

        String question = result + " / " + num2 + " = ?";
        answer = result / num2;

        int operation = random.nextInt(3);
        switch (operation) {
            case 0:
                question = result + " / " + num2 + " = ?";
                break;
            case 1:
                question = "?" + " / " + num2 + " = " + answer;
                answer = result;
                break;
            case 2:
                question = result + " / " + "?" + " = " + answer;
                answer = num2;
                break;
        }

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getModulusQuestions() {
        int answer;
        Random random = new Random();
        int num1 = random.nextInt(80) + 20;
        int num2 = random.nextInt(20) + 2;

        String question = "";

        int operation = random.nextInt(3);
        answer = num1 % num2;
        switch (operation) {
            case 0:
                question = num1 + " % " + num2 + " = ?";
                break;
            case 1:
                question = "?" + " % " + num2 + " = " + answer;
                answer = num1;
                break;
            case 2:
                question = num1 + " % " + "?" + " = " + answer;
                answer = num2;
                break;
        }

        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getEasyLevelQuestions() {
        Random random = new Random();
        int operator = random.nextInt(4);

        switch (operator) {
            case 0:
                return getAdditionQuestions(20);
            case 1:
                return getSubtractionQuestions(20);
            case 2:
                return getMultiplicationQuestions(20);
            case 3:
                return getDivisionQuestions();
        }
        return null;
    }
    public static Pair<String, Integer> getMediumLevelQuestions() {
        Random random = new Random();
        int operator = random.nextInt(4);

        switch (operator) {
            case 0:
                return getAdditionQuestions(100);
            case 1:
                return getSubtractionQuestions(100);
            case 2:
                return getMultiplicationQuestions(50);
            case 3:
                return getDivisionQuestions();
        }
        return null;
    }
    public static Pair<String, Integer> getHardLevelQuestions(int number) {
        Random random = new Random();
        int num1 = random.nextInt(number) + 1;
        int num2 = random.nextInt(number) + 1;
        int num3 = random.nextInt(number) + 1;
        String[] op1 = {"+", "-", "*"};
        String[] op2 = {"+", "-", "*"};
        int operator = random.nextInt(2);
        int operator1 = random.nextInt(3);
        int operator2 = random.nextInt(3);

        int answer = 0;
        String question = "";

        switch (operator) {
            case 0:
                answer = halfOperation(halfOperation(num1, num2, op1[operator1]), num3, op2[operator2]);
                question = "("+num1 + " " + op1[operator1] + " " + num2 + ") " + op2[operator2] + " " + num3 + " = ?";
                break;
            case 1:
                answer = halfOperation(num1, halfOperation(num2, num3, op1[operator1]), op2[operator2]);
                question = num1 + " " + op1[operator1] + " (" + num2 + " " + op2[operator2] + " " + num3 + ") = ?";
                break;
        }
        return Pair.create(question, answer);
    }

    public static Pair<String, Integer> getAdvancedLevelQuestions(int number) {
        Random random = new Random();
        int num1 = random.nextInt(number) + 1;
        int num2 = random.nextInt(number) + 1;
        int num3 = random.nextInt(number) + 1;
        int num4 = random.nextInt(number) + 1;
        String[] op1 = {"+", "-", "*"};
        String[] op2 = {"+", "-", "*"};
        int operator1 = random.nextInt(3);
        int operator2 = random.nextInt(3);
        int operator3 = random.nextInt(3);

        int answer = halfOperation(halfOperation(num1, num2, op1[operator1]), halfOperation(num3, num4, op2[operator3]), op2[operator2]);
        String question = "("+num1 + " " + op1[operator1] + " " + num2 + ") " + op2[operator2] + " (" + num3 + " " + op1[operator3] + " " + num4 + ") = ?";
        Log.d("@@@", question);
        return Pair.create(question, answer);
    }

    public static int halfOperation(int num1, int num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
        }
        return 0;
    }

}
