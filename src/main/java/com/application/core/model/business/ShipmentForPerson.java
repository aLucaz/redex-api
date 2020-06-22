package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class ShipmentForPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_shipment")
    private Shipment idShipment;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person idPerson;

    @ManyToOne
    @JoinColumn(name = "id_person_type")
    private PersonType idPersonType;
}
