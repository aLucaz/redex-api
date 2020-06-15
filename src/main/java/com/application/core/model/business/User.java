package com.application.core.model.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Employee employee;

    @Column(nullable = false, length = 40)
    private String email;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(nullable = false)
    private Boolean isActive;
    @Column(nullable = false, length = 20)
    private String documentId;
    @Column(nullable = false, length = 20)
    private String phone;
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
