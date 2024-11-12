package ru.skillfaktorydemo.internetbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfaktorydemo.internetbankapi.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {

    @Transactional(readOnly = true)
    List<Operation> findByIdSenderAndTimestampBetween (long idSender, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional(readOnly = true)
    List<Operation> findByIdSender (long idSender);

    @Transactional(readOnly = true) Operation findById (long idSender);
}
