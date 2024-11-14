package com.nttdata.bankservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "BankAccount")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable{
    @Id
    private String id;
    private String customerId; // ID del cliente asociado
    private String accountType; // "SAVINGS", "CHECKING", "FIXED_TERM"
    private String accountNumber;
    private Double balance; // Saldo Actual de la cuenta
    private String createdAt;
    private String updatedAt;
}
