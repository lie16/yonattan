package com.training.yonattan.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Column(length=20, unique=true, insertable = true, updatable = false)
    private String nik;
    private String employeeName;
    private String address;
    private String city;
    private String province;
    private String email;
    private String phoneNumber;
    private Boolean active;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Department department;
}
