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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer idPerson;

    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;

    @Column(nullable = false, length = 40)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String documentId;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 40)
    private String lastModifiedBy;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false, length = 40)
    private String registeredBy;

    @Column(nullable = false)
    private LocalDateTime registeredDate;
}
