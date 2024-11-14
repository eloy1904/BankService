package com.nttdata.bankservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto implements Serializable{
    private String id;
    private String productId;
    private String type;
    private Double amount;
    private LocalDateTime transactionDate;
}
