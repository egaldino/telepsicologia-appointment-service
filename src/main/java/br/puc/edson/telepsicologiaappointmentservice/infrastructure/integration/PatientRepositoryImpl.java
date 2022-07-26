package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRepositoryImpl implements PatientRepository {
    @Override
    public Appointment.Patient findPatientById(String id) {
        return Appointment.Patient.builder().id(id).build();
    }
}
