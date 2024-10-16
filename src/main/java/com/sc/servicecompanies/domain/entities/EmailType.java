package com.sc.servicecompanies.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "email_type")
@Data
public class EmailType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name email type cannot be empty")
    @Size(max = 50, message = "Name email type must be at most 50 characters")
    @Column(name = "name_email_type", length = 50, nullable = false)
    private String nameEmailType;

    @JsonIgnore
    @OneToMany(mappedBy = "emailType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPerson> emailPersons = new ArrayList<>();
}

