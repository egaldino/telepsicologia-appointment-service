package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.dto.PatientIntegrationDto;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration.mapper.IntegrationMapper;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
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
public class PatientRepositoryImpl implements PatientRepository {

    private final RestTemplate restTemplate;

    private final String patientApiUrl;

    public PatientRepositoryImpl(RestTemplate restTemplate, @Value("${api.patient.url}")  String patientApiUrl) {
        this.restTemplate = restTemplate;
        this.patientApiUrl = patientApiUrl;
    }

    @Override
    public Appointment.Patient findPatientById(String id, String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        return Optional
                .of(restTemplate.exchange(
                        patientApiUrl + "/patient/" + id, HttpMethod.GET, new HttpEntity<Object>(headers),
                        PatientIntegrationDto.class))
                .map(ResponseEntity::getBody)
                .map(IntegrationMapper.INSTANCE::dtoToModel)
                .orElse(null);
    }

}
