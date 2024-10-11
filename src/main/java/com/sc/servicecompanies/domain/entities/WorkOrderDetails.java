package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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
    // private Service assignedService;
    
    @NotNull(message = "Date cannot be null")
    @Column(nullable = false)
    private LocalDate date;
    
    @NotNull(message = "Work order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrderId;

    @NotNull(message = "Service order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_status_id", nullable = false)
    private ServiceOrderStatus serviceOrderStatusId;
}

