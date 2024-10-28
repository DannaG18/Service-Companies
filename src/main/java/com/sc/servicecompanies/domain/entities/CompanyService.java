package com.sc.servicecompanies.domain.entities;

import java.math.BigDecimal;

import com.sc.servicecompanies.domain.entities.fkclass.CompanyServiceId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "company_service")
@Data
public class CompanyService {
    @EmbeddedId
    @NotNull(message = "The composite key (id) cannot be null")
    private CompanyServiceId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Services service;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("branchId")
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @NotNull(message = "Service value cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Service value must be greater than 0")
    @Column(name = "service_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal serviceValue;
}
