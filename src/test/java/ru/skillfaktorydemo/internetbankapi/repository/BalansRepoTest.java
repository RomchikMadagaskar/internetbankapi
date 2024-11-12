package ru.skillfaktorydemo.internetbankapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BalansRepoTest {

    @Autowired
    private BalansRepo balansRepo;

    @Test
    void getFindAll(){
        List<ClientsBalance> clientsBalances=balansRepo.findAll();
        Assertions.assertEquals(1,clientsBalances.size());
    }

    @Test
    void getClientsBalanceNotNullId () {
        Optional<ClientsBalance> clientsBalance = balansRepo.findById(0);
        Assertions.assertEquals(Optional.empty(),clientsBalance);
    }

    @Test
    void getClientsBalanceOverExistId () {
        List<ClientsBalance> clientsBalance = balansRepo.findAll();
        Optional<ClientsBalance> clientsBalanceOpt = balansRepo.findById(clientsBalance.size()+1);
        Assertions.assertEquals(Optional.empty(),clientsBalance);
    }
}
