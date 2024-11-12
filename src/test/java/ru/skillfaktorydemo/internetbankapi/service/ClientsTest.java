package ru.skillfaktorydemo.internetbankapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;
import ru.skillfaktorydemo.internetbankapi.repository.BalansRepo;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientsTest {

    @Mock
    private BalansRepo balansRepo;

    @InjectMocks
    private ClientsService clientsService;

    @BeforeEach
    public void setup(){

    }

    @Test
    void getClients(){
        ClientsBalance clientsBalance=ClientsBalance.builder().ids(1L).balance(BigDecimal.valueOf(1000.00)).build();

        Mockito.when(balansRepo.findById(1)).thenReturn(Optional.of(clientsBalance));

        Optional<ClientsBalance> balanceTest=balansRepo.findById(1);
        System.out.println(balanceTest);
        Assertions.assertEquals(1L,balanceTest.get().getIds());
    }

    @Test
    void getBalance() {
    }

    @Test
    void putMoney() {
    }

    @Test
    void takeMoney() {
    }

    @Test
    void transferMoney() {
    }
}
