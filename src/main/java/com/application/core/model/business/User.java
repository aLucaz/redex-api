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
public class
User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idUser;
    @OneToMany(mappedBy = "idUser")
    Set<Employee> employees;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @Column(nullable = false, length = 40)
    private String document;
    @Column(nullable = false, length = 40)
    private String documentType;
    @Column(nullable = false, length = 40)
    private String email;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private Integer validEmail;
    @Column(nullable = false)
    private Boolean isActive;
    @Column(nullable = false, length = 40)
    private String username;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(nullable = false, length = 40)
    private String maidenName;
    @Column(nullable = false, length = 250)
    private String passwordHash;
    @Column(nullable = false, length = 50)
    private String passwordSalt;
    @Column(nullable = false, length = 40)
    private String lastModifiedBy;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    @Column(nullable = false, length = 40)
    private String registeredBy;
    @Column(nullable = false)
    private LocalDateTime registeredDate;
}
