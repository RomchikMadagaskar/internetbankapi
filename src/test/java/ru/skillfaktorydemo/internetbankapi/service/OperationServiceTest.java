package ru.skillfaktorydemo.internetbankapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skillfaktorydemo.internetbankapi.model.Operation;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @Mock
    private Operation operationRepository;

    @InjectMocks
    private OperationService operationService;

    @Test
    void addOperation() {
    }

    @Test
    void getOperationById() {
    }

}
