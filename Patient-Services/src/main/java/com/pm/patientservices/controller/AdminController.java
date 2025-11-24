package com.pm.patientservices.controller;


import com.pm.patientservices.Model.DeletedPatient;
import com.pm.patientservices.Services.Logs_PatientServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/logscontroller")
public class AdminController {

    private final Logs_PatientServices  logs_PatientServices;
    AdminController(Logs_PatientServices logs_PatientServices){
        this.logs_PatientServices = logs_PatientServices;
    }

    @GetMapping("/deletedpatients")
    public ResponseEntity<List<DeletedPatient>> getdeletedpatients(){
        return new ResponseEntity<>(logs_PatientServices.get_records(), HttpStatus.OK);
    }
    @GetMapping("/deletedpatients/{id}")
    public ResponseEntity<?>getdeletedpatient(@PathVariable UUID id) {
        DeletedPatient deletedPatient = logs_PatientServices.get_record(id);
        if (deletedPatient != null) {
            return new ResponseEntity<>(deletedPatient, HttpStatus.OK);
        }
        return new ResponseEntity<>("PATIENT IS NOT CHECKED OUT YET",HttpStatus.CONFLICT);
    }
    @DeleteMapping("/deletepermantly/{id}")
    public ResponseEntity<String> deletepermantly(@PathVariable UUID id){
        logs_PatientServices.Deletepermanently(id);
          return new ResponseEntity<>("DELETED PERMANTLY",HttpStatus.NO_CONTENT);
    }
}

