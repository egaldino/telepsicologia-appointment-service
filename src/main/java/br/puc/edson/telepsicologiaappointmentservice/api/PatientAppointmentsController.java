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
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientAppointmentsController {

    private final AppointmentService service;

    @GetMapping("/requests/{patientId}")
    List<RequestDto> getPatientAppointmentsRequests(@PathVariable String patientId) {
        return service.getPatientAppointmentsRequests(patientId);
    }

    @GetMapping("/scheduled/{patientId}")
    List<RequestDto> getPatientAppointmentsSchedulled(@PathVariable String patientId) {
        return service.getPatientAppointmentsSchedulled(patientId);
    }

    @GetMapping("/pastAppointments/{patientId}")
    List<RequestDto> getPatientAppointmentsDone(@PathVariable String patientId) {
        return service.getPatientAppointmentsDone(patientId);
    }

}
