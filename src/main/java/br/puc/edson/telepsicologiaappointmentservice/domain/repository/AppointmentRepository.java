package br.puc.edson.telepsicologiaappointmentservice.domain.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;

import java.util.List;

public interface AppointmentRepository  {

    List<Appointment> findByPatientIdAndStatus(String patientId, Appointment.AppointmentStatus status);

    List<Appointment> findByPsychologistIdAndStatus(String psychologistId, Appointment.AppointmentStatus status);

    Appointment save(Appointment appointment);
}
