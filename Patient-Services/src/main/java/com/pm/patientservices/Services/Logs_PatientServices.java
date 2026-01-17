package com.pm.patientservices.Services;


import com.pm.patientservices.Model.DeletedPatient;
import com.pm.patientservices.Repositories.DeletedPatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
public class Logs_PatientServices {
    private final DeletedPatientRepository deletedPatientRepository;

    Logs_PatientServices(DeletedPatientRepository deletedPatientRepository) {
        this.deletedPatientRepository = deletedPatientRepository;
    }
    public List<DeletedPatient> get_records(){
        List<DeletedPatient> deletedPatients = deletedPatientRepository.findAll();
        return deletedPatients;
    }

    public DeletedPatient get_record(UUID id){
        DeletedPatient deletedPatient = deletedPatientRepository.findById(id).orElse(null);
        return deletedPatient;
    }

    public void Deletepermanently(UUID id){
        deletedPatientRepository.deleteById(id);
    }

}
