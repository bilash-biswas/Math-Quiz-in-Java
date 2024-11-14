package com.example.mathquiz.Utility;

import java.util.Random;

public class ScoreComment {
    public static String getComment(int score) {
        String comment = "";
        Random random = new Random();
        int index = random.nextInt(9);
        String comments1[] = {
                "Keep going! You’ll get better with practice.",
                "It’s a start. Try again!",
                "Every step counts. Keep it up!",
                "Keep it up!",
                "Don’t give up!",
                "Practice makes perfect.",
                "Try once more!",
                "Stay motivated!",
                "Keep practicing!"
        };
        String comments2[] = {
                "Nice effort! Almost there!",
                "Good job! Just a bit more practice.",
                "Well done! You’re getting closer!",
                "Good job!",
                "Nice effort here!",
                "You're getting there!",
                "Almost halfway there!",
                "Keep improving!",
                "You’re doing well!"
        };
        String comments3[] = {
                "Great work! So close to perfect!",
                "Awesome job! Nearly there!",
                "Fantastic effort! Just a bit more!",
                "Great job there!",
                "Very well done!",
                "Almost perfect score!",
                "Keep it going!",
                "You’re nearly there!",
                "Almost perfect!"
        };
        String comments4[] = {
                "Perfect score! Well done!",
                "Amazing! You nailed it!",
                "Outstanding work! Bravo!",
                "Excellent work!",
                "Perfect score, amazing!",
                "Outstanding job!",
                "Top-notch work!",
                "Excellent result!",
                "Fantastic! Well done!"
        };
        if (score >= 0 && score <= 3) {
            comment = comments1[index];
        } else if (score >= 4 && score <= 6) {
            comment = comments2[index];
        } else if (score >= 7 && score <= 9) {
            comment = comments3[index];
        } else if (score >= 10) {
            comment = comments4[index];
        }
        return comment;
    }
}
