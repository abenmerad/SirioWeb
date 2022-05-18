package com.sirioitalia.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sirioitalia.api.embeddable.Address;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "\"firstName\"")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "\"lastName\"")
    private String lastName;

    @Email
    @Getter
    @Setter
    @Column
    private String email;

    @Getter
    @Setter
    @Column(name = "\"passwordHash\"")
    private String passwordHash;

    @Past
    @Getter
    @Setter
    @Column(name = "\"birthDate\"")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Getter
    @Setter
    @Column(name = "\"phoneNumber\"")
    private String phoneNumber;

    @NotNull
    @Getter
    @Setter
    @Embedded
    private Address address;

    @Getter
    @CreatedDate
    @Column(name = "\"createdAt\"", nullable = false)
    private Date createdAt;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "\"roleId\"", nullable = false)
    private  Role role;
}