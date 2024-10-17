package com.sc.servicecompanies.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "work_order_details")
@Data
public class WorkOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Assigned service cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_service_id", nullable = false)
    private Services assignedService;
    
    @NotNull(message = "Date cannot be null")
    @Column(nullable = false)
    private LocalDate date;
    
    @NotNull(message = "Work order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;

    @NotNull(message = "Status service order id order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_service_order_id", nullable = false) 
    private StatusServiceOrder statusServiceOrder;
}
