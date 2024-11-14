package com.nttdata.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto implements Serializable {

    private String id;
    private String type; // "personal" o "business"
    private String name;
    private String contactInfo;
}
