package com.daniel.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DummyArray{
    @JsonProperty("user_questions")
    public ArrayList<UserQuestions> userQuestions;
}
