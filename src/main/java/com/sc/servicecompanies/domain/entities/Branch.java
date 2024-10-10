package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchId;

    private int countryId;

    private int companyId;

    private String nameBranch;

    private LocalDate creationDate;
}
