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
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idShipment;

    @Column(nullable = false)
    private Float price;

    @Column//(nullable = false)
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
