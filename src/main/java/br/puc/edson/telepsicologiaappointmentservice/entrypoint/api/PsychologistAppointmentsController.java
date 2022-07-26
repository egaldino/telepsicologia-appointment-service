package br.puc.edson.telepsicologiaappointmentservice.entrypoint.api;


import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.AppointmentService;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.AppointmentDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.mapper.AppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/psychologist")
@AllArgsConstructor
public class PsychologistAppointmentsController {

    private final AppointmentService service;

    @GetMapping("/requests/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsRequests(@PathVariable String psychologistId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.getPsychologistAppointmentsRequests(psychologistId, token)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/scheduled/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsSchedulled(@PathVariable String psychologistId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.getPsychologistAppointmentsSchedulled(psychologistId, token)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pastAppointments/{psychologistId}")
    List<AppointmentDto> getPsychologistAppointmentsDone(@PathVariable String psychologistId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.getPsychologistAppointmentsDone(psychologistId, token)
                .stream()
                .map(AppointmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping ("/accept")
    ResponseEntity<Appointment> acceptRequest(@RequestBody ReplyRequestDto replyRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return Optional.of(service.acceptRequest(replyRequestDto.getAppointmentId(), token))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/deny")
    ResponseEntity<Appointment> denyRequest(@RequestBody ReplyRequestDto replyRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return Optional.of(service.denyRequest(replyRequestDto.getAppointmentId(), token))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
