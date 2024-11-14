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
public class AccountDto implements Serializable{
    private String id;
    private String customerId;
    private String accountType;
    private Double balance;
    private String accountNumber;
    private String createdAt;
    private String updatedAt;
}
