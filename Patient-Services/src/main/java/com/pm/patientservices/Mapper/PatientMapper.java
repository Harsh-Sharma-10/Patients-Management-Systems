package com.pm.patientservices.Mapper;

import com.pm.patientservices.Dto.PatientRequestDto;
import com.pm.patientservices.Dto.PatientResponseDto;
import com.pm.patientservices.Model.Patient;


import java.time.LocalDate;


public class PatientMapper {  /// this class is used to convert a patient type object into PatientResponseDto type object

      public static PatientResponseDto patientResponseDto(Patient p){
           PatientResponseDto patientResponseDto = new PatientResponseDto();
           patientResponseDto.setName(p.getName());
           patientResponseDto.setEmail(p.getEmail());
           patientResponseDto.setAddress(p.getAddress());
           patientResponseDto.setDateOfBirth(p.getDateofbirth());
           patientResponseDto.setPhoneNumber(p.getPhoneNumber());

           return patientResponseDto;
      }
      public static Patient totakepatientfromRequestDto(PatientRequestDto patientRequestDto){
          Patient patient = new Patient();
          patient.setName(patientRequestDto.getName());
          patient.setEmail(patientRequestDto.getEmail());
          patient.setAddress(patientRequestDto.getAddress());
          patient.setDateofbirth(LocalDate.parse(patientRequestDto.getBirthDate()));
          patient.setRegistredate(LocalDate.parse(patientRequestDto.getRegisterDate()));
          patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
          return patient;
      }
}
