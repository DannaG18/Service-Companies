package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "document_number", length = 45, nullable = false)
    private String documentNumber;

    @NotNull(message = "Person type cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_type_id", referencedColumnName = "id", nullable = false)
    private PersonType personType; 

    @NotNull(message = "Person name cannot be null")
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotNull(message = "Person last name cannot be null")
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @NotNull(message = "Person registration date cannot be null")
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @NotNull(message = "Document type cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", nullable = false)
    private DocumentType documentType; 

    @NotNull(message = "Branch cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
    private Branch branch; 

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPerson> emailPersons = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonSupply> personSupplies = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true) // Asegúrate que "client" coincida con la propiedad en ServiceOrder
    private List<ServiceOrder> serviceOrdersAsClient = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true) // Asegúrate que "employee" coincida con la propiedad en WorkOrder
    private List<WorkOrder> workOrdersAsEmployee = new ArrayList<>();
}

