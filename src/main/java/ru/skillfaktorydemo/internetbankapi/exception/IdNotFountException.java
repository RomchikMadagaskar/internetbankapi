package ru.skillfaktorydemo.internetbankapi.exception;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class IdNotFountException extends NoSuchElementException {

    private final int resultCode=-1;
    private final String stringResult="Ошибка при выполнении операции";
    private final String message;

    public IdNotFountException(long id){
        message="Клиент с id "+id+" не найден";
    }
}
