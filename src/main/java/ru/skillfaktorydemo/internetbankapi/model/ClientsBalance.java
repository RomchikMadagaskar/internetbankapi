package ru.skillfaktorydemo.internetbankapi.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@EqualsAndHashCode
@Data
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


