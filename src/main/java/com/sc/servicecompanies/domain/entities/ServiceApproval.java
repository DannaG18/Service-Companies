package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "service_approvals")
@Data
public class ServiceApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Work order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;

    @NotNull(message = "Client cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Person client;

    @NotNull(message = "Service cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @NotNull(message = "Approval status cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_status_id", nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(length = 255)
    private String issueDescription;  

    @Column(length = 255)
    private String solutionDescription;
}

