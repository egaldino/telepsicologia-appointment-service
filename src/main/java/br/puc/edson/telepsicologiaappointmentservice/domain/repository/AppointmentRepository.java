package br.puc.edson.telepsicologiaappointmentservice.domain.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository  {

    List<Appointment> findByPatientIdAndStatus(String patientId, Appointment.AppointmentStatus status, String token);

    List<Appointment> findByPsychologistIdAndStatus(String psychologistId, Appointment.AppointmentStatus status, String token);

    Appointment save(Appointment appointment, String token);

    Optional<Appointment> findById(String appointmentId, String token);
}
