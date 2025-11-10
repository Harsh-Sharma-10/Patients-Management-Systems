package com.pm.patientservices.Repositories;


import com.pm.patientservices.Model.DeletedPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeletedPatientRepository extends JpaRepository<DeletedPatient, UUID> {

}
