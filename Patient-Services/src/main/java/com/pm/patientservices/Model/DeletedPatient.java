package com.pm.patientservices.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Deleted_Patients")
@Data
public class DeletedPatient {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String address;
    private LocalDate dateofbirth;
    private LocalDate registredate;
    private String phoneNumber;

    private LocalDateTime deletedAt;


}
