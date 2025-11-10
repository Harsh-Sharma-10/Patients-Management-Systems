package com.pm.patientservices.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data  ///  Getters && Setters && ReqArgConstructor && toString
public class PatientResponseDto {


    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private String phoneNumber;



}
