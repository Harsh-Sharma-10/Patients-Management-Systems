package com.pm.patientservices.Dto;

import com.pm.patientservices.Dto.Validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
public class PatientRequestDto{


    @NotBlank(groups = CreatePatientValidationGroup.class, message =  " name is required !")
    @Size(max = 100, message =  "name does not contains more than 100 characters")
    private  String name;

    @NotBlank(groups = CreatePatientValidationGroup.class, message =  "email is required")
    @Email(message = "email is required !!")
    private  String email;

    @NotBlank(message = "Address is required !")
    @Size(max = 100 , message =  "Address does not contains more than 100 characters")
    private String address;


    @NotBlank(message =  "Birth Date is required !")
    private String birthDate;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Register Date is not Null")
    private String registerDate;

    @NotBlank(message =  "Phone number is required !")
    @Pattern(regexp="^\\+?[0-9]{10,15}$")
    private String phoneNumber;



}
