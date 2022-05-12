package com.sirioitalia.api.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Getter
    @Setter
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Getter
    @Setter
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "postalCode")
    private String postCode;

    @Getter
    @Setter
    @Column(name = "Country")
    private String country;

    @Getter
    @Setter
    @Column(name = "createdAt")
    private Date createdAt;

    @Getter
    @Setter
    @ManyToOne( optional = false)
    @JoinColumn(name = "\"roleId\"", nullable = false)
    private  Role roleId;
}