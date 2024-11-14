package com.nttdata.bankservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditProduct {

    @Id
    private String id;
    private String customerId; // Referencia al cliente propietario
    private String creditType; // "personal", "business", "credit_card"
    private double creditLimit;
    private double balance;
    private double interestRate;
    private String createdAt;
    private String updatedAt;
}
