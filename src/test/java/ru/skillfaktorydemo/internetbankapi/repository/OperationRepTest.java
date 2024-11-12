package ru.skillfaktorydemo.internetbankapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfaktorydemo.internetbankapi.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class OperationRepTest {

    @Autowired
    private OperationRepo operationRepository;

    @Test
    void findOperationById() {
        Operation operationById = operationRepository.findById(1);
        Assertions.assertEquals(1,operationById.getId());
    }

    @Test
    void getFindAll () {
        List<Operation> operations = operationRepository.findAll();
        Assertions.assertEquals(10, operations.size());
    }

    @Test
    void getOperationListZero () {
        List<Operation> operations = operationRepository.findByIdSender(0);
        Assertions.assertTrue(operations.isEmpty());
    }

    @Test
    void getOperationListOverExist () {
        List<Operation> operations = operationRepository.findAll();
        List<Operation> operationsOver = operationRepository.findByIdSender(operations.size()+1L);
        Assertions.assertTrue(operationsOver.isEmpty());
    }

    @Test
    void findByIdSenderAndTimestampBetween() {
        LocalDateTime beginDate = LocalDateTime.of(2024, 11, 10, 20, 10, 0, 0);
        LocalDateTime finishDate = LocalDateTime.of(2024, 11, 10, 20, 10, 0, 0);
        List<Operation> operationList = operationRepository.findByIdSenderAndTimestampBetween(5, beginDate, finishDate);
        Assertions.assertEquals(3,operationList.size());
    }
}
