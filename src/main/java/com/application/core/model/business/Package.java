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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idPackage;

    @ManyToOne
    @JoinColumn(name = "id_shipment")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "id_package_category")
    private PackageCategory packageCategory;

    @ManyToOne
    @JoinColumn(name = "id_packaging_type")
    private PackagingType packagingType;

    @Column(nullable = false)
    private Float weight;
    @Column(nullable = false)
    private Float dimensionX;
    @Column(nullable = false)
    private Float dimensionY;
    @Column(nullable = false)
    private Float dimensionZ;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean isDangerous;
}
