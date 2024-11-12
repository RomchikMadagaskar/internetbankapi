package ru.skillfaktorydemo.internetbankapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OPERATION")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ID_SENDER")
    private Long idSender;

    @Column (name = "ID_RECEIVER")
    private Long idReceiver;

    @Column (name = "OPERATIONTYPE")
    private int operationType;

    @Column (name = "SUM")
    private BigDecimal sum;

    @Column(name="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

}
