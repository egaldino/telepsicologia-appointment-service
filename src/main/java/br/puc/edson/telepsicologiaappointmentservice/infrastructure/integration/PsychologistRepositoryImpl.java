package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.dto.PsychologistIntegrationDto;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.mapper.IntegrationMapper;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PsychologistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PsychologistRepositoryImpl implements PsychologistRepository {

    private final RestTemplate restTemplate;

    private final String psychologistApiUrl;

    public PsychologistRepositoryImpl(RestTemplate restTemplate, @Value("${api.psychologist.url}")  String psychologistApiUrl) {
        this.restTemplate = restTemplate;
        this.psychologistApiUrl = psychologistApiUrl;
    }

    @Override
    public Appointment.Psychologist findPsychologistById(String id, String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        return Optional
                .of(restTemplate.exchange(
                        psychologistApiUrl + "/psychologist/" + id, HttpMethod.GET, new HttpEntity<Object>(headers),
                        PsychologistIntegrationDto.class))
                .map(ResponseEntity::getBody)
                .map(IntegrationMapper.INSTANCE::dtoToModel)
                .orElse(null);
    }

}
