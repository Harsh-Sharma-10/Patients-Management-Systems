package com.pm.patientservices.Dto;

import com.pm.patientservices.Dto.Validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


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

    public @NotBlank(groups = CreatePatientValidationGroup.class, message = " name is required !") @Size(max = 100, message = "name does not contains more than 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(groups = CreatePatientValidationGroup.class, message = " name is required !") @Size(max = 100, message = "name does not contains more than 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(groups = CreatePatientValidationGroup.class, message = "email is required") @Email(message = "email is required !!") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(groups = CreatePatientValidationGroup.class, message = "email is required") @Email(message = "email is required !!") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required !") @Size(max = 100, message = "Address does not contains more than 100 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required !") @Size(max = 100, message = "Address does not contains more than 100 characters") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Birth Date is required !") String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotBlank(message = "Birth Date is required !") String birthDate) {
        this.birthDate = birthDate;
    }

    public @NotBlank(groups = CreatePatientValidationGroup.class, message = "Register Date is not Null") String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(@NotBlank(groups = CreatePatientValidationGroup.class, message = "Register Date is not Null") String registerDate) {
        this.registerDate = registerDate;
    }

    public @NotBlank(message = "Phone number is required !") @Pattern(regexp = "^\\+?[0-9]{10,15}$") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required !") @Pattern(regexp = "^\\+?[0-9]{10,15}$") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
