package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "appointment")
@AllArgsConstructor
@Data
public class AppointmentDatabaseModel {

    @Id
    private String id;
    private LocalDateTime date;
    private String psychologistId;
    private String patientId;
    private String status;

}
