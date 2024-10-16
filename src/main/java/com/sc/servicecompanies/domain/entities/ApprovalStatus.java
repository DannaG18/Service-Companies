package com.sc.servicecompanies.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "approval_status")
@Data
public class ApprovalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Approval status name cannot be blank")
    @Size(max = 50, message = "Approval status name cannot exceed 50 characters")
    @Column(name = "name_approval_status", length = 50, nullable = false)
    private String nameApprovalStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "approvalStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceApproval> serviceApprovals = new ArrayList<>();
}

