package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "approvalStatus")
@Data
public class ApprovalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalStatusId;

    @Column(length = 50, nullable = false)
    private String nameApprovalStatus;
}
