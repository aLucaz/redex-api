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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idPersonType;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "personType")
    private Set<ShipmentForPerson> shipmentForPeople;

    @Column(nullable = false, length = 40)
    private String friendlyId;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;
}
