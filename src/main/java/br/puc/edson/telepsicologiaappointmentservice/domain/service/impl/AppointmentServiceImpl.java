package br.puc.edson.telepsicologiaappointmentservice.domain.service.impl;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.domain.repository.AppointmentRepository;
import br.puc.edson.telepsicologiaappointmentservice.domain.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Override
    public List<Appointment> getPsychologistAppointmentsRequests(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.REQUESTED);
    }

    @Override
    public List<Appointment> getPsychologistAppointmentsSchedulled(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.SCHEDULED);
    }

    @Override
    public List<Appointment> getPsychologistAppointmentsDone(String psychologistId) {
        return getPsychologistAppointmentsByStatus(psychologistId, Appointment.AppointmentStatus.DONE);
    }

    @Override
    public List<Appointment> getPatientAppointmentsRequests(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.REQUESTED);
    }

    @Override
    public List<Appointment> getPatientAppointmentsSchedulled(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.SCHEDULED);
    }

    @Override
    public List<Appointment> getPatientAppointmentsDone(String patientId) {
        return getPatientAppointmentsByStatus(patientId, Appointment.AppointmentStatus.DONE);
    }

    @Override
    public Appointment scheduleNewAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    private List<Appointment> getPsychologistAppointmentsByStatus(String psychologistId, Appointment.AppointmentStatus requested) {
        return repository.findByPsychologistIdAndStatus(psychologistId, requested);
    }

    private List<Appointment> getPatientAppointmentsByStatus(String patientId, Appointment.AppointmentStatus requested) {
        return repository.findByPatientIdAndStatus(patientId, requested);
    }
}
