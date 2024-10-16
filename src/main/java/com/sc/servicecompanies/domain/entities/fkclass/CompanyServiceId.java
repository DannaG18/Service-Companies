package com.sc.servicecompanies.domain.entities.fkclass;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CompanyServiceId implements Serializable{
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "service_id")
    private Long serviceId;
}
