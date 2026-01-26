package com.pm.patientservices.kafka;


import com.pm.patientservices.Model.PatientEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;


@Service
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(PatientEntity patientEntity) {

        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patientEntity.getId().toString())
                .setName(patientEntity.getName())
                .setEmail(patientEntity.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try {
            var future = kafkaTemplate.send(
                    "patient-topic",
                    event.getPatientId(),
                    event.toByteArray()
            );

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info(
                            "Kafka message sent successfully | topic=patient-topic | key={} | offset={}",
                            event.getPatientId(),
                            result.getRecordMetadata().offset()
                    );
                } else {
                    log.error(
                            "Kafka message send FAILED | topic=patient-topic | key={} | event={}",
                            event.getPatientId(),
                            event,
                            ex
                    );
                }
            });

        } catch (Exception ex) {
            log.error("Unexpected error while sending Kafka event {}", event, ex);
        }
    }

}
