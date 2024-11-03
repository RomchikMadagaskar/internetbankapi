package ru.skillfaktorydemo.internetbankapi.exception;

import lombok.Getter;

@Getter
public class NegativeAmountException extends RuntimeException{

    private final int resultCode=0;
    private final String stringResult="Недостаточно средств";
    private final String message;

    public NegativeAmountException (long id){
        message="Недостаточно средств на счете id: "+id;
    }
}
