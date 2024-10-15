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
@Table(name = "status_order")
@Data
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name status order cannot be empty")
    @Size(max = 50, message = "Name status order must be at most 50 characters")
    @Column(length = 50, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "statusOrder", cascade = CascadeType.ALL, orphanRemoval = true) // Cambia "status_order" por "statusOrder"
    private List<ServiceOrder> serviceOrders = new ArrayList<>();
}
