package br.puc.edson.telepsicologiaappointmentservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestDto {

    private String id;
    private String name;
    private String date;
    private String hour;

}
