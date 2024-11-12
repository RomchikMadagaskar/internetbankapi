package ru.skillfaktorydemo.internetbankapi.exception;

import lombok.Getter;

@Getter
public class NegativeSumException extends RuntimeException{

    private final int resultCode=0;
    private final String stringResult="Ошибка при выполнении операции";
    private final String message;

    public NegativeSumException (){
        message="Недопустимая сумма для операции..";
    }
}
