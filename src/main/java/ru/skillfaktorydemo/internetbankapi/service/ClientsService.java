package ru.skillfaktorydemo.internetbankapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfaktorydemo.internetbankapi.exception.*;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;
import ru.skillfaktorydemo.internetbankapi.model.Operation;
import ru.skillfaktorydemo.internetbankapi.repository.BalansRepo;
import ru.skillfaktorydemo.internetbankapi.repository.OperationRepo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClientsService {

    @Autowired
    private BalansRepo balansRepo;
    @Autowired
    private OperationRepo operationRepo;

    @Autowired
    private OperationService operationService;

    private final int putMoneyTypeOperation=1;
    private final int takeMoneyTypeOperation=2;
    private final int transferMoneyTypeOperation=3;

    public ClientsBalance getBalance (long id){
        return balansRepo.findById((int)id).orElseThrow(()-> new IdNotFountException(id));
    }

    public List<Operation> getOperationList(long id, LocalDateTime startDate, LocalDateTime endDate){
        System.out.println(startDate+" "+endDate);
        if(startDate!=null&&endDate!=null){
            //return operationService.getOperationByDepositorId(id);
            System.out.println(startDate+" "+endDate);
            operationRepo.findByIdSenderAndTimestampBetween(id,startDate,endDate);
        }
        return operationService.getOperationById(id);
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
            operationService.addOperation(id,id,takeMoneyTypeOperation,sum);
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
        operationService.addOperation(id,id,putMoneyTypeOperation,sum);

    }

    @Transactional
    public void transferMoney(long idSender,long idReceiver,BigDecimal sum){

        ClientsBalance clientsBalance;

        if(sum.compareTo(BigDecimal.valueOf(0))<0) throw new NegativeSumException();

        ClientsBalance senderClients=balansRepo.findById((int)idSender).orElseThrow(()->new IdNotFountException(idSender));
        ClientsBalance recieverClients=balansRepo.findById((int) idReceiver).orElseThrow(() -> new IdNotFountException(idReceiver));

        BigDecimal senderBalance=senderClients.getBalance().subtract(sum);
        if(senderBalance.compareTo(sum)>=0){

            BigDecimal recieverBalance=recieverClients.getBalance().add(sum);
            senderClients.setBalance(senderBalance);
            recieverClients.setBalance(recieverBalance);
            balansRepo.save(senderClients);
            balansRepo.save(recieverClients);
            operationService.addOperation(senderClients.getIds(),recieverClients.getIds(),transferMoneyTypeOperation,sum);

        }
    }
}
