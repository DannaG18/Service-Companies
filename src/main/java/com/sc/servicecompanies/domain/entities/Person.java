package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "persons")
@Data
public class Person {

    @Id
    private String documentNumber;

    @NotNull(message = "Person documentTypeId cannot be null")
    @Column(name = "documentype_id", length = 45, nullable = false)
    private String documentTypeId;

    @NotNull(message = "Person branchId cannot be null")
    @Column(name = "branch_id", length = 45, nullable = false)
    private String branchId;

    @NotNull(message = "Person personTypeId cannot be null")
    @Column(name = "persontype_id", length = 45, nullable = false)
    private String personTypeId;

    @NotNull(message = "Person name cannot be null")
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotNull(message = "Person last name cannot be null")
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @NotNull(message = "Person registration date cannot be null")
    @Column(name = "registration_date", length = 45, nullable = false)
    private String registrationDate;

}
