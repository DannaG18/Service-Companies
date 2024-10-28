package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "email_person")
@Data
public class EmailPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Number document cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_number", referencedColumnName = "document_number", nullable = false)
    private Person person;

    @NotNull(message = "Email type id cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email_type_id", referencedColumnName = "id", nullable = false)
    private EmailType emailType;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Column(length = 100, nullable = false)
    private String email;
}

