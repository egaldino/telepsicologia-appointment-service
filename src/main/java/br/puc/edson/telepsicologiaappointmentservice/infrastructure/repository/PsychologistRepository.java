package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;

public interface PsychologistRepository {

    Appointment.Psychologist findPsychologistById(String id, String token);
}
