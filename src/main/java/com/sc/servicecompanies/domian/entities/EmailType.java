package com.sc.servicecompanies.domian.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "emailType")
@Data
public class EmailType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emailTypeId;

    @Column(length = 50, nullable = false)
    private String nameEmailType;
}
