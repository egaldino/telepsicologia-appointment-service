package br.puc.edson.telepsicologiaappointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@Data
public class Appointment {

    @Id
    private String id;
    private String name;
    private LocalDateTime date;
    private String psychologistId;
    private String patientId;
    private AppointmentStatus status;

    public enum AppointmentStatus {
        REQUESTED,
        SCHEDULED,
        DONE
    }
}
