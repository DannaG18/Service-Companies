package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "document_number", length = 45, nullable = false)
    private String documentNumber;

    @NotNull(message = "Person personTypeId cannot be null")
    @Column(name = "person_type_id", length = 45, nullable = false)
    private PersonType personTypeId;
    
    @NotNull(message = "Person name cannot be null")
    @Column(name = "name", length = 45, nullable = false)
    private String name;
    
    @NotNull(message = "Person last name cannot be null")
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;
    
    @NotNull(message = "Person registration date cannot be null")
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @NotNull(message = "Person documentTypeId cannot be null")
    @Column(name = "document_type_id", length = 45, nullable = false)
    private DocumentType documentTypeId;
    
    @NotNull(message = "Person branchId cannot be null")
    @Column(name = "branch_id", length = 45, nullable = false)
    private Branch branchId;
}