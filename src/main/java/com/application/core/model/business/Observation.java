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
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idObservation;

    @ManyToOne
    @JoinColumn(name = "id_shipment_for_branch")
    private ShipmentForBranch idShipmentForBranch;

    @ManyToOne
    @JoinColumn(name = "id_observation_type")
    private ObservationType idObservationTYpe;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String description;

}
