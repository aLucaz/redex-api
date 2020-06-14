package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class EtFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idEdFlight;
    @Column(nullable = false, length = 20)
    private String friendlyId;
    @Column(nullable = false, length = 10)
    private String departurePoint;
    @Column(nullable = false, length = 10)
    private String departureTime;
    @Column(nullable = false, length = 10)
    private String arrivalPoint;
    @Column(nullable = false, length = 10)
    private String arrivalTime;
    @Column(nullable = false, length = 10)
    private String etFlightDate;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer isActive;
}
