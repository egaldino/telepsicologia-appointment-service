package br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;

public interface PatientRepository {

    Appointment.Patient findPatientById(String id);

}
