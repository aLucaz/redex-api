package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idBranch;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "branch")
    Set<Employee> employees;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "branch")
    Set<ShipmentForBranch> shipmentForBranches;

    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String friendlyId;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, length = 40)
    private String continent;
    @Column(nullable = false, length = 40)
    private String latitude;
    @Column(nullable = false, length = 40)
    private String longitude;
    @Column(nullable = false)
    private Integer isActive;
    @Column(nullable = false, length = 40)
    private String lastModifiedBy;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false, length = 40)
    private String registeredBy;
    @Column(nullable = false)
    private LocalDateTime registeredDate;
}
