package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idIncident;

    @Column
    private String flightFriendlyId;
    @Column
    private String branchFriendlyId;
    @Column(nullable = false)
    private LocalDateTime incidentDateTime;
    @Column(nullable = false)
    private Boolean isSimulated;
    @Column(nullable = false)
    private Boolean isActive;
    @Column(nullable = false)
    private String incidentType;
}

