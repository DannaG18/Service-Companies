package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "service_orders")
@Data
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_orden")
    private Long nroOrden;

    @NotNull(message = "Service order date cannot be null")
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @NotNull(message = "Service order client cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Person clientId;

    @NotNull(message = "Service order employee cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employeeId;

    @NotNull(message = "Service order status cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ServiceOrderStatus orderStatusId;
}

