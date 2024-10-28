package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "service_approvals")
@Data
public class ServiceApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Work order cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;

    @NotNull(message = "Client cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Person client;

    @NotNull(message = "Service cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private Services service;

    @NotNull(message = "Approval status cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approval_status_id", nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(length = 255)
    @Size(max = 255, message = "Issue description cannot exceed 255 characters")
    private String issueDescription;  

    @Column(length = 255)
    @Size(max = 255, message = "Solution description cannot exceed 255 characters")
    private String solutionDescription;
}


