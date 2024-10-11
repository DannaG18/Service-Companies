package com.sc.servicecompanies.domain.entities;

import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "personsupply")
@Data
public class PersonSupply {

    @EmbeddedId
    private PersonSupplyId id;

    @Column(nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private String descripcion;
}
