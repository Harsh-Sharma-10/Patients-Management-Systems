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
public class Patient {/// POJO

    @Id
    @GeneratedValue( strategy =  GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String Address;

    @NotNull
    private LocalDate dateofbirth;

    @NotNull
    private LocalDate registredate;

    @NotNull
    @Pattern(regexp="^\\+?[0-9]{10,15}$")
    private String phoneNumber;


}
