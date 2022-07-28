package br.puc.edson.telepsicologiaappointmentservice.infrastructure.integration;

import br.puc.edson.telepsicologiaappointmentservice.domain.model.Appointment;
import br.puc.edson.telepsicologiaappointmentservice.infrastructure.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientRepositoryImpl implements PatientRepository {

    private final RestTemplate restTemplate;

    private final String patientApiUrl;

    public PatientRepositoryImpl(RestTemplate restTemplate, @Value("${api.patient.url}")  String patientApiUrl) {
        this.restTemplate = restTemplate;
        this.patientApiUrl = patientApiUrl;
    }

    @Override
    public Appointment.Patient findPatientById(String id) {
        return restTemplate.getForEntity(patientApiUrl + "/patient/" + id, Appointment.Patient.class).getBody();
    }
}
