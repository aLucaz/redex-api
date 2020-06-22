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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idPackage;

    @ManyToOne
    @JoinColumn(name = "id_shipment")
    private Shipment idShipment;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private PackageCategory idCategory;

    @ManyToOne
    @JoinColumn(name = "id_packaging")
    private PackagingType idPackaging;

    @Column(nullable = false)
    private String description;


}
