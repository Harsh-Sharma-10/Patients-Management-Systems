package com.pm.patientservices.Services;

import com.pm.patientservices.Dto.PatientRequestDto;
import com.pm.patientservices.Dto.PatientResponseDto;
import com.pm.patientservices.Mapper.PatientMapper;

import com.pm.patientservices.Model.DeletedPatient;
import com.pm.patientservices.Model.Patient;
import com.pm.patientservices.RPC.BillingServiceGrpcClient;
import com.pm.patientservices.Repositories.DeletedPatientRepository;
import com.pm.patientservices.Repositories.PatientRepository;
import com.pm.patientservices.exception.EmailAlreadyExitsException;
import com.pm.patientservices.exception.PatientNotexistsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServices {
     private final  PatientRepository patientRepository;
     private  final DeletedPatientRepository deletedPatientRepository;
     private final BillingServiceGrpcClient billingServiceGrpcClient;

    PatientServices(PatientRepository patientRepository, DeletedPatientRepository deletedPatientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
         this.patientRepository = patientRepository;
         this.deletedPatientRepository = deletedPatientRepository;
         this.billingServiceGrpcClient = billingServiceGrpcClient;

     }

    public PatientResponseDto getPatientById(UUID id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        return PatientMapper.patientResponseDto(patient);
    }

    public List<PatientResponseDto> getpatients(){
         List<Patient> patients = patientRepository.findAll();
            return  patients.stream().map( PatientMapper::patientResponseDto).toList();

    }
    ///  an email address must be unique
    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto){

        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExitsException("A patient with this email is already exists" + patientRequestDto.getEmail());
        }
        Patient patient = PatientMapper.totakepatientfromRequestDto(patientRequestDto);
        patientRepository.save(patient);
        try {
            billingServiceGrpcClient
                    .createBilling(
                            patient.getId().toString(),
                            patient.getName(),
                            patient.getEmail());
        }catch (Exception e){
            System.out.println("Failed to call Billing gRPC service: " + e.getMessage());
        }
        return PatientMapper.patientResponseDto(patient);
    }
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotexistsException("Patient with this id does not exist "+ id));

                 if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),id)){
                     throw new
                             EmailAlreadyExitsException("A patient with this email is already exists | "
                             + patientRequestDto.getEmail());
                 }
                  patient.setEmail(patientRequestDto.getEmail());
                  patient.setAddress(patientRequestDto.getAddress());
                  patient.setDateofbirth(LocalDate.parse(patientRequestDto.getBirthDate()));
                  patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
                  Patient updatedPatient = patientRepository.save(patient);
          return PatientMapper.patientResponseDto(updatedPatient);
    }
    public void deletePatient(UUID id){
      Patient patient = patientRepository.findById(id)
              .orElseThrow(() -> new PatientNotexistsException("Patient with this id this already Checkedout " + id));
      DeletedPatient deletedPatient = new DeletedPatient();
      deletedPatient.setId(patient.getId());
      deletedPatient.setName(patient.getName());
      deletedPatient.setDateofbirth(patient.getDateofbirth());
      deletedPatient.setEmail(patient.getEmail());
      deletedPatient.setPhoneNumber(patient.getPhoneNumber());
      deletedPatient.setAddress(patient.getAddress());
      deletedPatient.setRegistredate(patient.getRegistredate());
      deletedPatient.setDeletedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));

      deletedPatientRepository.save(deletedPatient);/// first save the object to deletedpatients table and then deleting it from main patient table

      patientRepository.delete(patient); /// now deleting it from main patient table
    }

}

