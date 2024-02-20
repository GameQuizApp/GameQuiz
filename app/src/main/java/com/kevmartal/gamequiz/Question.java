package com.kevmartal.gamequiz;

import java.util.List;

public class Question {
    private String imageUrl;
    private List<String> options;
    private int correctAnswerIndex;

    // Constructor
    public Question(String imageUrl, List<String> options, int correctAnswerIndex) {
        this.imageUrl = imageUrl;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getters
    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
