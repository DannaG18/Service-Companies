package com.sc.servicecompanies.domain.entities.fkclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ServiceSupplyId  implements Serializable {

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "supply_id")
    private Long supplyId;
}
