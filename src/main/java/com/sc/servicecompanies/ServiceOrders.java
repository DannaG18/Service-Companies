package com.sc.servicecompanies;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "service_orders")
public class ServiceOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_orden")
    private Long nroOrden;

    @NotNull(message = "Service order date cannot be null")
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @NotNull(message = "Service order clientId cannot be null")
    @Column(name = "client_id", length = 45, nullable = false)
    private String clientId;

    @NotNull(message = "Service order employeeId cannot be null")
    @Column(name = "employee_id", length = 45, nullable = false)
    private String employeeId;

    @NotNull(message = "Service order statusId cannot be null")
    @Column(name = "status_id", length = 45, nullable = false)
    private String statusId;
}
