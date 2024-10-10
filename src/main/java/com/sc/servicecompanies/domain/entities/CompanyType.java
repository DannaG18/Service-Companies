package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "companyType")
@Data
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyTypeId;

    @Column(length = 100, nullable = false)
    private String description;
}
