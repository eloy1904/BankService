package com.nttdata.bankservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer implements Serializable {
    @Id
    private String id;
    private String type; // "personal" o "business"
    private String name;
    private String contactInfo;
    private String createdAt;
    private String updatedAt;

    //private String orgId;
    //private String orgName;
    //private String orgEmail;
    //private Long contact;
    //private boolean isActive;
}
