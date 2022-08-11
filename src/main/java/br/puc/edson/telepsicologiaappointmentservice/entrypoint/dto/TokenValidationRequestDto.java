package br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenValidationRequestDto {

    private String token;
}
