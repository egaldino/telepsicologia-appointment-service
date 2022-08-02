package br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ReplyRequestDto {

    @NotNull
    private String appointmentId;

}
