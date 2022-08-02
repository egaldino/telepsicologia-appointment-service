package br.puc.edson.telepsicologiaappointmentservice.domain.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository  {

    List<Appointment> findByPatientIdAndStatus(String patientId, Appointment.AppointmentStatus status);

    List<Appointment> findByPsychologistIdAndStatus(String psychologistId, Appointment.AppointmentStatus status);

    Appointment save(Appointment appointment);

    Optional<Appointment> findById(String appointmentId);
}
