package ru.skillfaktorydemo.internetbankapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;
import ru.skillfaktorydemo.internetbankapi.model.Operation;
import ru.skillfaktorydemo.internetbankapi.repository.OperationRepo;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OperationService {

    private final OperationRepo operationRepository;
    private ClientsBalance depositor;

    @Autowired
    public OperationService (OperationRepo operationRepository) {
        this.operationRepository = operationRepository;
    }

    public void addOperation(Long idSender, Long idReceiver, int operationType,
                             BigDecimal changeBalance) {
        final var newOperation = new Operation();
        newOperation.setIdSender(idSender);
        newOperation.setIdReceiver(idReceiver);
        newOperation.setOperationType(operationType);
        newOperation.setSum(changeBalance);
        newOperation.setTimestamp(java.time.LocalDateTime.now());
        operationRepository.save(newOperation);
    }

    public List<Operation> getOperationById(long senderId) {
        return operationRepository.findByIdSender(senderId);
    }

}
