package com.sc.servicecompanies.domain.entities.fkclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailId {

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "ordernumber")
    private Long nroOrder;
}
