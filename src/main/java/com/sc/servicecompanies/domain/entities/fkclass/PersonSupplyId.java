package com.sc.servicecompanies.domain.entities.fkclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PersonSupplyId implements Serializable {

    @Column(name = "supply_id")
    private Long supplyId;

    @Column(name = "documentnumber")
    private String documentNumber;
}
