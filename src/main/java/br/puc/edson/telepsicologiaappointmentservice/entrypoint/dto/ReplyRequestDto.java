package br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDto {

    @NotBlank
    private String appointmentId;

}
