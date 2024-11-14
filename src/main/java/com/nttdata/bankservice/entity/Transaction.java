package com.nttdata.bankservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "Transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction implements Serializable{
    @Id
    private String id;
    private String productId; // Puede ser el ID de una cuenta o tarjeta
    private String type; // "DEPOSIT", "WITHDRAWAL", "PAYMENT", etc.
    private Double amount;
    private LocalDateTime transactionDate;
}
