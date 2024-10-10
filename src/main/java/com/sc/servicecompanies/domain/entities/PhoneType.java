package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "phoneType")
@Data
public class PhoneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneTypeId;

    @Column(length = 50, nullable = false)
    private String description;
}