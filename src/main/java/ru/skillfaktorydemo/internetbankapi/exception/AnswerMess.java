package ru.skillfaktorydemo.internetbankapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerMess {
    private int responseCode;
    private String stringResponse;
}

