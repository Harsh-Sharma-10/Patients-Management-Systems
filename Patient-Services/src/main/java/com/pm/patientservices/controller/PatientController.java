package com.pm.patientservices.controller;


import com.pm.patientservices.Dto.PatientRequestDto;
import com.pm.patientservices.Dto.PatientResponseDto;
import com.pm.patientservices.Dto.Validators.CreatePatientValidationGroup;
import com.pm.patientservices.Services.PatientServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "PATIENTS MANAGEMENT SYSTEMS",description = "API for managing patients and Doctors")  /// Documentation for our API
public class PatientController {

    private final PatientServices patientServices;

    public PatientController(PatientServices patientServices){
        this.patientServices = patientServices;
    }
    @GetMapping("patient/{id}")
    @Operation(summary = "Get patiets by id ")   /// a small summary about our respective endpoint
    public ResponseEntity<PatientResponseDto>getPatient(@PathVariable UUID id){
         if(patientServices.getPatientById(id) != null) {
             return new ResponseEntity<>(patientServices.getPatientById(id), HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/patients")
    @Operation(summary = "Get all the patients of hospital")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
       if(patientServices.getpatients() != null){
           return new ResponseEntity<>(patientServices.getpatients(),HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    @Operation(summary = "adding a patient    ")
    public ResponseEntity<PatientResponseDto> addPatient(@Validated({Default.class ,CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto){

            PatientResponseDto p  = patientServices.addPatient(patientRequestDto);
            return new ResponseEntity<>(p,HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    @Operation(summary = "updating an existing patient")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto p = patientServices.updatePatient(id,patientRequestDto);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary =  "deleting a patient")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id){
        patientServices.deletePatient(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
