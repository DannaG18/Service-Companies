package com.sc.servicecompanies.domain.entities;

import com.sc.servicecompanies.domain.entities.fkclass.OrderDetailId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetail")
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EmbeddedId
    private OrderDetailId orderDetailId;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @MapsId("nroOrder")
    @JoinColumn(name = "nro_orden")
    private ServiceOrder serviceOrders;

    @Column(nullable = false)
    private BigDecimal serviceValue;
}
