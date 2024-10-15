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
import lombok.Data;

@Entity
@Table(name = "status_service_order")
@Data
public class StatusServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_status_service_order", length = 50, nullable = false)
    private String nameStatusServiceOrder;

    @JsonIgnore
    @OneToMany(mappedBy = "statusServiceOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkOrderDetails> workOrderDetails = new ArrayList<>();
}

