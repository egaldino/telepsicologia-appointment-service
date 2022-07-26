package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import org.springframework.stereotype.Service;

@Service
public class PsychologistRepositoryImpl implements PsychologistRepository {
    @Override
    public Appointment.Psychologist findPsychologistById(String id) {
        return Appointment.Psychologist.builder().id(id).build();
    }
}
