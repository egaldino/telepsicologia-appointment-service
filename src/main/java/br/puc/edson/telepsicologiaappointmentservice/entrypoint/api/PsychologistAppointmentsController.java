package br.puc.edson.telepsicologiaappointmentservice.entrypoint.api;


import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.AppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.mapper.AppointmentMapper;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.impl.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/psychologist")
@AllArgsConstructor
public class PsychologistAppointmentsController {

    private final AppointmentServiceImpl service;

    @GetMapping("/requests/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsRequests(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsRequests(psychologistId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/scheduled/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsSchedulled(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsSchedulled(psychologistId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pastAppointments/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsDone(@PathVariable String psychologistId) {
        return service.getPsychologistAppointmentsDone(psychologistId)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/accept")
    ResponseEntity<Appointment> acceptRequest(@RequestBody ReplyRequestDto replyRequestDto) {
        return Optional.of(service.acceptRequest(replyRequestDto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/deny")
    ResponseEntity<Appointment> denyRequest(@RequestBody ReplyRequestDto replyRequestDto) {
        return Optional.of(service.denyRequest(replyRequestDto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
