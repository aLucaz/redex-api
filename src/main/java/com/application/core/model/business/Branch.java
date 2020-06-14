package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idBranch;
    @OneToMany(mappedBy = "idBranch")
    Set<Employee> employees;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String friendlyId;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, length = 40)
    private String continent;
    @Column(nullable = false, length = 40)
    private String latitude;
    @Column(nullable = false, length = 40)
    private String longitude;
    @Column(nullable = false)
    private Integer isActive;
    @Column(nullable = false, length = 40)
    private String lastModifiedBy;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false, length = 40)
    private String registeredBy;
    @Column(nullable = false)
    private LocalDateTime registeredDate;
}
