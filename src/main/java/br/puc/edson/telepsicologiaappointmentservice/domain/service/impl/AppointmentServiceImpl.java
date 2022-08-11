package br.puc.edson.telepsicologiaappointmentservice.domain.service.impl;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.repository.AppointmentRepository;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.AppointmentService;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.ReplyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Override
    public List<Appointment> getPsychologistAppointmentsRequests(String psychologistId, String token) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED, token);
    }

    @Override
    public List<Appointment> getPsychologistAppointmentsSchedulled(String psychologistId, String token) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.SCHEDULED, token);
    }

    @Override
    public List<Appointment> getPsychologistAppointmentsDone(String psychologistId, String token) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.DONE, token);
    }

    @Override
    public List<Appointment> getPatientAppointmentsRequests(String patientId, String token) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.REQUESTED, token);
    }

    @Override
    public List<Appointment> getPatientAppointmentsSchedulled(String patientId, String token) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.SCHEDULED, token);
    }

    @Override
    public List<Appointment> getPatientAppointmentsDone(String patientId, String token) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.DONE, token);
    }

    @Override
    public Appointment scheduleNewAppointment(Appointment appointment, String token) {
        return repository.save(appointment, token);
    }

    @Override
    public Appointment acceptRequest(ReplyRequestDto replyRequestDto, String token) {
        return changeAppointmentStatus(replyRequestDto, Appointment.AppointmentStatus.SCHEDULED, token);
    }

    @Override
    public Appointment denyRequest(ReplyRequestDto replyRequestDto, String token) {
        return changeAppointmentStatus(replyRequestDto, Appointment.AppointmentStatus.DENIED, token);
    }

    private List<Appointment> getPsychologistAppointmentsByStatus(String psychologistId, Appointment.AppointmentStatus requested, String token) {
        return repository.findByPsychologistIdAndStatus(psychologistId, requested, token);
    }

    private List<Appointment> getPatientAppointmentsByStatus(String patientId, Appointment.AppointmentStatus requested, String token) {
        return repository.findByPatientIdAndStatus(patientId, requested, token);
    }


    private Appointment changeAppointmentStatus(ReplyRequestDto replyRequestDto, Appointment.AppointmentStatus newStatus, String token) {
        return repository.findById(replyRequestDto.getAppointmentId(), token).map(appointment -> {
            appointment.setStatus(newStatus);
            return repository.save(appointment, token);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}
