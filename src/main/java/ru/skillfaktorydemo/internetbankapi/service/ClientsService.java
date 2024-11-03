package ru.skillfaktorydemo.internetbankapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfaktorydemo.internetbankapi.exception.*;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;
import ru.skillfaktorydemo.internetbankapi.repository.BalansRepo;

import java.math.BigDecimal;

@Slf4j
@Service
public class ClientsService {

    @Autowired
    private BalansRepo balansRepo;

    public ClientsBalance getBalance (long id){
        return balansRepo.findById((int)id).orElseThrow(()-> new IdNotFountException(id));
    }

    @Transactional
    public void takeMoney(long id, BigDecimal sum){
        final ClientsBalance clientsBalance;

        if(sum.compareTo(BigDecimal.valueOf(0))<0) throw new NegativeSumException();

        clientsBalance=balansRepo.findById((int) id).orElseThrow(()->new IdNotFountException(id));

        BigDecimal currentBalance =clientsBalance.getBalance();
        if(currentBalance.compareTo(sum)>=0){
            BigDecimal newBalance=currentBalance.subtract(sum);
            clientsBalance.setBalance(newBalance);
            balansRepo.save(clientsBalance);
        }else throw new NegativeAmountException(id);

    }

    @Transactional
    public void putMoney(long id, BigDecimal sum){

        ClientsBalance clientsBalance;

        if(sum.compareTo(BigDecimal.valueOf(0))<0) throw new NegativeSumException();

        clientsBalance=balansRepo.findById((int) id).orElseThrow(()->new IdNotFountException(id));

        BigDecimal currentBalance =clientsBalance.getBalance();
        BigDecimal newBalance=currentBalance.add(sum);
        clientsBalance.setBalance(newBalance);
        balansRepo.save(clientsBalance);
    }

}
