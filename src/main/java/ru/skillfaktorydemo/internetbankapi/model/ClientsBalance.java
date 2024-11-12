package ru.skillfaktorydemo.internetbankapi.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;


@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "balance")
public class ClientsBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long ids;

    @Column(name = "thebalance")
    private BigDecimal balance;

}


