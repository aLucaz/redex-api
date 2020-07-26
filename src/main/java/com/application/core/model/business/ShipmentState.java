package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class ShipmentState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idShipmentState;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "shipmentState")
    private Set<ShipmentForBranch> shipmentForBranches;

    @Column(nullable = false, length = 40)
    private String friendlyId;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 1000)
    private String description;
}
