package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class ShipmentForBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_shipment")
    private Shipment idShipment;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch idBranch;

    @ManyToOne
    @JoinColumn(name = "id_shipment_state")
    private ShipmentState idShipmentState;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @Column(nullable = false)
    private Boolean checked;

    @Column(nullable = false)
    private String flightFriendlyId;

    @Column//(nullable = false)
    private Integer Sequence;

}
