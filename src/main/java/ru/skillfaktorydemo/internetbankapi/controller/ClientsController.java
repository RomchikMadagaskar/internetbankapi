package ru.skillfaktorydemo.internetbankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfaktorydemo.internetbankapi.exception.*;
import ru.skillfaktorydemo.internetbankapi.service.ClientsService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor

public class ClientsController {

    int metodOk=1;

    @Autowired
    private final ClientsService clientsService;

    @GetMapping("/getBalance/{id}")
    ResponseEntity<?> contGetBalance(@PathVariable Long id){
        return new ResponseEntity<>(clientsService.getBalance(id), HttpStatus.OK);
    }

    @PutMapping("/putMoney/{id}/{in}")
    ResponseEntity<?> contPutMoney(@PathVariable Long id, @PathVariable BigDecimal in) {
        clientsService.putMoney(id, in);
        return new ResponseEntity<>(new AnswerMess(metodOk,"Успех"), HttpStatus.OK);
    }

    @PutMapping("/takeMoney/{id}/{out}")
    ResponseEntity<?> contTakeMoney(@PathVariable Long id, @PathVariable BigDecimal out) {
        clientsService.takeMoney(id, out);
        return new ResponseEntity<>(new AnswerMess(metodOk, "Успех"),HttpStatus.OK);
    }

    @ExceptionHandler({IdNotFountException.class})
    public ResponseEntity<?> idNotFoundException(IdNotFountException exception) {
        return new ResponseEntity<>(new AnswerMessErr(exception.getResultCode(),exception.getStringResult(),exception.getMessage()),HttpStatus.OK);
    }

    @ExceptionHandler({NegativeSumException.class})
    public ResponseEntity<?> negativeSumException(NegativeSumException exception){
        return new ResponseEntity<>(new AnswerMessErr(exception.getResultCode(),exception.getStringResult(),exception.getMessage()),HttpStatus.OK);
    }

    @ExceptionHandler({NegativeAmountException.class})
    public ResponseEntity<?> NegativeAmountException(NegativeAmountException exception){
        return new ResponseEntity<>(new AnswerMessErr(exception.getResultCode(),exception.getStringResult(),exception.getMessage()),HttpStatus.OK);
    }
}
