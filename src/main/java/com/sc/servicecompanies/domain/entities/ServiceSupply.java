package com.sc.servicecompanies.domain.entities;

import com.sc.servicecompanies.domain.entities.fkclass.ServiceSupplyId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "service_supply")
@Data
public class ServiceSupply {

    @EmbeddedId
    private ServiceSupplyId id;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Services service;

    @ManyToOne
    @MapsId("supplyId")
    @JoinColumn(name = "supply_id")
    private Supply supply;

    @Column(nullable = false, name = "unit_value")
    private BigDecimal unitValue;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, name = "max_stock")
    private int maxStock;

    @Column(nullable = false, name = "min_stock")
    private int minStock;
}
