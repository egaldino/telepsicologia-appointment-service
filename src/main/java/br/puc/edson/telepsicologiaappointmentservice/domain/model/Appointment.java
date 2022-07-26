package br.puc.edson.telepsicologiaappointmentservice.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Appointment {

    private String id;
    private Patient patient;
    private Psychologist psychologist;
    private LocalDateTime date;
    private AppointmentStatus status;

    public enum AppointmentStatus {
        REQUESTED,
        SCHEDULED,
        DONE
    }

    @Data
    @Builder
    public static class Patient{
        private String id;
        private String name;
    }

    @Data
    @Builder
    public static class Psychologist{
        private String id;
        private String name;
    }

}
