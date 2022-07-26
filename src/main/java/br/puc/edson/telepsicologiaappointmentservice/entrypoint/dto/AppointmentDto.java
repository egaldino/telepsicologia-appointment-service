package br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AppointmentDto {

    private String id;
    private AppointmentDto.PatientDto patient;
    private AppointmentDto.PsychologistDto psychologist;
    private String date;
    private String hour;
    private AppointmentDto.AppointmentStatus status;

    public enum AppointmentStatus {
        REQUESTED,
        SCHEDULED,
        DONE
    }

    @Data
    @Builder
    public static class PatientDto{
        private String id;
        private String name;
    }

    @Data
    @Builder
    public static class PsychologistDto{
        private String id;
        private String name;
    }

}
