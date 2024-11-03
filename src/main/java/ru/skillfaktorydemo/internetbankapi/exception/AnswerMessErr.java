package ru.skillfaktorydemo.internetbankapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerMessErr {

    private int responseCode;
    private String stringResponse;
    private String errMessage;


}
