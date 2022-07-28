package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PsychologistRepositoryImpl implements PsychologistRepository {

    private final RestTemplate restTemplate;

    private final String psychologistApiUrl;

    public PsychologistRepositoryImpl(RestTemplate restTemplate, @Value("${api.psychologist.url}")  String psychologistApiUrl) {
        this.restTemplate = restTemplate;
        this.psychologistApiUrl = psychologistApiUrl;
    }

    @Override
    public Appointment.Psychologist findPsychologistById(String id) {
        return restTemplate.getForEntity(psychologistApiUrl + "/psychologist/" + id, Appointment.Psychologist.class).getBody();
    }

}
