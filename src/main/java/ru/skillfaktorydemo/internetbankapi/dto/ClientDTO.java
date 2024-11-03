package ru.skillfaktorydemo.internetbankapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientDTO {
    private Long id;
    private BigDecimal balance;
}
