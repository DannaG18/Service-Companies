package com.sc.servicecompanies.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "NIT cannot be empty")
    @Size(max = 100, message = "NIT must be at most 100 characters")
    @Column(length = 100, nullable = false)
    private String nit;

    @NotNull(message = "CompanyId cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @NotNull(message = "CountryId cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @NotBlank(message = "Branch name cannot be blank")
    @Size(max = 100, message = "Branch name must be at most 100 characters")
    @Column(name = "name_branch", length = 100, nullable = false)
    private String nameBranch;

    @PastOrPresent(message = "Creation date cannot be in the future")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyService> companyServices = new ArrayList<>();
}
