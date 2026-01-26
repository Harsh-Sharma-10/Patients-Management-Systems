package com.pm.patientservices.Services;

import com.pm.patientservices.Dto.PatientRequestDto;
import com.pm.patientservices.Dto.PatientResponseDto;
import com.pm.patientservices.Mapper.PatientMapper;

import com.pm.patientservices.Model.DeletedPatient;
import com.pm.patientservices.Model.PatientEntity;
import com.pm.patientservices.RPC.BillingServiceGrpcClient;
import com.pm.patientservices.Repositories.DeletedPatientRepository;
import com.pm.patientservices.Repositories.PatientRepository;
import com.pm.patientservices.exception.EmailAlreadyExitsException;
import com.pm.patientservices.exception.PatientNotexistsException;
import com.pm.patientservices.kafka.KafkaProducer;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServices {
     private final PatientRepository patientRepository;
     private final DeletedPatientRepository deletedPatientRepository;
     private final BillingServiceGrpcClient billingServiceGrpcClient;
     private final KafkaProducer kafkaProducer;



    PatientServices(PatientRepository patientRepository, DeletedPatientRepository deletedPatientRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
         this.patientRepository = patientRepository;
         this.deletedPatientRepository = deletedPatientRepository;
         this.billingServiceGrpcClient = billingServiceGrpcClient;
         this.kafkaProducer = kafkaProducer;
    }

    public PatientResponseDto getPatientById(UUID id) {
        PatientEntity patient = patientRepository.findById(id).orElse(null);

        return PatientMapper.patientResponseDto(patient);
    }

    public List<PatientResponseDto> getpatients(){
         List<PatientEntity> patients = patientRepository.findAll();
            return  patients.stream().map( PatientMapper::patientResponseDto).collect(Collectors.toList());

    }
    ///  an email address must be unique
    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto){

        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExitsException("A patient with this email  is already exists " + patientRequestDto.getEmail());
        }
        PatientEntity patient = PatientMapper.totakepatientfromRequestDto(patientRequestDto);
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
        kafkaProducer.sendEvent(patient);

        return PatientMapper.patientResponseDto(patient);
    }
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotexistsException("Patient with this id does not exist "+ id));

                 if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(),id)){
                     throw new
                             EmailAlreadyExitsException("A patient with this email is already exists | "
                             + patientRequestDto.getEmail());
                 }
                  patient.setEmail(patientRequestDto.getEmail());
                  patient.setAddress(patientRequestDto.getAddress());
                  patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getBirthDate()));
                  patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
                  PatientEntity updatedPatient = patientRepository.save(patient);
          return PatientMapper.patientResponseDto(updatedPatient);
    }
    public void deletePatient(UUID id){
      PatientEntity patient = patientRepository.findById(id)
              .orElseThrow(() -> new PatientNotexistsException("Patient with this id this already Checkedout " + id));
      DeletedPatient deletedPatient = new DeletedPatient();
      deletedPatient.setId(patient.getId());
      deletedPatient.setName(patient.getName());
      deletedPatient.setDateofbirth(patient.getDateOfBirth());
      deletedPatient.setEmail(patient.getEmail());
      deletedPatient.setPhoneNumber(patient.getPhoneNumber());
      deletedPatient.setAddress(patient.getAddress());
      deletedPatient.setRegistredate(patient.getRegisteredDate());
      deletedPatient.setDeletedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata"))); /// Time Zone Kolkata

      deletedPatientRepository.save(deletedPatient);                                   /// first save the object to deletedpatients table and then deleting it from main patient table

      patientRepository.delete(patient);                                              /// now deleting it from main patient table
    }


}

