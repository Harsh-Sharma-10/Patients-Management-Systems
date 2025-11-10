package com.pm.patientservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Patient Management System API",
                version = "1.0.0",
                description = "REST API documentation for managing patients, doctors, and records.",
                contact = @Contact(name = "Harsh Sharma", email = "harshsharmasharma943@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8089", description = "Local environment")
        }
)
public class PatientServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                PatientServicesApplication.class
                ,args);
    }
}
