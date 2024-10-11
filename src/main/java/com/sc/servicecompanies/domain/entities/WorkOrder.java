package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
@Entity
@Table(name = "work_orders")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Work order assignment date cannot be null")
    @Column(name = "assignment_date", nullable = false)
    private LocalDate assignmentDate;

    @NotNull(message = "Work order assignment hour cannot be null")
    @Column(name = "assignment_hour", nullable = false)
    private LocalTime assignmentHour;

    @NotNull(message = "Work order employee cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Person employee;

    @NotNull(message = "Work order order number cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_number", nullable = false)
    private ServiceOrder orderNumber;
}

