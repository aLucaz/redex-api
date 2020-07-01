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
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idShipment;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "shipment")
    private Set<ShipmentForBranch> shipmentForBranches;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "shipment")
    private Set<Package> packages;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "shipment")
    private Set<ShipmentForPerson> shipmentForPeople;

    @Column(nullable = false)
    private Float price;
    @Column
    private Boolean isSimulated;
    @Column
    private Boolean isActive;
    @Column
    private String referenceCode;
    @Column(nullable = false)
    private String lastModifiedBy;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false)
    private String registeredBy;
    @Column(nullable = false)
    private LocalDateTime registeredDate;
}
