package br.puc.edson.telepsicologiaappointmentservice.service;

import br.puc.edson.telepsicologiaappointmentservice.dto.RequestDto;
import br.puc.edson.telepsicologiaappointmentservice.mapper.AppointmentMapper;
import br.puc.edson.telepsicologiaappointmentservice.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;

    public List<RequestDto> getPsychologistAppointmentsRequests(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED);
    }

    public List<RequestDto> getPsychologistAppointmentsSchedulled(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.SCHEDULED);
    }

    public List<RequestDto> getPsychologistAppointmentsDone(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.DONE);
    }

    public List<RequestDto> getPatientAppointmentsRequests(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.REQUESTED);
    }

    public List<RequestDto> getPatientAppointmentsSchedulled(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.SCHEDULED);
    }

    public List<RequestDto> getPatientAppointmentsDone(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.DONE);
    }

    private List<RequestDto> getPsychologistAppointmentsByStatus(String psychologistId, Appointment.AppointmentStatus requested) {
        return repository.findByPsychologistIdAndStatus(psychologistId, requested)
                .stream()
                .map(AppointmentMapper.INSTANCE::toRequestDto)
                .collect(Collectors.toList());
    }

    private List<RequestDto> getPatientAppointmentsByStatus(String patientId, Appointment.AppointmentStatus requested) {
        return repository.findByPatientIdAndStatus(patientId, requested)
                .stream()
                .map(AppointmentMapper.INSTANCE::toRequestDto)
                .collect(Collectors.toList());
    }
}
