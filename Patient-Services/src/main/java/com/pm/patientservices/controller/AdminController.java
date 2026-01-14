package com.pm.patientservices.controller;


import com.pm.patientservices.Model.DeletedPatient;
import com.pm.patientservices.Services.Logs_PatientServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/logscontroller")
@Tag(name = "PATIENTS MANAGEMENT SYSTEMS",description = "API for DELETED/CHECKED OUT PATIENTS")
public class AdminController {

    private final Logs_PatientServices  logs_PatientServices;
    AdminController(Logs_PatientServices logs_PatientServices){
        this.logs_PatientServices = logs_PatientServices;
    }

    @GetMapping("/deletedpatients")
    @Operation(summary = "To get the list of deleted patients")
    public ResponseEntity<List<DeletedPatient>> getdeletedpatients(){
        return new ResponseEntity<>(logs_PatientServices.get_records(), HttpStatus.OK);
    }
    @GetMapping("/deletedpatients/{id}")
    @Operation(summary = "To get record of a deleted person")
    public ResponseEntity<?>getdeletedpatient(@PathVariable UUID id) {
        DeletedPatient deletedPatient = logs_PatientServices.get_record(id);
        if (deletedPatient != null) {
            return new ResponseEntity<>(deletedPatient, HttpStatus.OK);
        }
        return new ResponseEntity<>("PATIENT IS NOT CHECKED OUT YET",HttpStatus.CONFLICT);
    }
    @DeleteMapping("/deletepermantly/{id}")
    @Operation(summary = "To delete the patient's record permanently")
    public ResponseEntity<String> deletepermantly(@PathVariable UUID id){
         logs_PatientServices.Deletepermanently(id);
          return new ResponseEntity<>("DELETED PERMANTLY",HttpStatus.NO_CONTENT);
    }
}

