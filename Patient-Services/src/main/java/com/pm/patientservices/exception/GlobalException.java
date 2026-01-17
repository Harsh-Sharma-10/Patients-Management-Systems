package com.pm.patientservices.exception;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>   handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
       return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(EmailAlreadyExitsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExitsException(EmailAlreadyExitsException ex) {

        log.warn("Email Already Exists {} ", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", " email already exists ");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotexistsException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotexistsException(PatientNotexistsException ex) {
        log.warn("Patient No Exists {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "patient is Not exists ");
        return ResponseEntity.badRequest().body(errors);

    }


}
