package com.pm.patientservices.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "registredate")
    private LocalDate registeredDate;

    @NotNull
    @Pattern(regexp="^\\+?[0-9]{10,15}$")
    @Column(name = "phone_number")
    private String phoneNumber;
}
