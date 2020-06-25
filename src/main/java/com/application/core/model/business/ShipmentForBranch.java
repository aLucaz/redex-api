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
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "id_shipment_state")
    private ShipmentState shipmentState;

    @Column
    private LocalDateTime currentArrivalDateTime;
    @Column(nullable = false)
    private LocalDateTime currentDepartureDateTime;
    @Column(nullable = false)
    private LocalDateTime futureArrivalDateTime;
    @Column(nullable = false)
    private String flightFriendlyId;
    @Column(nullable = false)
    private Integer sequence;
}
