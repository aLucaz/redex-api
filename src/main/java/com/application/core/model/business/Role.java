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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idRole;
    @Column(nullable = false, length = 40)
    private String friendlyId;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false, length = 40)
    private String lastModifiedBy;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false, length = 40)
    private String registeredBy;
    @Column(nullable = false)
    private LocalDateTime registeredDate;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "role")
    private Set<User> users;
}
