package br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class NewAppointmentDto {

    @NotNull
    private String patientId;

    @NotNull
    private String crp;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

}
