package com.pm.patientservices.exception;

import java.util.UUID;

public class PatientNotexistsException extends RuntimeException {
    public PatientNotexistsException(String message) {
        super(message);
    }
}
