package br.puc.edson.telepsicologiaappointmentservice.api;


import br.puc.edson.telepsicologiaappointmentservice.dto.RequestDto;
import br.puc.edson.telepsicologiaappointmentservice.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/psychologist")
@AllArgsConstructor
public class PsychologistAppointmentsController {

    private final AppointmentService service;

    @GetMapping("/requests/{psychologistId}")
    List<RequestDto> getPsychologistAppointmentsRequests(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsRequests(psychologistId);
    }

    @GetMapping("/scheduled/{psychologistId}")
    List<RequestDto> getPsychologistAppointmentsSchedulled(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsSchedulled(psychologistId);
    }

    @GetMapping("/pastAppointments/{psychologistId}")
    List<RequestDto> getPsychologistAppointmentsDone(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsDone(psychologistId);
    }

}
