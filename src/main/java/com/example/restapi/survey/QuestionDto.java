package com.example.restapi.survey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionDto {
    private String id;
    private String description;
    private List<String> options;
    private String correctAnswer;

    public QuestionDto() {

    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
