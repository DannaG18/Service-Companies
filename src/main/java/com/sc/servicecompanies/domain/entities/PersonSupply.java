package com.sc.servicecompanies.domain.entities;

import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "person_supply")
@Data
public class PersonSupply {

    @EmbeddedId
    private PersonSupplyId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("supplyId")
    @JoinColumn(name = "supply_id")
    private Supply supply;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("documentNumber")
    @JoinColumn(name = "document_number")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Services service;

    @Column(nullable = false)
    private String descripcion;
}
