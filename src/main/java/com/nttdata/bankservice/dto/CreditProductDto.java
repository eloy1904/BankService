package com.nttdata.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditProductDto implements Serializable{
    private String id;
    private String customerId; // ID del cliente propietario
    private String creditType; // "personal", "business", "credit_card"
    private double creditLimit;
    private double balance;
    private double interestRate;
}
