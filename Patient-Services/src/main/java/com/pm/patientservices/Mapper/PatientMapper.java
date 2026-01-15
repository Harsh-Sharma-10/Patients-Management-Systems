package com.pm.patientservices.Mapper;

import com.pm.patientservices.Dto.PatientRequestDto;
import com.pm.patientservices.Dto.PatientResponseDto;
import com.pm.patientservices.Model.Patient;
import com.pm.patientservices.Model.PatientEntity;


import java.time.LocalDate;


public class PatientMapper {  /// this class is used to convert a patient type object into PatientResponseDto type object

      public static PatientResponseDto patientResponseDto(PatientEntity patient){
           PatientResponseDto patientResponseDto = new PatientResponseDto();
           patientResponseDto.setName(patient.getName());
           patientResponseDto.setEmail(patient.getEmail());
           patientResponseDto.setAddress(patient.getAddress());
           patientResponseDto.setDateOfBirth(patient.getDateOfBirth());
           patientResponseDto.setPhoneNumber(patient.getPhoneNumber());

           return patientResponseDto;
      }
      public static Patient totakepatientfromRequestDto(PatientRequestDto patientRequestDto){
          PatientEntity patient = new Patient();
          patient.setName(patientRequestDto.getName());
          patient.setEmail(patientRequestDto.getEmail());
          patient.setAddress(patientRequestDto.getAddress());
          patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getBirthDate()));
          patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisterDate()));
          patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
          return patient;
      }
}
