package br.puc.edson.telepsicologiaappointmentservice.entrypoint.api;


import br.puc.edson.telepsicologiaappointmentservice.domain.service.impl.AppointmentServiceImpl;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.AppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.NewAppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.mapper.AppointmentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
@Slf4j
public class PatientAppointmentsController {

    private final AppointmentServiceImpl service;

    @GetMapping("/requests/{patientId}")
    List<AppointmentDto> getPatientAppointmentsRequests(@PathVariable String patientId) {
        return service.getPatientAppointmentsRequests(patientId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/scheduled/{patientId}")
    List<AppointmentDto> getPatientAppointmentsSchedulled(@PathVariable String patientId) {
        return service.getPatientAppointmentsSchedulled(patientId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pastAppointments/{patientId}")
    List<AppointmentDto> getPatientAppointmentsDone(@PathVariable String patientId) {
        return service.getPatientAppointmentsDone(patientId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/scheduleNewAppointment")
    ResponseEntity<AppointmentDto> scheduleNewAppointment(@RequestBody NewAppointmentDto newAppointmentDto) {
        try {
            return ResponseEntity
                    .created(new URI("patient/requests/" + newAppointmentDto.getPatientId()))
                    .body(Optional.of(newAppointmentDto)
                            .map(AppointmentMapper.INSTANCE::newScheduletoAppointment)
                            .map(service::scheduleNewAppointment)
                            .map(AppointmentMapper.INSTANCE::toDto)
                            .orElseThrow(()-> new RuntimeException("Error saving new appointment")));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
