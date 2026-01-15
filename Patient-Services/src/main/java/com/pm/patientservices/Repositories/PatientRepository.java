package com.pm.patientservices.Repositories;


import com.pm.patientservices.Model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

      ///Patient findByname(String name);
      boolean existsPatientById(UUID id);
      boolean existsByEmail(String email);
      boolean existsByEmailAndIdNot(String email, UUID id);




}
